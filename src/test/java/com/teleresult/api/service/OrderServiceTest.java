package com.teleresult.api.service;

import com.teleresult.api.model.Order;
import com.teleresult.api.repository.OrderRepository;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(OrderService.class)
public class OrderServiceTest {



    @MockBean
    OrderService orderService;
    @Test
    public void createOrder_shouldReturNull_incorrectOrder() throws Exception {
        Order ob = new Order();
        ob.setType("iPhone13");
        ob.setTitle("this is a title");
        Order expected = orderService.createOrder(ob);
        Assert.assertEquals(null, expected);
    }
}
