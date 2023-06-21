/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import data.Flower;
import data.Order;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import tool.Tool;

/**
 *
 * @author NQDtotty
 */
public class StoreManagement implements I_Store {

    Set<Flower> listFlower = new HashSet<>();
    ArrayList<Order> listOrder = new ArrayList();

    @Override
    public void addFlower() throws ParseException {
        String flowerId = Tool.readString("Input Flower ID").toUpperCase();
        boolean isDuplicated = listFlower.contains(new Flower(flowerId));
        if (isDuplicated) {
            System.out.println("Flower ID is duplicated!");
        } else {
            String name = Tool.readString("Input name");
            String description = Tool.readPattern("Input description", "^[a-zA-Z0-9 ]{3,50}$");
            Date importDate = Tool.getDate("Input import date");
            Double unitPrice = Tool.readDouble("Input unit price", 0, Double.MAX_VALUE);
            String category = Tool.readString("Input category");
            listFlower.add(new Flower(flowerId, name, description, importDate, unitPrice, category));
            System.out.println("A Flower created successfully");
        }
    }

    @Override
    public void findFlower() {
        int choice = Tool.readInt("Input type to find flower: \n 1. Flower ID \n 2. Name ", 1, 2);
        boolean empty = true;
        switch (choice) {
            case 1:
                String flowerId = Tool.readString("Input Flower ID to find").toUpperCase();
                for (Object item : listFlower) {
                    Flower flower = (Flower) item;
                    if (flower.getFlowerId().equalsIgnoreCase(flowerId)) {
                        System.out.println(flower.toString());
                        empty = false;
                    }
                }
                break;
            case 2:
                String name = Tool.readString("Input name to find").toUpperCase();
                for (Object item : listFlower) {
                    Flower flower = (Flower) item;
                    if (flower.getName().equalsIgnoreCase(name)) {
                        System.out.println(flower.toString());
                        empty = false;
                    }
                }
                break;
        }
        if (empty) {
            System.out.println("Flower does not exist");
        }
    }

    @Override
    public void updateFlower() throws ParseException {
        String flowerId = Tool.readString("Input Flower ID to update").toUpperCase();
        boolean isExist = listFlower.contains(new Flower(flowerId));
        if (!isExist) {
            System.out.println("Flower does not exist");
        } else {
            for (Object item : listFlower) {
                Flower flower = (Flower) item;
                if (flower.getFlowerId().equalsIgnoreCase(flowerId)) {
                    flower.setName(Tool.updateString("Input category to update", flower.getName()));
                    flower.setDescription(Tool.updatePattern("Input category to update", "^[a-zA-Z0-9 ]{3,50}$", flower.getDescription()));
                    flower.setImportDate(Tool.updateDate("Input date to update", flower.getImportDate()));
                    flower.setUnitPrice(Tool.updateDouble("Input unit price to update", 0, Double.MAX_VALUE, flower.getUnitPrice()));
                    flower.setCategory(Tool.updateString("Input category to update", flower.getCategory()));
                }
            }
            System.out.println("Successfully updated");
        }
    }

    @Override
    public void deleteFlower() {
        String flowerId = Tool.readString("Input Flower ID to delete").trim();
        boolean checkFlower = true;
        boolean isExist = false;
        for (Object item : listOrder) {
            Order order = (Order) item;
            if (order.getFlowerId().equals(flowerId)) {
                checkFlower = false;
            }
        }
        if (!checkFlower) {
            System.out.println("Flower has already existed in an order detail");
        } else {
            boolean confirm = Tool.readBoolean("Are you sure to delete this flower");
            if (confirm) {
                for (Object item : listFlower) {
                    Flower flower = (Flower) item;
                    if (flower.getFlowerId().equalsIgnoreCase(flowerId)) {
                        listFlower.remove(flower);
                        System.out.println("Delete successfully");
                        isExist = true;
                    }
                }
            }
        }
        if (!isExist) {
            System.out.println("This flower does not exist");
        }
    }

