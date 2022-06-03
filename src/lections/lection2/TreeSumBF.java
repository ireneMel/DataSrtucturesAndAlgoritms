package lections.lection2;

import java.io.*;

import princeton.lib.In;
import princeton.lib.Stopwatch;


public class TreeSumBF {
	private static final String testFile = "8ints.txt"; // 8 ����
	private static final String testFile2 = "1Kints.txt"; //1000 ����
	private static final String testFile3 = "2Kints.txt"; //2000 ����
	private static final String testFile4 = "4Kints.txt";
	private static final String testFile5 = "8Kints.txt";
	
	public static int count(int[] a){
		
		int n = a.length;
		int count = 0;
		for (int i =0; i<n; i++)
			for (int j = i+1; j<n;j++)
				for (int k = j+1;k<n;k++)
					if (a[i]+a[j]+a[k] == 0)
						count++;
		return count;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {

		int[] a = In.readInts(testFile5);
		System.out.println(testFile5);
		Stopwatch stopwatch = new Stopwatch();
		System.out.println(count(a));
		double time = stopwatch.elapsedTime();
		System.out.println("���������� ��� "+time);
	}

}
