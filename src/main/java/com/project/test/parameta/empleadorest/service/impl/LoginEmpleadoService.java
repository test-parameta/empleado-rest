package com.project.test.parameta.empleadorest.service.impl;

import com.project.test.parameta.commons.dto.AuthResponseDTO;
import com.project.test.parameta.commons.dto.EmpleadoSeguridadDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import com.project.test.parameta.commons.service.impl.JwtService;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.repository.EmpleadoReplicaRepository;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginEmpleadoService implements ILoginEmpleadoService {

    private final EmpleadoReplicaRepository empleadoReplicaRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final EmpleadoMapper empleadoMapper;

    @Override
    public RespuestaGeneralDTO loginEmpleado(LoginEmpleadoDTO loginEmpleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDto = new RespuestaGeneralDTO();

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginEmpleadoDTO.getNumeroDocumentoEmpleado(), loginEmpleadoDTO.getPassword()));
            log.error(authentication.isAuthenticated());
            EmpleadoEntity empleado = empleadoReplicaRepository.findByNumeroDocumentoEmpleado(loginEmpleadoDTO.getNumeroDocumentoEmpleado());
            UserDetails user = new EmpleadoSeguridadDTO(empleadoMapper.entityToDto(empleado));
            String token = jwtService.getToken(user);
            respuestaGeneralDto.setData(AuthResponseDTO.builder().token(token).build());
            respuestaGeneralDto.setMessage("Se inicio correctamente");
            respuestaGeneralDto.setStatus(HttpStatus.OK);
        }catch (Exception e){
            log.error("Error ", e);
            respuestaGeneralDto.setMessage("Hubo un problema en las credenciales");
            respuestaGeneralDto.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return respuestaGeneralDto;
    }
}
