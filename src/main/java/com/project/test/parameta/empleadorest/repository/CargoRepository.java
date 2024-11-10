package com.project.test.parameta.empleadorest.repository;

import com.project.test.parameta.commons.entity.CargoEntity;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Integer> {

    Optional<CargoEntity> findByNombreCargo(CargoEnum nombre);

}
