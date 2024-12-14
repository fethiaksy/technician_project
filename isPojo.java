package D34_Proje;

public class isPojo {
    private int id; //unique // otomatik artacak, isnumarası
    private int mId =0 ; // non unique // işi kime yaptığı
    private IsTuru isTuru;
    private String aciklama;
    private int tutar;
    private int tahsilat;

    @Override
    public String toString() {
        return "isPojo{" +
                "id=" + id +
                ", mId=" + mId +
                ", isTuru=" + isTuru +
                ", aciklama='" + aciklama + '\'' +
                ", tutar=" + tutar +
                ", tahsilat=" + tahsilat +
                '}';
    }

    public isPojo(int id, int mId, IsTuru isTuru, String aciklama, int tutar, int tahsilat) {
        this.id = id;
        this.mId = mId;
        this.isTuru = isTuru;
        this.aciklama = aciklama;
        this.tutar = tutar;
        this.tahsilat = tahsilat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public IsTuru getIsTuru() {
        return isTuru;
    }

    public void setIsTuru(IsTuru isTuru) {
        this.isTuru = isTuru;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public int getTahsilat() {
        return tahsilat;
    }

    public void setTahsilat(int tahsilat) {
        this.tahsilat = tahsilat;
    }

    public isPojo() {
    }



}
