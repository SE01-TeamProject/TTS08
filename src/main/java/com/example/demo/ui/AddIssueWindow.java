package com.example.demo.ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddIssueWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private SwingController controller;
	
	private JPanel contentPane;
	
	
	private JTextField titleTextField;
	private JButton addBtn = new JButton("add");
	private JComboBox<String> priorityComboBox = new JComboBox<String>();	
	private JComboBox<String> typeComboBox = new JComboBox<String>();
	private JTextArea descriptionTextArea = new JTextArea();
	
	public AddIssueWindow(SwingController sc) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		setBounds(100, 100, 430, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		controller = sc;
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(10, 10, 70, 15);
		contentPane.add(titleLabel);
		
		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setBounds(200, 10, 50, 15);
		contentPane.add(priorityLabel);
		
		JLabel typeLable = new JLabel("Type");
		typeLable.setBounds(310, 10, 50, 15);
		contentPane.add(typeLable);
		
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(10, 70, 100, 15);
		contentPane.add(descriptionLabel);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(10, 30, 180, 30);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Issue Add btn action listener
				
				addIssue();
				setVisible(false);
			}
		});
		
		addBtn.setBounds(310, 265, 93, 23);
		contentPane.add(addBtn);

		priorityComboBox.setBounds(200, 30, 100, 30);
		contentPane.add(priorityComboBox);

		typeComboBox.setBounds(310, 30, 100, 30);
		contentPane.add(typeComboBox);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setBounds(10, 90, 400, 100);
		contentPane.add(descriptionTextArea);
		
		setComboBoxes();
		
	}
	
	public void setComboBoxes() {
		priorityComboBox.removeAllItems();
		typeComboBox.removeAllItems();
		
		ArrayList<String> priorities = controller.getPriorities();
		ArrayList<String> types = controller.getTypes();
		
		for(String pri : priorities) {
			priorityComboBox.addItem(pri);
		}
		for(String type : types) {
			typeComboBox.addItem(type);
		}
		repaint();
		revalidate();
	}
	
	private void addIssue() {
		String title = titleTextField.getText();
		String priority = (String) priorityComboBox.getSelectedItem();
		String type = (String) typeComboBox.getSelectedItem();
		String description = descriptionTextArea.getText();
		
		controller.addIssue(title, priority, type, description);
	}
	
}
