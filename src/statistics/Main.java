package statistics;

import java.io.*;
import java.util.ArrayList;

public class Main {

    private static Double average(ArrayList<Double> table) {
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

    private static Double sgm(ArrayList<Double> table) {
        int N = table.size();
        return Math.sqrt((N * sum(squareTable(table)) - Math.pow(sum(table),2)) / N / (N - 1));
    }

    private static ArrayList<Double> block(ArrayList<Double> table, int M) {
        int K = table.size() / M;
        ArrayList<Double> blocked = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            ArrayList<Double> blockI = new ArrayList<Double>(table.subList(i*M, (i+1)*M));
            blocked.add(average(blockI));
        }
        return blocked;
    }

    public static void main(String[] args) {

        ArrayList<Double> stAll1fs = new ArrayList<>();
        ArrayList<Double> stAll100fs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("nvt1fs.dat")))) {
            String line;
            while ((line = br.readLine()) != null) {
                stAll1fs.add(Double.parseDouble(line.split("\\s+")[1]));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File("nvt100fs.dat")))) {
            String line;
            while ((line = br.readLine()) != null) {
                stAll100fs.add(Double.parseDouble(line.split("\\s+")[1]));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }


        //System.out.printf("%d, %.3f %.3f%n", stAll1fs.size(), average(stAll1fs), sgm(stAll1fs));
        System.out.printf("%d 1fs: %.3f %.3f 100fs: %.3f %.3f%n", 1, average(stAll1fs), sgm(stAll1fs), average(stAll100fs), sgm(stAll100fs));
        int k = 1;
        for (int i = k; i < 100; i += k) {
            ArrayList<Double> blocked1fs = block(stAll1fs, i);
            //System.out.printf("%d, %.3f %.3f%n", blocked.size(), average(blocked), sgm(blocked));
            //System.out.printf("%d %.3f %.3f%n", i, average(blocked1fs), sgm(blocked1fs));
            ArrayList<Double> blocked100fs = block(stAll100fs, i);
            //System.out.printf("%d 1fs: %.3f %.3f 100fs: %.3f %.3f%n", i, average(blocked1fs), sgm(blocked1fs), average(blocked100fs), sgm(blocked100fs));
            //System.out.printf("%d 1fs: %.3f 100fs: %.3f%n", i, sgm(blocked1fs)*Math.sqrt(blocked1fs.size()), sgm(blocked100fs)*Math.sqrt(blocked100fs.size()));
            System.out.printf("%d 1fs: %.3f 100fs: %.3f%n", i, sgm(blocked1fs)*Math.sqrt(i), sgm(blocked100fs)*Math.sqrt(i));
        }
    }
}
