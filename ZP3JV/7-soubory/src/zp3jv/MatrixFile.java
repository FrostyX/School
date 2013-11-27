package zp3jv;

//import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class MatrixFile {
	public static int[][] readTextMatrix(String fileName) throws IOException {
		return readTextMatrix(new FileReader(fileName));
	}

	public static int[][] readTextMatrix(Reader r) throws IOException {
		BufferedReader reader = new BufferedReader(r);;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

		String line;
		while((line = reader.readLine()) != null) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(String number : line.split(" ")) {
				row.add(Integer.valueOf(number));
			};
			matrix.add(row);
		}
		reader.close();
		return toArray(matrix);
	}

	public static void writeTextMatrix(String fileName, int[][] matrix)
			throws FileNotFoundException, IOException {
		writeTextMatrix(new PrintWriter(new File(fileName)), matrix);
	}

	public static void writeTextMatrix(Writer w, int[][] matrix) throws IOException {
		Writer writer = w;
		for(int[] row : matrix) {
			int i = 0;
			for(int x : row) {
				writer.write(String.valueOf(x));
				if(i < row.length)
					writer.write(" ");
			}
			writer.write("\n");
		}
		writer.close();
	}

	public static void writeBinaryMatrix(OutputStream s, int[][] matrix)
			throws IOException {
		DataOutputStream output = new DataOutputStream(s);
		for(int[] row : matrix) {
			int i = 0;
			for(int x : row) {
				output.writeInt(x);

				if(i < row.length-1)
					output.writeChar(' ');
				i++;
			}
			output.writeChar('|');
		}
		output.close();
	}

	public static int[][] readBinaryMatrix(InputStream s) throws IOException {
		DataInputStream input = new DataInputStream(s);
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> row = new ArrayList<Integer>();
		while(input.available() > 0) {
			int number = input.readInt();
			row.add(number);

			char c = input.readChar();
			if(c == '|') {
				matrix.add(row);
				row = new ArrayList<Integer>();
			}
		}
		input.close();
		return toArray(matrix);
	}

	private static int[][] toArray(ArrayList<ArrayList<Integer>> matrix) {
		int[][] matrix_int = new int[matrix.size()][];
		int i = 0;
		for(ArrayList<Integer> row : matrix) {
			int[] row_int = new int[row.size()];
			int j = 0;
			for(int x : row) {
				row_int[j] = x;
				j++;
			}
			matrix_int[i] = row_int;
			i++;
		}
		return matrix_int;
	}
}
