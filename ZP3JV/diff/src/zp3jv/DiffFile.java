package zp3jv;

import java.util.ArrayList;

public class DiffFile {

	private ArrayList<DiffLine> content = new ArrayList<DiffLine>();

	public void add(DiffLine line) {
		content.add(line);
	}

	public void add(DiffFile file) {
		for(DiffLine line : file.getContent())
			content.add(line);
	}

	/**
	 * @return the content
	 */
	public ArrayList<DiffLine> getContent() {
		return content;
	}

	@Override
	public String toString()
	{
		//for(DiffLine l : content)
			//System.out.println(l.getNumber());

		String s = "";
		String lastSymbol = "";
		String lastAction = "";
		int lastChangedLine = 0;
		int next = 0;
		for(int i = 0; i < content.size(); i++) {
			DiffLine line = content.get(i);

			if(line.getSymbol() == Diff.KEEP_SYMBOL)
				continue;

			//System.out.println("XY"+content.get(nextChangedLineNumber(i)).getSymbol());
			//System.out.println(line.getNumber() + "vs" + next);

			if((lastSymbol != line.getSymbol()) || (line.getNumber() > next)) {
			//if((lastSymbol != line.getSymbol()) || (line.getNumber() > next)) {
			//if(lastSymbol != line.getSymbol()) {
				//System.out.println("XY"+content.get(nextChangedLineNumber(i)).getSymbol());
				//if(content.get(i+1).getSymbol() == Diff.KEEP_SYMBOL) {
				//System.out.println("X"+content.get(nextChangedLineNumber(i)).getSymbol());
				//if(content.get(nextChangedLineNumber(i)).getSymbol() == Diff.KEEP_SYMBOL) {
				//System.out.println(content.get(nextChangedLineNumber(i)).getSymbol() + "vs" + line.getSymbol());
				//if((content.get(nextChangedLineNumber(i)).getSymbol() == Diff.KEEP_SYMBOL) || (false)) {
				if((content.get(nextChangedLineNumber(i)).getSymbol() == Diff.KEEP_SYMBOL) || (((lastAction == "a") || (lastAction == "d")) && (content.get(nextChangedLineNumber(i)).getSymbol() == line.getSymbol()))) {
					s +=
						line.getNumber()-1-next
						+ "a"
						+ line.getNumber()
						+ (nextChangedLineNumber(i) > line.getNumber() ? "," + nextChangedLineNumber(i) : "")
					;
					lastAction = "a";
					//next = nextChangedLineNumber(i);
				}
				else {
					if(line.getNumber() > lastChangedLine) {
						//s += line.getNumber()-next + "c" + line.getNumber() + "," + nextChangedLineNumber(i+1);
						s +=
							line.getNumber()-next
							+ "c"
							+ line.getNumber()
							+ "," + nextChangedLineNumber(i+1)
							//+ (nextChangedLineNumber(i) > line.getNumber() ? "," + nextChangedLineNumber(i) : "")
						;
						lastChangedLine = line.getNumber();
						lastAction = "c";
					} else {
						s += "---";
					}
				}
				next = nextChangedLineNumber(i);
				s += System.getProperty("line.separator");
			}

			s += line.toString() + System.getProperty("line.separator");
			lastSymbol = line.getSymbol();

			//if(line.getNumber() > lastChangedLine) {
			//	s += line.getNumber() + "c" + line.getNumber() + "," + nextChangedLineNumber(i) + System.getProperty("line.separator");
			//}

			/*
			if((line.getNumber() == 1) || (line.getSymbol() != lastSymbol)) {
				// Rozdělit přidávání a měnění

				//System.out.println(nextChangedLineNumber(i+3));
				//if(line.getNumber() == nextChangedLineNumber(i+1)) {
					//System.out.println("FOO");
				//}

				if(line.getNumber() > lastChangedLine) {
					s += line.getNumber() + "c" + line.getNumber() + "," + nextChangedLineNumber(i) + System.getProperty("line.separator");
					lastChangedLine = line.getNumber();
				} else {
					s += "---" + System.getProperty("line.separator");
				}
			}
			*/

			//s += line.toString() + System.getProperty("line.separator");
			//lastSymbol = line.getSymbol();
		}
		return s;
	}

	protected int nextChangedLineNumber(int k) {
		String symbol = content.get(k).getSymbol();
		int keep = 0;
		int i;
		for(i = k; i < content.size(); i++) {
			if(content.get(i).getSymbol() == Diff.KEEP_SYMBOL) {
				keep++;
			} else {
				if(content.get(i).getSymbol() != symbol)
					return content.get(i).getNumber();
			}
		}
		return i-keep-1;
	}
}
