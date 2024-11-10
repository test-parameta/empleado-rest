package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.REST;

@Repository(REST)
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {

    EmpleadoEntity findByCorreoEmpleado(String correoEmpleado);

    Boolean existsByNumeroDocumentoEmpleado(String numeroDocumentoEmpleado);

}

