package com.project.test.parameta.empleadorest.controller;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadorest.dto.LoginEmpleadoDTO;
import com.project.test.parameta.empleadorest.service.ILoginEmpleadoService;
import com.project.test.parameta.empleadorest.service.impl.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;


@RestController
@RequestMapping("/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final ILoginEmpleadoService iLoginEmpleadoService;

    private final EmpleadoService empleadoService;

    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDTO> loginEmpleado(@RequestBody LoginEmpleadoDTO loginEmpleadoDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = iLoginEmpleadoService.loginEmpleado(loginEmpleadoDTO);
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

    @GetMapping("/empleados")
    public ResponseEntity<RespuestaGeneralDTO> buscarEmpleado(@RequestParam String nombres,
                                                              @RequestParam String apellidos,
                                                              @RequestParam String numeroDocumento,
                                                              @RequestParam String fechaNacimiento,
                                                              @RequestParam String fechaVinculacion,
                                                              @RequestParam String nombreCargo,
                                                              @RequestParam Double salario,
                                                              @RequestParam String nombreTipoDocumento
    ) throws ParseException {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder().nombreEmpleado(Utilidades.checkType(nombres, String.class).orElse(null)).apellidosEmpleado(Utilidades.checkType(apellidos, String.class).orElse(null))
                .numeroDocumentoEmpleado(Utilidades.checkType(numeroDocumento, String.class).orElse(null)).fechaNacimientoEmpleado(Utilidades.validarFormatoFecha(fechaNacimiento))
                .fechaVinculacionEmpleado(Utilidades.validarFormatoFecha(fechaVinculacion)).cargoFk(CargoDTO.builder().nombreCargo(Utilidades.checkType(nombreCargo, String.class).orElse(null)).build()).salarioEmpleado(Utilidades.checkType(salario, Double.class).orElse(null))
                .tipoDocumentoFk(TipoDocumentoDTO.builder().nombreTipoDocumento(Utilidades.checkType(nombreTipoDocumento, String.class).orElse(null)).build()).build();
        return ResponseEntity.ok(null);
    }
}


