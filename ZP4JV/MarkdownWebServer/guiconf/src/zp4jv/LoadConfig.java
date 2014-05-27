package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class LoadConfig implements ActionListener {

	private JTable table = null;
	private Config config = null;
	
	public LoadConfig(JTable table, Config config) {
		this.table = table;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			config.setPath(chooseFile());
			populate();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (FileNotChoosedException e1) {
		}
	}
	
	public void populate() {
		try {	
			VHostsTableModel model = (VHostsTableModel) table.getModel();
			model.clear();

			for(VHostConfig vhost : config.getVHosts()) {
				model.addRow(new VHostConfig(vhost.getPort(), vhost.getName(), vhost.getDocumentRoot()));
			}
			MainForm.setHeader(config.getName());

		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "File not found");
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
	}
	
	private String chooseFile() throws FileNotChoosedException, FileNotFoundException {
		JFileChooser dialog = new JFileChooser();
		dialog.setCurrentDirectory(new File(Config.DEFAULT_PATH));
		int result = dialog.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File file = dialog.getSelectedFile();
		    if(!file.exists())
		    	throw new FileNotFoundException();
			return file.getAbsolutePath();
		}
		throw new FileNotChoosedException();
	}
}
