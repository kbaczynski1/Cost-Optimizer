public class Manufacturer implements Comparable<Manufacturer> {
    private final int id;
    private final String name;
    private final int dailyProduction;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDailyProduction() {
        return dailyProduction;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dailyProduction=" + dailyProduction +
                '}';
    }

    public Manufacturer(int id, String name, int dailyProduction) {
        this.id = id;
        this.name = name;
        this.dailyProduction = dailyProduction;
    }

    public Manufacturer(String[] param) {
        try {
            this.id = Integer.parseInt(param[0].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[0].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

        this.name = param[1].trim();

        try {
            this.dailyProduction = Integer.parseInt(param[2].trim());
            if (dailyProduction < 0) {
                throw new IllegalArgumentException("Niedopuszczalna wartość ujemna dziennej produkcji producenta" +
                        " w pliku wejśćiowym: \"" + dailyProduction + "\".");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[2].trim() + "\". Oczekiwana liczba typu 'int'.");

        }

    }

    @Override
    public int compareTo(Manufacturer o) {
        return this.getId().compareTo(o.getId());
    }

}
