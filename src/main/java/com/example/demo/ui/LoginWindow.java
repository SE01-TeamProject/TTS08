package com.example.demo.ui;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JLabel laberTop;

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
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idTextField = new JTextField();
		idTextField.setText("ID");
		idTextField.setBounds(12, 54, 116, 21);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JTextField();
		pwTextField.setText("Password");
		pwTextField.setBounds(12, 99, 116, 21);
		contentPane.add(pwTextField);
		pwTextField.setColumns(10);
		
		
		//Login Button -> Edit action listener to add action of btn
		JButton loginBtn = new JButton("Sign in");
		loginBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit Here
				System.out.println("LoginButton pressed");
				System.out.println("ID : " + idTextField.getText());
				System.out.println("Password : " + pwTextField.getText() + "\n");
			}
		});
		loginBtn.setBounds(175, 54, 97, 66);
		contentPane.add(loginBtn);
		
		
		laberTop = new JLabel("Issue Ticketing System");
		laberTop.setFont(new Font("굴림", Font.PLAIN, 16));
		laberTop.setBackground(new Color(255, 255, 255));
		laberTop.setHorizontalAlignment(SwingConstants.CENTER);
		laberTop.setBounds(12, 23, 260, 15);
		contentPane.add(laberTop);
	}
}

	
