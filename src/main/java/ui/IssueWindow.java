package ui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.*;
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
	private JComboBox<String> milestoneComboBox = new JComboBox();	
	private JComboBox<String> assigneeComboBox = new JComboBox();
	private JTextArea decriptionTextArea = new JTextArea();
	private JTextArea commentTextArea = new JTextArea();
	private JTextField newCommentTextField = new JTextField();
	
	private JLabel numberLabel = new JLabel();
	private JLabel titleLabel = new JLabel();
	private JLabel reporterLabel = new JLabel();
	
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
	
	public IssueWindow(SwingController sc) {
		this();
		controller = sc;
		
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
	}
	
	public IssueWindow() {
		setTitle("Issue Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 900);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		commentPanel = new JScrollPane();
		commentPanel.setBounds(12, 565, 450, 200);
		contentPane.add(commentPanel);
		
		descriptionPanel = new JScrollPane();
		descriptionPanel.setBounds(12, 335, 450, 200);
		contentPane.add(descriptionPanel);
		
		commentTextArea = new JTextArea();
		commentTextArea.setEnabled(false);
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
				setTitle("save");
			}
		});
		saveBtn.setBounds(475, 54, 97, 38);
		contentPane.add(saveBtn);
		
			// Edit btn
		editBtn = new JButton("Edit");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit btn action listener
				setTitle("edit");
			}
		});
		editBtn.setBounds(475, 6, 97, 38);
		contentPane.add(editBtn);
		
		// Combo Boxes		
		priorityComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Blocker", "Critical", "Major", "Minor", "Trivial"}));
		priorityComboBox.setBounds(178, 113, 116, 32);
		contentPane.add(priorityComboBox);
		
		stateComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"New", "Assigned", "Resolved", "Closed", "Reopened"}));
		stateComboBox.setBounds(12, 113, 116, 32);
		contentPane.add(stateComboBox);
		
		milestoneComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Milestone1", "Milestone2", "Milestone3", "Milestone4"}));
		milestoneComboBox.setBounds(12, 215, 116, 32);
		contentPane.add(milestoneComboBox);
		
		assigneeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"None"}));
		assigneeComboBox.setBounds(178, 215, 116, 32);
		contentPane.add(assigneeComboBox);		
		
		decriptionTextArea = new JTextArea();
		decriptionTextArea.setEnabled(false);
		decriptionTextArea.setEditable(false);
		descriptionPanel.setViewportView(decriptionTextArea);

		newCommentTextField = new JTextField();
		newCommentTextField.setText("");
		newCommentTextField.setBounds(12, 795, 450, 32);
		contentPane.add(newCommentTextField);
		newCommentTextField.setColumns(10);
	}
	
	public void setNumber(String str) {
		if(numberLabel != null) {
			numberLabel.setText(str);
			redraw();
		}
	}
	public void setNumber(int num) {
		if(numberLabel != null) {
			numberLabel.setText("" + num);
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
			reporterLabel.setText(str);
			redraw();
		}
	}

	public void initPriorityCBox() {
		if(controller != null) {
			ArrayList<String> list = controller.getPriorties();
			String priority[] = list.toArray(new String[list.size()]);
			priorityComboBox.setModel(new DefaultComboBoxModel<String>(priority));
		}
		
	}
	
	public void initStateCBox() {
		if(controller != null) {
			ArrayList<String> list = controller.getStates();
			String priority[] = list.toArray(new String[list.size()]);
			priorityComboBox.setModel(new DefaultComboBoxModel<String>(priority));
		}
		
	}
	
 	private void redraw() {
		if(contentPane != null) {
			contentPane.revalidate();
			contentPane.repaint();
		}
	}
	
}
