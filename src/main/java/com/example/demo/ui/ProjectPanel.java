package com.example.demo.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;


public class ProjectPanel extends JPanel {	
	private static final long serialVersionUID = 1L;
	private SwingController controller;
	private JButton testBtn;
	private JTable projects;
	private JScrollPane scrollPane;
	
	
	public ProjectPanel(SwingController sc) {
		this();
		controller = sc;
		
		/*
		testBtn = new JButton("Project Panel");
		testBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Example. Should be checked from model (selec project)
				System.out.println("ProjectPanel");
				if(controller != null) {
					controller.projectSelect();
				}
				else {
					System.out.println("Null Controller for ProjectPanel");
				}
			}
		});
		add(testBtn, BorderLayout.CENTER);
		*/
		setTable();
		scrollPane = new JScrollPane(projects);
		add(scrollPane);
	}
	
	public ProjectPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
	}
	
	public void setTable() {
		projects = getTable();
		revalidate();
		repaint();
	}
	
	public JTable getTable() {
		/*	1. Get table header
		 * 	2. Get project name & description as 2D-Array (from controller.
		 * 		-> Controller will get it from model as JSON
		 */
		String header[] = {"Title", "Description", "Date"};
		String contents[][] = controller.getProjectContent();
		
		JTable table = new JTable();
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};		
		table.setModel(tableModel);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Get Double-Clicked Table's Record -> show Ticket Information
				if(e.getClickCount() < 2) {
					return;
				}
				
				int rowIndex = table.rowAtPoint(e.getPoint());
				if (rowIndex != -1) {
                    // Get the data from the selected row
                    int columnCount = table.getColumnCount();
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        rowData[i] = table.getValueAt(rowIndex, i);
                    }

                    // Print the data of the selected row
                    System.out.println("Selected row data: ");
                    for (Object data : rowData) {
                        System.out.print(data + " ");
                    }
                    
                    System.out.println();
                }
			}
			public void mousePressed(MouseEvent e) {				
			}
			public void mouseReleased(MouseEvent e) {				
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		
		return table;
	}
	
}
