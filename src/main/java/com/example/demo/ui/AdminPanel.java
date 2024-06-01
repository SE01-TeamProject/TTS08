package com.example.demo.ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class AdminPanel extends JPanel {
	
	private SwingController controller;
	private JButton addUserBtn;
	private JButton addProjectBtn;
	private JButton projectUserManageBtn;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminPanel(SwingController sc) {
		this();
		controller = sc;
	}
	
	
	public AdminPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(null);
		
		addUserBtn = makeAddUserBtn("Add User");
		add(addUserBtn);
		
		addProjectBtn = makeAddProjectBtn("Add Project");
		add(addProjectBtn);
		
		projectUserManageBtn = makeProjectUserManageBtn("Project Users");
		//add(projectUserManageBtn);
	}
	
	// Buttons
	private JButton makeAddUserBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add User Btn ACtion Listener
				System.out.println("Add User btn pressed!");
				AddUserWindow frame = new AddUserWindow(controller);
				frame.setVisible(true);
			}
		});
		btn.setBounds(12, 10, 300, 150);
		return btn;
	}
	
	// Buttons ---------------------------------------------------------------------
	private JButton makeProjectUserManageBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add User Btn ACtion Listener
				System.out.println("Project Users btn pressed!");
				ProjectUserWindow frame = new ProjectUserWindow(controller);
				frame.setVisible(true);
			}
		});
		btn.setBounds(468, 10, 300, 150);
		return btn;
	}
	
	private JButton makeAddProjectBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add User Btn ACtion Listener
				System.out.println("Add Project btn pressed!");
				AddProjectWindow addProjectWindow = new AddProjectWindow(controller);
				addProjectWindow.setVisible(true);
			}
		});
		btn.setBounds(928, 10, 300, 150);
		return btn;
	}
	
	
	
}











