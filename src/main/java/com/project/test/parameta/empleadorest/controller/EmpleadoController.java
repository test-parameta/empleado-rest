package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.dto.EmpleadoRequestDTO;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import com.project.test.parameta.empleadorest.service.impl.GuardarEmpleadoService;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoRequestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final ILoginEmpleadoService iLoginEmpleadoService;

    private final GuardarEmpleadoService empleadoService;

    private final EmpleadoRequestMapper empleadoRequestMapper;

    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDTO> loginEmpleado(@RequestBody LoginEmpleadoDTO loginEmpleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = iLoginEmpleadoService.loginEmpleado(loginEmpleadoDTO);
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

    @GetMapping("/guardar")
    public ResponseEntity<RespuestaGeneralDTO> guardarEmpleado(
            @Valid EmpleadoRequestDTO empleadoRequestDTO
    ) throws ParseException {
        RespuestaGeneralDTO respuestaGeneralDTO = empleadoService.validacionesEmpleado(empleadoRequestMapper.requestToDto(empleadoRequestDTO));
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }
}


