/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import data.Nurse;
import data.Patient;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tool.MyUtil;

/**
 *
 * @author NQDtotty
 */
public class HospitalManagement implements I_Hopital {

    private HashMap<String, Nurse> nurseList = new HashMap<String, Nurse>();
    private HashMap<String, Patient> patientList = new HashMap<String, Patient>();

    @Override
    public void createNurse() {
        String id = MyUtil.readString("Input Staff ID").toUpperCase();
        boolean isDuplicated = nurseList.containsKey(id);
        if (isDuplicated) {
            System.out.println("Staff ID is duplicated!");
        } else {
            String name = MyUtil.readString("Input Name");
            int age = MyUtil.readInt("Input Age", 0, Integer.MAX_VALUE);
            String gender = MyUtil.readString("Input Gender");
            String address = MyUtil.readString("Input Address");
            String phone = MyUtil.readPattern("Input Phone Number", "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
            String department = MyUtil.readPattern("Input Department", "^[a-zA-Z0-9 ]{3,50}$");
            int shift = MyUtil.readInt("Input Shift", 0, Integer.MAX_VALUE);
            double salary = MyUtil.readDouble("Salary", 0, Double.MAX_VALUE);

            nurseList.put(id, new Nurse(id, name, age, gender, address, phone, department, shift, salary));
            System.out.println("A nurse created successfully");
        }
    }

    @Override
    public void findNurse() {
        boolean check = false;
        String name = MyUtil.readString("Input Name");
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);

        for (Nurse nurse : nurseList.values()) {
            Matcher matcher = pattern.matcher(nurse.getName());
            boolean matchFound = matcher.find();
            if (matchFound) {
                System.out.println(nurse.toString());
                check = true;
            }
        }
        if (!check) {
            System.out.println("The nurse does not exist");
        }
    }

