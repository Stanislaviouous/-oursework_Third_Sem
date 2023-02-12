package com.example.demo.entitys;

import java.util.ArrayList;

public class Delivery {
    private String id;
    private ArrayList<String> orderList;

    public Delivery(String id, ArrayList<String> orderList) {
       setId(id);
       setOrderList(orderList);
    }

    public Delivery() {

    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }
/*
    @Override
    public int compareTo(Delivery o) {
        if (Integer.parseInt(this.id.substring(1,this.id.length()-1)) >=
                Integer.parseInt(o.id.substring(1,o.id.length()-1))) {
            return 1;
        }
        return 0;
    }*/
}
