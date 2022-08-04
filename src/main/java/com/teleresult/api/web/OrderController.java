package com.teleresult.api.web;

import com.teleresult.api.model.Order;
import com.teleresult.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "id") Long id){
            try{
            Order user =
                    orderRepository
                            .findById(id)
                            .orElseThrow(() -> new Exception("Order not found on :: " + id));
            return ResponseEntity.ok().body(user);
            }  catch (Exception e)
        {
            return ResponseEntity.ok().body("Order " + id + "does not exist");
        }
    }
    @PostMapping()
    public ResponseEntity<?> createdNewOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return ResponseEntity.ok().body(order);

    }
}
