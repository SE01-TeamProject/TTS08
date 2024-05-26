package com.example.demo.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;


public class ProjectPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ProjectPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Project Panel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ProjectPanel");
			}
		});
		add(btnNewButton, BorderLayout.CENTER);

	}

}
