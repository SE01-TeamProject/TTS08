package com.example.demo.ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

//project title -> title api -> project info get -> attribute (dev123, tester123, pl) -> get userlevel (already exist)

public class ProjectUserWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SwingController controller;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton findBtn = new JButton();
	
	private JComboBox<String> projectComboBox = new JComboBox<String>();
	private JComboBox<String> searchCategotyBox = new JComboBox<String>();
	
	private JTable userTable;
	private TableRowSorter<DefaultTableModel> sorter;
	
	public ProjectUserWindow(SwingController sc) {
		this();
		controller = sc;
		setComboBox();
		
		findBtn = new JButton("Find");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTable();
			}
		});
		
		contentPane.add(findBtn, BorderLayout.SOUTH);
		contentPane.add(projectComboBox, BorderLayout.NORTH);
		
		
		scrollPane = new JScrollPane(userTable);
		scrollPane.setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	public ProjectUserWindow() {
		setTitle("Project Users");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));		


		
		
	}
	
	public void setComboBox() {
		// init combobox
		projectComboBox.removeAllItems();
		
		ArrayList<String> projects = controller.getProjectTitles();
		for(String project : projects) {
			projectComboBox.addItem(project);
		}
		
		revalidate();
		repaint();
	}
	
	public void setFrame() {
		setComboBox();
		//System.out.println(userTable);
	}
	
	public void setTable() {
		contentPane.remove(scrollPane);
		userTable = getTable((String) projectComboBox.getSelectedItem());		
		scrollPane = new JScrollPane(userTable);
		scrollPane.setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		revalidate();
		repaint();
	}
	
	public JTable getTable(String projectTitle) {
		String[] header = controller.getProjectUsersHeader();
		String[][] contents = controller.getProjectUserContents(projectTitle);
		
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
		
		sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
		
		return table;
		
	}
	
}


