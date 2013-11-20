package zp3jv;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			File f1 = new File("/home/frostyx/diff/a");
			File f2 = new File("/home/frostyx/diff/b");
			File f3 = new File("/home/frostyx/diff/c");
			IO.write(f1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
