package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;


public class ProjectPanel extends JPanel {	
	private static final long serialVersionUID = 1L;
	private SwingController controller;
	private JButton testBtn;
	private JTable projects;
	
	
	public ProjectPanel(SwingController sc) {
		this();
		controller = sc;
		
		testBtn = new JButton("Project Panel");
		testBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Example. Should be checked from model (selec project)
				System.out.println("ProjectPanel");
				if(controller != null) {
					controller.projectSelect();
				}
				else {
					System.out.println("Null Controller for ProjectPanel");
				}
			}
		});
		add(testBtn, BorderLayout.CENTER);
	}
	
	public ProjectPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
	}

	public void setTable() {
		/*	1. Get table header
		 * 	2. Get project name & description as 2D-Array (from controller.
		 * 		-> Controller will get it from model as JSON
		 */
		
	}
	
}
