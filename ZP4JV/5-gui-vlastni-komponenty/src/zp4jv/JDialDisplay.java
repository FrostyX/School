package zp4jv;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class JDialDisplay extends JComponent {

	private static final long serialVersionUID = 1L;
	private String caption = "";

	public JDialDisplay() {
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fillRect(
				getBounds().x,
				getBounds().y,
				getBounds().width - 1,
				getBounds().height - 1
		);
		g2.setColor(Color.black);
		g2.drawString(caption, getCaptionGapX(), getCaptionGapY());
	}

	public void show(String s) {
		caption = s;
		repaint();
	}
	
	public void showAppend(String s) {
		caption += s;
		repaint();
	}
	
	private int getCaptionGapX() {
		return getBounds().x + (getBounds().width / 2) - (caption.length() * 3);
	}

	private int getCaptionGapY() {
		return getBounds().y + (getBounds().height / 2) + 5;
	}
}
