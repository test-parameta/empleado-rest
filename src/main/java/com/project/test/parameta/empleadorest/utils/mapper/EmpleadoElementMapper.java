package com.project.test.parameta.empleadorest.utils.mapper;

import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadorest.soap.CargoElement;
import com.project.test.parameta.empleadorest.soap.EmpleadoElement;
import com.project.test.parameta.empleadorest.soap.TipoDocumentoElement;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir objetos de tipo {@link EmpleadoDTO} a {@link EmpleadoElement}.
 * <p>
 * Este mapper se utiliza para transformar datos del modelo DTO a elementos SOAP,
 * integrando utilidades para el manejo de fechas y estructuras relacionadas.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface EmpleadoElementMapper {

    /**
     * Convierte un objeto {@link EmpleadoDTO} en un {@link EmpleadoElement}.
     * <p>
     * El mapeo incluye la transformación de fechas al formato {@code XMLGregorianCalendar}
     * y la conversión de objetos relacionados como {@link CargoElement} y
     * {@link TipoDocumentoElement}.
     * </p>
     *
     * @param dto el objeto {@link EmpleadoDTO} que contiene los datos del empleado.
     * @return un objeto {@link EmpleadoElement} listo para ser utilizado en servicios SOAP.
     */
    default EmpleadoElement dtoToElement(EmpleadoDTO dto) {
        return EmpleadoElement.builder()
                .codigoEmpleado(dto.getCodigoEmpleado())
                .nombreEmpleado(dto.getNombreEmpleado())
                .apellidosEmpleado(dto.getApellidosEmpleado())
                .fechaNacimientoEmpleado(Utilidades.convertToXMLGregorianCalendar(dto.getFechaNacimientoEmpleado()))
                .fechaVinculacionEmpleado(Utilidades.convertToXMLGregorianCalendar(dto.getFechaVinculacionCompaniaEmpleado()))
                .cargoFk(CargoElement.builder()
                        .idCargo(dto.getCargoFk().getIdCargo())
                        .nombreCargo(dto.getCargoFk().getNombreCargo().name())
                        .build())
                .tipoDocumentoFk(TipoDocumentoElement.builder()
                        .idTipoDocumento(dto.getTipoDocumentoFk().getIdTipoDocumento())
                        .nombreTipoDocumento(dto.getTipoDocumentoFk().getNombreTipoDocumento().name())
                        .build())
                .correoEmpleado(dto.getCorreoEmpleado())
                .salarioEmpleado(dto.getSalarioEmpleado())
                .numeroDocumentoEmpleado(dto.getNumeroDocumentoEmpleado())
                .hashPassword(dto.getHashPassword())
                .build();
    }
}
