package D34_Proje;

import java.util.*;


public class Raporlar {

    // 1 - Hangi işten ne kadar Kazandım
    public static void hangiIstenNeKadarKazandim() {
        Map<IsTuru, Integer> kazanc = new HashMap<>();
        for (isPojo is : Main.isler.values()) {
            kazanc.put(is.getIsTuru(), kazanc.getOrDefault(is.getIsTuru(), 0) + is.getTahsilat());
        }
        kazanc.forEach((isTuru, toplamKazanc) -> {
            System.out.println(isTuru + " işinden toplam kazanç: " + toplamKazanc);
        });
    }

    // 2 - Borç Alacak Raporu
    public static void borcAlacakRaporu() {
        int toplamBorc = 0;
        int toplamAlacak = 0;
        for (isPojo is : Main.isler.values()) {
            toplamBorc += is.getTutar() - is.getTahsilat();
            toplamAlacak += is.getTahsilat();
        }
        System.out.println("Toplam Borç: " + toplamBorc);
        System.out.println("Toplam Alacak: " + toplamAlacak);
    }

    // 3 - Hangi müşteriye ne kadar iş yaptım (çoktan aza doğru sıralı)
    public static void hangiMusteriyeNeKadarIsYaptim() {
        Map<Integer, Integer> musteriler = new HashMap<>();
        for (isPojo is : Main.isler.values()) {
            musteriler.put(is.getmId(), musteriler.getOrDefault(is.getmId(), 0) + is.getTutar());
        }
        List<Map.Entry<Integer, Integer>> sortedMusteriler = musteriler.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .toList();
        sortedMusteriler.forEach(entry -> {
            System.out.println("Müşteri ID: " + entry.getKey() + " - Toplam İş Tutarı: " + entry.getValue());
        });
    }

    // 4 - Müşteri Hesap Dökümü
    public static void musteriHesapDokumu() {
        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        do {
            try {
                System.out.println("Musteri id no =");
                int musteriId = scan.nextInt();
                System.out.println("Müşteri ID: " + musteriId + " için hesap dökümü:");
                for (isPojo is : Main.isler.values()) {
                    if (is.getmId() == musteriId) {
                        System.out.println(is);
                    }
                }
                isValid=true;
            }catch (Exception e){
                System.out.println("Hatali giris yaptiniz.");
            }
        }while (!isValid);

    }
}

