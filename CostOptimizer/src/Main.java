import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new InputMismatchException("Nieprawidłowa liczba argumnentów wejścowych. Jako argument wejściowy\n " +
                    "podaj tylko nazwę pliku wejściowego, umieszczonego w lokalizacji programu 'CostOptimizer.jar'.");
        }

        DataReader dataReader = new DataReader();
        dataReader.readFile(args[0]);

        Set<Manufacturer> manufacturers = dataReader.getManufacturers();
        Set<Pharmacy> pharmacies = dataReader.getPharmacies();
        Set<Connection> connections = dataReader.getConnections();

        int numberManufacturers = manufacturers.size();
        int numberPharmacies = pharmacies.size();
        int totalNodes = numberManufacturers + numberPharmacies + 2;

        int[][] capacity = new int[totalNodes][totalNodes];
        float[][] cost = new float[totalNodes][totalNodes];

        int j = 1;
        for (Manufacturer manufacturer : manufacturers) {
            capacity[0][j] = manufacturer.getDailyProduction();
            j++;
        }

        int i = numberManufacturers + 1;
        for (Pharmacy pharmacy : pharmacies) {
            capacity[i][totalNodes - 1] = pharmacy.getDailyDemand();
            i++;
        }

        i = 1;
        j = numberManufacturers + 1;
        int iReverse = numberManufacturers + 1;
        int jReverse = 1;
        float vaccineCost;
        for (Connection connection : connections) {
            capacity[i][j] = connection.getDailyMaxVaccines();
            vaccineCost = connection.getVaccineCost();
            cost[i][j] = vaccineCost;
            cost[iReverse][jReverse] = -vaccineCost;
            j++;
            iReverse++;
            if (j == totalNodes - 1) {
                i++;
                j = numberManufacturers + 1;
                jReverse++;
                iReverse = numberManufacturers + 1;
            }
        }

        Optimizer optimizer = new Optimizer();
        MyResults flowAndTotalCost =
                optimizer.getFlowAndTotalCost(capacity, cost);
        int[][] flow = flowAndTotalCost.getFlow();
        float totalCost = flowAndTotalCost.getTotalCost();

        String filePath = "output.txt";
        PrintWriter printWrite = new PrintWriter(filePath);

        Locale.setDefault(Locale.US);

        i = 1;
        j = numberManufacturers + 1;
        for (Pharmacy pharmacy : pharmacies) {
            for (Manufacturer manufacturer : manufacturers) {
                if (flow[i][j] > 0) {
                    printWrite.printf("%s -> %s [Koszt = %d * %s = %s zł]\n", manufacturer.getName(),
                            pharmacy.getName(), flow[i][j], cost[i][j], flow[i][j] * cost[i][j]);
                }
                if (i == numberManufacturers) {
                    i = 1;
                    j++;
                } else {
                    i++;
                }
            }
        }

        printWrite.printf("Opłaty całkowite: %s zł", totalCost);
        printWrite.close();

    }

}
