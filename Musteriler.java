package D34_Proje;

import java.util.Map;
import java.util.Scanner;

import static D34_Proje.Main.musteriIslemler;
import static D34_Proje.Main.musteriler;

public class Musteriler implements Islemler {
    @Override
    public void ekleme() {
            Scanner scan = new Scanner(System.in);
            Scanner scaNL = new Scanner(System.in); // Scan.nextLine icin yaptim
            boolean inPut = false;
            do {
                try {
                    // Müşteri adı girişi ve kontrolü
                    String isim = "";
                    while (true) {
                        try {
                            System.out.print("Müsterinin Adi = ");
                            isim = scaNL.nextLine().trim();
                            if (isim.isEmpty()) {
                                throw new IllegalArgumentException("İsim boş olamaz.");
                            }
                            break; // Doğru giriş yapılırsa döngüden çık
                        } catch (Exception e) {
                            System.out.println("Geçersiz giriş: " + e.getMessage());
                        }
                    }

                    // Müşteri telefonu girişi ve kontrolü
                    String tel = "";
                    while (true) {
                        try {
                            System.out.print("Müsterinin Telefonu = ");
                            tel = scan.next().trim();
                            if (tel.isEmpty()) {
                                throw new IllegalArgumentException("Telefon numarasında bosluk olmamalıdır.");
                            }
                            break; // Doğru giriş yapılırsa döngüden çık
                        } catch (Exception e) {
                            System.out.println("Geçersiz giriş: " + e.getMessage());
                        }
                    }

                    // Müşteri adresi girişi ve kontrolü
                    String adres = "";
                    while (true) {
                        try {
                            System.out.print("Müsterinin Adresi = ");
                            adres = scaNL.nextLine().trim();
                            if (adres.isEmpty()) {
                                throw new IllegalArgumentException("Adres boş olamaz.");
                            }
                            break; // Doğru giriş yapılırsa döngüden çık
                        } catch (Exception e) {
                            System.out.println("Geçersiz giriş: " + e.getMessage());
                        }
                    }

                    // Müşteri bilgileri ekleme onayı
                    String secim = "";
                    if (true) {
                        try {
                            System.out.print("Müsteri bilgileri eklensin mi (E/H)? ");
                            secim = scan.next().toUpperCase().trim();
                            if (!(secim.equals("E") || secim.equals("H"))) {
                                throw new IllegalArgumentException("Geçersiz seçim. Lütfen 'E' veya 'H' giriniz.");
                            }
                            break; // Doğru giriş yapılırsa döngüden çık
                        } catch (Exception e) {
                            System.out.println("Geçersiz giriş: " + e.getMessage());
                        }
                    }

                    if (secim.equals("E")) {
                        MusterilerPojo musteri = new MusterilerPojo(++Main.musidMax, isim, tel, adres);
                        musteriler.put(musteri.getmId(), musteri);
                        // Dosyaya yazma
                        MusterilerDosyala dosya = new MusterilerDosyala();
                        String line = musteri.getmId() + "//" + musteri.getIsim() + "//" + musteri.getTel() + "//" + musteri.getAdres();
                        dosya.dosyayaYaz(line);
                        System.out.println("Müsteri eklendi.");
                    } else if (secim.equals("H")) {
                        System.out.println("Islem iptal edildi.");
                        Main.musteriIslemler();
                        return;
                    }

                    inPut = true; // Başarıyla tamamlandı, döngüden çıkmak için inPut true yapılır

                } catch (Exception e) {
                    System.out.println("Hatali giris yaptiniz müsteri menüsüne yönlendiriliyorsunuz.");
                    Main.musteriIslemler();
                    return;
                }

            }while (!inPut);

    }

