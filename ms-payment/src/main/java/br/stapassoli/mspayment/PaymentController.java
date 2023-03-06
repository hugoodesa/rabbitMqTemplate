package br.stapassoli.mspayment;

import br.stapassoli.mspayment.model.Payment;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

//    @Autowired
//    OrderClient client;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity<String> payment(){
        return ResponseEntity.ok("Hellow World");
    }

    @PutMapping("/{id}")
    public ResponseEntity realizePayment(@PathVariable Long id){
        //client.realizePayment(id);

        rabbitTemplate.convertAndSend("payment.ex","",new Payment(id,"Hugo",new BigDecimal("120.0")));

        return ResponseEntity.ok("Pay Sucefully ");
    }

}
