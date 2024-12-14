package D34_Proje;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import static D34_Proje.Main.musidMax;
import static D34_Proje.Main.musteriler;

// Idosyala yı implement ET
public class MusterilerDosyala implements Idosyala {


    @Override
    public void dosyayaYaz(String line) {
        String filePath = System.getProperty("user.dir") + "/musteri_list.txt"; // dosyanın diskteki yeri belirlendi

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
        String filePath = System.getProperty("user.dir") + "/musteri_list.txt"; // dosyanın diskteki yeri belirlendi
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
            musteriler = new HashMap<>();
            while ((line = bReader.readLine()) != null) {
                // line i split yaparak bir String dizisine alin
                //dizinin her bir eleanini sirayla id, isim .. eslestirin
                //eslestrdiginiz id, isim, .. alttaki veri nesnesine gonderin.
                //veriyi Main.list e ekleyin
                String[] dizi = line.split("//");
                int mId = Integer.parseInt(dizi[0]);
                if (mId > Main.musidMax) {
                    musidMax = mId;
                }
                String isim = dizi[1];
                String tel = dizi[2];
                String adres = dizi[3];
                //System.out.println(mId+ "++"+isim+"++"+adres+"++"+tel);
                MusterilerPojo cr = new MusterilerPojo(mId, isim, tel, adres);
                musteriler.put(mId, cr);

            }
            System.out.println("Müsteri sayisi : " + musteriler.size());
            bReader.close();
            fReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public void dosyadanSil(int id) {

        try {
            // Mevcut dosyadan bilgileri okuyarak musteriler listesine aktar

            Map<Integer, MusterilerPojo> musteriler = Main.musteriler;

            // musteriler listesinden ilgili müşteriyi sil
            musteriler.remove(id);
            String filePath = System.getProperty("user.dir") + "/musteri_list.txt";
            File file = new File(filePath);
            // Dosyayı yeniden yaz
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (MusterilerPojo musteri : musteriler.values()) {
                    String line = musteri.getmId() + "//" + musteri.getIsim() + "//" + musteri.getAdres() + "//" + musteri.getTel();
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Dosyadan silme hatası: " + e.getMessage());
        }
    }
}




