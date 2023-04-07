package com.project.insurance.model.entity;

import com.project.insurance.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column (name = "type_of_vehicle")
    private Type type;
    @Column (name = "vin", nullable = false)
    private String vin;

    @Column (name = "owner", nullable = false)

    private Long owner;


}
