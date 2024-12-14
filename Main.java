package D34_Proje;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static HashMap<Integer, Customer_Pojo> customers = new HashMap<>();
    static HashMap<Integer, Work_Pojo> jobs = new HashMap<>();
    static int customer_id_MAX = 100;
    static int job_id_MAX = 0;




    public static void main(String[] args) {
        File_the_Customers md = new File_the_Customers();
        md.read_from_File();
        File_the_Jobs id = new File_the_Jobs();
        id.read_from_File();
        mainMenu();
        reports();
    }




    public static void mainMenu() {
        Scanner scan = new Scanner(System.in);
        String choice;
        mainMenuText();
        do {
            System.out.print("Your Choice ? : ");
            choice = scan.next().toUpperCase();
            switch (choice) {
                case "1" -> customer_Transactions();
                case "2" -> jobProgress();
                case "3" -> reports();
                case "X" -> exit();
                default -> System.out.println("Wrong input!");
            }
        } while (!choice.equals("X"));

    }


    public static void mainMenuText() {
        final String green = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        String menuText = green + """
                1 - Customer Reports
                2 - Job Menu
                3 - Reports
                X - Exit
                """ + ANSI_RESET;
        System.out.println(menuText);
    }

    public static void customer_Transactions() {
        customerMenuText();
        Scanner scan = new Scanner(System.in);
        Customers mm = new Customers();
        String choice;
        do {
            choice = scan.next().toUpperCase();
            switch (choice) {
                case "1" -> mm.adding();
                case "2" -> mm.update();
                case "3" -> mm.delete();
                case "4" -> mm.listing();
                case "X" -> mainMenu();
                default -> System.out.println("Wrong input!");
            }

        } while (!choice.equals("X"));
    }

    public static void customerMenuText() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        String customerMenuText = ANSI_RED + """
                    1 - Add Customer
                    2 - Update Customer
                    3 - Delete Customer
                    4 - List of Customers
                    X - Main Menu
                """ + ANSI_RESET;

        System.out.println(customerMenuText);
    }


    public static void jobProgress() {
        jobsMenuText();
        Scanner scan = new Scanner(System.in);
        Done_Jobs iy = new Done_Jobs();
        String choice;
        do {
            choice = scan.next().toUpperCase();
            switch (choice) {
                case "1" -> iy.adding();
                case "2" -> iy.update();
                case "3" -> iy.listing();
                case "4" -> iy.delete();
                case "X" -> mainMenu();
                default -> System.out.println("Wrong input!");
            }

        } while (!choice.equals("X"));
    }

    public static void jobsMenuText() {
        final String ANSI_BLUE = "\u001B[34m";
        final String RESET_BLUE = "\u001B[0m";
        String jobMenuText = ANSI_BLUE + """
                    1 - Add Job
                    2 - Update Job
                    3 - List of Job
                    4 - Delete Job
                    X - Main Menu
                """ + RESET_BLUE;

        System.out.println(jobMenuText);


    }
    public static void reports() {
        reportsTextMenu();
        Scanner scan = new Scanner(System.in);
        Done_Jobs iy = new Done_Jobs();
        String choice;
        do {
            choice = scan.next().toUpperCase();
            switch (choice) {
                case "1" -> Reports.how_much_earned_in_each_job();
                case "2" -> Reports.debt_receiving_report();
                case "3" -> Reports.how_many_job_done_on_which_customer();
                case "4" -> Reports.customer_account_statement();
                case "X" -> mainMenu();
                default -> System.out.println("Wrong input!");
            }

        } while (!choice.equals("X"));
    }

    public static void reportsTextMenu() {
        final String c_PURPLE = "\u001B[35m";
        final String reset = "\u001B[0m";
        String reportsTextMenu = c_PURPLE+ """
                    1 - How much i earned in each job
                    2 - Debt receiving Report
                    3 - How many job done on which customer (Order by most to least)
                    4 - Customer Account Statement
                    X - Main Menu
                """ + reset;

        System.out.println(reportsTextMenu);

    }

    public static void exit() {
        System.out.println("User pressed to 'X' program being terminating...");
        System.exit(0);
    }




}
