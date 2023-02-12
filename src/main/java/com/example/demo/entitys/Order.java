package com.example.demo.entitys;

import java.util.ArrayList;

public class Order implements Comparable<Order>{
    private String id;
    private ArrayList<String> productList;
    private String address;

    public Order(String id, ArrayList<String> productList, String address) {
        setId(id);
        setProductList(productList);
        setAddress(address);
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getProductList() {
        return productList;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductList(ArrayList<String> productList) {
        this.productList = productList;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Order o) {
        if (Integer.parseInt(this.id.substring(1,this.id.length()-1)) >=
                Integer.parseInt(o.id.substring(1,o.id.length()-1))) {
            return 1;
        }
        return 0;
    }
}
