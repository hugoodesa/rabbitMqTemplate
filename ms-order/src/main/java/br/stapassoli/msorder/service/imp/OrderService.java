package br.stapassoli.msorder.service.imp;

import br.stapassoli.msorder.dto.OrderDTO;
import br.stapassoli.msorder.model.Order;
import br.stapassoli.msorder.repository.OrderRepository;
import br.stapassoli.msorder.service.BasicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService extends BasicService<OrderDTO,OrderRepository> {

    public OrderService(OrderRepository orderRepository) {
        super(orderRepository);
    }

    @Override
    @Transactional
    public ResponseEntity<OrderDTO> post(OrderDTO orderDTO) {
        Order order = this.repository.save(orderDTO.toEntity());
        return ResponseEntity.ok(order.toDTO());
    }

    @Override
    @Transactional
    public ResponseEntity delete(Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderDTO> select(Long id) {
        Order order = this.repository.getReferenceById(id);
        return ResponseEntity.ok(order.toDTO());
    }

    @Override
    @Transactional
    public ResponseEntity<OrderDTO> put(Long id, OrderDTO orderDTO) {
        Order order = this.repository.getReferenceById(id);
        order.update(orderDTO);

        return ResponseEntity.ok(order.toDTO());
    }

    @Override
    public Page<OrderDTO> selectAll(Pageable pageable) {
        Page<Order> orders = this.repository.findAll(pageable);

        return new PageImpl<>(orders.stream().map(Order::toDTO).toList());
    }

    @Transactional
    public ResponseEntity realizePayment(Long id){
        Order order = this.repository.getReferenceById(id);

        order.realizePayment();

        return ResponseEntity.ok(order.toDTO());
    }


}
