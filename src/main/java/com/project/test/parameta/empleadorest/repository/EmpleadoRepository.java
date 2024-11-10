package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.REST;

/**
 * Repositorio JPA para la gestión de entidades de tipo {@link EmpleadoEntity}.
 * <p>
 * Proporciona métodos de acceso a datos relacionados con los empleados,
 * incluyendo búsqueda por correo electrónico y verificación de existencia
 * por número de documento.
 * </p>
 */
@Repository(REST)
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {

    /**
     * Busca un empleado en la base de datos por su correo electrónico.
     *
     * @param correoEmpleado el correo electrónico del empleado a buscar.
     * @return una entidad {@link EmpleadoEntity} que representa al empleado encontrado,
     *         o {@code null} si no existe un empleado con el correo especificado.
     */
    EmpleadoEntity findByCorreoEmpleado(String correoEmpleado);

    /**
     * Verifica si existe un empleado en la base de datos con el número de documento especificado.
     *
     * @param numeroDocumentoEmpleado el número de documento del empleado a verificar.
     * @return {@code true} si existe un empleado con el número de documento proporcionado,
     *         {@code false} en caso contrario.
     */
    Boolean existsByNumeroDocumentoEmpleado(String numeroDocumentoEmpleado);

}
