import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DataReader dataReader = new DataReader();
        dataReader.readFile("C:\\Users\\Laptop\\Downloads\\data.txt");
        System.out.println(dataReader.getManufacturers());
        System.out.println(dataReader.getPharmacies());
        System.out.println(dataReader.getConnections());
        //ArrayList<Manufacturer> manufacturers = dataReader.getManufacturers();
        //System.out.println(manufacturers.get(0).getName());
    }
}
