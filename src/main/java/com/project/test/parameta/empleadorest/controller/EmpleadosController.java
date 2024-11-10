package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.service.IConsultarEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    private final IConsultarEmpleadoService iConsultarEmpleadoService;

    @GetMapping("/consulta")
    public ResponseEntity<RespuestaGeneralDTO> guardarEmpleado()  {
        RespuestaGeneralDTO respuestaGeneralDTO = iConsultarEmpleadoService.consultarEmpleados();
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

}
