package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.tablemodel.AlumnoExamenTableModel;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ExamScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private AlumnoExamenTableModel tablemodel;
	private DAOManager dao = new DAOManager();

	public ExamScreen(final Alumno alumno) {
		
	  setTitle("Inscripci\u00F3n a Examen");
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
	  JPanel panel_Flow_Register = new JPanel();
	  panel_Oper_Career.add(panel_Flow_Register, BorderLayout.WEST);
			
	  JButton btnAddExam = new JButton("Inscribirse");
	  panel_Flow_Register.add(btnAddExam);
	  
	  JPanel panel_My_Exams = new JPanel();
	  panel_Bottom.add(panel_My_Exams, BorderLayout.EAST);
	  
	  JButton btnMyExams = new JButton("Mis Ex\u00E1menes");
	  panel_My_Exams.add(btnMyExams);
			
	  LocalDate today = LocalDate.of(2022, 12, 6);
		
	  SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
			
	  Instant instant = Instant.from(today.atStartOfDay(ZoneId.of("GMT-3")));
				 
	  String format = formatFecha.format(Date.from(instant)); 
			
	  lblDate.setText("Fecha: " + format);
	  
	  JPanel panel_Selector_Table = new JPanel();
	  contentPane.add(panel_Selector_Table, BorderLayout.CENTER);
	  panel_Selector_Table.setLayout(new BorderLayout(0, 0));
	  
	  table = new JTable();
	 
	  
	  JPanel panel_Label_Selector = new JPanel();
	  panel_Selector_Table.add(panel_Label_Selector, BorderLayout.NORTH);
	  
	  JLabel lblSelectCareer = new JLabel("Seleccione su carrera:");
	  panel_Label_Selector.add(lblSelectCareer);
	  
	  JComboBox<String> comboBox = new JComboBox<String>();
	  ArrayList<Carrera> carreras = dao.getCarreraDAO().carrerasAlumno(alumno.getAlumno_id());
	  
	  DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
	  for (Carrera c: carreras) {
			combomodel.addElement(c.getNombre());
	  };

	  comboBox.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				@SuppressWarnings("unchecked")
				Integer index = ((JComboBox<String>) e.getSource()).getSelectedIndex();
				tablemodel = new AlumnoExamenTableModel();
				tablemodel.updateModel(alumno, carreras.get(index));
				table.setModel(tablemodel);
			}
		}
	  });
	  
	  JScrollPane scrollPane = new JScrollPane();
	  scrollPane.setViewportView(table);
		
	  panel_Selector_Table.add(scrollPane, BorderLayout.CENTER);
	  
	  comboBox.setModel(combomodel);
	  comboBox.setSelectedIndex(0);
	  
	  panel_Label_Selector.add(comboBox);
			
	  setLocationRelativeTo(null);
	}

}
