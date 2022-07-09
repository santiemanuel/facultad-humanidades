package com.milprogramadores.ui;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.AlumnoExamen;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Examen;
import com.milprogramadores.model.MesaExamen;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.tablemodel.AlumnoExamenTableModel;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class ExamScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private AlumnoExamenTableModel tablemodel;
	private DAOManager dao = new DAOManager();
	ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
	
	public ExamScreen(final Alumno alumno, final Usuario usuario) {
		
	  setTitle("Inscripción a Examen");
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
	  if (usuario == null)
		  lblName.setText("Alumno: " + alumno.getAlumno_apellido() + ", " + alumno.getAlumno_nombre());
	  else
		  lblName.setText("Usuario Administrador");
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
	  
	  if (alumno == null) {
		  btnAddExam.setText("Crear Nueva Mesa");
	  }
	  
	  btnAddExam.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (alumno == null) {
				CreateExamDialog dialog = new CreateExamDialog();
				dialog.setVisible(true);
				return;
			}
		
			JPanel panel = new JPanel();
			int fila = table.getSelectedRow();
			
			if (fila < 0) {
				JOptionPane.showMessageDialog(panel, "Debe seleccionar una carrera", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			};
			AlumnoExamen examen = (AlumnoExamen) tablemodel.getValueAt(fila, AlumnoExamenTableModel.OBJECT_COL);
			
			ArrayList<Examen> listaExamen = dao.getAlumnoDAO().obtenerHistorial(alumno);
			
			Boolean estaInscripto = dao.getAlumnoDAO().buscarExamenId(listaExamen, examen.getMesa_examen_id());
			
			if (!estaInscripto) {
				MesaExamen mesa = dao.getExamenDAO().obtenerMesaExamen(examen.getMesa_examen_id());
				dao.getAlumnoDAO().inscribirExamen(alumno, mesa);
				JOptionPane.showMessageDialog(panel, "Te has inscripto para rendir", "Info", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(panel, "Ya estás inscripto a esa mesa de examen", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	  });
	  panel_Flow_Register.add(btnAddExam);
	  
	  JPanel panel_My_Exams = new JPanel();
	  panel_Bottom.add(panel_My_Exams, BorderLayout.EAST);
	  
	  JButton btnMyExams = new JButton("Mis Exámenes");
	  
	  if (alumno == null) btnMyExams.setVisible(false);
	  
	  btnMyExams.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MyExamsScreen screen = new MyExamsScreen(alumno);
			screen.setVisible(true);
			dispose();
		}
		  
	  });
	  
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
	  if (usuario == null) {
		  ArrayList<Carrera> carreras = dao.getCarreraDAO().carrerasAlumno(alumno.getAlumno_id());
		  for (Carrera c: carreras) {
				combomodel.addElement(c.getNombre());
		  };
	  }

	  comboBox.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				@SuppressWarnings("unchecked")
				Integer index = ((JComboBox<String>) e.getSource()).getSelectedIndex();
				if (usuario == null) {
					tablemodel = new AlumnoExamenTableModel();
					tablemodel.updateModel(alumno, carreras.get(index));
					table.setModel(tablemodel);
				}
				
			}
		}
	  });
	  
	  JScrollPane scrollPane = new JScrollPane();
	  scrollPane.setViewportView(table);
		
	  panel_Selector_Table.add(scrollPane, BorderLayout.CENTER);
	  
	  if (usuario == null) {
		  comboBox.setModel(combomodel);
		  comboBox.setSelectedIndex(0); 
	  }
	  
	  panel_Label_Selector.add(comboBox);
			
	  setLocationRelativeTo(null);
	}

}
