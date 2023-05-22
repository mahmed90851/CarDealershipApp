package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    private String fileName;

    public ContractDataManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(contract.getPersistenceString()+"\n");
            System.out.println("Contract saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving contract: ");
        }
    }
}
