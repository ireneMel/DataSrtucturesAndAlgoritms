package homework.lesson1Percolation;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        int length, maxLength, i, j;
        while (in.hasNext()) {
            i = in.nextInt();
            j = in.nextInt();
            maxLength = 0;
            for (int a = Math.min(i, j); a <= Math.max(i, j); a++) {
                length = countCycleLength(a);
                if (length > maxLength) maxLength = length;
            }
            out.printf("%d %d %d\n", i, j, maxLength);
        }

    }

    private static int countCycleLength(int n) {
        int cnt = 1;
        while (n != 1) {
            if (n % 2 != 0) n = 3 * n + 1;
            else n /= 2;
            cnt++;
        }
        return cnt;
    }
}
