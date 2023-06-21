/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.text.ParseException;
import manager.StoreManagement;
import tool.Menu;
import tool.Tool;

/**
 *
 * @author NQDtotty
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        // Add menu
        Menu menu = new Menu();
        menu.add("\t\tMANAGE FLOWER");
        menu.add("1. Add a flower");
        menu.add("2. Find a flower");
        menu.add("3. Update a flower");
        menu.add("4. Delete a flower");
        menu.add("\t\tMANAGE ORDER");
        menu.add("5. Add an order");
        menu.add("6. Display orders");
        menu.add("7. Sort orders");
        menu.add("8. Save data");
        menu.add("9. Load data");
        menu.add("10. Quit");

        StoreManagement store = new StoreManagement();
        int choice;
        boolean quit = false;
        do {
            menu.showMenu();
            choice = menu.getChoice(10);
            switch (choice) {
                case 1:
                    boolean continueAddFlower = true;
                    do {
                        store.addFlower();
                        continueAddFlower = Tool.readBoolean("Continue add new flower?");
                    } while (continueAddFlower);
                    break;
                case 2:
                    store.findFlower();
                    break;
                case 3:
                    store.updateFlower();
                    break;
                case 4:
                    store.deleteFlower();
                    break;
                case 5:
                    boolean continueAddOrder = true;
                    do {
                        store.addOrder();
                        continueAddOrder = Tool.readBoolean("Continue add new order?");
                    } while (continueAddOrder);
                    break;
                case 6:
                    store.displayOrder();
                    break;
                case 7:
                    store.sortOrder();
                    break;
                case 8:
                    store.saveFileFlower();
                    store.saveFileOrder();
                    break;
                case 9:
                    store.loadFileFlower();
                    store.loadFileOrder();
                    break;
                default:
                    quit = Tool.readBoolean("Do you want to quit program?");
            }
        } while (!quit);
    }
}
