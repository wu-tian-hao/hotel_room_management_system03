package model;

public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private String idCard;

    public Customer() {
    }

    public Customer(int customerId, String name,
                    String phone, String idCard) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.idCard = idCard;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    @Override
    public String toString() {
        return name + " (ID:" + customerId + ")";
    }
}