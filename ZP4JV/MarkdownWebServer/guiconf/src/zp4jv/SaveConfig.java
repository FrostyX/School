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
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class SaveConfig implements ActionListener {

	private JTable table = null;
	private Config config = null;
	
	public SaveConfig(JTable table, Config config) {
		this.table = table;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {

			if(config.getPath() == null) {
				config.setPath(chooseFile());
				
				File f = new File(config.getPath());
				if(!f.exists()) {
					f.createNewFile();
					config.initializeConfigFile();
				}
			}
			VHostsTableModel model = (VHostsTableModel) table.getModel();
			config.save(model.getAll());
			MainForm.setHeader(config.getName());

		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e1) {
			e1.printStackTrace();
		} catch (FileNotChoosedException e1) {
		}
	}

	private String chooseFile() throws FileNotChoosedException, FileNotFoundException {
		JFileChooser dialog = new JFileChooser();
		dialog.setCurrentDirectory(new File(Config.DEFAULT_PATH));
		int result = dialog.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File file = dialog.getSelectedFile();
			return file.getAbsolutePath();
		}
		throw new FileNotChoosedException();
	}
}
