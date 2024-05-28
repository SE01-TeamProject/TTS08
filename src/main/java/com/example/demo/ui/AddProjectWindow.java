package com.example.demo.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

public class AddProjectWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SwingController controller;
	private JTextField titleTextField;
	private JTextArea descriptionTextArea = new JTextArea();
	private JButton addBtn;
	private JComboBox<String> plComboBox = new JComboBox<String>();
	private JComboBox<String> devComboBox = new JComboBox<String>();
	private JComboBox<String> testerComboBox = new JComboBox<String>();
	
	
	public AddProjectWindow(SwingController sc) {
		controller = sc;
		
		setTitle("Add new project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		// title
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(12, 10, 57, 15);
		contentPane.add(titleLabel);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(12, 30, 430, 21);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		// description
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(12, 60, 70, 15);
		contentPane.add(descriptionLabel);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setToolTipText("Write short description of project");
		descriptionTextArea.setBounds(12, 82, 430, 70);
		contentPane.add(descriptionTextArea);
		
		// add btn
		addBtn = new JButton("add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Project-Add!");
				
				String title = titleTextField.getText();
				String description = descriptionTextArea.getText().replace('\n', ' ');
				String pl = plComboBox.getSelectedItem().toString();
				String dev = devComboBox.getSelectedItem().toString();
				String tester = testerComboBox.getSelectedItem().toString();
				
				if(!title.isEmpty() && !description.isEmpty()) {
					controller.addProject(title, description, pl, dev, tester);
					setVisible(false);
				}			
			}
		});
		addBtn.setBounds(180, 228, 100, 23);
		contentPane.add(addBtn);
		
		// Combo Boxes
		plComboBox.setBounds(12, 189, 100, 23);
		contentPane.add(plComboBox);

		devComboBox.setBounds(180, 189, 100, 23);
		contentPane.add(devComboBox);

		testerComboBox.setBounds(342, 189, 100, 23);
		contentPane.add(testerComboBox);
		
		setComboBox();
	}
	
	private void setComboBox() {
		JComboBox[] arr = {plComboBox, devComboBox, testerComboBox};
		
		for(int i = 0; i < arr.length; i++) {
			ArrayList<String> users = controller.getUserByLevel(i + 1);
			for(String user : users) {
				arr[i].addItem(user);
			}
		}
		revalidate();
		repaint();		
	}
	
	// END----
}
