package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class MainWindow {


	
	private MainFrame frame;
	
	private static int idgame=0;

	
	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame(idgame);
		frame.setBounds(100, 60, 1000, 700);
		
		MapPanel mpanel = new MapPanel();
		mpanel.initialize(frame);
		frame.getContentPane().add(mpanel, BorderLayout.CENTER);

		
		
		InfoPanel infobox = new InfoPanel();
		infobox.initialize(frame);
		frame.getContentPane().add(infobox, BorderLayout.EAST);
		

		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
