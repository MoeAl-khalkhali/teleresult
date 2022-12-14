package com.teleresult.api.web;

import com.teleresult.api.model.Order;
import com.teleresult.api.model.OrderView;
import com.teleresult.api.repository.OrderRepository;
import com.teleresult.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "id") Long id){
            try{
            Order order =
                    orderService
                            .getOrderById(id)
                            .orElseThrow(() -> new Exception("Order not found on :: " + id));
            return ResponseEntity.ok().body(order);
            }  catch (Exception e)
        {
            return ResponseEntity.ok().body("Order " + id + " does not exist");
        }
    }
    @PostMapping()
    public ResponseEntity<?> createdNewOrder(@RequestBody Order order) {
        Order order_return = orderService.createOrder(order);
        if (order_return == null){
            return ResponseEntity.ok().body("Order ID already exists");
        }
        return ResponseEntity.ok().body(order);
    }
    @GetMapping("/{type}/{date}")
    public ResponseEntity<OrderView> getOrderByTypeDate(@PathVariable(value = "type") String type, @PathVariable(value = "date") String date)
            throws Exception {
        Date d = new SimpleDateFormat("yyyyMMdd").parse(date); // This throws a ParseException
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());

        OrderView order = orderService.getOrderByTypeAndDate(type, sqlDate);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
