package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class RemoveVHost implements ActionListener {

	private JTable table = null;

	public RemoveVHost(JTable vHostsTable, Config config) {
		this.table = vHostsTable;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		VHostsTableModel model = (VHostsTableModel) table.getModel();
		model.removeRow(table.getSelectedRow());
		table.repaint();
	}

}
