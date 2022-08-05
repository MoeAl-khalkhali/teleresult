package com.teleresult.api.web;

import com.teleresult.api.model.Order;
import com.teleresult.api.repository.OrderRepository;
import com.teleresult.api.service.OrderService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderService orderService;

    @Test
    public void getOrder_shouldReturnHTTPStatus200_OrderExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getOrder_shouldReturnHTTPStatus200AndMessage_OrderDoesExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/1231432"))
                .andExpect(status().isOk()).andExpect(content().string("Order 1231432 does not exist"));
    }
    @Test
    public void createOrder_shouldReturnHTTPStatus200_OrderAlreadyExists() throws Exception {
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("id", "1");
        mvc.perform(MockMvcRequestBuilders.post("/orders").content(new JSONObject(ob).toJSONString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Order ID already exists"));
    }
    @Test
    public void createOrder_shouldReturnHTTPStatus$00_whenMissingParameters() throws Exception {
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("id", "1");
        ob.put("date", new Date(3213232));
        ob.put("customer", "customer-93");
        ob.put("type", "iPhone14");
        mvc.perform(MockMvcRequestBuilders.post("/orders").content(new JSONObject(ob).toJSONString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
