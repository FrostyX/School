package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class SaveConfig implements ActionListener {

	private JTable table = null;
	private Config config = null;
	
	public SaveConfig(JTable table, Config config) {
		this.table = table;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VHostsTableModel model = (VHostsTableModel) table.getModel();
		for(VHostConfig vhost : model) {
			System.out.println("FOO");
		}
		System.out.println("---> " + config.getPath());
	}

}
