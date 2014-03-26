package zp4jv;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class MainForm extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public MainForm() {
		super();
		
		JDial dial = new JDial();
		dial.addActionListener(new ConsoleOutputActionListener());
		dial.addActionListener(new MessageBoxActionListener());
		getContentPane().add(dial);

		setPreferredSize(new Dimension(209, 280));
		pack();
	}
	
	private class ConsoleOutputActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ConsoleOutputActionListener");
		}
	}

	private class MessageBoxActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("MessageBox");
		}
	}
}
