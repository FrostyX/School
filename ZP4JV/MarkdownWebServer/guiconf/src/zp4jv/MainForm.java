package zp4jv;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MainForm extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JMenuBar mainMenu;
	private VHostsTableModel vHostsModel;
	private JTable vHostsTable;
	private Config config;

	public MainForm() {
		initializeComponents();
		this.setPreferredSize(new Dimension(450, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}

	private void initializeComponents() {
		initializeVhosts();
		populateVhosts();
		initializeMainMenu();
	}
	
	private void initializeMainMenu() {
		mainMenu = new JMenuBar();
		initializeMainMenuConfig();
		initializeMainMenuVHost();
		initializeMainMenuHelp();
		setJMenuBar(mainMenu);
	}

	private void initializeMainMenuConfig() {
		JMenu menuConfig = new JMenu("Config");
		mainMenu.add(menuConfig);

		// New config
		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new NewConfig(vHostsTable, config));
		menuConfig.add(menuItemNew);

		// Save config
		JMenuItem menuItemSave = new JMenuItem("Save");
		System.out.println(config);
		menuItemSave.addActionListener(new SaveConfig(vHostsTable, config));
		menuConfig.add(menuItemSave);

		// Load config
		config = new Config(null);
		JMenuItem menuItemLoad = new JMenuItem("Load");
		menuItemLoad.addActionListener(new LoadConfig(vHostsTable, config));
		menuConfig.add(menuItemLoad);
	}
	
	private void initializeMainMenuVHost() {
		JMenu menuVHost = new JMenu("VHost");
		mainMenu.add(menuVHost);

		// New VHost
		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new NewVHost(vHostsTable));
		menuVHost.add(menuItemNew);
	}

	private void initializeMainMenuHelp() {
		JMenu menuHelp = new JMenu("Help");
		mainMenu.add(menuHelp);

		// About
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.addActionListener(new About());
		menuHelp.add(menuItemAbout);
	}

	private void initializeVhosts() {
		
		String[] columns = new String[] {"Port", "Name", "DocumentRoot"};
		Object[][] values = new Object[][] {};

		vHostsModel = new VHostsTableModel(columns, values);
		vHostsTable = new JTable(vHostsModel);
		JScrollPane tableScroll = new JScrollPane(vHostsTable);
		
		// Set columns width
		// http://stackoverflow.com/questions/953972/java-jtable-setting-column-width
		//vHostsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//vHostsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		//vHostsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		getContentPane().add(tableScroll);
	}
	
	private void populateVhosts() {
		config = new Config(Config.DEFAULT_PATH);
		new LoadConfig(vHostsTable, config).actionPerformed(null);
	}
}