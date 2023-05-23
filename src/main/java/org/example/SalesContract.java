package org.example;

public class SalesContract extends Contract {
    private final double salesTaxRate = 0.05;
    private final double recordingFee = 100.00;
    private double processingFee;
    private boolean financeOption;

    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold,
                         double v, double parseDouble, double aDouble, boolean financeOption) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295.00 : 495.00;
        this.financeOption = financeOption;
    }

    public double getSalesTaxAmount() {
        return getVehicleSold().getPrice() * salesTaxRate;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanceOption() {
        return financeOption;
    }

    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double totalPrice = vehiclePrice + getSalesTaxAmount() + recordingFee + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (financeOption) {
            double vehiclePrice = getVehicleSold().getPrice();
            if (vehiclePrice >= 10000) {
                double interestRate = 0.0425; // 4.25%
                int loanTerm = 48;
                double monthlyInterestRate = interestRate / 12;
                double loanAmount = vehiclePrice;
                double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
                return monthlyPayment;
            } else {
                double interestRate = 0.0525; // 5.25%
                int loanTerm = 24;
                double monthlyInterestRate = interestRate / 12;
                double loanAmount = vehiclePrice;
                double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
                return monthlyPayment;
            }
        } else {
            return 0.0; // No financing option chosen, return 0 as monthly payment
        }
    }

    @Override
    public String getPersistenceString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SALE").append("|");
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
        builder.append(getSalesTaxAmount()).append("|");
        builder.append(getRecordingFee()).append("|");
        builder.append(getProcessingFee()).append("|");
        builder.append(getTotalPrice()).append("|");
        builder.append(isFinanceOption() ? "YES" : "NO").append("|");
        builder.append(getMonthlyPayment()).append("|");

        return builder.toString();
    }
}
