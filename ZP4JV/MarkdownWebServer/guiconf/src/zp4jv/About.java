package zp4jv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new AboutForm().setVisible(true);
	}

}
