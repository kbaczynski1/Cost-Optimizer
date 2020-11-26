import java.util.ArrayList;

public class Manufacturer {
    private int id;
    private String name;
    private int dailyProduction;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyProduction(int dailyProduction) {
        this.dailyProduction = dailyProduction;
    }

    public int getId() {
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

    public Manufacturer(ArrayList<String> param) {
        this.id = Integer.parseInt(param.get(0).trim());
        this.name = param.get(1).trim();
        this.dailyProduction = Integer.parseInt(param.get(2).trim());
    }

}
