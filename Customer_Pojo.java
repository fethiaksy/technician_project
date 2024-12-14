package D34_Proje;

public class Customer_Pojo {
    private int customerId; // unique, starting at 1 and has to be automatically incremented by 1 on each client.
    private String name;
    private String tel;
    private String address;
    private int balance;  // do not use this area for writing and reading only use it just for reporting!
    public Customer_Pojo(int customerId, String name, String tel, String address) {
        this.customerId = customerId;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBalance() {
        return balance;
    }


    public void setBalance(int balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Customers_Pojo{" +
                "mId=" + customerId +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
