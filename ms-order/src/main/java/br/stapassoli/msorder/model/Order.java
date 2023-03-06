package br.stapassoli.msorder.model;

import br.stapassoli.msorder.dto.OrderDTO;
import br.stapassoli.msorder.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy" , shape = JsonFormat.Shape.STRING,locale = "pt-BR")
    private LocalDate orderDate;
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

//    public Order(LocalDate orderDate, BigDecimal value, OrderStatus orderStatus) {
//        this.orderDate = orderDate;
//        this.value = value;
//        this.orderStatus = orderStatus;
//    }

    public OrderDTO toDTO(){
        return OrderDTO
                .builder()
                .id(this.id)
                .value(this.value)
                .orderStatus(this.orderStatus)
                .orderDate(this.orderDate)
                .build();
    }

    public void update(OrderDTO orderDTO) {
        this.value = orderDTO.getValue();
        this.orderDate = orderDTO.getOrderDate();
        this.orderStatus = orderDTO.getOrderStatus();
    }

    public void realizePayment() {
        this.orderStatus = OrderStatus.PAY;
    }
}
