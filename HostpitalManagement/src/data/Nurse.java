/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;

/**
 *
 * @author NQDtotty
 */
public class Nurse extends Person implements Serializable {

    private String staffID = super.getId();
    private String department;
    private int shift;
    private Double salary;

    public Nurse() {
    }

    public Nurse(String staffID, String name, int age, String gender, String address, String phone, String department, int shift, Double salary) {
        super(staffID, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = this.shift;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    
    
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

//    @Override
//    public String toString() {
//        return staffID + ";" + super.getName() + ";" + super.getAge() + ";" + super.getGender()
//                + ";" + super.getAddress() + ";" + super.getPhone() + ";" + department + ";" + shift + ";" + salary;
//    }

    @Override
    public String toString() {
        return "Nurse{" + "staffID=" + staffID + ", name=" + super.getName() + ", age=" + super.getAge() + ", gender=" +super.getGender() +
                ", address=" + super.getAddress() + ", phone=" + super.getPhone() + ", department=" + department +
                ", shift=" + shift + ", salary=" + salary + '}';
    }
}
