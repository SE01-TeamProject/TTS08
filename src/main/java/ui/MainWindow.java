package ui;

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
	private TicketPanel ticketPanel = new TicketPanel();
	private TimelinePanel timelinePanel = new TimelinePanel();
	private AdminPanel adminPanel = new AdminPanel();
	
	
	private JButton homeBtn;
	private JButton logoutBtn;
	private JButton timelineBtn;
	private JButton ticketBtn;
	private JButton adminBtn;
	private JButton addBtn;
	private JTextField userIdTextField;
	
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
		System.out.println("Main Window Create with Controller");
		
		controller = sc;
		projectPanel = new ProjectPanel(controller);
		ticketPanel = new TicketPanel(controller);
		timelinePanel = new TimelinePanel(controller);
		adminPanel = new AdminPanel(controller);
		this.setDpPanel(projectPanel);
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
		dpPanel = projectPanel;
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
		
		// Add Btn
		addBtn = makeAddBtn("Add Ticekt");
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
	
	public void setUser(String id) {
		userIdTextField.setText("USER: " + id);
	}
	
	
	// Buttons -----------------------------------------------------------------------------------------------
	private JButton makeHomeBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Home Btn ACtion Listenser
				System.out.println("Home btn pressed!");
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
	
	private JButton makeTimelineBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Timeline Btn ACtion Listenser
				System.out.println("Timeline btn pressed!");
				if(controller.getProjectFlag()) {
					setDpPanel(timelinePanel);
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
				setDpPanel(adminPanel);
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
					
				}
			}
		});
		btn.setBounds(1138, 799, 114, 37);
		return btn;
		
	}
}
