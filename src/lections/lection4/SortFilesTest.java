package lections.lection4;

import java.io.*;
import princeton.lib.StdOut;

public class SortFilesTest {

	private static final String testDir = "C:\\Windows"; // ������� ���������
	
	public static void main(String[] args) {
		File directory = new File(testDir);
		File[] files = directory.listFiles();
		FirstSort.sort(files);
		for (int i=0;i<files.length;i++)
			StdOut.println(files[i].getName());
	}

}
