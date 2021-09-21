package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageError {
    HttpStatus status;
    List<String> message;

}
