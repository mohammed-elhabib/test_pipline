package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class FacteurDto {
    private  String numberFacteur;
    private Date dateFacteur;
    private Date dateEch;
}
