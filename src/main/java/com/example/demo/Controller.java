package com.example.demo;

import com.example.demo.entitys.Delivery;
import com.example.demo.entitys.Order;
import com.example.demo.entitys.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@org.springframework.stereotype.Controller
public class Controller {

    final static String fileNameProducts = "src/main/resources/templates/theme/assets/goods.json";
    final static String fileNameOrders = "src/main/resources/database/orders.json";
    final static String fileNameDelivery = "src/main/resources/database/deliverys.json";

    public static ArrayList<Product> productArrayList = new ArrayList<>();
    public static ArrayList<Order> orderArrayList = new ArrayList<>();
    public static ArrayList<Delivery> deliveryArrayList = new ArrayList<>();
    public static ArrayList<User> userArrayList= new ArrayList<>();

    public static TreeMap<String, Delivery> couriers = new TreeMap<>();
    public static TreeMap<String, String> statusOfDelivery = new TreeMap<>();

    public static TreeMap<String, String> linkMap = new TreeMap<>();

    public Controller() throws IOException {
        String s = "", s1 = "", s2 = "", s3 = "";
        Gson gson = new Gson();
        try {
        s1 = new String(Files.readAllBytes(Paths.get(fileNameProducts)));
        s2 = new String(Files.readAllBytes(Paths.get(fileNameOrders)));
        s3 = new String(Files.readAllBytes(Paths.get(fileNameDelivery)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productArrayList = gson.fromJson(s1, new TypeToken<ArrayList<Product>>(){}.getType());
        orderArrayList = gson.fromJson(s2, new TypeToken<ArrayList<Order>>(){}.getType());
        deliveryArrayList = gson.fromJson(s3, new TypeToken<ArrayList<Delivery>>(){}.getType());
        userArrayList.add(new User("Оля Тиньков", "a", "a", "admin"));
        userArrayList.add(new User("Герда Греф", "s", "s", "storeman"));
        userArrayList.add(new User("Олеся Тиньков", "d", "d", "dispatcher"));
        userArrayList.add(new User("Aleksandr_Ivanovich", "c1", "c1", "courier"));
        userArrayList.add(new User("Nara", "c2", "c2", "courier"));

        linkMap.put("", "");
    }

    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    public static void update(){
        Gson gson = new Gson();
        FileWriter fw;
        try {
            fw = new FileWriter("src/main/resources/templates/theme/assets/goods.json");
            String l = "[";
            for (int i = 0; i < productArrayList.size(); i++) {
                if (i == productArrayList.size() - 1) {
                    l += gson.toJson(productArrayList.get(i));
                }
                else {
                    l += gson.toJson(productArrayList.get(i)) + ",";
                }
            }
            fw.write(l + "]");
            fw.close(); //// Поговорить со стасиком
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update1(){
        Gson gson = new Gson();
        FileWriter fw;
        try {
            fw = new FileWriter( "src/main/resources/database/orders.json");
            String l = "[";
            for (int i = 0; i < orderArrayList.size(); i++) {
                if (i == orderArrayList.size() - 1) {
                    l += gson.toJson(orderArrayList.get(i));
                }
                else {
                    l += gson.toJson(orderArrayList.get(i)) + ",";
                }
            }
            fw.write(l + "]");
            fw.close(); //// Поговорить со стасиком
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update2(){
        Gson gson = new Gson();
        FileWriter fw;
        try {
            fw = new FileWriter( "src/main/resources/database/deliverys.json");
            String l = "[";
            for (int i = 0; i < deliveryArrayList.size(); i++) {
                if (i == deliveryArrayList.size() - 1) {
                    l += gson.toJson(deliveryArrayList.get(i));
                }
                else {
                    l += gson.toJson(deliveryArrayList.get(i)) + ",";
                }
            }
            fw.write(l + "]");
            fw.close(); //// Поговорить со стасиком
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
/*
    @GetMapping("/joinOrders")
    @ResponseBody
    public String joinOrders(@RequestParam String idDelivery) {
        int index = -1;
        for (int i = 0; i < deliveryArrayList.size(); i++) {
            if (idDelivery.equals(deliveryArrayList.get(i).getId())){
                index = i;
            }
        }
        if (index != -1 && statusOfDelivery.keySet().contains(deliveryArrayList.get(index))) {
            return statusOfDelivery.get(deliveryArrayList.get(index));
        }
        return "false";
    }*/

    @RequestMapping(
            value = "/joinOrdersInOrder",
            method = GET)
    @ResponseBody
    public String joinOrdersInOrder(@RequestParam String list) {
        return list;
    }

    @GetMapping("/setOrder")
    @ResponseBody
    public String setOrder(@RequestParam String id) {
        return id;
    }

    @RequestMapping(
            value = "/joinOrdersInDelivery",
            method = GET)
    @ResponseBody
    public String joinOrdersInDelivery(@RequestParam List<String> listIdOfOrders) {
        String nameOfDelivery = listIdOfOrders.get(0);
        ArrayList<String> stringList = new ArrayList<>();
        stringList.addAll(listIdOfOrders.subList(1, listIdOfOrders.size()));
        try {
            deliveryArrayList.add(new Delivery(nameOfDelivery, (ArrayList<String>) stringList));
        }
        catch (Error e){
            return "false";
        }
        catch (Exception p){
            return "false";
        }
        update2();
        return "true";
    }

    @GetMapping("/getStatus")
    @ResponseBody
    public String getStatus(@RequestParam String idDelivery) {
        if (statusOfDelivery.keySet().contains(idDelivery)) {
            return statusOfDelivery.get(idDelivery);
        }
        return "false";
    }

    @GetMapping("/getDelivery")
    @ResponseBody
    public  String getData(@RequestParam String name) {
        if (couriers.containsKey(name)) {
            return couriers.get(name).getId();
        }
        return "false";
    }

    @RequestMapping(
            value = "/setStatus",
            params = { "idDelivery", "status"},
            method = GET)
    @ResponseBody
    public String setStatus(
            @RequestParam("idDelivery") String idDelivery,
            @RequestParam("status") String status) {
        statusOfDelivery.put(idDelivery, status);
        return status;
    }

    @RequestMapping(
            value = "/setCourier",
            params = { "name", "idDelivery"},
            method = GET)
    @ResponseBody
    public String setCourier(
            @RequestParam("name") String name,
            @RequestParam("idDelivery") String idDelivery) {
        int index = -1;
        for (int i = 0; i < deliveryArrayList.size(); i++) {
            if (idDelivery.equals(deliveryArrayList.get(i).getId())){
                index = i;
            }
        }
        if (index != -1) {
            couriers.put(name, deliveryArrayList.get(index));
            //statusOfDelivery.put(deliveryArrayList.get(index).getId(), "");
            return "true";
        }
        return "false";
    }

    @RequestMapping(
            value = "/authorization",
            params = { "email", "password"},
            method = GET)
    @ResponseBody
    public String authorization(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        for (int i = 0; i < userArrayList.size(); i++) {
            if(userArrayList.get(i).getEmail().equals(email) && userArrayList.get(i).getPassword().equals(password)){
                return "" + userArrayList.get(i).nameAll + "-" + userArrayList.get(i).getRole();
            }
        }
        return "false";
    }

    @RequestMapping(
            value = "/registration",
            params = { "displayName", "email", "password" },
            method = GET)
    @ResponseBody
    public String registration(
            @RequestParam("displayName") String displayName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        return "Done";
    }

    @RequestMapping(
            value = "/addProduct",
            method = GET)
    @ResponseBody
    public String addProduct(@RequestParam List<String> listArgs) {
        try {
            productArrayList.add(new Product(listArgs.get(0), listArgs.get(1), ("https://www.bestvinyl.ru/components/com_virtuemart/shop_image/product/resized/") + listArgs.get(2), Double.valueOf(listArgs.get(3))));
        }
        catch (Error e){
            return "false";
        }
        catch (Exception p){
            return "false";
        }
        update();
        return "true";
    }

    @RequestMapping(
            value = "/products",
            method = GET)
    @ResponseBody
    public Iterable<Product> returnProducts() {
        return productArrayList;
    }

    @RequestMapping(
            value = "/orders",
            method = GET)
    @ResponseBody
    public Iterable<Order> returnOrders() {
        return orderArrayList;
    }

    @RequestMapping(
            value = "/deliveries",
            method = GET)
    @ResponseBody
    public Iterable<Delivery> returnDeliveries() {
        return deliveryArrayList;
    }

    @GetMapping("/")
    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/productDelete")
    @ResponseBody
    public String deleteProduct(@RequestParam String id) {;
        for (int i = 0; i < productArrayList.size(); i++) {
            System.out.println(productArrayList.get(i).getId());
            if(productArrayList.get(i).getId().equals(String.valueOf(id.trim()))){
                productArrayList.remove(i);
                update();
                return "true";
            }
        }
        return "false";
    }
    @GetMapping("/deliveryDelete")
    @ResponseBody
    public String deleteDelivery(@RequestParam String id) {
        for (int i = 0; i < deliveryArrayList.size(); i++) {
            if(deliveryArrayList.get(i).getId().equals(String.valueOf(id))){
                deliveryArrayList.remove(i);
                return "true";
            }
        }
        return "false";
    }
    @GetMapping("/orderDelete")
    @ResponseBody
    public String deleteOrder(@RequestParam String id) {
        for (int i = 0; i < orderArrayList.size(); i++) {
            if(orderArrayList.get(i).getId().equals(String.valueOf(id))){
                orderArrayList.remove(i);
                update1();
                return "true";
            }
        }
        return "false";
    }

}
