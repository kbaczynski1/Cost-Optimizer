import java.util.ArrayList;

public class Pharmacy {
    private int id;
    private String name;
    private int dailyDemand;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyDemand(int dailyDemand) {
        this.dailyDemand = dailyDemand;
    }

    public int getId() {
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

    public Pharmacy(ArrayList<String> param) {
        this.id = Integer.parseInt(param.get(0).trim());
        this.name = param.get(1).trim();
        this.dailyDemand = Integer.parseInt(param.get(2).trim());
    }

}
