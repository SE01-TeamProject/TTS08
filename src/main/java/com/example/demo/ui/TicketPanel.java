package com.example.demo.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class TicketPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public TicketPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Ticket Panel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("TicketPanel");
			}
		});
		add(btnNewButton);
		

	}

}
