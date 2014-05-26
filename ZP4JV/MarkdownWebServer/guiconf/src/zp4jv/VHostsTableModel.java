package zp4jv;

import java.util.Arrays;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class VHostsTableModel extends AbstractTableModel implements Iterable<VHostConfig> {
	private static final long serialVersionUID = 1L;
	private String[] columns;
	private Object[][] values;

	public VHostsTableModel(String[] columns, Object[][] values) {
		super();
		this.columns = columns;
		this.values = values;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return values.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
		return values[r][c];
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		values[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
		fireTableDataChanged();
	}

	public void addRow(VHostConfig vhost) {
		values = Arrays.copyOf(values, values.length + 1);
		values[values.length - 1] = new Object[] {vhost.getPort(), vhost.getName(), vhost.getDocumentRoot()};
		fireTableStructureChanged();
	}

	public void clear() {
		values = new Object[][] {};
		fireTableStructureChanged();
	}

	@Override
	public Iterator<VHostConfig> iterator() {
		return new Iterator<VHostConfig>() {
			
			private int row = 0;
			
			@Override
			public boolean hasNext() {
				return row < getRowCount();
			}
	
			@Override
			public VHostConfig next() {
				row ++;
				return null;
			}
	
			@Override
			public void remove() {
			}
		};
	}
}
