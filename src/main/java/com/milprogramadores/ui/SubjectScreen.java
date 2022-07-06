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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.tablemodel.MateriaTableModel;

public class SubjectScreen extends JFrame {

	private JPanel contentPane;
	private MateriaTableModel tablemodel;

	public SubjectScreen(Carrera carrera) {
			setTitle("Materias");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 550, 400);
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
			
			JButton btnAddCareer = new JButton("Inscripcion a Materia");
			panel_Flow_Career.add(btnAddCareer);
			
			JButton btnMain = new JButton("Inicio");
			panel_Bottom.add(btnMain, BorderLayout.EAST);
			
			LocalDate today = LocalDate.of(2022, 12, 6);
			
			SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
			
			Instant instant = Instant.from(today.atStartOfDay(ZoneId.of("GMT-3")));
				 
			String format = formatFecha.format(Date.from(instant)); 
			
			lblDate.setText("Fecha: " + format);
			
			JTable table = new JTable();
			contentPane.add(table, BorderLayout.CENTER);
			
			table = new JTable();
			tablemodel = new MateriaTableModel();
			tablemodel.updateModel(carrera);
			table.setModel(tablemodel);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(table);
			
			contentPane.add(scrollPane, BorderLayout.CENTER);
			
			setLocationRelativeTo(null);
	}

}