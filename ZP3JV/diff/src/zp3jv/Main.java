package zp3jv;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		String output = null;
		if(false) {
			output = "/home/frostyx/diff/output.diff";
		}

		try {
			//File f1 = new File("/home/frostyx/diff/a");
			//File f2 = new File("/home/frostyx/diff/b");
			//File f3 = new File("/home/frostyx/diff/c");
			File f4 = new File("/home/frostyx/diff/d");
			File f5 = new File("/home/frostyx/diff/e");

			IO.setColored(true);
			IO.setNumberLines(false);
			IO.setOutput(output);

			//IO.write(Diff.compare(f1, f2));
			//IO.write(Diff.compare(f2, f3));
			//IO.write(Diff.compare(f1, f3));
			IO.write(Diff.compare(f4, f5));
			//IO.write(Diff.compare(f4, f5));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
