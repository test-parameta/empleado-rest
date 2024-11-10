package com.project.test.parameta.empleadorest.service.impl;

import com.project.test.parameta.commons.dto.AuthResponseDTO;
import com.project.test.parameta.commons.dto.EmpleadoSeguridadDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import com.project.test.parameta.commons.service.impl.JwtService;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.repository.EmpleadoRepository;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.*;

/**
 * Servicio para gestionar el inicio de sesión de empleados.
 * <p>
 * Esta clase implementa la interfaz {@link ILoginEmpleadoService} y se encarga
 * de realizar la autenticación del empleado, generar un token JWT y devolver
 * la información relacionada con el inicio de sesión.
 * </p>
 */
@Service
@Log4j2
public class LoginEmpleadoService implements ILoginEmpleadoService {

    /**
     * Repositorio para gestionar los datos de empleados.
     */
    private final EmpleadoRepository empleadoRepository;

    /**
     * Manejador de autenticación para validar las credenciales del empleado.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * Servicio para generar tokens JWT.
     */
    private final JwtService jwtService;

    /**
     * Mapper para transformar entidades de empleado a DTOs.
     */
    private final EmpleadoMapper empleadoMapper;

    /**
     * Constructor para inyectar las dependencias necesarias.
     *
     * @param empleadoRepository      el repositorio de empleados, identificado con {@link Qualifier}.
     * @param authenticationManager   el manejador de autenticación para validar credenciales.
     * @param jwtService              el servicio para generar tokens JWT.
     * @param empleadoMapper          el mapper para transformar entidades de empleados a DTOs.
     */
    public LoginEmpleadoService(
            @Qualifier(REST) EmpleadoRepository empleadoRepository,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public RespuestaGeneralDTO loginEmpleado(LoginEmpleadoDTO loginEmpleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDto = new RespuestaGeneralDTO();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginEmpleadoDTO.getCorreo(), loginEmpleadoDTO.getPassword()));
            if(authentication.isAuthenticated()){
                EmpleadoEntity empleado = empleadoRepository.findByCorreoEmpleado(loginEmpleadoDTO.getCorreo());
                UserDetails user = new EmpleadoSeguridadDTO(empleadoMapper.entityToDto(empleado));
                String token = jwtService.getToken(user);
                respuestaGeneralDto.setData(AuthResponseDTO.builder().token(token).build());
                respuestaGeneralDto.setMessage(INICIO_EXITOSO);
                respuestaGeneralDto.setStatus(HttpStatus.OK);
            }else{
                respuestaGeneralDto.setMessage(ERROR_CREDENCIALES);
                respuestaGeneralDto.setStatus(HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            log.error(ERROR_AUTENTICACION, e.getMessage());
            respuestaGeneralDto.setMessage(ERROR_CREDENCIALES);
            respuestaGeneralDto.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return respuestaGeneralDto;
    }
}
