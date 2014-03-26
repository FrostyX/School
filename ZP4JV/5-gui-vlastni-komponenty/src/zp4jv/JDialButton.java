package zp4jv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JComponent;

public class JDialButton extends JComponent {

	private static final long serialVersionUID = 1L;
	private String caption = "";
	private Action action;

	public JDialButton() {
	}

	public JDialButton(String caption) {
		this.caption = caption;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(
				0,
				0,
				getBounds().width - 1,
				getBounds().height - 1
		);

		g2.setColor(Color.WHITE);
		g2.drawString(caption, getCaptionGapX(), getCaptionGapY());
	}
	
	public void actionPerformed(ActionEvent e) {
		if(action != null) {
			action.actionPerformed(e);
		}
	}
	
	private int getCaptionGapX() {
		return (getBounds().width / 2) - (caption.length() * 3);
	}

	private int getCaptionGapY() {
		return (getBounds().height / 2) + 5;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}