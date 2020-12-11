import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.*;

public class DataReader {
    private final Set<Manufacturer> manufacturers;
    private final Set<Pharmacy> pharmacies;
    private final Set<Connection> connections;

    public Set<Manufacturer> getManufacturers() {
        if (manufacturers.isEmpty()) {
            throw new Error("Set is empty.");
        }
        return manufacturers;
    }

    public Set<Pharmacy> getPharmacies() {
        if (pharmacies.isEmpty()) {
            throw new Error("Set is empty.");
        }
        return pharmacies;
    }

    public Set<Connection> getConnections() {
        if (connections.isEmpty()) {
            throw new Error("Set is empty.");
        }
        return connections;
    }

    public DataReader() {
        this.manufacturers = new TreeSet<>();
        this.pharmacies = new TreeSet<>();
        this.connections = new TreeSet<>();
    }

    public void ifAreAllConnections() throws Exception {
        for (Manufacturer manufacturer : manufacturers) {
            for (Pharmacy pharmacy : pharmacies) {
                if (!connections.contains(new Connection(
                        manufacturer.getId(), pharmacy.getId(), 0, 0))) {
                    throw new Exception("Połączenie producenta z id=" + manufacturer.getId() + " i apteki z id="
                            + pharmacy.getId() + " nie zostało zdefiniowane w pliku wejściowym w sekcji\n" +
                            " \"Połączenia...\". Połączenie każdego producenta z każdą apteką musi być zdefiniowane.");
                }
            }
        }
    }

    public void readFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Nie znaleziono pliku wejściowego o nazwie: \""
                    + filePath + "\".");
        } else if (file.length() == 0) {
            throw new FileSystemException("Plik wejściowy: \"" + filePath + "\" jest pusty.");
        }

        int whichSection = 0;
        int lineCount = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            lineCount++;

            if (line.isEmpty()) {
                throw new Exception("Pusta linia: " + lineCount + ". W pliku wejściowym nie może być pustych lini.");
            }

            if (line.charAt(0) == '#') {
                whichSection++;
            } else {
                if (whichSection == 0) {
                    throw new Exception("Brak tytułu pierwszej sekcji - \"Producenci...\", linia: " + lineCount +
                            ". Tytuł musi zawierać co najmniej znak: '#'.");
                }

                String[] parsedLine = line.split("\\|", -1);

                int pipeCount = parsedLine.length - 1;
                if (pipeCount != 2 && (whichSection == 1 || whichSection == 2)) {
                    throw new IllegalAccessException("Nieprawidłowa liczba znaków: '|' (x" + pipeCount + ") w linii: "
                            + lineCount +
                            ".\n Liczba znaków '|' w tej linii powinna wynosić dwa.");
                } else if (pipeCount != 3 && whichSection == 3) {
                    throw new IllegalAccessException("Nieprawidłowa liczba znaków '|': (x" + pipeCount + ") w linii: "
                            + lineCount +
                            ".\n Liczba znaków '|' w tej linii powinna wynosić trzy.");
                }

                switch (whichSection) {
                    case 1:
                        Manufacturer manufacturer = new Manufacturer(parsedLine);
                        if (this.manufacturers.contains(manufacturer)) {
                            throw new IllegalAccessException("Producent z id="
                                    + manufacturer.getId() + " już istnieje, linia: " + lineCount);
                        }
                        this.manufacturers.add(manufacturer);
                        break;
                    case 2:
                        if (manufacturers.isEmpty()) { // sprawdzenie czy poprzednia sekcja - Producenci jest pusta
                            throw new Exception("Sekcja \"Producenci...\" jest pusta.");
                        }

                        Pharmacy pharmacy = new Pharmacy(parsedLine);
                        if (this.pharmacies.contains(pharmacy)) {
                            throw new IllegalAccessException("Apteka z id=" + pharmacy.getId()
                                    + " już istnieje, linia: " + lineCount);
                        }
                        this.pharmacies.add(pharmacy);
                        break;
                    case 3:
                        if (pharmacies.isEmpty()) { // sprawdzenie czy poprzednia sekscja - Apteki jest pusta
                            throw new Exception("Sekcja \"Apteki\" jest pusta.");
                        }

                        Connection connection = new Connection(parsedLine);
                        if (this.connections.contains(connection)) {
                            throw new IllegalAccessException("Połączenie producenta z id="
                                    + connection.getIdManufacturer() + " i apteki z id="
                                    + connection.getIdPharmacy() + " już istnieje, linia: " + lineCount);
                        }

                        if (!this.manufacturers.contains(
                                new Manufacturer(connection.getIdManufacturer(), "", 0))) {
                            throw new IllegalAccessException("Producent z id="
                                    + connection.getIdManufacturer() +
                                    " użyty w sekcji \"Połączenia...\" nie istnieje, ponieważ nie został" +
                                    " zdefiniowany\n w sekcji \"Producenci...\", linia: " + lineCount);
                        }

                        if (!this.pharmacies.contains(
                                new Pharmacy(connection.getIdPharmacy(), "", 0))) {
                            throw new IllegalAccessException("Apteka z id="
                                    + connection.getIdPharmacy() +
                                    " użyta w sekcji \"Połączenia...\" nie istnieje, ponieważ nie została" +
                                    " zdefiniowana\n w sekcji \"Apteki\", linia: " + lineCount);
                        }
                        this.connections.add(connection);
                        break;
                }
            }
        }
        if (connections.isEmpty()) { // sprawdzenie czy ostatnia sekcja - Połączenia jest pusta
            throw new Exception("Sekcja \"Połączenia...\" jest pusta.");
        }
        ifAreAllConnections();
    }

}
