package D34_Proje;

public enum Work_Type_ENUM {
    ELECTRIC("ELECTRIC"),
    PLUMBING("PLUMBING"),
    PAINT("PAINT"),
    RENOVATION("RENOVATION");
private String work_Type;

    public String getWork_Type() {
        return work_Type;
    }

    Work_Type_ENUM(String work_Type) {
        this.work_Type = work_Type;
    }


}
