package com.teleresult.api.service;

import com.teleresult.api.model.Order;
import com.teleresult.api.model.OrderView;
import com.teleresult.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;;
    public OrderView getOrderByTypeAndDate(String type, Date date){
//        Store customers in list
        ArrayList<String> customers = new ArrayList<>();

//        Store Id's in list
        ArrayList<Long> order_ids = new ArrayList<Long>();

//        get orders which meet criteria
        List<Order> orders = orderRepository.findByTypeAndDate(type, date);
        int count = 0;
        for (Order order:orders){
//            Ensure no duplicates
            if (!customers.contains(order.getCustomer())){
                customers.add(order.getCustomer());
            }
            if (count < 10){
                order_ids.add(order.getId());
                count++;
            }
        }
        return new OrderView(orders.size(), type, customers, order_ids);
    }
    public Order createOrder(Order order){
        if (orderRepository.findById((Long)order.getId()).isEmpty()){
            orderRepository.save(order);
            return order;
        }
        return null;
    }
    public java.util.Optional<Order> getOrderById(Long id){

        return orderRepository.findById(id);
    }


    }
