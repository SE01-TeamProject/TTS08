package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class TimelinePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	private JTextArea timelineTextArea = new JTextArea();
	private JScrollPane scrollPane;
	
	
	public TimelinePanel(SwingController sc) {
		this();
		controller = sc;
		setTextArea();
		
	}
	
	public TimelinePanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
		
		timelineTextArea.setText("");
		timelineTextArea.setEnabled(false);
		
		scrollPane = new JScrollPane(timelineTextArea);
		add(scrollPane, BorderLayout.CENTER);
		
		//timelineTextArea.setText("Hello");
	}
	
	public void setTextArea() {
		/* Get Timeline from model -> controller (Ask to controller)
		 * Controller will Parse JSON file, return long~~~~~~ String contain '\n'
		 */
		
		revalidate();
		repaint();
	}

}
