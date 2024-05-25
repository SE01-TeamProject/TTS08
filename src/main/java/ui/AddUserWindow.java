package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

public class AddUserWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	
	private JPanel contentPane;
	
	private JButton addBtn;
	private JTextField idTextField = new JTextField();
	private JTextField pwTextField = new JTextField();
	private JTextField nameTextField = new JTextField();;
	private JComboBox<String> levelComboBox = new JComboBox<String>();
	
	
	public AddUserWindow(SwingController sc) {
		this();
		if(sc != null) {
			System.out.println("Not Null on add user window!");
			controller = sc;
			setLevelCBox();
			setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
		}
		
	}

	public AddUserWindow() {
		super("Add User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addBtn = makeAddBtn("add");
		contentPane.add(addBtn);
		
		// User ID
		idTextField = new JTextField();
		idTextField.setBounds(12, 35, 140, 30);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel idLabel = new JLabel("User ID");
		idLabel.setBounds(12, 10, 60, 15);
		contentPane.add(idLabel);
		
		// Password
		pwTextField = new JTextField();
		pwTextField.setColumns(10);
		pwTextField.setBounds(200, 35, 140, 30);
		contentPane.add(pwTextField);
		
		JLabel pwLabel = new JLabel("password");
		pwLabel.setBounds(200, 10, 60, 15);
		contentPane.add(pwLabel);
		
		// Name
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(12, 110, 328, 30);
		contentPane.add(nameTextField);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(12, 85, 60, 15);
		contentPane.add(nameLabel);
		
		
		// Access level
		levelComboBox.setBounds(12, 185, 100, 30);
		contentPane.add(levelComboBox);
		
		JLabel levelLabel = new JLabel("level");
		levelLabel.setBounds(12, 160, 60, 15);
		contentPane.add(levelLabel);
		
	}
	private void setLevelCBox() {
		if(controller != null) {
			ArrayList<String> list = controller.getLevelString();
			String levels[] = list.toArray(new String[list.size()]);
			levelComboBox.setModel(new DefaultComboBoxModel<String>(levels));
			redraw();
		}
	}
	
	private JButton makeAddBtn(String str) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add User!");
				if(controller != null) {
					String id = idTextField.getText();
					String pw = pwTextField.getText();
					String name = nameTextField.getText();
					String level = levelComboBox.getSelectedItem().toString();
					
					System.out.println(id.isEmpty());
					
					if(!id.isEmpty() && !pw.isEmpty() && !name.isEmpty()) {
						controller.addUser(id, pw, name, level);
						setVisible(false);
					}
					
				}
			}
		});
		btn.setBounds(243, 228, 97, 23);
		return btn;
	}
	private void redraw() {
		contentPane.revalidate();
		contentPane.repaint();
	}
}
