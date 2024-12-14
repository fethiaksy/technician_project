package D34_Proje;


import java.io.*;
import java.util.HashMap;


public class File_the_Jobs implements Progress_File {
    @Override
    public void Write_to_file(String line) {
        String filePath = System.getProperty("user.dir") + "/jobs.txt"; // File has been located in disk.

        File file = new File(filePath);  // File has been defined
        if (!file.exists()) { // if the File does not exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            //everything is standard except below two lines
            bWriter.write(line);
            bWriter.newLine();


            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("File writing error: " + e.getMessage());
        }

    }

    @Override
    public void read_from_File() {
        String filePath = System.getProperty("user.dir") + "/jobs.txt"; // File has been located in disk.
        String line;
        File file = new File(filePath); // if the File does not exist
        if (!file.exists()) { // if the File does not exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            Main.jobs = new HashMap<>();
            while ((line = bReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] array = line.split("//");
                int isId = Integer.parseInt(array[0]);
                if (isId > Main.job_id_MAX) {
                    Main.job_id_MAX = isId;
                }
                int mId = Integer.parseInt(array[1]);
                Work_Type_ENUM workType = Work_Type_ENUM.valueOf(array[2]);
                String explanation = array[3];
                int cost = Integer.parseInt(array[4]);
                int collection = Integer.parseInt(array[5]);
                Work_Pojo addProgress = new Work_Pojo(isId, mId, workType, explanation, cost, collection);
                Main.jobs.put(isId, addProgress);
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }





    @Override
    public void delete_from_File(int id) {
        String filePath = System.getProperty("user.dir") + "/jobs.txt";
        File file = new File(filePath);
        File tempFile = new File(filePath + ".tmp"); // make temporarily file

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] array = currentLine.split("//");
                int isId = Integer.parseInt(array[0]);
                if (isId == id) {
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deleting from file: " + e.getMessage());
        }

        // Delete the original file and change the name of temporarily file
        if (!file.delete()) {
            throw new RuntimeException("Old File couldn't' deleted.");
        }
        if (!tempFile.renameTo(file)) {
            throw new RuntimeException("Temporarily File couldn't renamed.");
        }
    }



 }


