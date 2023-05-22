package org.example;

public abstract class Contract {
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String getPersistenceString();
}

