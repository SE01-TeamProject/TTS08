package com.example.demo.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;


public class TicketPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	private JTable tickets;
	private JScrollPane scrollPane;
	
	
	public TicketPanel(SwingController sc) {
		this();
		controller = sc;
		tickets = tempTable();
		scrollPane = new JScrollPane(tickets);
		add(scrollPane);
	}
	
	public TicketPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new BorderLayout(0, 0));
		
		/*
		JButton btnNewButton = new JButton("Ticket Panel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("TicketPanel");
			}
		});
		add(btnNewButton);
		*/

	}
	
	public JTable tempTable() {
		String header[] = controller.getIssueHeader();
		String contents[][] = controller.getIssueContent();
		
		JTable table = new JTable();
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};		
		table.setModel(tableModel);
		
		// Table Header
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() < 2) {
					return;
				}
				// TODO Get Double-Clicked Table's Header -> Use if-else to do Sorting & Updating
				int columnIndex = tableHeader.columnAtPoint(e.getPoint());
				String columnName = table.getColumnName(columnIndex);
				System.out.println(columnName);
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
