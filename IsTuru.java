package D34_Proje;

public enum IsTuru {
    ELEKTRIK("ELEKTRIK"),
    SU("SU"),
    BOYA("BOYA"),
    TADILAT("TADILAT");
private String isCesidi;

    public String getIsCesidi() {
        return isCesidi;
    }

    IsTuru(String isCesidi) {
        this.isCesidi = isCesidi;
    }
// elektrik,su,boya,diger


}
