package br.stapassoli.msorder.dto;

import br.stapassoli.msorder.enums.OrderStatus;
import br.stapassoli.msorder.model.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {

    private Long id;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, locale = "pt-BR")
    private LocalDate orderDate;
    private BigDecimal value;
    private OrderStatus orderStatus;

    public Order toEntity() {
        return new Order(this.getId(),this.getOrderDate(), this.getValue(), this.orderStatus);
    }

}
