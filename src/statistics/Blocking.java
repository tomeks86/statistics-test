package statistics;

import java.util.ArrayList;

import static statistics.GeneralTools.average;

public class Blocking {
    public static ArrayList<Double> block(ArrayList<Double> table, int M) {
        int K = table.size() / M;
        ArrayList<Double> blocked = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            ArrayList<Double> blockI = new ArrayList<Double>(table.subList(i*M, (i+1)*M));
            blocked.add(average(blockI));
        }
        return blocked;
    }
}
