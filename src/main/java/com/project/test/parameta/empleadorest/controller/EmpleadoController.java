package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.empleadorest.dto.EmpleadoRequestDTO;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.service.IGuardarEmpleadoService;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import com.project.test.parameta.empleadorest.utils.mapper.EmpleadoRequestMapper;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Controlador REST para gestionar operaciones relacionadas con empleados.
 * <p>
 * Este controlador expone endpoints para el inicio de sesión y el guardado
 * de información de empleados en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    /**
     * Servicio para la lógica de inicio de sesión de empleados.
     */
    private final ILoginEmpleadoService iLoginEmpleadoService;

    /**
     * Servicio para la lógica de validación y guardado de empleados.
     */
    private final IGuardarEmpleadoService empleadoService;

    /**
     * Mapper para transformar las solicitudes de empleado en objetos DTO.
     */
    private final EmpleadoRequestMapper empleadoRequestMapper;

    /**
     * Endpoint para realizar el inicio de sesión de un empleado.
     * <p>
     * Este endpoint valida las credenciales del empleado y devuelve
     * un token de autenticación en caso de éxito.
     * </p>
     *
     * @param loginEmpleadoDTO Objeto con las credenciales del empleado
     *                         ({@link LoginEmpleadoDTO}).
     * @return {@link ResponseEntity} con el resultado de la operación.
     *         Contiene un objeto {@link RespuestaGeneralDTO} con el estado,
     *         mensaje y datos de la respuesta.
     */
    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDTO> loginEmpleado(@RequestBody LoginEmpleadoDTO loginEmpleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = iLoginEmpleadoService.loginEmpleado(loginEmpleadoDTO);
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

    /**
     * Endpoint para guardar la información de un empleado.
     * <p>
     * Este endpoint recibe los datos de un empleado, realiza validaciones
     * y guarda la información en el sistema.
     * </p>
     *
     * @param empleadoRequestDTO Objeto con los datos del empleado
     *                           ({@link EmpleadoRequestDTO}).
     * @return {@link ResponseEntity} con el resultado de la operación.
     *         Contiene un objeto {@link RespuestaGeneralDTO} con el estado,
     *         mensaje y datos de la respuesta.
     * @throws ParseException Si ocurre un error al procesar las fechas del empleado.
     * @throws JAXBException  Si ocurre un error al procesar datos XML durante la operación.
     */
    @GetMapping("/guardar")
    public ResponseEntity<RespuestaGeneralDTO> guardarEmpleado(
            @Valid EmpleadoRequestDTO empleadoRequestDTO
    ) throws ParseException, JAXBException {
        RespuestaGeneralDTO respuestaGeneralDTO = empleadoService.validacionesEmpleado(empleadoRequestMapper.requestToDto(empleadoRequestDTO));
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }
}
