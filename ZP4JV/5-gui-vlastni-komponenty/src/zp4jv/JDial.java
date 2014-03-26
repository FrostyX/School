package zp4jv;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class JDial extends JComponent {

	private static final long serialVersionUID = 1L;
	private final int padding = 1;
	private int numberHeight = 50;
	private int numberWidht = 50;
	private int numbersGapX = 0;
	private int numbersGapY = 30;
	
	private JDialDisplay display;
	private JDialButton enter;
	private JDialButton clear;
	
	private ArrayList<ActionListener> actions;
	
	
	public JDial() {
		initializeComponents();
		actions = new ArrayList<ActionListener>();
	}

	public void addActionListener(ActionListener actionListener) {
		actions.add(actionListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	private void initializeNumericButtons() {

		for(int i = 9, row = 0, cell = 2; i >= 0; i--, cell--) {

			if(i == 0) 
				cell = 0;
			
			
			JDialButton b = new JDialButton(String.valueOf(i));
			b.setAction(new NumberAction());
			
			b.registerKeyboardAction(new DialNumberButtonActionListener(), KeyStroke.getKeyStroke(Character.forDigit(i, 10)), JComponent.WHEN_IN_FOCUSED_WINDOW);
			b.addMouseListener(new DialNumberButtonMouseAdapter());
			b.setBounds(
					numbersGapX + (cell * numberWidht), 
					numbersGapY + (row * numberHeight), 
					numberWidht,
					numberHeight
			);
			add(b);

			if((i - 1) % 3 == 0) {
				row++;
				cell = 3;
			}
		}
	}
	
	private void initializeComponents() {
		
		// Display
		display = new JDialDisplay();
		display.setBounds(0, 0, 200, 50);
		add(display);
		numbersGapY = display.getHeight() + padding;

		// Čísílka
		initializeNumericButtons();
		
		// Clear
		clear = new JDialButton("C");
		clear.setAction(new ClearAction());
		clear.addMouseListener(new ClearMouseAdapter());
		clear.registerKeyboardAction(new ClearActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		clear.setBounds(
				numbersGapX + (3 * numberWidht), 
				numbersGapY,
				numberWidht,
				numberHeight
		);
		add(clear);

		// Enter
		enter = new JDialButton("OK");
		enter.setAction(new EnterAction());
		enter.addMouseListener(new EnterMouseAdapter());
		enter.registerKeyboardAction(new EnterActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		enter.setBounds(
				numbersGapX + (3 * numberWidht), 
				numbersGapY + numberHeight,
				numberWidht,
				numberHeight * 2
		);
		add(enter);
	}
	
	
	/*
	 * Actions
	 */
	public class EnterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(ActionListener a : actions) {
				a.actionPerformed(arg0);
			}
		}
	}

	public class ClearAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			display.show("");
		}
	}

	public class NumberAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JDialButton b = (JDialButton) arg0.getSource();
			display.showAppend(b.getCaption());
		}
	}
	
	
	/*
	 * Listeners and Adapters
	 */
	
	// Numbers
	private class DialNumberButtonMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JDialButton b = (JDialButton) arg0.getSource();
			b.actionPerformed(new ActionEvent(b, 0, "number"));
		}
	}

	private class DialNumberButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JDialButton b = (JDialButton) arg0.getSource();
			b.actionPerformed(new ActionEvent(b, 0, "number"));
		}
    }

	// Clear
	private class ClearMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			clear.actionPerformed(null);
		}
	}
	
	private class ClearActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			clear.actionPerformed(null);
		}
    }

	// Enter
	private class EnterMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			enter.actionPerformed(null);
		}
	}
	
	private class EnterActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			enter.actionPerformed(null);
		}
    }
}
