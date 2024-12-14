package D34_Proje;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static HashMap<Integer, MusterilerPojo> musteriler = new HashMap<>();
    static HashMap<Integer, isPojo> isler = new HashMap<>();
    static int musidMax = 100;
    static int isIdMax = 0;


    // bu classta sadece gereken statik tanımlar (id ler, map lar v.s.)
    // ve menu olacak
    public static void main(String[] args) {
        MusterilerDosyala md = new MusterilerDosyala();
        md.dosyadanOku();
        IsleriDosyala id = new IsleriDosyala();
        id.dosyadanOku();
        anaMenu();
        raporLar();
    }




    public static void anaMenu() {
        Scanner scan = new Scanner(System.in);
        String secim;
        anaMenuText();
        do {
            System.out.print("Seciminiz = ? ");
            secim = scan.next().toUpperCase();
            switch (secim) {
                case "1" -> musteriIslemler();
                case "2" -> isIslemler();
                case "3" ->raporLar();
                case "X" -> exit();
                default -> System.out.println("Hatali giris");
            }
        } while (!secim.equals("X"));

    }


    public static void anaMenuText() {
        final String Yesil = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        String menuText = Yesil + """
                1 - Müsteri Kayitlari
                2 - Is menusu
                3 - Raporlar
                X- Cikis
                """ + ANSI_RESET;
        System.out.println(menuText);
    }

    public static void musteriIslemler() {
        musteriMenuText();
        Scanner scan = new Scanner(System.in);
        Musteriler mm = new Musteriler();
        String secim;
        do {
            secim = scan.next().toUpperCase();
            switch (secim) {
                case "1" -> mm.ekleme();
                case "2" -> mm.guncelleme();
                case "3" -> mm.silme();
                case "4" -> mm.listeleme();
                case "X" -> anaMenu();
                default -> System.out.println("Hatali giris");
            }

        } while (!secim.equals("X"));
    }

    public static void musteriMenuText() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        String musterimenuText = ANSI_RED + """
                    1 - Müsteri Ekleme
                    2 - Müsteri Güncelleme
                    3 - Müsteri Silme
                    4 - Müsteri Listeleme
                    X - Ana Menu
                """ + ANSI_RESET;

        System.out.println(musterimenuText);
    }


    public static void isIslemler() {
        islerMenuText();
        Scanner scan = new Scanner(System.in);
        IslerYapilan iy = new IslerYapilan();
        String secim;
        do {
            secim = scan.next().toUpperCase();
            switch (secim) {
                case "1" -> iy.ekleme();
                case "2" -> iy.guncelleme();
                case "3" -> iy.listeleme();
                case "4" -> iy.silme();
                case "X" -> anaMenu();
                default -> System.out.println("Hatali giris");
            }

        } while (!secim.equals("X"));
    }

    public static void islerMenuText() {
        final String ANSI_BLUE = "\u001B[34m";
        final String RESET_BLUE = "\u001B[0m";
        String musterimenuText = ANSI_BLUE + """
                    1 - Is Ekleme
                    2 - Is Güncelleme
                    3 - Is Listeleme
                    4 - Is Silme
                    X - Ana Menu
                """ + RESET_BLUE;

        System.out.println(musterimenuText);


    }
    public static void raporLar() {
        raporlarTextMenu();
        Scanner scan = new Scanner(System.in);
        IslerYapilan iy = new IslerYapilan();
        String secim;
        do {
            secim = scan.next().toUpperCase();
            switch (secim) {
                case "1" ->Raporlar.hangiIstenNeKadarKazandim();
                case "2" ->Raporlar.borcAlacakRaporu();
                case "3" ->Raporlar.hangiMusteriyeNeKadarIsYaptim();
                case "4" ->Raporlar.musteriHesapDokumu();
                case "X" -> anaMenu();
                default -> System.out.println("Hatali giris");
            }

        } while (!secim.equals("X"));
    }

    public static void raporlarTextMenu() {
        final String c_PURPLE = "\u001B[35m";
        final String reset = "\u001B[0m";
        String musterimenuText = c_PURPLE+ """
                    1 - Hangi isten ne kadar Kazandim
                    2 - Borc Alacak Raporu
                    3 - Hangi müsteriye ne kadar is yaptim (coktan aza dogru sirali)
                    4 - Musteri Hesap dökümü
                    X - Ana Menu
                """ + reset;

        System.out.println(musterimenuText);

    }

    public static void exit() {
        System.out.println(" X e basildi programdan cikiliyor.");
        System.exit(0);
    }

    /* proje işlem sırası
    - Pojo Classlar lar ve alanları(field) belirlenir
    - ilgili intefaceler yazılır, classlar açılır
    - Ustekiler hocanın hazırladıkları
    - Mainde gerekli statik tanımlar yapılır
    - islemler clasları yapılır
    - en son dosya işleri yapılır
     */


}
