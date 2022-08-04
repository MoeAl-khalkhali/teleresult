package com.teleresult.api.model;

import java.util.ArrayList;

public class OrderView {
    private int count;
    private String type;

    public int getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getRelated_customers() {
        return related_customers;
    }

    public ArrayList<Long> getOrders() {
        return orders;
    }

    private ArrayList<String> related_customers;
    private ArrayList<Long> orders;

    public OrderView(int count, String type, ArrayList<String> related_customers, ArrayList<Long> orders) {
        this.count = count;
        this.type = type;
        this.related_customers = related_customers;
        this.orders = orders;
    }
}
