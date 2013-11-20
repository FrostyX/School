package zp3jv;

import java.io.IOException;
import java.util.ArrayList;

public class File {
	private String path = "";
	private ArrayList<String> content = new ArrayList<String>();

	/**
	 * @param path
	 * @throws IOException
	 */
	public File(String path) throws IOException {
		this.path = path;
		this.content = IO.readFile(path);
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the content
	 */
	public ArrayList<String> getContent() {
		return content;
	}
}

