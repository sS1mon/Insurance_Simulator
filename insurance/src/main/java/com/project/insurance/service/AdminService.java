package com.project.insurance.service;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.model.dto.InsuredCarListDto;
import com.project.insurance.model.dto.ListOfClientsDto;

public interface AdminService {
    public ListOfClientsDto seeAllClients(AdminDto admin);
    public InsuredCarListDto seeAllInsuredCars(AdminDto admin);
}
