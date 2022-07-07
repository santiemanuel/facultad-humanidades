package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.milprogramadores.dao.DAOManager;

import net.miginfocom.swing.MigLayout;

public class WelcomeScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static DAOManager dao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dao = new DAOManager();
					
					WelcomeScreen frame = new WelcomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WelcomeScreen() {
		setTitle("Sistema de la Facultad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Facultad de Humanidades");
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 26));
		
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[][][][][][][][][][][][]", "[][][][][][]"));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("images/image.png"));
		lblLogo.setVerticalTextPosition(JLabel.BOTTOM);
		lblLogo.setVerticalTextPosition(JLabel.CENTER);
		panel_1.add(lblLogo, "cell 4 0");
		
		JButton btnLogin = new JButton("Iniciar Sesi\u00F3n");
		panel_1.add(btnLogin, "cell 1 5");
		
		
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();
				LoginDialog dialog = new LoginDialog();
				dialog.setVisible(true);
			}	
		});

		JButton btnSignUp = new JButton("Registrarse");
		panel_1.add(btnSignUp, "cell 11 5");
		
		setLocationRelativeTo(null);
	}

}
