package zp4jv;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddActivityForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JButton bAdd;

	public AddActivityForm() {
		super();

		this.setTitle("Timesheets - Add Activity");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setContentPane(mainPanel);
		this.pack();
	}
}
