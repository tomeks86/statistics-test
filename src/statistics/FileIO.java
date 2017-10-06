package statistics;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public static ArrayList<Double> readArray(String file, int col) {
        ArrayList<Double> st = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                st.add(Double.parseDouble(line.replaceAll("^\\s+", "").split("\\s+")[col - 1]));
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
        return st;
    }

    public static void writeString(String file, String content) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(file)));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String arrayListToString(ArrayList<Double> list){
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%8d %8.3f%n", (i + 1), list.get(i)));
        }
        return sb.toString();
    }
}
