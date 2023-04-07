package com.project.insurance.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class InsureCarDto {

    @NotBlank(message = "Please fill out brand of your car!")
    private String brand;
    @NotBlank(message = "Please fill out model of your car!")
    private String model;
    @NotBlank(message = "Please fill out year of production!")
    private Integer productionYear;
    @NotBlank(message = "Please fill out power of the car")
    private Integer power;
    @NotBlank(message = "Please choose if was car modified")
    private Boolean modified;
    private String Type;
    @NotBlank(message = "Please fill out \"VIN\" of the car")
    private String vin;

}
