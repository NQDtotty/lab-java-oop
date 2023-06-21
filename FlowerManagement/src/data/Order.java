/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author NQDtotty
 */
public class Order implements Serializable {

    private String orderId;
    private Date orderDate;
    private String customerName;
    private String orderDetailId;
    private String flowerId;
    private int quantity;
    private Double flowerCost;

    public Order() {
    }

    public Order(String orderId, Date orderDate, String customerName, String orderDetailId, String flowerId, int quantity, Double flowerCost) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetailId = orderDetailId;
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getFlowerCost() {
        return flowerCost;
    }

    public void setFlowerCost(Double flowerCost) {
        this.flowerCost = flowerCost;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", customerName=" + customerName + ", orderDetailId=" + orderDetailId + ", flowerId=" + flowerId + ", quantity=" + quantity + ", flowerCost=" + flowerCost + '}';
    }
}
