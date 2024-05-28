package com.example.demo.ui;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private SwingController controller;
	
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JLabel labelTop;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow(SwingController sc) {
		this();
		controller = sc;
		
	}
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 300, 200);
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
		
		idTextField = new JTextField();
		idTextField.setText("");
		idTextField.setBounds(12, 54, 116, 21);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JTextField();
		pwTextField.setText("");
		pwTextField.setBounds(12, 99, 116, 21);
		contentPane.add(pwTextField);
		pwTextField.setColumns(10);
		
		
		//Login Button -> Edit action listener to add action of btn
		JButton loginBtn = new JButton("Sign in");
		loginBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit Here
				String id = idTextField.getText();
				String pw = pwTextField.getText();
				boolean loginFlag = false;
				
				System.out.println("LoginButton pressed");
				System.out.println("ID : " + idTextField.getText());
				System.out.println("Password : " + pwTextField.getText() + "\n");
				
				
				if(controller != null) {
						loginFlag = controller.login(id, pw);
				}
			}
		});
		loginBtn.setBounds(175, 54, 97, 66);
		contentPane.add(loginBtn);
		
		
		labelTop = new JLabel("Issue Ticketing System");
		labelTop.setFont(new Font("굴림", Font.PLAIN, 16));
		labelTop.setBackground(new Color(255, 255, 255));
		labelTop.setHorizontalAlignment(SwingConstants.CENTER);
		labelTop.setBounds(12, 23, 260, 15);
		contentPane.add(labelTop);
	}
}

	
