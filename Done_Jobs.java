package D34_Proje;

import java.util.Map;
import java.util.Scanner;

import static D34_Proje.Main.jobs;


public class Done_Jobs implements Progress {


    @Override
    public void adding() {
        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in); // I wrote this code just because for ScanNextLine
        boolean validInput = false;

        do {
            try {
                System.out.print("Customer id  = ");
                int mId = scan.nextInt();
                Customer_Pojo customers = Main.customers.get(mId);

                if (customers != null) {
                    System.out.println("Available information: ");
                    System.out.println("Name: " + customers.getName());
                    System.out.println("Address: " + customers.getAddress());
                    System.out.println("Telephone: " + customers.getTel());

                    Work_Type_ENUM workType = null;
                    boolean validJobType = false;
                    do {
                        try {
                            System.out.print("What work was done to this customer? (ELECTRIC, PLUMBING, PAINT, RENOVATION) = ");
                            workType = Work_Type_ENUM.valueOf(scan.next().toUpperCase());
                            validJobType = true;
                        } catch (Exception e) {
                            System.out.println("Invalid Work Type. Please try again.");
                            adding();
                        }
                    } while (!validJobType);
                    System.out.print("Job explanation = ");
                    String explanation = scaNL.nextLine();
                    do {
                        try {
                            System.out.print("How much it cost ? = ");
                            int cost = scan.nextInt();
                            System.out.print("How much was collected ? = ");
                            int collection = scan.nextInt();
                            Work_Pojo progress = new Work_Pojo(++Main.job_id_MAX, mId, workType, explanation, cost, collection);
                            Main.jobs.put(Main.job_id_MAX, progress);
                            //Write to File
                            File_the_Jobs file_the_jobs = new File_the_Jobs();
                            String line = progress.getId() + "//" + progress.getCustomer_Id() + "//" + progress.getWorkType() + "//" + progress.getExplanation() + "//"
                                    + progress.getAmount() + "//" + progress.getCollection();
                            file_the_jobs.Write_to_file(line);
                            System.out.println("The transaction has been added successfully.");
                            validInput = true;
                        }catch (Exception _){
                            System.out.println("Re-enter the information to continue adding the transaction.");
                            adding();
                        }
                    }while (!validInput);

                } else {
                    System.out.println("The customer with the specified ID number was not found.");
                    System.out.println("Re-enter the information to continue adding the transaction.");
                    adding();

                }
            } catch (Exception e) {
                System.out.println("Invalid login please try again.");
                System.out.println("Re-enter the information to continue adding the transaction.");
                scan.nextLine(); // this code cleaning if the user wrote the wrong info
                adding();
            }
        } while (!validInput);


    }


    @Override
    public void update() {
        String c_PURPLE = "\u001B[35m";
        String purpleReset = "\u001B[0m";
        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in);
        boolean validInput = false;

        do {
            try {
                System.out.print("Enter the ID of the job you want to update: ");
                int searchIsId = scan.nextInt();

                Work_Pojo updateJob = jobs.get(searchIsId);

                if (updateJob != null) {
                    System.out.println("Available information: ");
                    System.out.println("Job ID: " + updateJob.getId());
                    System.out.println("Customer ID: " + updateJob.getCustomer_Id());
                    System.out.println("Work Type: " + updateJob.getWorkType());
                    System.out.println("Job Explanation: " + updateJob.getExplanation());
                    System.out.println("Cost: " + updateJob.getAmount());
                    System.out.println("Received: " + updateJob.getCollection());

                    boolean valid_workType = false;
                    do {
                        try {
                            System.out.print("Please enter new work type (ELECTRIC, PLUMBING, PAINT, RENOVATION), if you don't want to change please input = '*' : ");
                            String newJobType = scan.next().toUpperCase();
                            if (!newJobType.equals("*")) {
                                updateJob.setWorkType(Work_Type_ENUM.valueOf(newJobType));
                            }
                            valid_workType = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Work Type. Please try again.");
                            update();
                        }
                    } while (!valid_workType);

                    System.out.print("Change the job explanation (if you don't want to change please input = '*'): ");
                    String newExplanation = scaNL.nextLine();
                    boolean inputNumber = false;
                    if (!newExplanation.equals("*")) {
                        updateJob.setExplanation(newExplanation);
                    }
                    do {
                        try {
                            System.out.print("Change the cost (if you don't want to change please input = '0'): ");
                            int newCost = scan.nextInt();
                            inputNumber =true;
                            if (newCost != 0) {
                                updateJob.setAmount(newCost);
                            }
                        }catch (Exception _){
                            System.out.println("Please input only number !");
                            System.out.println("Re-enter the information to continue updating the transaction.");
                            update();
                        }
                    }while (!inputNumber);
                    inputNumber =false;
                    do {
                        try {
                            System.out.print("Change the receiving Amount (if you don't want to change the receiving amount please input the same amount of cost a: ");
                            int newReceiving = scan.nextInt();
                            inputNumber =true;
                            if (newReceiving <=0) {
                                updateJob.setCollection(newReceiving);
                            }
                        }catch (Exception _){
                            System.out.println("Please input only positive number");
                            System.out.println("Re-enter the information to continue updating the transaction.");
                            update();
                        }
                    }while (!inputNumber);

                    File_the_Jobs file = new File_the_Jobs();
                    file.delete_from_File(searchIsId);

                    String line = updateJob.getId() + "//" + updateJob.getCustomer_Id() + "//" + updateJob.getWorkType() + "//" + updateJob.getExplanation() + "//"
                            + updateJob.getAmount() + "//" + updateJob.getCollection();

                    file.Write_to_file(line);


                    System.out.println("Job infos has been updated.");
                    validInput = true;
                } else {
                    System.out.println("The Job with the specified ID number was not found.");
                }
            } catch (Exception e) {
                System.out.println("Wrong input please try again");
                System.out.println("Re-enter the information to continue updating the transaction.");
                scan.nextLine();
                update();
            }

        } while (!validInput);
    }


    @Override
    public void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the ID number of the customer you want to delete: ");
        int searchCustomerId = scan.nextInt();

        if (Main.customers.containsKey(searchCustomerId)) {
            Customer_Pojo customerPojo = Main.customers.get(searchCustomerId);
            System.out.println("Available information: " + customerPojo);
            boolean processed = false;
            for (Map.Entry<Integer, Work_Pojo> entry : Main.jobs.entrySet()) {
                if (entry.getValue().getCustomer_Id() == searchCustomerId) {
                    processed = true;
                }
            }
            if (processed) {
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
        System.out.printf("%-10s %-20s %-20s %-30s %-15s %-15s", "JOB ID", "CUSTOMER ID", "JOB", "JOB EXPLANATION", "COST", "RECEIVED");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, Work_Pojo> val : Main.jobs.entrySet()) {
            System.out.printf("%-10d %-20s %-20s %-30s %-15s %-15s \n", val.getValue().getId(), val.getValue().getCustomer_Id(), val.getValue().getWorkType()
                    , val.getValue().getExplanation(), val.getValue().getAmount(), val.getValue().getCollection());


        }

    }

}