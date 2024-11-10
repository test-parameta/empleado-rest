package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.CargoEntity;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la gestión de entidades de tipo {@link CargoEntity}.
 * <p>
 * Proporciona métodos de acceso a datos relacionados con los cargos,
 * incluyendo una operación personalizada para buscar un cargo por su nombre.
 * </p>
 */
@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Integer> {

    /**
     * Busca un cargo en la base de datos por su nombre.
     *
     * @param nombre el nombre del cargo a buscar, representado por {@link CargoEnum}.
     * @return un {@link Optional} que contiene la entidad {@link CargoEntity} si se encuentra,
     *         o vacío si no existe un cargo con el nombre especificado.
     */
    Optional<CargoEntity> findByNombreCargo(CargoEnum nombre);

}