    @Override
    public void addOrder() throws ParseException {
        if (listFlower.isEmpty()) {
            System.out.println("Don't have flower to order");
        } else {
            String orderId = Tool.readString("Input Order ID");
            boolean isDuplicated = false;
            for (int i = 0; i < listOrder.size(); i++) {
                if (listOrder.get(i).getOrderId().equalsIgnoreCase(orderId)) {
                    isDuplicated = true;
                }
            }
            if (isDuplicated) {
                System.out.println("Order ID is duplicated");
            } else {
                Date orderDate = Tool.getDate("Input order date");
                String customerName = Tool.readString("Input customer's name");
                boolean checkFlower = false;
                Double priceFlower = 1d;
                String flowerId;
                do {
                    flowerId = Tool.readString("Input Flower ID");
                    for (Object item : listFlower) {
                        Flower flower = (Flower) item;
                        if (flower.getFlowerId().equalsIgnoreCase(flowerId)) {
                            checkFlower = true;
                            priceFlower = flower.getUnitPrice();
                        }
                    }
                    if (!checkFlower) {
                        System.out.println("Flower does not exist");
                    }
                } while (!checkFlower);
                int quantity = Tool.readInt("Input quantity", 0, Integer.MAX_VALUE);
                Double cost = quantity * priceFlower;
                listOrder.add(new Order(orderId, orderDate, customerName, orderId, flowerId, quantity, cost));
                System.out.println("Create Order Successfully");
            }
        }
    }

    @Override
    public void displayOrder() throws ParseException {
        Date startDate = Tool.getDate("Input start date");
        Date endDate = Tool.getDate("Input end date");
        System.out.println("LIST ORDER FROM " + startDate + " TO " + endDate);
        ArrayList<Order> newList = new ArrayList<>();
        for (int i = 0; i < listOrder.size(); i++) {
            if (listOrder.get(i).getOrderDate().after(startDate)
                    && listOrder.get(i).getOrderDate().before(endDate)) {
                newList.add(listOrder.get(i));
            }
        }
        Tool.displayOrder(newList);
    }

    @Override
    public void sortOrder() {
        if (listOrder.isEmpty()) {
            System.out.println("Empty order");
        } else {
            int choiceField = Tool.readInt("\t1. ID\n2. Date\n3. Customer name\n4. Total", 1, 4);
            int choiceType = Tool.readInt("[1.ASC or 2.DESC]: ", 1, 2);
            System.out.println("LIST OF ORDERS");
            System.out.println("Sort by: " + (choiceField == 1 ? "ID" : (choiceField == 2 ? "Date" : (choiceField == 3
                    ? "Customer name" : "Total"))));
            System.out.println("Sort order: " + (choiceType == 1 ? "ASC" : "DESS"));

            switch (choiceField) {
                case 1:
                    if (choiceType == 1) {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o1.getFlowerId().compareToIgnoreCase(o2.getFlowerId());
                        });
                    } else {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o2.getFlowerId().compareToIgnoreCase(o1.getFlowerId());
                        });
                    }
                    break;
                case 2:
                    if (choiceType == 1) {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o1.getFlowerId().compareToIgnoreCase(o2.getFlowerId());
                        });
                    } else {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o2.getFlowerId().compareToIgnoreCase(o1.getFlowerId());
                        });
                    }
                    break;
                case 3:
                    if (choiceType == 1) {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o1.getCustomerName().compareToIgnoreCase(o2.getCustomerName());
                        });
                    } else {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return o2.getCustomerName().compareToIgnoreCase(o1.getCustomerName());
                        });
                    }
                    break;
                case 4:
                    if (choiceType == 1) {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return Double.compare(o1.getFlowerCost(), o2.getFlowerCost());
                        });
                    } else {
                        Collections.sort(listOrder, (o1, o2) -> {
                            return Double.compare(o2.getFlowerCost(), o1.getFlowerCost());
                        });
                    }
                    break;
            }
        }
    }

    @Override
    public void loadFileFlower() {
        listFlower.clear();
        boolean checkFlower = true;
        try {
            FileInputStream fis = new FileInputStream("flowers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listFlower = (HashSet<Flower>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Load data from flowers.dat fail.");
            checkFlower = false;
        }
        if (checkFlower) {
            System.out.println("Load data from flowers.dat successfully.");
        }
    }

    @Override
    public void saveFileFlower() {
        boolean checkFlower = true;
        try {
            FileOutputStream fos = new FileOutputStream("flowers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listFlower);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to flowers.dat fail.");
            checkFlower = false;
        }
        if (checkFlower) {
            System.out.println("Save data to flowers.dat successfully.");
        }
    }

    @Override
    public void loadFileOrder() {
        listOrder.clear();
        boolean checkOrder = true;
        try {
            FileInputStream fis = new FileInputStream("orders.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listOrder = (ArrayList<Order>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Load data from orders.dat fail.");
            checkOrder = false;
        }
        if (checkOrder) {
            System.out.println("Load data from orders.dat successfully.");
        }
    }

    @Override
    public void saveFileOrder() {
        boolean checkOrder = true;
        try {
            FileOutputStream fos = new FileOutputStream("orders.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listOrder);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to orders.dat fail.");
            checkOrder = false;
        }
        if (checkOrder) {
            System.out.println("Save data to orders.dat successfully.");
        }
    }
}
