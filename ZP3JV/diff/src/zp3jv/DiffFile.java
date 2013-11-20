package zp3jv;

import java.util.ArrayList;

public class DiffFile {

	private ArrayList<DiffLine> content = new ArrayList<DiffLine>();

	public void add(DiffLine line) {
		content.add(line);
	}

	/**
	 * @return the content
	 */
	public ArrayList<DiffLine> getContent() {
		return content;
	}

	//public DiffFile() {
		//;
	//}
}
