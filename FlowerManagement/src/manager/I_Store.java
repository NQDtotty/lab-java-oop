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
public interface I_Store {

    public void addFlower() throws ParseException;

    public void findFlower();

    public void updateFlower() throws ParseException;

    public void deleteFlower();

    public void addOrder() throws ParseException;

    public void displayOrder() throws ParseException;

    public void sortOrder();

    public void loadFileFlower();

    public void saveFileFlower();

    public void loadFileOrder();

    public void saveFileOrder();
}