    @Override
    public void updateNurse() {
        String id = MyUtil.readString("Enter Staff ID to update").toUpperCase();
        boolean isExist = nurseList.containsKey(id);
        if (isExist) {
            nurseList.get(id).setName(MyUtil.updateString("Input name to update", nurseList.get(id).getName()));
            nurseList.get(id).setAge(MyUtil.updateInt("Input age to update", 0, Integer.MAX_VALUE, nurseList.get(id).getAge()));
            nurseList.get(id).setGender(MyUtil.updateString("Input gender to update", nurseList.get(id).getGender()));
            nurseList.get(id).setAddress(MyUtil.updateString("Input Address to update", nurseList.get(id).getAddress()));
            nurseList.get(id).setPhone(MyUtil.updatePattern("Input Phone Number to update", "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$", nurseList.get(id).getPhone()));
            nurseList.get(id).setDepartment(MyUtil.updatePattern("Input Department to update", "^[a-zA-Z0-9 ]{3,50}$", nurseList.get(id).getDepartment()));
            nurseList.get(id).setShift(MyUtil.updateInt("Input shift to update", 0, Integer.MAX_VALUE, nurseList.get(id).getShift()));
            nurseList.get(id).setSalary(MyUtil.updateDouble("Input salary to update", 0, Double.MAX_VALUE, nurseList.get(id).getSalary()));
            System.out.println("Successfully updated");
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    @Override
    public void deleteNurse() {
        String staffID = MyUtil.readString("Input Staff ID to delete").toUpperCase();
        boolean isExist = nurseList.containsKey(staffID);
        if (isExist) {
            boolean confirmDelete = MyUtil.readBoolean("Are you sure to delete this nurse?");
            if (confirmDelete) {
                boolean check = false;
                for (Patient patient : patientList.values()) {
                    for (int i = 0; i < patient.getNurseAssigned().size(); i++) {
                        if (patient.getNurseAssigned().get(i).getStaffID().equalsIgnoreCase(staffID)) {
                            check = true;
                        }
                    }
                }
                if (check) {
                    System.out.println("This doctor is taking care of the patient.");
                } else {
                    nurseList.remove(staffID);
                    System.out.println("Delete successfully");
                }
            } else {
                System.out.println("Fail to delete");
            }
        } else {
            System.out.println("The nurse does not exist");
        }
    }

    public boolean checkNurse(String id) {
        boolean check = false;
        for (Nurse nurse : nurseList.values()) {
            if (nurse.getId().equalsIgnoreCase(id)) {
                check = true;
            }
        }
        if(check = true) {
            int count = 0;
            for(Patient patient : patientList.values()) {
                for(int i = 0; i < 2; i++) {
                    if(patient.getNurseAssigned().get(i).getId().equalsIgnoreCase(id)) {
                        count++;
                    }
                }
            }
            
            if(count >= 2) {
                System.out.println("This nurse is assign of 2 patient");
                check = false;
            }
        }
        return check;
    }
    
    public boolean checkNurseIsAssgined(String nurseId) {
        boolean check = false;
        for(Patient patient : patientList.values()) {
           for(int i = 0; i < 2; i++) {
               if(patient.getNurseAssigned().get(i).equals(nurseId)) {
                   check = true;
               }
           } 
        }
        return check;
    }
    
    public boolean checkCanAssginedNurse() {
        ArrayList<Nurse> list = new ArrayList<>();
        ArrayList<Nurse> otherList = new ArrayList<>();
        
//        for(Patient patient : patientList.values()) {
//            for(int i = 0; i < 2; i++) {
//                for(Nurse nurse : nurseList.values()) {
//                    if(patient.getNurseAssigned().get(i).equals(nurse.getId())) {
//                        otherList.add(nurse);
//                    }
//                }
//            
//            }
//        }
        
        for(Nurse nurse : nurseList.values()) {
            if(checkNurseIsAssgined(nurse.getId())) {
                otherList.add(nurse);
            }
        }
        
        for(Patient patient : patientList.values()) {
            for(int i = 0; i < 2; i++) {
                list.add(nurseList.get(patient.getNurseAssigned().get(i)));
            }
        }
        
        if(list.size() <= (nurseList.size() * 2)) {
            if(otherList.size() < (nurseList.size() - 2)) {
                return true;
            } else return false;
        } else {
            return false;
        }
    }
    
    @Override
    public void addPatient() throws ParseException {
        boolean checkCanAssign = checkCanAssginedNurse();
        if(checkCanAssign) {
            String idNurse1 = "", idNurse2 = "";
            String id = MyUtil.readString("Input Patient ID").toUpperCase();
            boolean isDuplicated = patientList.containsKey(id);
            boolean success = true;
            if (isDuplicated) {
                System.out.println("Patient ID is duplicated!");
            } else {
                String name = MyUtil.readString("Input Name");
                int age = MyUtil.readInt("Input Age", 0, Integer.MAX_VALUE);
                String gender = MyUtil.readString("Input Gender");
                String address = MyUtil.readString("Input Address");
                String phone = MyUtil.readPattern("Input Phone Number", "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
                String diagnosis = MyUtil.readString("Input Diagnosis");
                Date admissionDate = MyUtil.getDate("Input Admission Date");
                Date dischargeDate = MyUtil.getDate("Input Discharge Date");
                do {
                    idNurse1 = MyUtil.readString("Input ID nurse number 1").toUpperCase();
                    success = checkNurse(idNurse1);
                    if (!success) {
                        System.out.println("Input again!");
                    }
                } while (!success);
                do {
                    idNurse2 = MyUtil.readString("Input ID nurse number 2").toUpperCase();
                    if (idNurse2.equalsIgnoreCase(idNurse1)) {
                        System.out.println("The nurse is duplicated");
                        success = false;
                    } else {
                        success = checkNurse(idNurse2);
                        if (!success) {
                            System.out.println("Input again!");
                        }
                    }
                } while (!success);
                ArrayList<Nurse> list = new ArrayList();
                list.add(nurseList.get(idNurse1));
                list.add(nurseList.get(idNurse2));
                // Add new Patient
                patientList.put(id, new Patient(diagnosis, admissionDate, dischargeDate, list, id, name, age, gender, address, phone));
                System.out.println("A patient created successfully");
            }
        } else {
            System.out.println("There are not enough nurse can assign patient");
        }
        
    }

    @Override
    public void displayPatient() throws ParseException {
        Date startDate = MyUtil.getDate("Input start date");
        Date endDate = MyUtil.getDate("Input end date");
        ArrayList<Patient> newList = new ArrayList<>();

        System.out.println("LIST OF PATIENTS");
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);

        for (Patient patient : patientList.values()) {
            if (patient.getAdmissionDate().after(startDate)
                    && patient.getAdmissionDate().before(endDate)) {
                newList.add(patient);
            }
        }
        MyUtil.displayPatient(newList);
    }

    @Override
    public void sortPatient() {
        if (patientList.isEmpty()) {
            System.out.println("List patient is empty");
        } else {
            ArrayList<Patient> listSort = new ArrayList();
            for (Patient list : patientList.values()) {
                listSort.add(list);
            }
            int choiceField = MyUtil.readInt("[1.ID or 2.Name]: ", 1, 2);
            int choiceType = MyUtil.readInt("[1.ASC or 2.DESC]: ", 1, 2);
            System.out.println("LIST OF PATIENTS");
            System.out.println("Sort by: " + (choiceField == 1 ? "Name" : "ID"));
            System.out.println("Sort order: " + (choiceType == 1 ? "ASC" : "DESS"));
            switch (choiceField) {
                case 1:
                    if (choiceType == 1) {
                        Collections.sort(listSort, (o1, o2) -> {
                            return o1.getId().compareToIgnoreCase(o2.getId());
                        });
                    } else {
                        Collections.sort(listSort, (o1, o2) -> {
                            return o2.getId().compareToIgnoreCase(o1.getId());
                        });
                    }
                    break;
                case 2:
                    if (choiceType == 1) {
                        Collections.sort(listSort, (o1, o2) -> {
                            return o1.getName().compareToIgnoreCase(o2.getName());
                        });
                    } else {
                        Collections.sort(listSort, (o1, o2) -> {
                            return o2.getName().compareToIgnoreCase(o1.getName());
                        });
                    }
                    break;
            }
            MyUtil.displayPatient(listSort);
        }
    }

    @Override
    public void loadFileNurse() {
        nurseList.clear();
        boolean checkNurse = true;
        try {
            FileInputStream fis = new FileInputStream("nurses.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            nurseList = (HashMap<String, Nurse>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Load data from nurses.dat fail.");
            checkNurse = false;
        }
        if (checkNurse) {
            System.out.println("Load data from nurses.dat successfully.");
        }
    }

    @Override
    public void saveFileNurse() {
        boolean checkNurse = true;
        try {
            FileOutputStream fos = new FileOutputStream("nurses.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nurseList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to nurses.dat fail.");
            checkNurse = false;
        }
        if (checkNurse) {
            System.out.println("Save data to nurses.dat successfully.");
        }
    }

    @Override
    public void loadFilePatient() {
        patientList.clear();
        boolean checkPatient = true;
        try {
            FileInputStream fis = new FileInputStream("patients.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            patientList = (HashMap<String, Patient>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Load data from patients.dat fail.");
            checkPatient = false;
        }
        if (checkPatient) {
            System.out.println("Load data from patients.dat successfully.");
        }
    }

    @Override
    public void saveFilePatient() {
        boolean checkPatient = true;
        try {
            FileOutputStream fos = new FileOutputStream("patients.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(patientList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to patients.dat fail.");
            checkPatient = false;
        }
        if (checkPatient) {
            System.out.println("Save data to patients.dat successfully.");
        }
    }

}
