package br.stapassoli.msorder.repository;

import br.stapassoli.msorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
