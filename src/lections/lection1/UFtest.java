package lections.lection1;

import princeton.lib.StdIn;
import princeton.lib.StdOut;
import java.io.FileNotFoundException;

public class UFtest {
	private static final String testFile = "tinyUF.txt"; // 10 �'������ �� 11 �������
	private static final String testFile2 = "mediumUF.txt"; // 900 �'������ �� 625 �������
	private static final String testFile3 = "largeUF.txt"; //2 ����� �'������ �� 1 �. �����
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//	BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile3));
		//	System.setIn(in);
		
		int n = StdIn.readInt();
		//QuickFindUF uf = new QuickFindUF(n);
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
		//QuickUnionUF uf = new QuickUnionUF(n);
		while (!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p,q)){
				uf.union(p,q);
				StdOut.println(p+" "+q);
			}else{
				StdOut.println(p+" "+q +" already connected");
			}
		}
		StdOut.println(uf);
	}

}
