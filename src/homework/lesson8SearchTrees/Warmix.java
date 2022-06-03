package homework.lesson8SearchTrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Warmix {

    static int INFINITY = (int) Double.POSITIVE_INFINITY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int[] mint = new int[100001];
        int[] mintprev = new int[100001];

        for (int i = 1; i < mint.length; i++) {
            mint[i] = INFINITY;
            mintprev[i] = INFINITY;
        }

        int N = Integer.parseInt(s[0]), //кількість місій
                K = Integer.parseInt(s[1]);  //кількість очок, які потрібно набрати, щоб відкрити бій з босом

        int sumPoints = 0;

        for (int i = 0; i < N; i++) {
            s = br.readLine().split(" ");

            int a = Integer.parseInt(s[0]), //кількість очок
                    t = Integer.parseInt(s[1]); //час

            sumPoints += a;

            if (mint[1] == INFINITY) {
                for (int j = 1; j <= a; j++) mint[j] = t;
            } else {
                for (int j = 1; j <= sumPoints; j++) {
                    if (j <= a) mint[j] = Math.min(mintprev[j], t);
                    else mint[j] = Math.min(mintprev[j], mintprev[j - a] + t);
                }
            }
            mintprev = mint.clone();
        }

        if (mint[K] != INFINITY) System.out.println(mint[K]);
        else System.out.println("-1");
    }
}
/*
4 10
5 5
3 1
10 10
7 6


res - 7
-----------------------------

4 12
4 2
5 3
3 1
3 1

res - 6


 */