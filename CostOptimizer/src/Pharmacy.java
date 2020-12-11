public class Pharmacy implements Comparable<Pharmacy> {
    private final int id;
    private final String name;
    private final int dailyDemand;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDailyDemand() {
        return dailyDemand;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dailyDemand=" + dailyDemand +
                '}';
    }

    public Pharmacy(int id, String name, int dailyDemand) {
        this.id = id;
        this.name = name;
        this.dailyDemand = dailyDemand;
    }

    public Pharmacy(String[] param) {
        try {
            this.id = Integer.parseInt(param[0].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[0].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

        this.name = param[1].trim();

        try {
            this.dailyDemand = Integer.parseInt(param[2].trim());
            if (dailyDemand < 0) {
                throw new IllegalArgumentException("Niedopuszczalna wartość ujemna dziennego zapotrzebowania" +
                        " apteki w pliku wejśćiowym: \"" + dailyDemand + "\".");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[2].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

    }

    @Override
    public int compareTo(Pharmacy o) {
        return this.getId().compareTo(o.getId());
    }

}
