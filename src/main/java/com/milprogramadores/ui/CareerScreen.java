package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CareerScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CareerScreen frame = new CareerScreen();
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
	public CareerScreen() {
		setTitle("Carreras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Top = new JPanel();
		contentPane.add(panel_Top, BorderLayout.NORTH);
		panel_Top.setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("Alumno: ");
		panel_Top.add(lblName, BorderLayout.WEST);
		
		JLabel lblDate = new JLabel("Fecha:");
		lblDate.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_Top.add(lblDate, BorderLayout.EAST);
		
		JPanel panel_Bottom = new JPanel();
		contentPane.add(panel_Bottom, BorderLayout.SOUTH);
		panel_Bottom.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Oper_Career = new JPanel();
		panel_Bottom.add(panel_Oper_Career, BorderLayout.WEST);
		panel_Oper_Career.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Flow_Career = new JPanel();
		panel_Oper_Career.add(panel_Flow_Career, BorderLayout.WEST);
		
		JButton btnAddCareer = new JButton("Inscripci\u00F3n a Carrerra");
		panel_Flow_Career.add(btnAddCareer);
		
		JButton btnDetCareer = new JButton("Ver detalles Carrera");
		panel_Flow_Career.add(btnDetCareer);
		
		JButton btnMain = new JButton("Inicio");
		panel_Bottom.add(btnMain, BorderLayout.EAST);
		
		LocalDate today = LocalDate.of(2022, 12, 6);
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
		
		Instant instant = Instant.from(today.atStartOfDay(ZoneId.of("GMT-3")));
			 
		String format = formatFecha.format(Date.from(instant)); 
		
		lblDate.setText("Fecha: " + format);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
	}
}