    @Override
    public void guncelleme() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in); // Scan.nextLine için yaptım
        System.out.println("Mevcut musteriler:");
        for (Map.Entry<Integer, MusterilerPojo> entry : musteriler.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Isim: " + entry.getValue().getIsim());
        }

        System.out.print(ANSI_RED + "Güncellemek istediginiz Müsterinin id nosunu giriniz :");
        int searchmId = scan.nextInt();
        //buraya do while ile try catch kullan icinde giris basarili booleni olsun
        MusterilerPojo musteri = musteriler.get(searchmId);

        if (musteri != null) {
            System.out.println("Mevcut bilgiler: " + ANSI_RESET);
            System.out.println("Isim: " + musteri.getIsim());
            System.out.println("Adres: " + musteri.getAdres());
            System.out.println("Telefon: " + musteri.getTel());

            System.out.print("Yeni Isim (değiştirmek istemiyorsanız '*' girin): ");
            String yeniIsim = scan.next();
            if (!yeniIsim.equals("*")) {
                musteri.setIsim(yeniIsim);
            }
            System.out.print("Yeni Adres (değiştirmek istemiyorsanız *' girin): ");
            String yeniAdres = scaNL.nextLine();
            if (!yeniAdres.equals("*")) {
                musteri.setAdres(yeniAdres);
            }
            System.out.print("Yeni Telefon (değiştirmek istemiyorsanız *' girin): ");
            String yeniTel = scan.next();
            if (!yeniTel.equals("*")) {
                musteri.setTel(yeniTel);
            }
            MusterilerDosyala dosya = new MusterilerDosyala();

            dosya.dosyadanSil(searchmId);
            musteriler.put(searchmId, musteri);

            // Güncellenmiş müşteri bilgilerini dosyaya yazma

            String line = musteri.getmId() + "//" + musteri.getIsim() + "//" + musteri.getTel() + "//" + musteri.getAdres();
            dosya.dosyayaYaz(line);

            System.out.println("Müşteri bilgileri güncellendi." + ANSI_RESET);
        } else {
            System.out.println("Belirtilen ID numarasına sahip müşteri bulunamadı.");
        }
    }

    @Override
    public void silme() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Silmek istediğiniz müşterinin ID numarasını giriniz: ");
        int searchmId = scan.nextInt();

        if (Main.musteriler.containsKey(searchmId)) {
            MusterilerPojo musteri = Main.musteriler.get(searchmId);
            System.out.println("Mevcut bilgiler: ");
            System.out.println("Isim: " + musteri.getIsim());
            System.out.println("Adres: " + musteri.getAdres());
            System.out.println("Telefon: " + musteri.getTel());
            boolean islemGormus = false;
            for (Map.Entry<Integer, isPojo> entry : Main.isler.entrySet()) {
                if (entry.getValue().getmId() == searchmId) {
                    islemGormus = true;
                }
            }
            if (islemGormus) {
                System.out.println("Islem gormus kayit silinemez!");
            } else {
                Main.musteriler.remove(searchmId);
                MusterilerDosyala dosya = new MusterilerDosyala();
                dosya.dosyadanSil(searchmId);

                System.out.println("Müşteri silindi.");
            }

            // isler mapinde bu ID'ye ait hareket varsa işlem görmüş, silinemez (borç)
//        System.out.println("searchmId: " + searchmId);
//        System.out.println("musteriler.get(searchmId): " + musteriler.get(searchmId));
//        System.out.println("Main.isler: " + Main.isler);


        }

    }


    @Override
    public void listeleme() {
        System.out.printf("%-15s %-15s %-20s %-20s", "MUSTERI ID", "ISIM", "ADRES", "TELEFON");
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
        for (Map.Entry<Integer, MusterilerPojo> val : musteriler.entrySet()) {
            System.out.printf("%-15d %-15s %-20s %-20s\n", val.getValue().getmId(), val.getValue().getIsim()
                    , val.getValue().getAdres(), val.getValue().getTel());


        }


    }

}
// Islemleri implement et
    /* ör:ekleme metodu
    kullanıcıdan veriler alınır, Map e eklenir ve
    dosyaya yazma metodu çağrılır (MusterilerDosyala clasından)

     */

