package zp3jv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		String txt = "/tmp/matrix.txt";
		String bin = "/tmp/matrix.bin";

		int[][] m1 = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		};

		try {
			MatrixFile.writeTextMatrix(txt, m1);
			MatrixFile.writeBinaryMatrix(new FileOutputStream(bin), m1);

			printMatrix(MatrixFile.readTextMatrix(txt));
			System.out.println("\n-----\n");
			printMatrix(MatrixFile.readBinaryMatrix(new FileInputStream(bin)));
		}
		catch (FileNotFoundException e) {
			System.out.println("Soubor neexistuje!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printMatrix(int[][] matrix) {
		for(int[] row : matrix) {
			for(int x : row)
				System.out.print(x + " ");
			System.out.println();
		}
	}

}
