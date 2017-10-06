package statistics;

import java.util.ArrayList;

public class JackKnife {
    public static ArrayList<Double> blockKnife(ArrayList<Double> input) {
        ArrayList<Double> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            double avg = 0.;
            for (int j = 0; j < input.size(); j++) {
                if (j == i) continue;
                avg += input.get(j);
            }
            avg /= (input.size() - 1);
            output.add(avg);
        }
        return output;
    }
}
