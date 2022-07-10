package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.tablemodel.MateriaTableModel;

public class SubjectScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Alumno alumno = new Alumno();
	private DAOManager dao = new DAOManager();
	JTable table = new JTable();
	MateriaTableModel tablemodel;

	public SubjectScreen(final Usuario usuario, final Carrera carrera) {
		
		if (!usuario.getRol_admin())
			alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
		setTitle("Materias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
			
		JPanel panel_Top = new JPanel();
		contentPane.add(panel_Top, BorderLayout.NORTH);
		panel_Top.setLayout(new BorderLayout(0, 0));
			
		JLabel lblName = new JLabel("");
		if (!usuario.getRol_admin())
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
			
		JPanel panel_Flow_Career = new JPanel();
		panel_Oper_Career.add(panel_Flow_Career, BorderLayout.WEST);
			
		JButton btnAddSubject = new JButton("Inscripcion a Materia");

		if ((usuario.getRol_admin())) {
			btnAddSubject.setText("Crear Nueva Materia");
		}
		panel_Flow_Career.add(btnAddSubject);
		
		JButton btnSelSubject = new JButton("Seleccionar Materia");
		
		if (!usuario.getRol_admin()) {
			btnAddSubject.setVisible(false);
		}
		
		btnSelSubject.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SelectSubject selector = new SelectSubject(SubjectScreen.this, usuario, carrera);
				selector.setVisible(true);
			}
		});
		panel_Flow_Career.add(btnSelSubject);
		
		btnAddSubject.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ((usuario.getRol_admin())) {			
					InsertSubject dialog = new InsertSubject(SubjectScreen.this, carrera);
					dialog.setVisible(true);
					return;
				} else {
					SelectSubject selector = new SelectSubject(SubjectScreen.this, usuario, carrera);
					selector.setVisible(true);
				}
			}
		});
			
		JButton btnMain = new JButton("Inicio");
		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}	
		});
		panel_Bottom.add(btnMain, BorderLayout.EAST);
			

		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
			
		Instant instant = Instant.from(MainWindow.today.atStartOfDay(ZoneId.of("GMT-3")));
				 
		String format = formatFecha.format(Date.from(instant)); 
		
		lblDate.setText("Fecha: " + format);
			
		tablemodel = new MateriaTableModel();
		if (!usuario.getRol_admin())
		{
			tablemodel.updateModel(alumno, carrera);
		} else {
			tablemodel.updateModel(carrera);
		};
		table.setModel(tablemodel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
			
		contentPane.add(scrollPane, BorderLayout.CENTER);
			
		setLocationRelativeTo(null);
	}
}
