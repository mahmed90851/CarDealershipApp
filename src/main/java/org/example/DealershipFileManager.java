package org.example;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    private final String fileName;

    public DealershipFileManager(String fileName) {
        this.fileName = fileName;
    }

    public Dealership getDealership() {
        Dealership dealership = new Dealership("Royalty exotic car dealership", "123 main street", "3478849803");;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufReader = new BufferedReader(fileReader)) {

            String input;
            while ((input = bufReader.readLine()) != null) {
                if (!input.isEmpty()) { // skip empty lines
                    String[] parts = input.split("\\|");
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file: ");
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicle = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
                fileWriter.write(vehicle);
            }
            fileWriter.close();

            System.out.println("Dealership inventory saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving dealership inventory");
        }

    }
}
