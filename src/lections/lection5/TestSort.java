package lections.lection5;

import princeton.lib.In;
import princeton.lib.StdOut;

import java.util.HashMap;

public class TestSort {

	private static final String testFile = "words3.txt"; // ���� � �������
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = In.readStrings(testFile);
		MergeSort.sort(a);
		for (int i=0;i<a.length;i++)
			StdOut.println(a[i]);

	}
}
