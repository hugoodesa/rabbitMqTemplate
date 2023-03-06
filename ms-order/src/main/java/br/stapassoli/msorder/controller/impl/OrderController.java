package br.stapassoli.msorder.controller.impl;

import br.stapassoli.msorder.controller.BasicController;
import br.stapassoli.msorder.dto.OrderDTO;
import br.stapassoli.msorder.enums.OrderStatus;
import br.stapassoli.msorder.service.imp.OrderService;
/*import jakarta.ws.rs.Path;*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/order")
public class OrderController extends BasicController<OrderService, OrderDTO> {

    protected OrderController(OrderService orderService) {
        super(orderService);
    }

    @Override
    @PostMapping
    public ResponseEntity<OrderDTO> post(@RequestBody OrderDTO orderDTO) {
        return this.service.post(orderDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> put(@PathVariable Long id,@RequestBody OrderDTO orderDTO) {
        return this.service.put(id, orderDTO);
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        return this.service.select(id);
    }

    @Override
    @GetMapping
    public Page<OrderDTO> getAll(@PageableDefault(size = 10,sort = {"value"},page = 0) Pageable pageable) {
        return this.service.selectAll(pageable);
    }


    @PutMapping("/pay/{id}")
    public ResponseEntity realizePayment(@PathVariable Long id){
        return this.service.realizePayment(id);
    }

    @GetMapping("/teste")
    public ResponseEntity<OrderDTO> hellowWorld() {

        OrderDTO build = OrderDTO
                .builder()
                .orderStatus(OrderStatus.PROCESSING)
                .orderDate(LocalDate.now())
                .value(new BigDecimal("1000"))
                .build();

        return ResponseEntity.ok(build);
    }
}
