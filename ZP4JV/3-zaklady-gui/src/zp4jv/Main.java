package zp4jv;

import javax.swing.DefaultListModel;

public class Main {
	
	@SuppressWarnings("rawtypes")
	public static DefaultListModel listModel = new DefaultListModel();

	public static void main(String[] args) {
		MainForm form = new MainForm(listModel);
		form.setVisible(true);
	}
}
