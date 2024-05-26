package com.example.demo.ui;

import javax.swing.JFrame;
import java.awt.*;

public class SwingApp {

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MainWindow();

			        frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
    }
    
}