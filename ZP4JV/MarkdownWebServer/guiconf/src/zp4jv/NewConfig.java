package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class NewConfig implements ActionListener {
	
	private JTable table;
	private Config config;
	
	public NewConfig(JTable table, Config config) {
		this.table = table;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		config.setPath(null);
		VHostsTableModel model = (VHostsTableModel) table.getModel();
		model.clear();

		MainForm.setHeader("New unsaved config");
	}
}
