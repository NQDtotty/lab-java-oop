/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import data.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author NQDtotty
 */
public class Tool {

    public static Scanner sc = new Scanner(System.in);

    public static boolean readBoolean(String message) {
        System.out.print(message + " (T/F, Y/N, 1/0): ");
        String input = sc.nextLine().trim().toUpperCase();
        char res = input.charAt(0);
        return (res == 'T' || res == 'Y' || res == '1');
    }

    public static boolean updateBool(String mess, boolean oldData) {
        System.out.print(mess + " (T/F, Y/N, 1/0): ");
        String input = sc.nextLine().trim().toUpperCase();
        if (!input.isEmpty()) {
            char res = input.charAt(0);
            return (res == 'T' || res == 'Y' || res == '1');
        } else {
            return oldData;
        }
    }

    public static int readInt(String message, int min, int max) {
        int temp = 0;
        boolean error = false;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        do {
            try {
                System.out.print(message + ": ");
                temp = Integer.parseInt(sc.nextLine());
                error = false;
            } catch (Exception e) {
                System.out.println(e);
                error = true;
            }
        } while (temp < min || temp > max || error);
        return temp;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        int temp = 0;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        boolean error = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome + ": ");
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    return oldData;
                } else {
                    temp = Integer.parseInt(line);
                    error = false;
                }
            } catch (Exception e) {
                System.out.println(e);
                error = true;
            }
        } while (temp < min || temp > max || error);
        return temp;
    }

    public static double readDouble(String message, double min, double max) {
        double temp = 0;
        boolean error = false;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        do {
            try {
                System.out.print(message + ": ");
                temp = Double.parseDouble(sc.nextLine());
                error = false;
            } catch (Exception e) {
                System.out.println(e);
                error = true;
            }
        } while (temp < min || temp > max || error);
        return temp;
    }

    public static double updateDouble(String welcome, double min, double max, double oldData) {
        double temp = 0;
        if (min > max) {
            temp = min;
            min = max;
            max = temp;
        }
        boolean error = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome + ": ");
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    return oldData;
                } else {
                    temp = Double.parseDouble(line);
                    error = false;
                }
            } catch (Exception e) {
                System.out.println(e);
                error = true;
            }
        } while (temp < min || temp > max || error);
        return temp;
    }

    public static String readString(String mess) {
        String result;
        boolean success = true;
        do {
            System.out.print(mess + ": ");
            result = sc.nextLine().trim();
            if (result.isEmpty()) {
                success = false;
                System.out.println("Empty input!!");
            } else {
                success = true;
            }
        } while (!success);
        return result;
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome + ": ");
        String line = sc.nextLine().trim();
        if (!line.isEmpty()) {
            result = line;
        }
        return result;
    }

    public static String readPattern(String message, String regex) {
        String result;
        boolean success = true;
        do {
            System.out.print(message + ": ");
            result = sc.nextLine().trim();
            success = result.matches(regex);
            if (!success) {
                System.out.println("Invalid input!");
            }
        } while (!success);
        return result;
    }

    public static String updatePattern(String message, String regex, String oldData) {
        String result = oldData;
        boolean success = true;
        do {
            System.out.print(message + ": ");
            result = sc.nextLine().trim();
            if (!result.isEmpty()) {
                success = result.matches(regex);
                if (!success) {
                    System.out.println("Invalid input!");
                }
            }
        } while (!success);
        return result;
    }

    public static Date getDate(String message) throws ParseException {
        Date result = null;
        boolean success = true;
        do {
            try {
                System.out.println(message + " (dd/mm/yyyy): ");
                String date = sc.nextLine().trim();
                result = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid date!!");
                success = false;
            }
        } while (!success);

        return result;
    }

    public static Date updateDate(String message, Date oldData) throws ParseException {
        Date result = oldData;
        boolean success = true;
        do {
            try {
                System.out.println(message + " (dd/mm/yyyy): ");
                String date = sc.nextLine().trim();
                if (!date.isEmpty()) {
                    result = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                }
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid date!!");
                success = false;
            }
        } while (!success);
        return result;
    }

    public static void displayOrder(ArrayList<Order> listOrder) {
        int count = 1;
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println("+-----+----------+---------------+---------------+---------------+---------------+");
        System.out.println("| No. | Order ID |   Order Date  |   Customer    | Flower Count  |  Order Total  |");
        System.out.println("+-----+----------+---------------+---------------+---------------+---------------+");
        for (Order list : listOrder) {
            System.out.printf("|%-5d|%-10s|%-15s|%-15s|%-15s|%-15s|\n", count++, list.getOrderId(),
                    simpleDateFormat.format(list.getOrderDate()), list.getCustomerName(), list.getQuantity(),
                    "$ " + list.getFlowerCost());
        }
        System.out.println("+-----+----------+---------------+---------------+---------------+---------------+");
    }
}
