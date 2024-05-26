package com.example.demo.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class IssueWindow extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField newCommentTextField;
	
	private JButton editBtn;
	private JButton saveBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueWindow frame = new IssueWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IssueWindow() {
		setTitle("Issue Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 900);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		JScrollPane commentPanel = new JScrollPane();
		commentPanel.setBounds(12, 565, 450, 200);
		contentPane.add(commentPanel);
		
		JScrollPane descriptionPanel = new JScrollPane();
		descriptionPanel.setBounds(12, 335, 450, 200);
		contentPane.add(descriptionPanel);
		
		JTextArea commentTextArea = new JTextArea();
		commentTextArea.setEnabled(false);
		commentTextArea.setEditable(false);
		commentPanel.setViewportView(commentTextArea);
		
		
		// Labels
			// Number
		JLabel numberLabel = new JLabel("number");
		numberLabel.setBounds(12, 10, 93, 15);
		contentPane.add(numberLabel);
		
			// Reporter
		JLabel reporterLabel = new JLabel("Reporter_Name");
		reporterLabel.setBounds(117, 10, 103, 15);
		contentPane.add(reporterLabel);
			
			// Title
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 15));
		titleLabel.setBounds(12, 35, 300, 32);
		contentPane.add(titleLabel);
		
			// State
		JLabel stateLabel = new JLabel("State");
		stateLabel.setBounds(12, 93, 57, 15);
		contentPane.add(stateLabel);
		
			// Priority
		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setBounds(178, 93, 57, 15);
		contentPane.add(priorityLabel);
		
			// Assignee
		JLabel assigneeLabel = new JLabel("Assignee");
		assigneeLabel.setBounds(178, 195, 57, 15);
		contentPane.add(assigneeLabel);
		
			// Milestone
		JLabel milestoneLabel = new JLabel("Milestone");
		milestoneLabel.setBounds(12, 195, 57, 15);
		contentPane.add(milestoneLabel);
		
			// Description
		JLabel decriptionLabel = new JLabel("Description");
		decriptionLabel.setBounds(12, 320, 140, 15);
		contentPane.add(decriptionLabel);
				
			// Comment
		JLabel commentLabel = new JLabel("Comments");
		commentLabel.setBounds(12, 550, 140, 15);
		contentPane.add(commentLabel);
			// New Comment
		JLabel newCommentLabel = new JLabel("New Comment");
		newCommentLabel.setBounds(12, 780, 140, 15);
		contentPane.add(newCommentLabel);
		
		
		// Buttons
			// Save btn
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save btn action listener
			}
		});
		saveBtn.setBounds(475, 54, 97, 38);
		contentPane.add(saveBtn);
		
			// Edit btn
		editBtn = new JButton("Edit");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit btn action listener
			}
		});
		editBtn.setBounds(475, 6, 97, 38);
		contentPane.add(editBtn);
		
		
		
		// Combo Boxes
		
		JComboBox priorityComboBox = new JComboBox();
		priorityComboBox.setModel(new DefaultComboBoxModel(new String[] {"Blocker", "Critical", "Major", "Minor", "Trivial"}));
		priorityComboBox.setBounds(178, 113, 116, 32);
		contentPane.add(priorityComboBox);
		
		JComboBox stateComboBox = new JComboBox();
		stateComboBox.setModel(new DefaultComboBoxModel(new String[] {"New", "Assigned", "Resolved", "Closed", "Reopened"}));
		stateComboBox.setBounds(12, 113, 116, 32);
		contentPane.add(stateComboBox);
		
		JComboBox priorityComboBox_1 = new JComboBox();
		priorityComboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Milestone1", "Milestone2", "Milestone3", "Milestone4"}));
		priorityComboBox_1.setBounds(12, 215, 116, 32);
		contentPane.add(priorityComboBox_1);
		
		JComboBox priorityComboBox_1_1 = new JComboBox();
		priorityComboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"None"}));
		priorityComboBox_1_1.setBounds(178, 215, 116, 32);
		contentPane.add(priorityComboBox_1_1);
		
		
		
		
		
		JTextArea decriptionTextArea = new JTextArea();
		decriptionTextArea.setEnabled(false);
		decriptionTextArea.setEditable(false);
		descriptionPanel.setViewportView(decriptionTextArea);
		
		
		
		newCommentTextField = new JTextField();
		newCommentTextField.setText("Write Comment");
		newCommentTextField.setBounds(12, 795, 450, 32);
		contentPane.add(newCommentTextField);
		newCommentTextField.setColumns(10);
	}
	
}
