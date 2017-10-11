package statistics;

import java.util.ArrayList;

import static statistics.Blocking.block;
import static statistics.FileIO.arrayListToString;
import static statistics.FileIO.readArray;
import static statistics.FileIO.writeString;
import static statistics.GeneralTools.*;
import static statistics.JackKnife.blockKnife;

public class Main {
    public static void main(String[] args) {

        ArrayList<Double> stAll1fs = readArray("nvt1fs.dat", 2);
        ArrayList<Double> stAll100fs = readArray("nvt100fs.dat", 2);
        ArrayList<Double> stDPPC = readArray("dppc.st", 2); //310K simulation, DPPC @ 100A^2 per lipid, output every 5ps, total 50ns

        ArrayList<Double> notBlocked1fs = block(stAll1fs, 1);
        double sgm1fs = sgm(notBlocked1fs);
        ArrayList<Double> notBlocked100fs = block(stAll100fs, 1);
        double sgm100fs = sgm(notBlocked100fs);
        ArrayList<Double> notBlocked5psDPPC = block(stDPPC, 1);
        double sgmDPPC = sgm(notBlocked5psDPPC);

        int k = 1;
        StringBuffer lines = new StringBuffer("");
        for (int i = k; i < 1000; i += k) {
            ArrayList<Double> blocked100fs = block(stAll100fs, i);
            double stat_ineff = i * Math.pow(sgm(blocked100fs) / sgm100fs, 2);
            //System.out.printf("%8d %8.3f%n", i, stat_ineff);
            lines.append(String.format("%8d %8.3f%n", i, stat_ineff));
        }
        writeString("stat_ineff_100fs.dat", lines.toString());

        k = 1000;
        lines = new StringBuffer("");
        for (int i = k; i < 140000; i += k) {
            ArrayList<Double> blocked1fs = block(stAll1fs, i);
            double stat_ineff = i * sgm(blocked1fs) / sgm1fs;
            //System.out.printf("%8d %8.3f%n", i, stat_ineff);
            lines.append(String.format("%8d %8.3f%n", i, stat_ineff));
        }
        writeString("stat_ineff_1fs.dat", lines.toString());

        k = 10;
        lines = new StringBuffer("");
        for (int i = k; i < 5000; i += k) {
            ArrayList<Double> blockedDPPC = block(stDPPC, i);
            double stat_ineff = i * sgm(blockedDPPC) / sgmDPPC;
            lines.append(String.format("%8d %8.3f%n", i, stat_ineff));
        }
        writeString("stat_ineff_DPPC.dat", lines.toString());

        ArrayList<Double> jknDPPC = blockKnife(stDPPC);
        System.out.printf("%8.3f %8.3f", average(jknDPPC), Math.sqrt((stDPPC.size() - 1) / stDPPC.size()) * sgm(jknDPPC));
        writeString("test", arrayListToString(jknDPPC));
    }
}
