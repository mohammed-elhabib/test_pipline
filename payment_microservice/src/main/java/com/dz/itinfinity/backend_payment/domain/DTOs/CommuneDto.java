package com.dz.itinfinity.backend_payment.domain.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommuneDto {
  private int   idCommune;
    private String communeName;
    private int idWilaya;

}
