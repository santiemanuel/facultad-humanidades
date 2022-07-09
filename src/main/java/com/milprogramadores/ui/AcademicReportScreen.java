package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

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
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Usuario;

public class AcademicReportScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOManager dao = new DAOManager();
	private Alumno alumno = new Alumno();
	private JPanel contentPane;

	public AcademicReportScreen(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
			
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
			
		JLabel lblName = new JLabel("");
		if (!usuario.getRol_admin())
			lblName.setText("Alumno: " + alumno.getAlumno_apellido() + ", " + alumno.getAlumno_nombre());
		else
			lblName.setText("Usuario Administrador");
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
			
		JButton btnCareers = new JButton("Ver Historial Academico");
		panel_1.add(btnCareers, "4, 12");
		
		JButton btnReports = new JButton("Certificado de Alumno Regular");
		panel_1.add(btnReports, "8, 12");
			
		JButton btnExams = new JButton("Generar Estado Curricular");
		panel_1.add(btnExams, "12, 12");
				
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");			
		Instant instant = Instant.from(MainWindow.today.atStartOfDay(ZoneId.of("GMT-3")));
		String format = formatFecha.format(Date.from(instant)); 
			
			
		lblDate.setText("Fecha: " + format);
			
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
