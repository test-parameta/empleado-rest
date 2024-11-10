package com.project.test.parameta.empleadorest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.test.parameta.commons.dto.AuthResponseDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.EmpleadoSeguridadDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import com.project.test.parameta.commons.service.impl.JwtService;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.service.impl.LoginEmpleadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.ERROR_CREDENCIALES;
import static com.project.test.parameta.empleadorest.utils.constants.Constantes.INICIO_EXITOSO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginEmpledoServiceTest {

    @InjectMocks private LoginEmpleadoService loginEmpleadoService;

    @Mock private AuthenticationManager authenticationManager;

    @Mock private JwtService jwtService;

    @Mock private EmpleadoRepository empleadoRepository;

    @Spy
    private EmpleadoMapper empleadoMapper;

    @Test
    void loginExitoso() {
        // Datos de prueba
        String correo = "test@example.com";
        String password = "password123";
        String token = "mocked-jwt-token";
        LoginEmpleadoDTO loginEmpleadoDTO = new LoginEmpleadoDTO();
        loginEmpleadoDTO.setCorreo(correo);
        loginEmpleadoDTO.setPassword(password);

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        UserDetails userDetails = new EmpleadoSeguridadDTO(empleadoDTO);

        // Crear un mock explícito para Authentication
        Authentication authenticationMock = mock(Authentication.class);

        // Configurar el comportamiento de los mocks
        when(authenticationMock.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);

        when(empleadoRepository.findByCorreoEmpleado(correo)).thenReturn(empleadoEntity);
        when(empleadoMapper.entityToDto(empleadoEntity)).thenReturn(empleadoDTO);
        when(jwtService.getToken(userDetails)).thenReturn(token);

        // Llamar al método
        RespuestaGeneralDTO respuesta = loginEmpleadoService.loginEmpleado(loginEmpleadoDTO);

        // Verificaciones
        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(INICIO_EXITOSO, respuesta.getMessage());
        assertNotNull(respuesta.getData());

        AuthResponseDTO authResponse = (AuthResponseDTO) respuesta.getData();
        assertEquals(token, authResponse.getToken());

        // Verificar interacciones
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(empleadoRepository, times(1)).findByCorreoEmpleado(correo);
        verify(jwtService, times(1)).getToken(userDetails);
    }

    @Test
    void loginFallido() {
        // Datos de prueba
        String correo = "test@example.com";
        String password = "password123";
        LoginEmpleadoDTO loginEmpleadoDTO = new LoginEmpleadoDTO();
        loginEmpleadoDTO.setCorreo(correo);
        loginEmpleadoDTO.setPassword(password);

        // Configurar mocks
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Error inesperado"));

        // Llamar al método
        RespuestaGeneralDTO respuesta = loginEmpleadoService.loginEmpleado(loginEmpleadoDTO);

        // Verificaciones
        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatus());
        assertEquals(ERROR_CREDENCIALES, respuesta.getMessage());
        assertNull(respuesta.getData());

        // Verificar interacciones
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(empleadoRepository, jwtService, empleadoMapper);
    }
}
