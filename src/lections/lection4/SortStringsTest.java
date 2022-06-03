package lections.lection4;

import java.io.*;

import princeton.lib.In;
import princeton.lib.StdOut;

public class SortStringsTest {

	private static final String testFile = "words3.txt"; // ���� � �������
	
	public static void main(String[] args) throws FileNotFoundException {
		String[] a = In.readStrings(testFile);
		FirstSort.sort(a);
		for (int i=0;i<a.length;i++)
			StdOut.println(a[i]);
	}

}
