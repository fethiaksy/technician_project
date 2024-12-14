package D34_Proje;

public class Work_Pojo {
    private int id; //unique // otomatik artacak, isnumarası
    private int customer_Id =0 ; // non unique // işi kime yaptığı
    private Work_Type_ENUM workType;
    private String explanation;
    private int amount;
    private int collection;

    @Override
    public String toString() {
        return "Work_Pojo{" +
                "id=" + id +
                ", Customer_Id=" + customer_Id +
                ", Work Type=" + workType +
                ", Explanation='" + explanation + '\'' +
                ", Amount =" + amount +
                ", Collection=" + collection +
                '}';
    }

    public Work_Pojo(int id, int customer_Id, Work_Type_ENUM workType, String explanation, int amount, int collection) {
        this.id = id;
        this.customer_Id = customer_Id;
        this.workType = workType;
        this.explanation = explanation;
        this.amount = amount;
        this.collection = collection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public Work_Type_ENUM getWorkType() {
        return workType;
    }

    public void setWorkType(Work_Type_ENUM workType) {
        this.workType = workType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public Work_Pojo() {
    }



}
