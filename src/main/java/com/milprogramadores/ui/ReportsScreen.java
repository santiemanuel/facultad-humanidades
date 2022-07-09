package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Certificado;
import com.milprogramadores.model.Historial;
import com.milprogramadores.model.PdfCreatorHistorial;
import com.milprogramadores.model.Usuario;

public class ReportsScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Alumno alumno = new Alumno();
	private DAOManager dao = new DAOManager();

	public ReportsScreen(Usuario usuario) {
		if (!usuario.getRol_admin())
			alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
		setTitle("Facultad de Humanidades");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
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
		{
			panel_1.setLayout(new FormLayout(new ColumnSpec[] {
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(185dlu;default)"),
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(17dlu;default)"),
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(67dlu;default)"),},
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
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
		};
		

		JButton btnHistory = new JButton("Ver Historial Académico");
		panel_1.add(btnHistory, "7, 16");
		
		btnHistory.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				HistoryScreen screen = new HistoryScreen(usuario);
				dispose();
				screen.setVisible(true);
			}
		});
		
		JButton btnCertRegular = new JButton("Certificado Alumno Regular");
		panel_1.add(btnCertRegular, "7, 20");
		
		btnCertRegular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CertInfoToCreate screen = new CertInfoToCreate(alumno);
				screen.setVisible(true);
			}
			
		});
		
		JButton btnExams = new JButton("Generar Estado Curricular");
		panel_1.add(btnExams, "7, 24");
		
		btnExams.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(null);
				File file = saveFile.getSelectedFile();
				
				Certificado cert = new Certificado();
				ArrayList<Historial> historial = dao.getAlumnoDAO().historialExamenes(alumno, MainWindow.today);
				cert.generarEstadoCurricular(historial);
				
				PdfCreatorHistorial pdfFile = new PdfCreatorHistorial(cert, alumno, file);
				pdfFile.createPdf();
				JOptionPane.showMessageDialog(null, "Certificado de estado curricular creado con éxito", "Info", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		});
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
		
		Instant instant = Instant.from(MainWindow.today.atStartOfDay(ZoneId.of("GMT-3")));
			 
		String format = formatFecha.format(Date.from(instant)); 
		
		
		lblDate.setText("Fecha: " + format);
		
		
		setLocationRelativeTo(null);
	}


}
