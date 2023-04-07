package com.project.insurance.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.insurance.enums.Type;
import com.project.insurance.model.dto.InsureCarDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class CarInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "car_brand", nullable = false)
    private String brand;
    @Column(name = "car_model", nullable = false)
    private String model;
    @Column(name = "production_year", nullable = false)
    private Integer productionYear;
    @Column (name = "power_in_kw", nullable = false)
    private Integer power;
    @Column (name = "was_modified", nullable = false)
    private Boolean modified;
    @Enumerated(EnumType.STRING)
    @Column (name = "type_of_vehicle")
    private Type type;
    @Column (name = "vin", nullable = false)
    private String vin;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private Client owner;

    public CarInsurance(String brand, String model, Integer productionYear, Integer power, Boolean modified, Type type, String vin, Client owner) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.power = power;
        this.modified = modified;
        this.type = type;
        this.vin = vin;
    }

    public CarInsurance(InsureCarDto car, Client owner){
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.productionYear = car.getProductionYear();
        this.power = car.getPower();
        this.modified = car.getModified();
        this.type = car.getType();
        this.vin = car.getVin();
        this.owner = owner;
    }
}
