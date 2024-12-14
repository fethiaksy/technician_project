package D34_Proje;

public class MusterilerPojo {
    private int mId; // unique, 1 den başlayarak her müşteriye 1 artarak otomatik verilsin
    private String isim;
    private String tel;
    private String adres;
    private int Bakiye; // bu alanı dosyaya yazıp okurken kullanmayın sadece raporlarda işe yarayacak

    public MusterilerPojo(int mId, String isim, String tel, String adres) {
        this.mId = mId;
        this.isim = isim;
        this.tel = tel;
        this.adres = adres;
    }
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getBakiye() {
        return Bakiye;
    }


    public void setBakiye(int bakiye) {
        Bakiye = bakiye;
    }
    @Override
    public String toString() {
        return "MusterilerPojo{" +
                "mId=" + mId +
                ", isim='" + isim + '\'' +
                ", tel='" + tel + '\'' +
                ", adres='" + adres + '\'' +
                '}';
    }


}
