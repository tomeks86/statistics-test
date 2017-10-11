package statistics;

import java.util.ArrayList;
import java.util.Locale;

import static statistics.Blocking.block;
import static statistics.FileIO.arrayListToString;
import static statistics.FileIO.readArray;
import static statistics.FileIO.writeString;
import static statistics.GeneralTools.average;
import static statistics.GeneralTools.sgm;

public class Doer {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        String file = args[0];
        int blockSize = Integer.parseInt(args[1]);
        ArrayList<Double> data = readArray(file, 2);
        ArrayList<Double> blocked = block(data, blockSize);
        double avgB = average(blocked);
        double sgmB = sgm(blocked);
        /*ArrayList<Double> jackB = blockKnife(blocked);
        double avgJ = average(jackB);
        double sgmJ = sgmJack(jackB);*/

        System.out.printf("%6.2f %6.2f", avgB, sgmB);

        writeString(file.substring(0, file.lastIndexOf('.')) + "_blocked.dat", arrayListToString(blocked));
        /*System.out.printf("blocked results:   average: %8.3f sigma: %8.3f%n", avgB, sgmB);
        writeString(file.substring(0, file.lastIndexOf('.')) + "_jackknifed.dat", arrayListToString(jackB));
        System.out.printf("JackKnife resuts:  average: %8.3f sigma: %8.3f%n", avgJ, sgmJ);*/
    }
}
