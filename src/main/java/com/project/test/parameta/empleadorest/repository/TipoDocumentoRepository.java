package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la gestión de entidades de tipo {@link TipoDocumentoEntity}.
 * <p>
 * Proporciona métodos de acceso a datos relacionados con los tipos de documento,
 * incluyendo una consulta personalizada para buscar un tipo de documento por su nombre.
 * </p>
 */
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Integer> {

    /**
     * Busca un tipo de documento en la base de datos por su nombre.
     * <p>
     * Utiliza una consulta personalizada para filtrar por el valor del
     * {@link TipoDocumentoEnum}.
     * </p>
     *
     * @param nombre el nombre del tipo de documento, representado por {@link TipoDocumentoEnum}.
     * @return un {@link Optional} que contiene la entidad {@link TipoDocumentoEntity} si se encuentra,
     *         o vacío si no existe un tipo de documento con el nombre especificado.
     */
    @Query("""
        SELECT td FROM TipoDocumentoEntity td where td.nombreTipoDocumento = :nombre
    """)
    Optional<TipoDocumentoEntity> buscarTipoDocumentoPorNombre(@Param("nombre") TipoDocumentoEnum nombre);

}
