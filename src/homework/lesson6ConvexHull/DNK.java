package homework.lesson6ConvexHull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DNK {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        Additional[] ad;
        for (int i = 0; i < t; i++) {
            br.readLine();
            String[] s1 = br.readLine().split(" ");
            int n = Integer.parseInt(s1[0]);
            int m = Integer.parseInt(s1[1]);
            ad = new Additional[m];
            for (int j = 0; j < m; j++) {
                String s = br.readLine();
                int cnt = 0;
                for (int k = 0; k < s.length(); k++) {
                    char ch = s.charAt(k);
                    for (int l = k; l < s.length(); l++) {
                        if (ch > s.charAt(l)) cnt++;
                    }
                }
                ad[j] = new Additional(s, cnt);
            }
            Arrays.sort(ad);
            for (Additional a : ad) {
                System.out.println(a);
            }
            if (i < t - 1) System.out.println();
        }
    }

    private static class Additional implements Comparable<Additional> {
        private final String s;
        private final int n;

        public Additional(String s, int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public int compareTo(Additional that) {
            return Integer.compare(this.n, that.n);
        }

        @Override
        public String toString() {
            return this.s;
        }
    }
}

