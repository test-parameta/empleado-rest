package com.project.test.parameta.empleadorest.utils.mapper;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadorest.soap.CargoElement;
import com.project.test.parameta.empleadorest.soap.EmpleadoElement;
import com.project.test.parameta.empleadorest.soap.TipoDocumentoElement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoElementMapper {

    default EmpleadoElement dtoToElement(EmpleadoDTO dto) {
        return EmpleadoElement.builder()
                .codigoEmpleado(dto.getCodigoEmpleado())
                .nombreEmpleado(dto.getNombreEmpleado())
                .apellidosEmpleado(dto.getApellidosEmpleado())
                .fechaNacimientoEmpleado(Utilidades.convertToXMLGregorianCalendar(dto.getFechaNacimientoEmpleado()))
                .fechaVinculacionEmpleado(Utilidades.convertToXMLGregorianCalendar(dto.getFechaVinculacionCompaniaEmpleado()))
                .cargoFk(CargoElement.builder().idCargo(dto.getCargoFk().getIdCargo()).nombreCargo(dto.getCargoFk().getNombreCargo().name()).build())
                .tipoDocumentoFk(TipoDocumentoElement.builder().idTipoDocumento(dto.getTipoDocumentoFk().getIdTipoDocumento()).nombreTipoDocumento(dto.getTipoDocumentoFk().getNombreTipoDocumento().name()).build())
                .correoEmpleado(dto.getCorreoEmpleado())
                .salarioEmpleado(dto.getSalarioEmpleado())
                .numeroDocumentoEmpleado(dto.getNumeroDocumentoEmpleado())
                .hashPassword(dto.getHashPassword())
                .build();
    }

}
