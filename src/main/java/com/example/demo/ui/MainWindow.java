package com.example.demo.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	//private control control
	private SwingController controller;
	
	
	private JPanel contentPane;
	private JPanel dpPanel;
	private JPanel btnPanel;
	private ProjectPanel projectPanel = new ProjectPanel();
	private TicketPanel ticketPanel;
	private StatisticPanel statisticPanel = new StatisticPanel();
	private AdminPanel adminPanel = new AdminPanel();
	
	
	private JButton homeBtn;
	private JButton logoutBtn;
	private JButton statisticBtn;
	private JButton ticketBtn;
	private JButton adminBtn;
	private JButton addBtn;
	private JButton searchBtn;
	private JTextField userIdTextField;
	private JTextField searchTextField;
	private JComboBox<String> searchTypeComboBox = new JComboBox<String>();

	public MainWindow(SwingController sc) {
		this();
		controller = sc;		
		projectPanel = new ProjectPanel(controller);
		ticketPanel = new TicketPanel(controller);
		statisticPanel = new StatisticPanel(controller);
		adminPanel = new AdminPanel(controller);
		
		setComboBox();
		
		this.setDpPanel(projectPanel);
	}
	
	public MainWindow() {
		super("Issue Ticketing System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		// Panels
		// DP Panel
		dpPanel = projectPanel;
		dpPanel.setBounds(12, 143, 1240, 600);
		contentPane.add(dpPanel);
		
		// Btn Panel
		btnPanel = new JPanel();
		btnPanel.setBounds(352, 73, 900, 60);
		contentPane.add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 3, 0, 0));	
		
		// Buttons
		// Home Btn
		homeBtn = makeHomeBtn("HOME");
		contentPane.add(homeBtn);
		
		// Timeline Btn
		statisticBtn = makeStatisticBtn("Statistic");
		btnPanel.add(statisticBtn); // TODO Change into Statistic
		
		// Ticket Btn
		ticketBtn = makeTicketBtn("Tickets");		
		btnPanel.add(ticketBtn);
		
		// Admin Btn
		adminBtn = makeAdminBtn("Admin");
		btnPanel.add(adminBtn);
		
		// Add Btn
		addBtn = makeAddBtn("Add Ticket");
		contentPane.add(addBtn);
		
		// Logout Btn
		logoutBtn = makeLogoutBtn("Log Out");
		contentPane.add(logoutBtn);
		
		// Show Current User ID
		userIdTextField = new JTextField();
		userIdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		userIdTextField.setText("USER: temp_name");
		userIdTextField.setEditable(false);
		userIdTextField.setBounds(947, 18, 196, 37);
		contentPane.add(userIdTextField);
		userIdTextField.setColumns(10);
		
		// About Searching
		searchTextField = new JTextField();
		searchTextField.setBounds(107, 760, 160, 23);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton searchBtn = makeSearchBtn("Search");		//189, 760, 93, 23 (search btn bound)
		contentPane.add(searchBtn);
		
		searchTypeComboBox.setBounds(12, 760, 90, 23);
		contentPane.add(searchTypeComboBox);
	}
	
	public void setComboBox() {
		searchTypeComboBox.removeAllItems();
		String[] types = controller.getIssueHeader();
		
		for(String type : types) {
			searchTypeComboBox.addItem(type);
		}
		
		revalidate();
		repaint();
	}
	
	// Panels
	public void setDpPanel(JPanel panel) {
		System.out.print("setDpPanel: ");
		System.out.println(panel);
		
		contentPane.remove(dpPanel);
		dpPanel = panel;		
		dpPanel.setBounds(12, 143, 1240, 600);
		contentPane.add(dpPanel);
		
		dpPanel.revalidate();
		dpPanel.repaint();
	}
	
	public void setUser(String id) {
		userIdTextField.setText("USER: " + id);
	}
	
	public void updateTicketPanel() {
		ticketPanel = new TicketPanel(controller);
	}
	
	public void updateProjectPanel() {
		projectPanel = new ProjectPanel(controller);
	}
	
	public void updateStatisticPanel() {
		statisticPanel = new StatisticPanel(controller);
	}
	
	// Buttons -----------------------------------------------------------------------------------------------
	private JButton makeHomeBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Home Btn ACtion Listenser
				System.out.println("Home btn pressed!");
				updateProjectPanel();
				setDpPanel(projectPanel);
			}
		});
		btn.setBounds(12, 10, 150, 53);		
		return btn;
	}
	
	private JButton makeLogoutBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Home Btn ACtion Listenser
				System.out.println("Logout btn pressed!");
				if(controller != null) {
					controller.logout();
				}
			}
		});
		btn.setBounds(1155, 10, 97, 53);
		return btn;
	}
	
	private JButton makeStatisticBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Timeline Btn ACtion Listenser
				System.out.println("Timeline btn pressed!");
				if(controller.getProjectFlag()) {
					updateStatisticPanel();
					setDpPanel(statisticPanel);
				}
			}
		});
		
		return btn;
	}
	
	private JButton makeTicketBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ticket Btn ACtion Listener
				System.out.println("Ticket btn pressed!");
				if(controller.getProjectFlag()) {
					updateTicketPanel();
					setDpPanel(ticketPanel);
				}
			}
		});
		return btn;
	}
	
	private JButton makeAdminBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Admin Btn ACtion Listener
				System.out.println("Admin btn pressed!");
				if(controller.getCurrUserInfo("level").equals("0")) {
					updateTicketPanel();
					setDpPanel(adminPanel);
				}
				
			}
		});
		return btn;
	}
	
	private JButton makeAddBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add Ticket Btn ACtion Listener
				System.out.println("Add btn pressed!");
				if(controller.getProjectFlag()) {
					// Adding Ticket Here
					AddIssueWindow frame = new AddIssueWindow(controller);
					frame.setVisible(true);
				}
			}
		});
		btn.setBounds(1138, 753, 114, 37);
		return btn;	
	}
	
	private JButton makeSearchBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Search Issue Btn ACtion Listener
				System.out.println("Search btn pressed!");
				if(controller.getProjectFlag()) {
					String text = searchTextField.getText();
					int type = searchTypeComboBox.getSelectedIndex();
					System.out.println("Search with [" + type + " / " + text + "]");
					searchTextField.setText("");
					
					ticketPanel.search(text, type);
				}
			}
		});
		btn.setBounds(272, 760, 93, 23);
		return btn;	
	}
}
