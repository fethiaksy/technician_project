package D34_Proje;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import static D34_Proje.Main.customer_id_MAX;
import static D34_Proje.Main.customers;


public class File_the_Customers implements Progress_File {


    @Override
    public void Write_to_file(String line) {
        String filePath = System.getProperty("user.dir") + "/customer_list.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            bWriter.write(line);
            bWriter.newLine();


            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("File writing error: : " + e.getMessage());
        }
    }

    @Override
    public void read_from_File() {
        String filePath = System.getProperty("user.dir") + "/customer_list.txt";
        String line;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            customers = new HashMap<>();
            while ((line = bReader.readLine()) != null) {
                String[] array = line.split("//");
                int mId = Integer.parseInt(array[0]);
                if (mId > Main.customer_id_MAX) {
                    customer_id_MAX = mId;
                }
                String name = array[1];
                String tel = array[2];
                String address = array[3];
                //System.out.println(mId+ "++"+name+"++"+address+"++"+tel);
                Customer_Pojo cr = new Customer_Pojo(mId, name, tel, address);
                customers.put(mId, cr);

            }
            System.out.println("Count of Customer : " + customers.size());
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public void delete_from_File(int id) {

        try {


            Map<Integer, Customer_Pojo> customers = Main.customers;


            customers.remove(id);
            String filePath = System.getProperty("user.dir") + "/customer_list.txt";
            File file = new File(filePath);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Customer_Pojo customer : customers.values()) {
                    String line = customer.getCustomerId() + "//" + customer.getName() + "//" + customer.getAddress() + "//" + customer.getTel();
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deleting from file: " + e.getMessage());
        }
    }
}




