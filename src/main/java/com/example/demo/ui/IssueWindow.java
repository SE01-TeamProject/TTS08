package com.example.demo.ui;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.*;

import org.json.JSONObject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class IssueWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private SwingController controller;
	
	private JPanel contentPane;	
	private JScrollPane descriptionPanel;
	private JScrollPane commentPanel;
	
	private JButton editBtn;
	private JButton saveBtn;
	
	private JComboBox<String> priorityComboBox = new JComboBox();
	private JComboBox<String> stateComboBox = new JComboBox();
	private JComboBox<String> assigneeComboBox = new JComboBox();
	private JTextArea descriptionTextArea = new JTextArea();
	private JTextArea commentTextArea = new JTextArea();
	private JTextField newCommentTextField = new JTextField();
	private JTextField recommendTextField = new JTextField();
	private JTextField fixerTextField = new JTextField();
	
	private JLabel numberLabel = new JLabel();
	private JLabel titleLabel = new JLabel();
	private JLabel reporterLabel = new JLabel();
	private String ticketIDString = "";
	
	private Component[] level0comp = {editBtn, saveBtn, priorityComboBox,
									stateComboBox, assigneeComboBox};
	
	private Component[] level1comp = {editBtn, saveBtn, priorityComboBox,
									stateComboBox, assigneeComboBox};
	
	private Component[] level2comp = {editBtn, saveBtn, priorityComboBox,
									stateComboBox};
	
	private Component[] level3comp = {editBtn, saveBtn, priorityComboBox,
									stateComboBox};
	

	/*
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
	}*/
	
	
	
	public IssueWindow(SwingController sc, String ticketTitle) {
		this();
		controller = sc;
		
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		updateValues(ticketTitle);
		lockAccessibility();
				
		recommendTextField.setText(recommendAssignee());
		redraw();
	}
	
	public IssueWindow() {
		setTitle("Issue Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 760);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		commentPanel = new JScrollPane();
		commentPanel.setBounds(12, 440, 450, 200);
		contentPane.add(commentPanel);
		
		descriptionPanel = new JScrollPane();
		descriptionPanel.setBounds(12, 200, 450, 200);
		contentPane.add(descriptionPanel);
		
		commentTextArea = new JTextArea();
		commentTextArea.setEditable(false);
		commentPanel.setViewportView(commentTextArea);
		
		
		// Labels
			// **Number
		numberLabel = new JLabel("number");
		numberLabel.setBounds(12, 10, 93, 15);
		contentPane.add(numberLabel);
		
			// **Reporter
		reporterLabel = new JLabel("Reporter_Name");
		reporterLabel.setBounds(117, 10, 103, 15);
		contentPane.add(reporterLabel);
			
			// **Title
		titleLabel = new JLabel("Title");
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
		assigneeLabel.setBounds(344, 93, 57, 15);
		contentPane.add(assigneeLabel);
		
			// Description
		JLabel decriptionLabel = new JLabel("Description");
		decriptionLabel.setBounds(12, 180, 140, 15);
		contentPane.add(decriptionLabel);
				
			// Comment
		JLabel commentLabel = new JLabel("Comments");
		commentLabel.setBounds(12, 420, 140, 15);
		contentPane.add(commentLabel);
			// New Comment
		JLabel newCommentLabel = new JLabel("New Comment");
		newCommentLabel.setBounds(12, 660, 140, 15);
		contentPane.add(newCommentLabel);
		
			// Recommend Assignee
		JLabel recommendLabel = new JLabel("Recommend");
		recommendLabel.setBounds(344, 29, 103, 15);
		contentPane.add(recommendLabel);
		
		JLabel fixerLabel = new JLabel("Fixer");
		fixerLabel.setBounds(178, 29, 103, 15);
		contentPane.add(fixerLabel);
		
		// Buttons
			// Save btn
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save btn action listener
				setIssue();
				addComment();
				controller.updateMainWindow();
				setVisible(false);
			}
		});
		saveBtn.setBounds(475, 110, 97, 38);
		contentPane.add(saveBtn);
		
			// Edit btn
		editBtn = new JButton("Edit");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit btn action listener
				unlockAccessibility();
			}
		});
		editBtn.setBounds(475, 47, 97, 38);
		contentPane.add(editBtn);
		
		// Combo Boxes		
		//priorityComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Blocker", "Critical", "Major", "Minor", "Trivial"}));
		priorityComboBox.setBounds(178, 113, 116, 32);
		contentPane.add(priorityComboBox);
		
		//stateComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"New", "Assigned", "Resolved", "Closed", "Reopened"}));
		stateComboBox.setBounds(12, 113, 116, 32);
		contentPane.add(stateComboBox);
		
		//assigneeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None"}));
		assigneeComboBox.setBounds(346, 113, 116, 32);
		contentPane.add(assigneeComboBox);		
		
		descriptionTextArea = new JTextArea();
		descriptionPanel.setViewportView(descriptionTextArea);

		newCommentTextField = new JTextField();
		newCommentTextField.setText("");
		newCommentTextField.setBounds(12, 679, 450, 32);
		contentPane.add(newCommentTextField);
		newCommentTextField.setColumns(10);
		
		recommendTextField.setBounds(346, 51, 116, 32);
		contentPane.add(recommendTextField);
		recommendTextField.setColumns(10);
		recommendTextField.setEditable(false);
		
		fixerTextField.setBounds(178, 51 , 116, 32);
		contentPane.add(fixerTextField);
		fixerTextField.setText("N/A");
		fixerTextField.setEditable(false);
		
		
	}
	
	public void addComment() {
		String text = newCommentTextField
						.getText()
						.trim();
		
		if(!text.equals("")) controller.addComment(this.titleLabel.getText(), text);
	}
	
	public void setIssue() {
		/*
		obj.put("id", id);
		obj.put("priority", priority);
		obj.put("status", status);
		obj.put("assignee", assignee);
		obj.put("description", description);*/
		String id = ticketIDString;
		int priority = priorityComboBox.getSelectedIndex();
		
		String sta = (String) stateComboBox.getSelectedItem();
		int status = controller.getStatusIndex(sta);
		
		//int status = stateComboBox.getSelectedIndex();
		String assignee = (String) assigneeComboBox.getSelectedItem();
		String description = descriptionTextArea.getText();
		
		controller.setIssue(id, priority, status, assignee, description);
	}
	
	public void updateValues(String title) {
		String jsonString = controller.getIssueJsonByTitle(title);		
		setAll(jsonString);
	}
	
	public void setAll(String jsonString) {
		initComboBoxes();
		JSONObject obj = new JSONObject(jsonString);
		
		System.out.println(jsonString);
		
		String id = "" + obj.get("issuenum");
		String title = obj.getString("title");
		
		String reporter = obj.getString("reporter");
		String assignee = obj.getString("assignee");
		
		String priority = controller.getPriorityString(obj.getInt("priority"));
		String type = controller.getTypeString(obj.getInt("type"));
		String status = controller.getStatusString(obj.getInt("status"));
		
		String description = obj.getString("description");
		//String comment = controller.getIssueComment(id);
		
		setNumber(id);
		setTitle(title);
		setReporter(reporter);
		
		setPriority(priority);
		setAssignee(assignee);
		setStatus(status);
		
		setDescription(description);
		setComment(id);
		
		this.restrictStateCBox(status);
		
		if(!(status.equals("Assigned") || status.equals("New") || status.equals("Fixed"))) {
			fixerTextField.setText(assignee);
		}
		
	}
	
	public void setNumber(String str) {
		ticketIDString = "" + str;
		if(numberLabel != null) {
			numberLabel.setText("ID: " + ticketIDString);
			redraw();
		}
	}
	public void setNumber(int num) {
		ticketIDString = "" + num;
		if(numberLabel != null) {
			numberLabel.setText("ID: " + ticketIDString);
			redraw();
		}
	}
	
	public void setTitle(String str) {
		if(titleLabel != null) {
			titleLabel.setText(str);	
			redraw();
		}
	}
	
	public void setReporter(String str) {
		if(reporterLabel != null) {
			reporterLabel.setText("Reporter: " + str);
			redraw();
		}
	}
	
	public void setPriority(String priority) {
		this.priorityComboBox.setSelectedItem(priority);
	}

	public void setStatus(String status) {
		stateComboBox.setSelectedItem(status);
	}
	
	public void setAssignee(String devID) {
		assigneeComboBox.setSelectedItem(devID);
	}
	
	public void setDescription(String description) {
		descriptionTextArea.setText(description);
		descriptionTextArea.setEditable(false);
	}
	
	public void setComment(String issueID) {
		String comment = controller.getIssueComment(issueID);
		commentTextArea.setText(comment);
		commentTextArea.setEditable(false);
	}
	
	public void lockAccessibility() {
		for(Component compo : level0comp) {
			if(compo != null) compo.setEnabled(false);
		}
		newCommentTextField.setEditable(false);
	}
	
	public void unlockAccessibility() {
		int level = controller.getUserLevel();
		switch(level) {
		case 0:
			for(Component compo : level0comp) {
				if(compo != null) compo.setEnabled(true);
			}
			break;
		case 1:
			for(Component compo : level1comp) {
				if(compo != null) compo.setEnabled(true);
			}
			break;
		case 2:
			for(Component compo : level2comp) {
				if(compo != null) compo.setEnabled(true);
			}
			break;
		case 3:
			for(Component compo : level3comp) {
				if(compo != null) compo.setEnabled(true);
			}
			break;			
		}
		newCommentTextField.setEditable(true);
	}
	
	public void initComboBoxes() {
		//stateComboBox
		initPriorityCBox();
		initStateCBox();
		initAssigneeCBox();
	}
	
	public void initPriorityCBox() {
		priorityComboBox.removeAllItems();
		if(controller != null) {
			ArrayList<String> list = controller.getPriorities();
			String priority[] = list.toArray(new String[list.size()]);
			
			for(String pri : priority) {
				priorityComboBox.addItem(pri);
			}
			
			
			
		}
	}
	
	public void initStateCBox() {
		stateComboBox.removeAllItems();
		if(controller != null) {
			ArrayList<String> list = controller.getStatus();
			String status[] = list.toArray(new String[list.size()]);
			
			for(String sta : status) {
				stateComboBox.addItem(sta);
			}
		}
	}
	
	public void restrictStateCBox(String status) {
		stateComboBox.removeAllItems();
		stateComboBox.addItem(status);
		
		if(status.equals("New")) {
			stateComboBox.addItem("Assigned");
		}
		
		if(status.equals("Assigned")) {
			stateComboBox.addItem("Fixed");
			stateComboBox.addItem("Resolved");
			stateComboBox.addItem("Closed");
		}
		
		if(status.equals("Fixed")) {
			stateComboBox.addItem("Resolved");
			stateComboBox.addItem("Closed");
			stateComboBox.addItem("Assigned");
		}
		
		if(status.equals("Resolved")) {
			stateComboBox.addItem("Closed");
			stateComboBox.addItem("Assigned");
			stateComboBox.addItem("Fixed");
			
		}
		
		if(status.equals("Closed")) {
			stateComboBox.addItem("Reopened");
		}
		
		if(status.equals("Reopened")) {
			stateComboBox.addItem("Assigned");
			stateComboBox.addItem("Fixed");
			stateComboBox.addItem("Resolved");
			stateComboBox.addItem("Closed");			
		}
	}
	
	public void initAssigneeCBox() {
		assigneeComboBox.removeAllItems();
		if(controller != null) {
			ArrayList<String> list = controller.getUserByLevel(2);
			String devs[] = list.toArray(new String[list.size()]);
			
			//assigneeComboBox.addItem("N/A");
			for(String dev : devs) {
				assigneeComboBox.addItem(dev);
			}
		}
	}
	
	private String recommendAssignee() {
		String description = descriptionTextArea.getText();
		String assignee = controller.recommendAssignee(description);
		System.out.println("recommend: " + assignee);
		return assignee;
	}
	
 	private void redraw() {
		if(contentPane != null) {
			contentPane.revalidate();
			contentPane.repaint();
		}
	}
	
 	
 	
}
