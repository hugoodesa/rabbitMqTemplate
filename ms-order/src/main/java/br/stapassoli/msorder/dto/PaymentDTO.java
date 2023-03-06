package br.stapassoli.msorder.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class PaymentDTO {
    private Long id;
    private String clientName;
    private BigDecimal value;
}
