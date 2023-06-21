/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.text.ParseException;
import manager.HospitalManagement;
import tool.Menu;
import tool.MyUtil;

/**
 *
 * @author NQDtotty
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        // Main menu
        Menu mainMenu = new Menu();
        mainMenu.add("Nurse\'s management");
        mainMenu.add("Patient\'s management");
        mainMenu.add("Others - Quit");

        // Nurse menu
        Menu nurseMenu = new Menu();
        nurseMenu.add("Create a nurse");
        nurseMenu.add("Find the nurse");
        nurseMenu.add("Update the nurse");
        nurseMenu.add("Delete the nurse");

        // Patient menu
        Menu patientMenu = new Menu();
        patientMenu.add("Add a patient");
        patientMenu.add("Display patients");
        patientMenu.add("Sort the patients list");
        patientMenu.add("Save data");
        patientMenu.add("Load data");

        HospitalManagement hospitalManagement = new HospitalManagement();
        int mainChoice, nurseChoice, patientChoice;
        boolean quit = false;
        
        do {
            mainMenu.showMenu();
            mainChoice = mainMenu.getChoice();

            switch (mainChoice) {
                case 1:
                    nurseMenu.showMenu();
                    nurseChoice = nurseMenu.getChoice();
                    switch (nurseChoice) {
                        case 1:
                            boolean continueAdd = true;
                            do {
                                hospitalManagement.createNurse();
                                continueAdd = MyUtil.readBoolean("Continue add new nurse?");
                            } while(continueAdd);         
                            break;
                        case 2:
                            hospitalManagement.findNurse();
                            break;
                        case 3:
                            hospitalManagement.updateNurse();
                            break;
                        case 4:
                            hospitalManagement.deleteNurse();
                            break;
                    }
                    break;
                case 2:
                    patientMenu.showMenu();
                    patientChoice = patientMenu.getChoice();
                    switch (patientChoice) {
                        case 1:
                            boolean continueAdd = true;
                            do {
                                hospitalManagement.addPatient();
                                continueAdd = MyUtil.readBoolean("Continue add new patient?");
                            } while(continueAdd);    
                            break;
                        case 2:
                            hospitalManagement.displayPatient(); 
                            break;
                        case 3:
                            hospitalManagement.sortPatient();
                            break;
                        case 4:
                            hospitalManagement.saveFileNurse();
                            hospitalManagement.saveFilePatient();
                            break;
                        case 5:
                            hospitalManagement.loadFileNurse();
                            hospitalManagement.loadFilePatient();
                            break;
                    }
                    break;
                default:
                    quit = MyUtil.readBoolean("Do you want to quit program?");
            }
        } while (!quit);
    }
}
