package D34_Proje;


import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class IsleriDosyala implements Idosyala {
    @Override
    public void dosyayaYaz(String line) {
        String filePath = System.getProperty("user.dir") + "/isler.txt"; // dosyanın diskteki yeri belirlendi

        File file = new File(filePath);  // dosya tanımlandı
        if (!file.exists()) { // dosya yoksa
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // yukarıdakı 2 satır veriyi dosyaya yazalım
        try {
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            // alt 2 satır hariç hepsi standart
            bWriter.write(line);
            bWriter.newLine();


            bWriter.close();
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Dosyaya yazma hatasi : " + e.getMessage());
        }

    }

    @Override
    public void dosyadanOku() {
        String filePath = System.getProperty("user.dir") + "/isler.txt"; // dosyanın diskteki yeri belirlendi
        String line;
        File file = new File(filePath);  // dosya tanımlandı
        if (!file.exists()) { // dosya yoksa
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            Main.isler = new HashMap<>();
            while ((line = bReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Boş satırları atla
                }
                String[] dizi = line.split("//");
                int isId = Integer.parseInt(dizi[0]);
                if (isId > Main.isIdMax) {
                    Main.isIdMax = isId;
                }
                int mId = Integer.parseInt(dizi[1]);
                IsTuru isTuru = IsTuru.valueOf(dizi[2]);
                String aciklama = dizi[3];
                int tutar = Integer.parseInt(dizi[4]);
                int tahsilat = Integer.parseInt(dizi[5]);
                isPojo islemEkle = new isPojo(isId, mId, isTuru, aciklama, tutar, tahsilat);
                Main.isler.put(isId, islemEkle);
            }
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }





    @Override
    public void dosyadanSil(int id) {
        String filePath = System.getProperty("user.dir") + "/isler.txt";
        File file = new File(filePath);
        File tempFile = new File(filePath + ".tmp"); // Geçici dosya oluştur

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Silinecek satırı atla
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] dizi = currentLine.split("//");
                int isId = Integer.parseInt(dizi[0]);
                if (isId == id) {
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Dosyadan silme hatası: " + e.getMessage());
        }

        // Orijinal dosyayı sil ve geçici dosyanın adını değiştir
        if (!file.delete()) {
            throw new RuntimeException("Eski dosya silinemedi.");
        }
        if (!tempFile.renameTo(file)) {
            throw new RuntimeException("Geçici dosya yeniden adlandırılamadı.");
        }
    }



 }
    // Idosyala yı implement ET

