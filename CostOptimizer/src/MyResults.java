public class MyResults {
    private final int[][] flow;
    private final float totalCost;

    public MyResults(int[][] flow, float totalCost) {
        this.flow = flow;
        this.totalCost = totalCost;
    }

    public int[][] getFlow() {
        return flow;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
