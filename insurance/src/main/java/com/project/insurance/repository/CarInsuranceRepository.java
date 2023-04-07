package com.project.insurance.repository;

import com.project.insurance.model.entity.CarInsurance;
import com.project.insurance.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarInsuranceRepository extends JpaRepository<CarInsurance, Long> {
    List<CarInsurance> findByProductionYear(Integer year);

    List<CarInsurance> findByPower(Integer power);

    List<CarInsurance> findByOwner(Client owner);

    CarInsurance findByVin(String vin);
}
