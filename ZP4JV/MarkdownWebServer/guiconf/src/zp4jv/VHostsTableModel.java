package zp4jv;

import java.util.ArrayList;
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
		String port = vhost.getPort() < 0 ? "" : String.valueOf(vhost.getPort());
		values[values.length - 1] = new Object[] {port, vhost.getName(), vhost.getDocumentRoot()};
		fireTableStructureChanged();
	}
	
	public void removeRow(int index) {
		ArrayList<Object[]> a = new ArrayList<Object[]>(Arrays.asList(values));
		a.remove(index);
		values = new Object[a.size()][];
		a.toArray(values);
	}

	public void clear() {
		values = new Object[][] {};
		fireTableStructureChanged();
	}
	
	public ArrayList<VHostConfig> getAll() {
		ArrayList<VHostConfig> vhosts = new ArrayList<VHostConfig>();
		for(VHostConfig vhost : this) {
			vhosts.add(vhost);
		}
		return vhosts;
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
				
				int port = 0;
				try {
					// Manually edited ports in table are String
					if(values[row][0].getClass().equals(String.class)) {
						port = Integer.valueOf((String) values[row][0]);
					}
					else if(values[row][0].getClass().equals(Integer.class)) {
						port = (Integer)values[row][0];
					}
				}
				catch(Exception e) {
					throw new NumberFormatException("Incorrect port number: " + (String)values[row][0]);
				}

				VHostConfig vhost = new VHostConfig(
						port,
						(String)values[row][1],
						(String)values[row][2]);
				row ++;
				return vhost;
			}
	
			@Override
			public void remove() {
			}
		};
	}
}
