import java.util.Arrays;

public class Optimizer {
    private int[][] capacity;
    private float[][] cost;
    private int[][] flow;
    private int numberNodes;
    private boolean[] found;
    private int[] demand;
    private float[] picked;
    private float[] distance;

    static final int INF = Integer.MAX_VALUE / 2 - 1;

    boolean isFlowFromSrcToSink(int src, int sink) {
        Arrays.fill(found, false);
        Arrays.fill(distance, INF);

        distance[src] = 0;

        while (src != numberNodes) {

            int best = numberNodes;
            found[src] = true;

            for (int i = 0; i < numberNodes; i++) {
                if (found[i]) {
                    continue;
                }

                if (flow[i][src] != 0) {
                    float value = distance[src] + picked[src] - picked[i] - cost[i][src];
                    if (distance[i] > value) {
                        distance[i] = value;
                        demand[i] = src;
                    }
                }

                if (flow[src][i] < capacity[src][i]) {
                    float value = distance[src] + picked[src] - picked[i] + cost[src][i];
                    if (distance[i] > value) {
                        distance[i] = value;
                        demand[i] = src;
                    }
                }

                if (distance[i] < distance[best]) {
                    best = i;
                }

            }
            src = best;
        }

        for (int i = 0; i < numberNodes; i++) {
            picked[i] = Math.min(picked[i] + distance[i], INF);
        }
        return found[sink];
    }

    MyResults getFlowAndTotalCost(int[][] capacity, float[][] cost) {
        this.capacity = capacity;
        this.cost = cost;

        numberNodes = cost.length;
        found = new boolean[numberNodes];
        flow = new int[numberNodes][numberNodes];
        distance = new float[numberNodes + 1];
        demand = new int[numberNodes];
        picked = new float[numberNodes];

        float totalCost = 0;
        int src = 0;
        int sink = numberNodes - 1;
        while (isFlowFromSrcToSink(src, sink)) {

            int amount = INF;
            for (int x = sink; x != src; x = demand[x]) {
                amount = Math.min(amount, flow[x][demand[x]] != 0 ? flow[x][demand[x]]
                        : capacity[demand[x]][x] - flow[demand[x]][x]);
            }

            for (int x = sink; x != src; x = demand[x]) {
                if (flow[x][demand[x]] != 0) {
                    flow[x][demand[x]] -= amount;
                    totalCost -= amount * cost[x][demand[x]];
                } else {
                    flow[demand[x]][x] += amount;
                    totalCost += amount * cost[demand[x]][x];
                }
            }
        }
        return new MyResults(flow, totalCost);
    }

}

