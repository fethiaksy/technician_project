package D34_Proje;

import java.util.*;


public class Reports {

    // 1 - How much I earned in each job
    public static void how_much_earned_in_each_job() {
        Map<Work_Type_ENUM, Integer> profit = new HashMap<>();
        for (Work_Pojo job : Main.jobs.values()) {
            profit.put(job.getWorkType(), profit.getOrDefault(job.getWorkType(), 0) + job.getCollection());
        }
        profit.forEach((workType, totalEarned) -> {
            System.out.println("in "+ workType + " job total profit: " + totalEarned);
        });
    }

    // 2 - Debt receiving Report
    public static void debt_receiving_report() {
        int totalDebt = 0;
        int remainAmount = 0;
        for (Work_Pojo is : Main.jobs.values()) {
            totalDebt += is.getAmount() - is.getCollection();
            remainAmount += is.getCollection();
        }
        System.out.println("Total Debt: " + totalDebt);
        System.out.println("Total RemainAmount: " + remainAmount);
    }

    // 3 - How many job done on which customer (Order by most to least)
    public static void how_many_job_done_on_which_customer() {
        Map<Integer, Integer> customers = new HashMap<>();
        for (Work_Pojo is : Main.jobs.values()) {
            customers.put(is.getCustomer_Id(), customers.getOrDefault(is.getCustomer_Id(), 0) + is.getAmount());
        }
        List<Map.Entry<Integer, Integer>> sortedCustomer = customers.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .toList();
        sortedCustomer.forEach(entry -> {
            System.out.println("Customer ID: " + entry.getKey() + " - Total Job Cost: " + entry.getValue());
        });
    }

    // 4 - Customer Account Statement
    public static void customer_account_statement() {
        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        do {
            try {
                System.out.println("Customer id no =");
                int customerId = scan.nextInt();
                System.out.println("Customer ID: " + customerId + "Customer Account Statement : ");
                for (Work_Pojo is : Main.jobs.values()) {
                    if (is.getCustomer_Id() == customerId) {
                        System.out.println(is);
                    }
                }
                isValid=true;
            }catch (Exception e){
                System.out.println("You have logged in incorrectly.");
            }
        }while (!isValid);

    }
}

