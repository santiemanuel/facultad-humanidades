package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	public MainWindow() {
		setTitle("Facultad de Humanidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("Alumno: ");
		panel.add(lblName, BorderLayout.WEST);
		
		JLabel lblDate = new JLabel("Fecha:");
		lblDate.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblDate, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("images/image.png"));
		lblLogo.setVerticalTextPosition(JLabel.BOTTOM);
		lblLogo.setVerticalTextPosition(JLabel.CENTER);
		
		panel_1.add(lblLogo, "8, 2");
		
		JButton btnCareers = new JButton("Mis Carreras");
		panel_1.add(btnCareers, "4, 12");
		
		btnCareers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CareerScreen screen = new CareerScreen();
				screen.setVisible(true);
			}
			
		});
		
		
		JButton btnReports = new JButton("Reportes");
		panel_1.add(btnReports, "8, 12");
		
		JButton btnExams = new JButton("Ex\u00E1menes");
		panel_1.add(btnExams, "12, 12");
		
		LocalDate today = LocalDate.of(2022, 12, 6);
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
		
		Instant instant = Instant.from(today.atStartOfDay(ZoneId.of("GMT-3")));
			 
		String format = formatFecha.format(Date.from(instant)); 
		
		
		lblDate.setText("Fecha: " + format);
		
		
		setLocationRelativeTo(null);
	}

}
