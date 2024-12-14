package D34_Proje;

import java.util.Map;
import java.util.Scanner;

import static D34_Proje.Main.customers;

public class Customers implements Progress {
    @Override
    public void adding() {
            Scanner scan = new Scanner(System.in);
            Scanner scaNL = new Scanner(System.in);
            boolean inPut = false;
            do {
                try {
                    String name = "";
                    while (true) {
                        try {
                            System.out.print("Customer Name : ");
                            name = scaNL.nextLine().trim();
                            if (name.isEmpty()) {
                                throw new IllegalArgumentException("Name can not be null.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Wrong input: " + e.getMessage());
                        }
                    }

                    String tel = "";
                    while (true) {
                        try {
                            System.out.print("Customer Telephone = ");
                            tel = scan.next().trim();
                            if (tel.isEmpty()) {
                                throw new IllegalArgumentException("There must be no spaces in the phone number.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Wrong input: " + e.getMessage());
                        }
                    }


                    String address = "";
                    while (true) {
                        try {
                            System.out.print("Customer Address = ");
                            address = scaNL.nextLine().trim();
                            if (address.isEmpty()) {
                                throw new IllegalArgumentException("Address can not be null.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Wrong input: " + e.getMessage());
                        }
                    }


                    String choice = "";
                    if (true) {
                        try {
                            System.out.print("Add customer information? Yes or No (Y/N)? ");
                            choice = scan.next().toUpperCase().trim();
                            if (!(choice.equals("Y") || choice.equals("N"))) {
                                throw new IllegalArgumentException("Wrong input. Please input 'Y' or 'N'.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Wrong input. : " + e.getMessage());
                        }
                    }

                    if (choice.equals("E")) {
                        Customer_Pojo customer = new Customer_Pojo(++Main.customer_id_MAX, name, tel, address);
                        customers.put(customer.getCustomerId(), customer);
                        File_the_Customers file = new File_the_Customers();
                        String line = customer.getCustomerId() + "//" + customer.getName() + "//" + customer.getTel() + "//" + customer.getAddress();
                        file.Write_to_file(line);
                        System.out.println("Customer Successfully added.");
                    } else if (choice.equals("N")) {
                        System.out.println("Progress being cancelled.");
                        Main.customer_Transactions();
                        return;
                    }

                    inPut = true;

                } catch (Exception e) {
                    System.out.println("You entered incorrectly and you are directed to the customer menu.");
                    Main.customer_Transactions();
                    return;
                }

            }while (!inPut);

    }

    @Override
    public void update() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in);
        System.out.println("Current customers:");
        for (Map.Entry<Integer, Customer_Pojo> entry : customers.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue().getName());
        }

        System.out.print(ANSI_RED + "Enter the ID number of the Customer you want to update :");
        int searchCustomerId = scan.nextInt();
        Customer_Pojo customer = customers.get(searchCustomerId);

        if (customer != null) {
            System.out.println("Customer Infos: " + ANSI_RESET);
            System.out.println("Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Telephone: " + customer.getTel());

            System.out.print("New Name (if you don't want to change please input = '*'): ");
            String newName = scan.next();
            if (!newName.equals("*")) {
                customer.setName(newName);
            }
            System.out.print("New Address (if you don't want to change please input = '*'): ");
            String newAddress = scaNL.nextLine();
            if (!newAddress.equals("*")) {
                customer.setAddress(newAddress);
            }
            System.out.print("New Telephone (if you don't want to change please input = '*'): ");
            String newTel = scan.next();
            if (!newTel.equals("*")) {
                customer.setTel(newTel);
            }
            File_the_Customers file = new File_the_Customers();

            file.delete_from_File(searchCustomerId);
            customers.put(searchCustomerId, customer);


            String line = customer.getCustomerId() + "//" + customer.getName() + "//" + customer.getTel() + "//" + customer.getAddress();
            file.Write_to_file(line);

            System.out.println("Customer infos has been updated." + ANSI_RESET);
        } else {
            System.out.println("The customer with the specified ID number was not found.");
        }
    }

    @Override
    public void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the ID number of the Customer you want to delete : ");
        int searchCustomerId = scan.nextInt();

        if (Main.customers.containsKey(searchCustomerId)) {
            Customer_Pojo customer = Main.customers.get(searchCustomerId);
            System.out.println("Customer infos: ");
            System.out.println("Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Telephone: " + customer.getTel());
            boolean beingProcessed = false;
            for (Map.Entry<Integer, Work_Pojo> entry : Main.jobs.entrySet()) {
                if (entry.getValue().getCustomer_Id() == searchCustomerId) {
                    beingProcessed = true;
                }
            }
            if (beingProcessed) {
                System.out.println("A processed record cannot be deleted!");
            } else {
                Main.customers.remove(searchCustomerId);
                File_the_Customers file = new File_the_Customers();
                file.delete_from_File(searchCustomerId);

                System.out.println("Customer deleted.");
            }


        }

    }


    @Override
    public void listing() {
        System.out.printf("%-15s %-15s %-20s %-20s", "CUSTOMER ID", "NAME", "ADDRESS", "TELEPHONE");
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
        for (Map.Entry<Integer, Customer_Pojo> val : customers.entrySet()) {
            System.out.printf("%-15d %-15s %-20s %-20s\n", val.getValue().getCustomerId(), val.getValue().getName()
                    , val.getValue().getAddress(), val.getValue().getTel());


        }


    }

}


