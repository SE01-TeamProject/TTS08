package com.example.demo.ui;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddProjectWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SwingController controller;
	private JTextField titleTextField;
	private JTextArea descriptionTextArea = new JTextArea();
	private JButton addBtn;
	
	private JComboBox<String> plComboBox = new JComboBox<String>();
	
	private JComboBox<String> devComboBox1 = new JComboBox<String>();
	private JComboBox<String> devComboBox2 = new JComboBox<String>();
	private JComboBox<String> devComboBox3 = new JComboBox<String>();

	private JComboBox<String> testerComboBox1 = new JComboBox<String>();
	private JComboBox<String> testerComboBox2 = new JComboBox<String>();
	private JComboBox<String> testerComboBox3 = new JComboBox<String>();
	
	
	public AddProjectWindow(SwingController sc) {
		controller = sc;
		
		setTitle("Add new project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 350);
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
				
				String dev1 = devComboBox1.getSelectedItem().toString();
				String dev2 = devComboBox2.getSelectedItem().toString();
				String dev3 = devComboBox3.getSelectedItem().toString();
				
				String tester1 = testerComboBox1.getSelectedItem().toString();
				String tester2 = testerComboBox2.getSelectedItem().toString();
				String tester3 = testerComboBox3.getSelectedItem().toString();
				
				boolean devCheck = (dev1 == dev2 || dev2 == dev3 || dev3 == dev1);
				boolean testerCheck = (tester1 == tester2 || tester2 == tester3 || tester3 == tester1);
				
				if(!title.isEmpty() && !description.isEmpty() && !devCheck && !testerCheck) {
					controller.addProject(title, description, pl, dev1, dev2, dev3, tester1, tester2, tester3);
					setVisible(false);
				}			
			}
		});
		addBtn.setBounds(180, 282, 100, 23);
		contentPane.add(addBtn);
		
		// Combo Boxes
		plComboBox.setBounds(12, 189, 100, 23);
		contentPane.add(plComboBox);

		devComboBox2.setBounds(180, 203, 100, 23);
		contentPane.add(devComboBox2);

		testerComboBox2.setBounds(342, 203, 100, 23);
		contentPane.add(testerComboBox2);
		devComboBox3.setBounds(180, 236, 100, 23);
		
		contentPane.add(devComboBox3);
		devComboBox1.setBounds(180, 170, 100, 23);
		
		contentPane.add(devComboBox1);
		testerComboBox1.setBounds(342, 170, 100, 23);
		
		contentPane.add(testerComboBox1);
		testerComboBox3.setBounds(342, 236, 100, 23);
		
		contentPane.add(testerComboBox3);
		
		setComboBox();
	}
	
	private void setComboBox() {
		JComboBox[] plArr = {plComboBox};
		JComboBox[] devArr = {devComboBox1, devComboBox2, devComboBox3};
		JComboBox[] testerArr = {testerComboBox1, testerComboBox2, testerComboBox3}; 
		
		ArrayList<String> pls = controller.getUserByLevel(1);
		for(String user : pls) {
			for(JComboBox box : plArr) {
				box.addItem(user);
			}
		}
		ArrayList<String> devs = controller.getUserByLevel(2);
		for(String user : devs) {
			for(JComboBox box : devArr) {
				box.addItem(user);
			}
		}
		ArrayList<String> testers = controller.getUserByLevel(3);
		for(String user : testers) {
			for(JComboBox box : testerArr) {
				box.addItem(user);
			}
		}
		revalidate();
		repaint();		
	}
	
	// END----
}