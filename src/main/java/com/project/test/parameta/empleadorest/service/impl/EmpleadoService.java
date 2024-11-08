package com.project.test.parameta.empleadorest.service.impl;


import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.RespuestaGeneralDTO;
import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.empleadorest.service.IEmpleadoService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class EmpleadoService implements IEmpleadoService {


    @Override
    public RespuestaGeneralDTO validacionesEmpleado(EmpleadoDTO empleadoDTO) {

        if(empleadoDTO.getNombreEmpleado()== null){

        }
        if(empleadoDTO.getApellidosEmpleado()== null){

        }
        if(empleadoDTO.getNumeroDocumentoEmpleado()== null){

        }

        if(empleadoDTO.getFechaNacimientoEmpleado()== null){

        }

        if(empleadoDTO.getFechaVinculacionEmpleado()== null){

        }
        if(empleadoDTO.getCargoFk()== null){

        }

        if(empleadoDTO.getSalarioEmpleado()== null){

        }

        if(empleadoDTO.getTipoDocumentoFk()== null){

        }
        return null;
    }

    private long calcularEdad(Date fecha){
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos
        long diferenciaMilisegundos = fechaActual.getTime() - fecha.getTime();

        // Convertir milisegundos a años (1 año = 365.25 días promedio)
        long milisegundosPorAnio = (long) (365.25 * 24 * 60 * 60 * 1000);
        return diferenciaMilisegundos / milisegundosPorAnio;
    }
}
