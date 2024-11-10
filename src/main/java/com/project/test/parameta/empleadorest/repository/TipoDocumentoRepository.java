package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Integer> {

    @Query("""
        SELECT td FROM TipoDocumentoEntity td where td.nombreTipoDocumento = :nombre
    """)
    Optional<TipoDocumentoEntity> buscarTipoDocumentoPorNombre(@Param("nombre") TipoDocumentoEnum nombre);

}
