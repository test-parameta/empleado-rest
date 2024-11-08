package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoReplicaRepository extends JpaRepository<EmpleadoEntity, String> {

    EmpleadoEntity findByNumeroDocumentoEmpleado(String numeroDocumentoEmpleado);

}

