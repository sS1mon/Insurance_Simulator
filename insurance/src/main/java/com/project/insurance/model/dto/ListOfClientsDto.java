package com.project.insurance.model.dto;

import com.project.insurance.model.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfClientsDto {
    List<Client> clients;
}
