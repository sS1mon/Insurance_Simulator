package com.project.insurance.model.dto;

import com.project.insurance.model.entity.CarInsurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuredCarDetailsDto {
    private String brand;
    private String model;
    private Integer yearOfProduction;
    private Long clientId;
    private String clientName;
    private String clientSureName;

    public InsuredCarDetailsDto(CarInsurance car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.yearOfProduction = car.getProductionYear();
        this.clientId = car.getOwner().getId();
        this.clientName = car.getOwner().getSureName();
        this.clientSureName = car.getOwner().getSureName();
    }
}
