package com.teleresult.api.web;

import com.teleresult.api.model.Order;
import com.teleresult.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long id)
            throws Exception {
        Order user =
                orderRepository
                        .findById(id)
                        .orElseThrow(() -> new Exception("Order not found on :: " + id));
        return ResponseEntity.ok().body(user);
    }
    @PostMapping()
    public ResponseEntity<?> createdNewOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return ResponseEntity.ok().body(order);

    }
}
