import java.util.ArrayList;

public class Connection {
    private int idManufacturer;
    private int idPharmacy;
    private int dailyMaxVaccines;
    private double vaccineCost;

    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public void setDailyMaxVaccines(int dailyMaxVaccines) {
        this.dailyMaxVaccines = dailyMaxVaccines;
    }

    public void setVaccineCost(double vaccineCost) {
        this.vaccineCost = vaccineCost;
    }

    public int getIdManufacturer() {
        return idManufacturer;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public int getDailyMaxVaccines() {
        return dailyMaxVaccines;
    }

    public double getVaccineCost() {
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

    public Connection(int idManufacturer, int idPharmacy, int dailyMaxVaccines, double vaccineCost) {
        this.idManufacturer = idManufacturer;
        this.idPharmacy = idPharmacy;
        this.dailyMaxVaccines = dailyMaxVaccines;
        this.vaccineCost = vaccineCost;
    }

    public Connection(ArrayList<String> param) {
        this.idManufacturer = Integer.parseInt(param.get(0).trim());
        this.idPharmacy = Integer.parseInt(param.get(1).trim());
        this.dailyMaxVaccines = Integer.parseInt(param.get(2).trim());
        this.vaccineCost = Double.parseDouble(param.get(3).trim());
    }

}
