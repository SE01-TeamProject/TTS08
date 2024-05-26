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
	
	private ProjectPanel testProjectPanel = new ProjectPanel();
	private TicketPanel testTicketPanel = new TicketPanel();
	private TimelinePanel testTimelinePanel = new TimelinePanel();
	private ArrayList<JPanel> panelList = new ArrayList<JPanel>();{{
		panelList.add(testProjectPanel);
		panelList.add(testTimelinePanel);
		panelList.add(testTicketPanel);
	}};
	
	private JButton homeBtn;
	private JButton timelineBtn;
	private JButton ticketBtn;
	private JButton adminBtn;
	private JButton addBtn;
	
	/* Private project -> Selected project. If project NOT SELECTED -> btn disabled
	 * 
	 *
	 *
	 **/
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow(SwingController sc) {
		this();
		controller = sc;
	}
	public MainWindow() {
		super("Issue Ticketing System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Panels
		// DP Panel
		dpPanel = testProjectPanel;
		dpPanel.setBounds(12, 189, 1240, 600);
		contentPane.add(dpPanel);
		
		// Btn Panel
		btnPanel = new JPanel();
		btnPanel.setBounds(352, 119, 900, 60);
		contentPane.add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		// Buttons
		// Home Btn
		homeBtn = makeHomeBtn("HOME");
		contentPane.add(homeBtn);
		
		// Timeline Btn
		timelineBtn = makeTimelineBtn("Timeline");
		btnPanel.add(timelineBtn);
		
		// Ticket Btn
		ticketBtn = makeTicketBtn("Tickets");		
		btnPanel.add(ticketBtn);
		
		// Admin Btn
		adminBtn = makeAdminBtn("Admin");
		btnPanel.add(adminBtn);
		
		addBtn = makeAddBtn("Add");
		contentPane.add(addBtn);
		
	}
	
	public void setDpPanel(JPanel panel) {
		
		System.out.print("setDpPanel: ");
		System.out.println(panel);
		
		contentPane.remove(dpPanel);
		dpPanel = panel;		
		dpPanel.setBounds(12, 189, 1240, 600);
		contentPane.add(dpPanel);
		
		dpPanel.revalidate();
		dpPanel.repaint();
	}
	
	
	// Buttons -----------------------------------------------------------------------------------------------
	private JButton makeHomeBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Home Btn ACtion Listenser
				System.out.println("Home btn pressed!");
				setDpPanel(testProjectPanel);
			}
		});
		btn.setBounds(12, 10, 150, 53);		
		return btn;
		
	}
	
	private JButton makeTimelineBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Timeline Btn ACtion Listenser
				System.out.println("Timeline btn pressed!");
				setDpPanel(testTimelinePanel);
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
				setDpPanel(testTicketPanel);
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
			}
		});
		return btn;
	}
	
	private JButton makeAddBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add Btn ACtion Listener
				System.out.println("Add btn pressed!");
			}
		});
		btn.setBounds(1138, 799, 114, 37);
		return btn;
		
	}
}
