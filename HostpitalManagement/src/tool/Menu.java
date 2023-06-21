/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NQDtotty
 */
public class Menu extends ArrayList<String> {

    public void showMenu() {
        int i = 0;
        System.out.println("");
        for (String item : this) {
            System.out.println("      |" + (++i) + " - " + item);
        }
    }

    public int getChoice() {
        int choice = this.size();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("---> Choose your choice: ");
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
        }
        return choice;
    }
}
