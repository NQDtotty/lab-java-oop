/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author NQDtotty
 */
public class Flower implements Serializable {

    private String flowerId;
    private String name;
    private String description;
    private Date importDate;
    private Double unitPrice;
    private String category;

    public Flower() {
    }

    public Flower(String flowerId) {
        this.flowerId = flowerId;
    }

    public Flower(String flowerId, String name, String description, Date importDate, Double unitPrice, String category) {
        this.flowerId = flowerId;
        this.name = name;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Flower{" + "flowerId=" + flowerId + ", description=" + description + ", importDate=" + importDate + ", unitPrice=" + unitPrice + ", category=" + category + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.flowerId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flower other = (Flower) obj;
        return true;
    }
}
