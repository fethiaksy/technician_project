package D34_Proje;

import java.util.Map;
import java.util.Scanner;

import static D34_Proje.Main.isler;
import static D34_Proje.Main.musteriler;


public class IslerYapilan implements Islemler {


    @Override
    public void ekleme() {
        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in); // Scan.nextLine için yaptım
        boolean validInput = false;

        do {
            try {
                System.out.print("Müsterinin id si  = ");
                int mId = scan.nextInt();
                MusterilerPojo musteri = Main.musteriler.get(mId);

                if (musteri != null) {
                    System.out.println("Mevcut bilgiler: ");
                    System.out.println("Isim: " + musteri.getIsim());
                    System.out.println("Adres: " + musteri.getAdres());
                    System.out.println("Telefon: " + musteri.getTel());

                    IsTuru isTuru = null;
                    boolean validIsTuru = false;
                    do {
                        try {
                            System.out.print("Bu müsteriye hangi is yapildi ? (ELEKTRIK, SU, BOYA, TADILAT) = ");
                            isTuru = IsTuru.valueOf(scan.next().toUpperCase());
                            validIsTuru = true;
                        } catch (Exception e) {
                            System.out.println("Geçersiz iş türü. Lütfen tekrar deneyin.");
                            ekleme();
                        }
                    } while (!validIsTuru);
                    System.out.print("Is aciklamasi = ");
                    String aciklama = scaNL.nextLine();
                    do {
                        try {
                            System.out.print("Ne kadar tuttu ? = ");
                            int tutar = scan.nextInt();
                            System.out.print("Ne kadar tahsilat yapildi ? = ");
                            int tahsilat = scan.nextInt();
                            isPojo islem = new isPojo(++Main.isIdMax, mId, isTuru, aciklama, tutar, tahsilat);
                            Main.isler.put(Main.isIdMax, islem);
                            //dosyaya yazma
                            IsleriDosyala isleriDosyala = new IsleriDosyala();
                            String line = islem.getId() + "//" + islem.getmId() + "//" + islem.getIsTuru() + "//" + islem.getAciklama() + "//"
                                    + islem.getTutar() + "//" + islem.getTahsilat();
                            isleriDosyala.dosyayaYaz(line);
                            System.out.println("İşlem başarıyla eklendi.");
                            validInput = true;
                        }catch (Exception _){
                            System.out.println("Is eklemeye devam etmek icin bilgileri bastan giriniz.");
                            ekleme();
                        }
                    }while (!validInput);

                } else {
                    System.out.println("Belirtilen ID numarasına sahip müşteri bulunamadı.");
                    System.out.println("Is eklemeye devam etmek icin bilgileri bastan giriniz.");
                    ekleme();

                }
            } catch (Exception e) {
                System.out.println("Geçersiz giriş. Lütfen tekrar deneyin.");
                System.out.println("Is eklemeye devam etmek icin bilgileri bastan giriniz.");
                scan.nextLine(); // Hatalı girişi temizlemek için
                ekleme();
            }
        } while (!validInput);


    }


    @Override
    public void guncelleme() {
        String c_PURPLE = "\u001B[35m";
        String purpleReset = "\u001B[0m";
        Scanner scan = new Scanner(System.in);
        Scanner scaNL = new Scanner(System.in); // Scan.nextLine için yaptım
        boolean validInput = false;

        do {
            try {
                System.out.print("Güncellemek istediginiz işin id nosunu giriniz: ");
                int searchIsId = scan.nextInt();

                isPojo isGuncelle = isler.get(searchIsId);

                if (isGuncelle != null) {
                    System.out.println("Mevcut bilgiler: ");
                    System.out.println("İş ID: " + isGuncelle.getId());
                    System.out.println("Müşteri ID: " + isGuncelle.getmId());
                    System.out.println("İş Türü: " + isGuncelle.getIsTuru());
                    System.out.println("İş Açıklaması: " + isGuncelle.getAciklama());
                    System.out.println("Tutar: " + isGuncelle.getTutar());
                    System.out.println("Tahsilat: " + isGuncelle.getTahsilat());

                    boolean validIsTuru = false;
                    do {
                        try {
                            System.out.print("Yeni iş türünü giriniz (ELEKTRIK, SU, BOYA, TADILAT), değiştirmek istemiyorsanız '*' girin: ");
                            String yeniIsTuru = scan.next().toUpperCase();
                            if (!yeniIsTuru.equals("*")) {
                                isGuncelle.setIsTuru(IsTuru.valueOf(yeniIsTuru));
                            }
                            validIsTuru = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Geçersiz iş türü. Lütfen tekrar deneyin.");
                            guncelleme();
                        }
                    } while (!validIsTuru);

                    System.out.print("İş açıklamasını değiştir (değiştirmek istemiyorsanız '*' girin): ");
                    String yeniAciklama = scaNL.nextLine();
                    boolean sayiGirdi = false;
                    if (!yeniAciklama.equals("*")) {
                        isGuncelle.setAciklama(yeniAciklama);
                    }
                    do {
                        try {
                            System.out.print("Tutarı değiştir (değiştirmek istemiyorsanız '0' giriniz): ");
                            int yeniTutar = scan.nextInt();
                            sayiGirdi=true;
                            if (yeniTutar != 0) {
                                isGuncelle.setTutar(yeniTutar);
                            }
                        }catch (Exception _){
                            System.out.println("Lütfen sadece sayi girisi yapiniz !");
                            System.out.println("Güncelleme icin bilgileri bastan giriniz.");
                            guncelleme();
                        }
                    }while (!sayiGirdi);
                    sayiGirdi=false;
                    do {
                        try {
                            System.out.print("Tahsilatı değiştir (değiştirmek istemiyorsanız mevcut fiyati giriniz): ");
                            int yeniTahsilat = scan.nextInt();
                            sayiGirdi=true;
                            if (yeniTahsilat <=0) {
                                isGuncelle.setTahsilat(yeniTahsilat);
                            }
                        }catch (Exception _){
                            System.out.println("Lütfen sadece pozitif sayi giriniz!");
                            System.out.println("Güncelleme icin bilgileri bastan giriniz.");
                            guncelleme();
                        }
                    }while (!sayiGirdi);

                    IsleriDosyala dosya = new IsleriDosyala();
                    dosya.dosyadanSil(searchIsId);
                    // Güncellenmiş iş bilgilerini isler HashMap'ine geri koyma

                   // isler.put(searchIsId, isGuncelle);

                    // Dosyaya yazma işlemi

                    String line = isGuncelle.getId() + "//" + isGuncelle.getmId() + "//" + isGuncelle.getIsTuru() + "//" + isGuncelle.getAciklama() + "//"
                            + isGuncelle.getTutar() + "//" + isGuncelle.getTahsilat();

                    dosya.dosyayaYaz(line);


                    System.out.println("İş bilgileri güncellendi.");
                    validInput = true;
                } else {
                    System.out.println("Belirtilen ID numarasına sahip iş bulunamadı.");
                }
            } catch (Exception e) {
                System.out.println("Geçersiz giriş. Lütfen tekrar deneyin.");
                System.out.println("Güncelleme icin bilgileri bastan giriniz.");
                scan.nextLine(); // Hatalı girişi temizlemek için
                guncelleme();
            }

        } while (!validInput);
    }


    @Override
    public void silme() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Silmek istediğiniz müşterinin ID numarasını giriniz: ");
        int searchmId = scan.nextInt();

        if (Main.musteriler.containsKey(searchmId)) {
            MusterilerPojo musteri = Main.musteriler.get(searchmId);
            System.out.println("Mevcut bilgiler: " + musteri);
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
        System.out.printf("%-10s %-20s %-20s %-30s %-15s %-15s", "IS ID", "MUSTERI ID", "YAPILAN IS", "IS ACIKLAMASI", "TUTAR", "TAHSILAT");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, isPojo> val : Main.isler.entrySet()) {
            System.out.printf("%-10d %-20s %-20s %-30s %-15s %-15s \n", val.getValue().getId(), val.getValue().getmId(), val.getValue().getIsTuru()
                    , val.getValue().getAciklama(), val.getValue().getTutar(), val.getValue().getTahsilat());


        }

    }

}


// Islemleri implement et



