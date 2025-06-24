package repository;

import model.*;
import utils.CustomException;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacilityRepository implements IFacilityRepository {
    private static final String FILE_PATH = "/data/facility.txt";

    public FacilityRepository() {
        File file = new File(path + FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Đã tạo file facility.txt mới.");
            } catch (IOException e) {
                System.err.println("Lỗi khi tạo file facility.txt: " + e.getMessage());
            }
        }
    }

    @Override
    public void writeToFile(Map<Facility, Integer> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + FILE_PATH))) {
            for (Map.Entry<Facility, Integer> entry : entities.entrySet()) {
                writer.write(entry.getKey().toString() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ghi file thất bại: " + e.getMessage());
        }
    }


    @Override
    public Map<Facility, Integer> readFromFile() {
        Map<Facility, Integer> map = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path+FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\,");
                if ((line.startsWith("SVVL") && parts.length < 9) ||
                        (line.startsWith("SVHO") && parts.length < 8) ||
                        (line.startsWith("SVRO") && parts.length < 7)) {
                    System.out.println(" Dòng lỗi: " + line);
                    continue;
                }

                String serviceCode = parts[0].trim();
                String serviceName = parts[1].trim();
                double usableArea = Double.parseDouble(parts[2].trim());
                double rentalCost = Double.parseDouble(parts[3].trim());
                int maxPeople = Integer.parseInt(parts[4].trim());
                String Type = parts[5].trim();

                Facility facility = null;
                if (serviceCode.startsWith("SVVL")) {
                    String standard = parts[6];
                    double poolArea = Double.parseDouble(parts[7].trim());
                    int floors = Integer.parseInt(parts[8].trim());
                    facility = new Villa(serviceCode, serviceName, usableArea, rentalCost,
                            maxPeople, Type, standard, poolArea, floors);
                } else if (serviceCode.startsWith("SVHO")) {
                    String roomStandard = parts[6].trim();
                    int floors = Integer.parseInt(parts[7].trim());
                    facility = new House(serviceCode, serviceName, usableArea, rentalCost,
                            maxPeople, Type, roomStandard, floors);
                } else if (serviceCode.startsWith("SVRO")) {
                    String freeService = parts[6];
                    facility = new Room(serviceCode, serviceName, usableArea, rentalCost,
                            maxPeople, Type, freeService);
                }

                if (facility != null) {
                    map.put(facility, 0);
                }

            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file Facility: " + e.getMessage());
        }
        return  map;
    }
    public void addFacility(Facility facility) {
        Map<Facility, Integer> facilityMap = readFromFile();
        facilityMap.putIfAbsent(facility, 0); // chỉ thêm nếu chưa có
        writeToFile(facilityMap);
    }



}
