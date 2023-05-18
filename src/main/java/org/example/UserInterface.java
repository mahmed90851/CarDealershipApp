package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private DealershipFileManager fileManager;
    Dealership dealership;
    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        dealership = fileManager.getDealership();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void printHeader(){
        System.out.println("VIN              YEAR        MAKE             MODEL            TYPE             COLOR       ODOMETER         PRICE");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void displayVehicles(ArrayList<Vehicle> inventory) {
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
                case 0 -> {
                    exit=true ;
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

        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(minPrice, maxPrice) ;
        System.out.println("Vehicles by Price:");
        displayVehicles(aList);
        System.out.println();
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        System.out.println("Vehicles by Make and Model:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model) ;

            displayVehicles(aList);

        System.out.println();
    }


    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        System.out.println("Vehicles by Year:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(minYear, maxYear) ;

            displayVehicles(aList);

        System.out.println();
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        System.out.println("Vehicles by Color:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color) ;

            displayVehicles(aList);

        System.out.println();
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();

        System.out.println("Vehicles by Mileage:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(minMileage, maxMileage) ;

            displayVehicles(aList);

        System.out.println();
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();

        System.out.println("Vehicles by Vehicle Type:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(vehicleType) ;

            displayVehicles(aList);

        System.out.println();
    }


    public void processGetAllVehiclesRequest() {
        ArrayList aList = (ArrayList) dealership.getAllVehicles() ;
            displayVehicles(aList);


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
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = scanner.nextInt();
        for(Vehicle i : dealership.getAllVehicles()){
            if(i.getVin() == vin) {
                System.out.println("Vehicle has been removed");
                v = i;
                break;
            }
        }
        dealership.removeVehicle(v);
        fileManager.saveDealership(dealership);
    }
}
