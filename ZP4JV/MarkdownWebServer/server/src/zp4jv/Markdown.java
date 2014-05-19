package zp4jv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;

public class Markdown {
	
	private String markdown = "";
	
	public Markdown(String markdown) {
		this.markdown = markdown;
	}
	
	public static Markdown fromFile(String file) throws IOException {
		// @TODO Ověřit jestli je to md soubor a pokud ne, vyhodit vyjímku

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line;
		while((line = reader.readLine()) != null) {
			sb.append(line + System.getProperty("line.separator"));
		}

		reader.close();
		return new Markdown(sb.toString());
	}
	
	public String toHtml() throws MarkdownException {
		Markdown4jProcessor m4j = new Markdown4jProcessor();
		try {
			return m4j.process(markdown);
		}
		catch (IOException e) {
			throw new MarkdownException();
		}
	}
}
