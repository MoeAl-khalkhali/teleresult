package com.teleresult.api.web;

import com.teleresult.api.repository.OrderRepository;
import com.teleresult.api.service.OrderService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@WebMvcTest()

public class OrderControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    @MockBean
    OrderRepository orderRepository;

    @MockBean
    OrderService orderService;

    @Test
    public void getOrder_shouldReturnHTTPStatus404_WrongApiUsed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/order/1"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getOrder_shouldReturnHTTPStatus200_orderIsFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getOrder_shouldReturnHTTPStatus200_orderIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/123"))
                .andExpect(status().isOk()).andExpect(content().string("Order 123 does not exist"));
    }
    @Test
    public void createOrder_shouldReturnHTTPStatus400_badOrderNumber() throws Exception {
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("type", "iPhone13");
        ob.put("title", "this is a title");
        ob.put("date", "2022-01-011");
        ob.put("customer", "customer-1");
        ob.put("id", "customer-1");

        mvc.perform(MockMvcRequestBuilders.post("/orders")
                        .content(new JSONObject(ob).toJSONString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void createOrder_shouldReturnHTTPStatus400_badDateFormat() throws Exception {
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("type", "iPhone13");
        ob.put("title", "this is a title");
        ob.put("date", "2022-01-asdf011");
        ob.put("customer", "customer-1");
        ob.put("id", "1");

        mvc.perform(MockMvcRequestBuilders.post("/orders")
                        .content(new JSONObject(ob).toJSONString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void createOrder_shouldReturnHTTPStatus200_badDateFormat() throws Exception {
        HashMap<String, Object> ob = new HashMap<>();
        ob.put("type", "iPhone13");


        mvc.perform(MockMvcRequestBuilders.post("/orders")
                        .content(new JSONObject(ob).toJSONString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getOrderByDate_shouldReturnHTTPStatus200_orderDoesntExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/orders/iPhone13/20160622"))
                .andExpect(status().isOk());
    }
}
