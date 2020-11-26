import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataReader {
    ArrayList<Manufacturer> manufacturers;
    ArrayList<Pharmacy> pharmacies;
    ArrayList<Connection> connections;

    public void setManufacturers(ArrayList<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void setPharmacies(ArrayList<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    public ArrayList<Manufacturer> getManufacturers() {
        if (manufacturers.isEmpty()) {
            throw new Error("List is empty");
        }
        return manufacturers;
    }

    public ArrayList<Pharmacy> getPharmacies() {
        if (pharmacies.isEmpty()) {
            throw new Error("List is empty");
        }
        return pharmacies;
    }

    public ArrayList<Connection> getConnections() {
        if (connections.isEmpty()) {
            throw new Error("List is empty");
        }
        return connections;
    }

    public DataReader() {
        this.manufacturers = new ArrayList<Manufacturer>();
        this.pharmacies = new ArrayList<Pharmacy>();
        this.connections = new ArrayList<Connection>();
    }

    public void readFile(String filePath) throws FileNotFoundException {
        int whichList = 0;
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == '#') {
                if (line.startsWith("# Producenci")) {
                    whichList = 0;
                } else if (line.startsWith("# Apteki")) {
                    whichList = 1;
                } else {
                    whichList = 2;
                }
            } else {
                ArrayList<String> parsedLine = new ArrayList<>(Arrays.asList(line.split("[|]")));
                switch (whichList) {
                    case 0:
                        Manufacturer manufacturer = new Manufacturer(parsedLine);
                        this.manufacturers.add(manufacturer);
                        break;
                    case 1:
                        Pharmacy pharmacy = new Pharmacy(parsedLine);
                        this.pharmacies.add(pharmacy);
                        break;
                    case 2:
                        Connection connection = new Connection(parsedLine);
                        this.connections.add(connection);
                        break;
                }
            }
        }
    }

}
