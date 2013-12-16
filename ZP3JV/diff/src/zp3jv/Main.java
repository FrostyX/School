package zp3jv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		try {
			if(args.length < 2)
				throw new MissingArgumentException("Missing argument: What files do you want compare?");

			IObyArguments(args);

			File f1 = new File(args[0]);
			File f2 = new File(args[1]);
			IO.write(Diff.compare(f1, f2));

		} catch (FileNotFoundException e) {
			System.out.println(IO.getOutput() + ": Permission denied");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnknownArgumentException e) {
			System.out.println(e.getMessage());
		} catch (MissingArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void IObyArguments(String[] args)
			throws MissingArgumentException, UnknownArgumentException {

		for(int i=2; i<args.length; i++) {
			if((i > 2) && (args[i-1].equals("-o")) || (args[i-1].equals("--output"))) {
				continue;
			}

			if((args[i].equals("-nc")) || (args[i].equals("--no-color"))) {
				IO.setColored(false);
			}
			else if((args[i].equals("-l")) || (args[i].equals("--number-lines"))) {
				IO.setNumberLines(true);
			}
			else if((args[i].equals("-o")) || (args[i].equals("--output"))) {
				if(i >= args.length-1) {
					throw new MissingArgumentException("Missing argument for: --output");
				}
				IO.setOutput(args[i+1]);
			}
			else {
				throw new UnknownArgumentException("Unknown argument: " + args[i]);
			}
		}
	}
}
