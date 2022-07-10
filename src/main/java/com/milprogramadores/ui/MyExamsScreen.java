package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Historial;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.tablemodel.HistorialTableModel;

public class MyExamsScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private HistorialTableModel tablemodel;
	private JPanel contentPane;
	private DAOManager dao = new DAOManager();
	private Alumno alumno = new Alumno();
	/**
	 * Create the frame.
	 */
	public MyExamsScreen(Usuario usuario) {
		if (!usuario.getRol_admin())
			alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
		
		setTitle("Mis Exámenes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 400);
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
				
		JButton btnTakeExam = new JButton("Rendir Examen");
		btnTakeExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if (fila < 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un examen a rendir", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				};
				Historial entrada = (Historial) tablemodel.getValueAt(fila, HistorialTableModel.OBJECT_COL);
				
				java.util.Random rnd = new java.util.Random();
				int min = 2;
				int max = 10;
				Integer nota = rnd.nextInt(max - min + 1) + min;
				
				dao.getAlumnoDAO().rendirExamen(alumno, entrada.getExamen_id(), nota);
				
				tablemodel = new HistorialTableModel(usuario);
				tablemodel.updateModel(usuario, MainWindow.today);
				table.setModel(tablemodel);
			}
		});
		panel_Flow_Career.add(btnTakeExam);
		
		JButton btnCancelExam = new JButton("Cancelar Examen");
		panel_Flow_Career.add(btnCancelExam);
		btnCancelExam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if (fila < 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un examen a rendir", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				};
				Historial entrada = (Historial) tablemodel.getValueAt(fila, HistorialTableModel.OBJECT_COL);
				
				dao.getAlumnoDAO().cancelarExamen(alumno, entrada.getExamen_id());
				JOptionPane.showMessageDialog(null, "Inscripcion cancelada", "Error", JOptionPane.INFORMATION_MESSAGE);
				tablemodel = new HistorialTableModel(usuario);
				tablemodel.updateModel(usuario, MainWindow.today);
				table.setModel(tablemodel);
			}
			
		});	  	
	
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
				
		Instant instant = Instant.from(MainWindow.today.atStartOfDay(ZoneId.of("GMT-3")));
					 
		String format = formatFecha.format(Date.from(instant)); 
				
		lblDate.setText("Fecha: " + format);
		
		JPanel panel_Selector_Table = new JPanel();
		contentPane.add(panel_Selector_Table, BorderLayout.CENTER);
		panel_Selector_Table.setLayout(new BorderLayout(0, 0));
				
		table= new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		tablemodel = new HistorialTableModel(usuario);
		tablemodel.updateModel(usuario, MainWindow.today);
		table.setModel(tablemodel);
			
		panel_Selector_Table.add(scrollPane, BorderLayout.CENTER);
				
		setLocationRelativeTo(null);
	}
}
