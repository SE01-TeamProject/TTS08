package com.example.demo.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class ProjectUserWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SwingController controller;
	private JPanel userCheckBoxPanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JButton confirmBtn = new JButton();
	private JComboBox<String> projectComboBox = new JComboBox<String>();
	
	
	public ProjectUserWindow(SwingController sc) {
		this();
		controller = sc;
		
		// Confirm Btn

		setFrame();
	}
	
	public ProjectUserWindow() {
		setTitle("Project Users");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));
		
		userCheckBoxPanel.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));	
		userCheckBoxPanel.setLayout(new BoxLayout(userCheckBoxPanel, BoxLayout.Y_AXIS));
		
		scrollPane.setViewportView(userCheckBoxPanel);
		contentPane.add(scrollPane);
		

		JCheckBox testCheckBox = new JCheckBox("New check box");
		userCheckBoxPanel.add(testCheckBox);
		
		JCheckBox testCheckBox2 = new JCheckBox("New check box2");
		userCheckBoxPanel.add(testCheckBox2);
		
		confirmBtn = new JButton("confirm");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selectedUsers = new ArrayList<String>();
				
				for(Component component : userCheckBoxPanel.getComponents()) {
					if(component instanceof JCheckBox) {
						JCheckBox box = (JCheckBox) component;
						if(box.isSelected()) {
							selectedUsers.add(box.getText());
						}
					}
				}
				
				System.out.println(selectedUsers);
				setVisible(false);
			}
		});
		contentPane.add(confirmBtn, BorderLayout.SOUTH);
		
		
		contentPane.add(projectComboBox, BorderLayout.NORTH);
		
	}
	
	public void setCheckBoxes() {
		// init check boxes
		userCheckBoxPanel.removeAll();
		
		ArrayList<String> users = controller.getUserByLevel(-1);
		for(String user : users) {
			JCheckBox box = new JCheckBox(user);
			System.out.println("CheckBox - " + user);
			// if user in this project -> box.setSelected(true)
			userCheckBoxPanel.add(box);
		}
		
		revalidate();
		repaint();
	}
	
	public void setComboBox() {
		// init combobox
		projectComboBox.removeAll();
		
		ArrayList<String> projects = controller.getProjectTitles();
		for(String project : projects) {
			projectComboBox.addItem(project);
		}
		
		revalidate();
		repaint();
	}
	
	public void setFrame() {
		setCheckBoxes();
		setComboBox();
	}
	
}