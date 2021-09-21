package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WilayaDto {
   private int  idWilaya;
   private String wilayaName;

}
