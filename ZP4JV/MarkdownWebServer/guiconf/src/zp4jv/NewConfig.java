package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class NewConfig implements ActionListener {
	
	private JTable table;
	
	public NewConfig(JTable table, Config config) {
		this.table = table;
		config = new Config(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		VHostsTableModel model = (VHostsTableModel) table.getModel();
		model.clear();
	}

}
