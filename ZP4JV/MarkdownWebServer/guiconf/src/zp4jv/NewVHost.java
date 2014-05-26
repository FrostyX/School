package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class NewVHost implements ActionListener {
	
	private JTable table = null;

	public NewVHost(JTable vHostsTable) {
		this.table = vHostsTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		VHostsTableModel model = (VHostsTableModel) table.getModel();
		model.addRow(new VHostConfig(-1, "", ""));

		// Select new row
		int last = model.getRowCount() - 1;
		table.setRowSelectionInterval(last, last);
		table.editCellAt(last, 0);
		
		// Edit port field
		// http://stackoverflow.com/a/11838443/3285282
	}

}
