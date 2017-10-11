package statistics;

import java.util.*;

public class MeanMedianMode {
    static String mean(int[] tab) {
        double sum = 0.;
        for (int i = 0; i < tab.length; i++) {
            sum += tab[i];
        }
        return String.format("%.1f", sum / tab.length);
    }

    static String median(int[] tab) {
        int n = tab.length;
        Arrays.sort(tab);
        //System.out.println(Arrays.toString(tab));
        if (n % 2 == 1) return String.valueOf(tab[n / 2]);
        else return String.format("%.1f", (tab[n / 2 - 1] + tab [n / 2]) / 2.);
    }

    static String mode(int[] tab) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i : tab) {
            freq.put(i, (freq.get(i) == null ? 1 : freq.get(i) + 1));
        }
        int maxFreq = Collections.max(freq.values());
        Integer min = null;
        for (Integer i : freq.keySet()) {
            if (min == null && freq.get(i) == maxFreq) min = i;
            else if (freq.get(i) == maxFreq && i < min) min = i;
        }
        return String.format("%d", min);
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        int[] tab = {1, 1, 3, 1, 3, 423, 3, -5, 32, 2};
        System.out.println(mean(tab));
        System.out.println(median(tab));
        System.out.println(mode(tab));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //int[] tab = new int[n];
        tab = new int[n];
        for(int i = 0; i < n; i++) {
            tab[i] = sc.nextInt();
        }
        System.out.println(mean(tab));
        System.out.println(median(tab));
        System.out.println(mode(tab));
    }
}
