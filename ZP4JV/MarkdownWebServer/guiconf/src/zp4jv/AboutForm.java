package zp4jv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutForm extends JDialog {
	
	private static final long serialVersionUID = 1L;

	public AboutForm() {
		initializeComponents();
		this.setTitle("MarkdownWebServer GUIConf - About");
		this.setPreferredSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	private void initializeComponents() {
		getContentPane().setLayout(new BorderLayout());
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html><center>");
		sb.append("<b>MarkdownWebServer GUIConf</b><br>");
		sb.append("Jakub Kadlčík<br>");
		sb.append("<br>");
		sb.append("This is configuration tool for MarkdownWebServer<br>");
		sb.append("</center></html>");
		
		JLabel about = new JLabel(sb.toString());
		about.setFont(about.getFont().deriveFont(Font.PLAIN));
		getContentPane().add(about);
	}
}
