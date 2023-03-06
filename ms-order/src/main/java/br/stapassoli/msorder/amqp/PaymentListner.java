package br.stapassoli.msorder.amqp;

import br.stapassoli.msorder.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListner {

    @RabbitListener(queues = "order-queue")
    public void getMessages(PaymentDTO paymentDTO){
        System.out.println("============");
        System.out.println(paymentDTO);
        System.out.println("============");
    }

}
