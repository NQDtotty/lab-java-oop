/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NQDtotty
 */
public class Patient extends Person implements Serializable {
    private String diagnosis;
    private Date admissionDate;
    private Date dischargeDate;
    private ArrayList<Nurse> nurseAssigned;

    public Patient() {
    }

    public Patient(String diagnosis, Date admissionDate, Date dischargeDate, ArrayList<Nurse> nurseAssigned, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public ArrayList<Nurse> getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(ArrayList<Nurse> nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }
    
    @Override
    public String toString() {
        return super.getId() + ";" + super.getName() + ";" + super.getAge() + ";" + super.getGender()
                + ";" + super.getAddress() + ";" + super.getPhone() + ";" + diagnosis + ";" + admissionDate + ";" + dischargeDate
                + nurseAssigned.get(0).toString() + ";" + nurseAssigned.get(1).toString();
    }
}
