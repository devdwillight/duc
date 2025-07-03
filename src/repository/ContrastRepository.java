package repository;

import model.Booking;
import model.Contract;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class ContrastRepository implements IContactRepository {
    private static final String FILE_PATH = "/data/contract.txt";
    static {
        File file = new File(path+FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Không thể tạo file contracts.txt");
            }
        }
    }
    @Override
    public void writeToFile(Queue<Contract> contracts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path+FILE_PATH))) {
            for (Contract contract : contracts) {
                writer.write(contract.getContractID() + "," +
                        contract.getBookingID() + "," +
                        contract.getDepositAmount() + "," +
                        contract.getTotalPayment());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi hợp đồng: " + e.getMessage());
        }
    }

    @Override
    public Queue<Contract> readFromFile() {
        Queue<Contract> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path+FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    list.add(new Contract(parts[0], parts[1],
                            Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc hợp đồng: " + e.getMessage());
        }
        return list;
    }
    public void addContract(Contract contract) {
        Queue<Contract>contracts= readFromFile();
        contracts.add(contract);
        writeToFile(contracts);
    }
    public void updateContract(Contract updated) {
        Queue<Contract> contracts = readFromFile();
        for (Contract contract : contracts) {
            if (contract.getContractID().equals(updated.getContractID())) {
                contract.setDepositAmount(updated.getDepositAmount());
                contract.setTotalPayment(updated.getTotalPayment());
                break;
            }
        }
        writeToFile(contracts);
    }
}
