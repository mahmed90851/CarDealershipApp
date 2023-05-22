package org.example;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue, double leaseFee) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double totalPrice = vehiclePrice + leaseFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        double vehiclePrice = getVehicleSold().getPrice();
        double interestRate = 0.04; // 4.0%
        int loanTerm = 36;
        double monthlyInterestRate = interestRate / 12;
        double loanAmount = vehiclePrice - expectedEndingValue;
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
        return monthlyPayment;
    }

    @Override
    public String getPersistenceString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LEASE").append("|");
        builder.append(getContractDate()).append("|");
        builder.append(getCustomerName()).append("|");
        builder.append(getCustomerEmail()).append("|");
        builder.append(getVehicleSold().getVin()).append("|");
        builder.append(getVehicleSold().getYear()).append("|");
        builder.append(getVehicleSold().getMake()).append("|");
        builder.append(getVehicleSold().getModel()).append("|");
        builder.append(getVehicleSold().getVehicleType()).append("|");
        builder.append(getVehicleSold().getColor()).append("|");
        builder.append(getVehicleSold().getOdometer()).append("|");
        builder.append(getVehicleSold().getPrice()).append("|");
        builder.append(getExpectedEndingValue()).append("|");
        builder.append(getLeaseFee()).append("|");
        builder.append(getTotalPrice()).append("|");
        builder.append(getMonthlyPayment()).append("|");

        return builder.toString();
    }
}
