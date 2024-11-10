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

/**
 * Mapper para transformar objetos relacionados con empleados.
 * <p>
 * Esta interfaz proporciona métodos para convertir entre DTOs de solicitud,
 * DTOs internos y DTOs de respuesta de empleados, integrando validaciones y
 * formateo de datos como fechas y enumeraciones.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface EmpleadoRequestMapper {

    /**
     * Convierte un objeto {@link EmpleadoRequestDTO} en un {@link EmpleadoDTO}.
     * <p>
     * Realiza las transformaciones necesarias, incluyendo:
     * <ul>
     *   <li>Conversión de fechas a objetos {@link Date} mediante utilidades.</li>
     *   <li>Transformación de enumeraciones para tipos de documento y cargos.</li>
     * </ul>
     * </p>
     *
     * @param requestDTO el objeto de solicitud {@link EmpleadoRequestDTO}.
     * @return un objeto {@link EmpleadoDTO} con los datos transformados.
     * @throws ParseException si ocurre un error al convertir fechas.
     */
    default EmpleadoDTO requestToDto(EmpleadoRequestDTO requestDTO) throws ParseException {
        TipoDocumentoEnum tipoDocumentoEnum;
        CargoEnum cargoEnum;
        if(TipoDocumentoEnum.esDescripcion(requestDTO.getTipoDocumento())){
            tipoDocumentoEnum = TipoDocumentoEnum.valueOf(TipoDocumentoEnum.obtenerNombrePorDescripcion(requestDTO.getTipoDocumento()));
        }else{
            tipoDocumentoEnum = TipoDocumentoEnum.valueOf(requestDTO.getTipoDocumento());
        }
        if(CargoEnum.esDescripcion(requestDTO.getCargo())){
            cargoEnum = CargoEnum.valueOf(CargoEnum.obtenerNombrePorDescripcion(requestDTO.getCargo()));
        }else{
            cargoEnum = CargoEnum.valueOf(requestDTO.getCargo());
        }
        return EmpleadoDTO.builder()
                .nombreEmpleado(requestDTO.getNombres())
                .apellidosEmpleado(requestDTO.getApellidos())
                .tipoDocumentoFk(TipoDocumentoDTO.builder()
                        .nombreTipoDocumento(tipoDocumentoEnum)
                        .build())
                .numeroDocumentoEmpleado(requestDTO.getNumeroDocumento())
                .fechaNacimientoEmpleado(Utilidades.checkType(requestDTO.getFechaNacimiento(), Date.class).orElse(null))
                .fechaVinculacionCompaniaEmpleado(Utilidades.checkType(requestDTO.getFechaVinculacionComania(), Date.class).orElse(null))
                .cargoFk(CargoDTO.builder()
                        .nombreCargo(cargoEnum)
                        .build())
                .salarioEmpleado(requestDTO.getSalario())
                .build();
    }

    /**
     * Convierte un objeto {@link EmpleadoDTO} en un {@link EmpleadoResponseDTO}.
     * <p>
     * Realiza las transformaciones necesarias, incluyendo:
     * <ul>
     *   <li>Formateo de fechas a cadenas de texto.</li>
     *   <li>Concatenación de nombres y descripciones para enumeraciones de tipo de documento y cargo.</li>
     * </ul>
     * </p>
     *
     * @param requestDTO el objeto {@link EmpleadoDTO} con los datos del empleado.
     * @return un objeto {@link EmpleadoResponseDTO} con los datos preparados para la respuesta.
     */
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
                .cargo(
                        requestDTO.getCargoFk().getNombreCargo().name() + "-" +
                                requestDTO.getCargoFk().getNombreCargo().getDescripcion())
                .correo(requestDTO.getCorreoEmpleado())
                .password(requestDTO.getHashPassword())
                .salario(requestDTO.getSalarioEmpleado())
                .build();
    }
}
