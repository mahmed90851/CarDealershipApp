package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private static final ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            double price = vehicle.getPrice();
            if (price >= min && price <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            int year = vehicle.getYear();
            if (year >= min && year <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
       List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByMileage(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            double mileage = vehicle.getOdometer();
            if (mileage >= min && mileage <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }


    public static void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
}
