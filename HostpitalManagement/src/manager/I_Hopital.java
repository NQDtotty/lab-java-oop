/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manager;

import java.text.ParseException;

/**
 *
 * @author NQDtotty
 */
public interface I_Hopital {

    public void createNurse();

    public void findNurse();

    public void updateNurse();

    public void deleteNurse();

    public void loadFileNurse();

    public void saveFileNurse();

    public void addPatient() throws ParseException;

    public void displayPatient() throws ParseException;

    public void sortPatient();

    public void loadFilePatient();

    public void saveFilePatient();
}
