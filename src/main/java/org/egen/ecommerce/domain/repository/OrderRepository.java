package org.egen.ecommerce.domain.repository;

import org.egen.ecommerce.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findByOrderId(String orderId);


}
