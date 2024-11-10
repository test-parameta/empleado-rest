package com.project.test.parameta.empleadorest.utils.mapper;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadorest.dto.EmpleadoRequestDTO;
import com.project.test.parameta.empleadorest.dto.EmpleadoResponseDTO;
import org.mapstruct.Mapper;

import java.text.ParseException;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface EmpleadoRequestMapper {

    default EmpleadoDTO requestToDto(EmpleadoRequestDTO requestDTO) throws ParseException {
        return EmpleadoDTO.builder()
                .nombreEmpleado(requestDTO.getNombres())
                .apellidosEmpleado(requestDTO.getApellidos())
                .tipoDocumentoFk(TipoDocumentoDTO.builder().nombreTipoDocumento(TipoDocumentoEnum.valueOf(requestDTO.getTipoDocumento())).build())
                .numeroDocumentoEmpleado(requestDTO.getNumeroDocumento())
                .fechaNacimientoEmpleado(Utilidades.checkType(requestDTO.getFechaNacimiento(), Date.class).orElse(null))
                .fechaVinculacionCompaniaEmpleado(Utilidades.checkType(requestDTO.getFechaVinculacionComania(), Date.class).orElse(null))
                .cargoFk(CargoDTO.builder().nombreCargo(CargoEnum.valueOf(requestDTO.getCargo())).build())
                .salarioEmpleado(requestDTO.getSalario())
                .build();
    }

    default EmpleadoResponseDTO dtoToResponse(EmpleadoDTO requestDTO) {
        return EmpleadoResponseDTO.builder()
                .nombres(requestDTO.getNombreEmpleado())
                .apellidos(requestDTO.getApellidosEmpleado())
                .tipoDocumento(
                        requestDTO.getTipoDocumentoFk().getNombreTipoDocumento().name() + "-" +
                        requestDTO.getTipoDocumentoFk().getNombreTipoDocumento().getDescripcion())
                .numeroDocumento(requestDTO.getNumeroDocumentoEmpleado())
                .fechaNacimiento(Utilidades.dateToString(requestDTO.getFechaNacimientoEmpleado()))
                .fechaVinculacionComania(Utilidades.dateToString(requestDTO.getFechaVinculacionCompaniaEmpleado()))
                .cargo(requestDTO.getCargoFk().getNombreCargo().name() + "-" + requestDTO.getCargoFk().getNombreCargo().getDescripcion())
                .correo(requestDTO.getCorreoEmpleado())
                .password(requestDTO.getHashPassword())
                .salario(requestDTO.getSalarioEmpleado()).build();
    }


}
