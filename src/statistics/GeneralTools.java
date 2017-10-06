package statistics;

import java.util.ArrayList;

public class GeneralTools {
    public static Double average(ArrayList<Double> table) {
        return sum(table) / table.size();
    }

    private static Double sum(ArrayList<Double> table) {
        Double sum = 0.;
        for (int i = 0; i < table.size(); i++) {
            sum += table.get(i);
        }
        return sum;
    }

    private static ArrayList<Double> squareTable(ArrayList<Double> table) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            result.add(Math.pow(table.get(i),2));
        }
        return result;
    }

    public static Double sgm(ArrayList<Double> table) {
        int N = table.size();
        return Math.sqrt((N * sum(squareTable(table)) - Math.pow(sum(table), 2)) / N / (N - 1));
    }

}
