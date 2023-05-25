package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private DealershipFileManager fileManager;
    private ContractDataManager contractDataManager;
    private Dealership dealership;

    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        contractDataManager = new ContractDataManager("contract.csv");
        dealership = fileManager.getDealership();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void printHeader() {
        System.out.println("VIN              YEAR        MAKE             MODEL            TYPE             COLOR       ODOMETER         PRICE");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void displayVehicles(List<Vehicle> inventory) {
        printHeader();
        for (Vehicle vehicle : inventory) {
            System.out.printf("%-15d  %-10d  %-15s  %-15s  %-15s  %-10s  %-15d  %.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }

    public void display() {
        init();
        boolean exit = false;
        while (!exit) {
            System.out.println("------ Dealership Menu ------");
            System.out.println("[1] Get vehicles by price");
            System.out.println("[2] Get vehicles by make and model");
            System.out.println("[3] Get vehicles by year");
            System.out.println("[4] Get vehicles by color");
            System.out.println("[5] Get vehicles by mileage");
            System.out.println("[6] Get vehicles by vehicle type");
            System.out.println("[7] Get all vehicles");
            System.out.println("[8] Add a vehicle");
            System.out.println("[9] Remove a vehicle");
            System.out.println("[10] Create a contract");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processCreateContractRequest();
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting Dealership Menu...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();
        System.out.println("Vehicles by Price:");
        displayVehicles(dealership.getVehiclesByPrice(minPrice, maxPrice));
        System.out.println();
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        System.out.println("Vehicles by Make and Model:");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
        System.out.println();
    }

    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        System.out.println("Vehicles by Year:");
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
        System.out.println();
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();
        System.out.println("Vehicles by Color:");
        displayVehicles(dealership.getVehiclesByColor(color));
        System.out.println();
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();
        System.out.println("Vehicles by Mileage:");
        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));
        System.out.println();
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();
        System.out.println("Vehicles by Vehicle Type:");
        displayVehicles(dealership.getVehiclesByType(vehicleType));
        System.out.println();
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.println("Enter the vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Make: ");
        String make = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.next();
        System.out.print("Color: ");
        String color = scanner.next();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Vehicle added successfully.\n");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle you would like to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public void processCreateContractRequest() {
        System.out.print("Enter VIN of the vehicle: ");
        int vin = scanner.nextInt();

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle != null) {
            System.out.println("Select the contract type:");
            System.out.println("[1] Sales Contract");
            System.out.println("[2] Lease Contract");
            System.out.print("Enter your choice: ");
            int contractTypeChoice = scanner.nextInt();

            if (contractTypeChoice == 1) {
                createSalesContract(vehicle);
            } else if (contractTypeChoice == 2) {
                createLeaseContract(vehicle);
            } else {
                System.out.println("Invalid contract type choice.");
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public void createSalesContract(Vehicle vehicle) {
        System.out.println("Creating Sales Contract...");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String contractDate = currentDate.format(dateFormatter);
        System.out.print("Enter customer name: ");
        String customerName = scanner.next().trim();
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.next().trim();
        double salesTaxAmount = vehicle.getPrice() * 0.05; // Calculate sales tax amount as 5% of the vehicle price
        double recordingFee = 100.00; // Set the recording fee to $100
        double processingFee = (vehicle.getPrice() < 10000) ? 295.00 : 495.00; // Set the processing fee based on vehicle price
        System.out.print("Enter finance option (yes/no): ");
        boolean financeOption = Boolean.parseBoolean(scanner.next());

        // Format prices and costs to display only two decimal places
        String formattedSalesTaxAmount = String.format("%.2f", salesTaxAmount);
        String formattedRecordingFee = String.format("%.2f", recordingFee);
        String formattedProcessingFee = String.format("%.2f", processingFee);

        SalesContract salesContract = new SalesContract(contractDate, customerName, customerEmail, vehicle,
                Double.parseDouble(formattedSalesTaxAmount), Double.parseDouble(formattedRecordingFee),
                Double.parseDouble(formattedProcessingFee), financeOption);
        contractDataManager.saveContract(salesContract);

        dealership.removeVehicle(vehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Sales Contract created and saved.");
    }

    public void createLeaseContract(Vehicle vehicle) {
        System.out.println("Creating Lease Contract...");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String contractDate = currentDate.format(dateFormatter);
        System.out.print("Enter customer name: ");
        String customerName = scanner.next().trim();
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.next().trim();
        double expectedEndingValue = vehicle.getPrice() * 0.5; // Calculate expected ending value as 50% of the vehicle price
        double leaseFee = vehicle.getPrice() * 0.07; // Calculate lease fee as 7% of the vehicle price

        // Format prices and costs to display only two decimal places
        String formattedExpectedEndingValue = String.format("%.2f", expectedEndingValue);
        String formattedLeaseFee = String.format("%.2f", leaseFee);

        LeaseContract leaseContract = new LeaseContract(contractDate, customerName, customerEmail, vehicle,
                Double.parseDouble(formattedExpectedEndingValue), Double.parseDouble(formattedLeaseFee));
        contractDataManager.saveContract(leaseContract);

        dealership.removeVehicle(vehicle);
        fileManager.saveDealership(dealership);

        System.out.println("Lease Contract created and saved.");
    }
}
