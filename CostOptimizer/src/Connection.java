public class Connection implements Comparable<Connection> {
    private final int idManufacturer;
    private final int idPharmacy;
    private final int dailyMaxVaccines;
    private final float vaccineCost;

    public Integer getIdManufacturer() {
        return idManufacturer;
    }

    public Integer getIdPharmacy() {
        return idPharmacy;
    }

    public int getDailyMaxVaccines() {
        return dailyMaxVaccines;
    }

    public float getVaccineCost() {
        return vaccineCost;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "idManufacturer=" + idManufacturer +
                ", idPharmacy=" + idPharmacy +
                ", dailyMaxVaccines=" + dailyMaxVaccines +
                ", vaccineCost=" + vaccineCost +
                '}';
    }

    public Connection(int idManufacturer, int idPharmacy, int dailyMaxVaccines, float vaccineCost) {
        this.idManufacturer = idManufacturer;
        this.idPharmacy = idPharmacy;
        this.dailyMaxVaccines = dailyMaxVaccines;
        this.vaccineCost = vaccineCost;
    }

    public Connection(String[] param) {
        try {
            this.idManufacturer = Integer.parseInt(param[0].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[0].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

        try {
            this.idPharmacy = Integer.parseInt(param[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[1].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

        try {
            this.dailyMaxVaccines = Integer.parseInt(param[2].trim());
            if (dailyMaxVaccines < 0) {
                throw new IllegalArgumentException("Niedopuszczalna wartość ujemna " +
                        "dziennej maksymalnej liczby dostarczanych szczepionek\n od producenta z id="
                        + idManufacturer + " do apteki z id=" + idPharmacy + " w pliku wejśćiowym: \""
                        + dailyMaxVaccines + "\".");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[2].trim() + "\". Oczekiwana liczba typu 'int'.");
        }

        try {
            this.vaccineCost = Float.parseFloat(param[3].trim());
            String vaccineCostInString = Float.toString(vaccineCost);
            if (vaccineCost < 0) {
                throw new IllegalArgumentException("Niedopuszczalna wartość ujemna kosztu szczepionki [zł]\n" +
                        " od producenta z id=" + idManufacturer + " dla apteki z id=" + idPharmacy
                        + " w pliku wejśćiowym: \"" + vaccineCostInString + "\".");
            }

            int integerPlaces = vaccineCostInString.indexOf('.');
            int decimalPlaces = vaccineCostInString.length() - integerPlaces - 1;
            if (decimalPlaces > 2) {
                throw new IllegalArgumentException("Zbyt dużo cyfr po przecinku w koszcie szczepionki [zł]\n" +
                        " od producenta z id=" + idManufacturer + " dla apteki z id=" + idPharmacy
                        + " w pliku wejśćiowym: \"" + vaccineCostInString + "\". Dopuszczalne są maksymalnie" +
                        " dwie cyfry po przecinku.");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Nieprawidłowy typ danych w pliku wejściowym: \""
                    + param[3].trim() + "\". Oczekiwana liczba typu 'float'.");
        }
    }

    @Override
    public int compareTo(Connection o) {
        int comparisonByManufacturerID = this.getIdManufacturer().compareTo(o.getIdManufacturer());
        if (comparisonByManufacturerID == 0) {
            return this.getIdPharmacy().compareTo(o.getIdPharmacy());
        } else {
            return comparisonByManufacturerID;
        }
    }
}
