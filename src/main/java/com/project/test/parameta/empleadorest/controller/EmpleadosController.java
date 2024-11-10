package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.service.IConsultarEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las operaciones relacionadas con empleados.
 * <p>
 * Este controlador proporciona un endpoint para consultar la información
 * de los empleados disponibles en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    /**
     * Servicio encargado de la lógica para consultar empleados.
     */
    private final IConsultarEmpleadoService iConsultarEmpleadoService;

    /**
     * Endpoint para consultar la información de todos los empleados.
     * <p>
     * Este endpoint realiza una consulta de los empleados registrados en el sistema
     * y devuelve la información correspondiente en una respuesta general.
     * </p>
     *
     * @return {@link ResponseEntity} con el resultado de la operación.
     *         Contiene un objeto {@link RespuestaGeneralDTO} con el estado,
     *         mensaje y datos de la respuesta.
     */
    @GetMapping("/consulta")
    public ResponseEntity<RespuestaGeneralDTO> consultarEmpledos()  {
        RespuestaGeneralDTO respuestaGeneralDTO = iConsultarEmpleadoService.consultarEmpleados();
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

}
