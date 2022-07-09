package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Certificado;
import com.milprogramadores.model.InformacionCertificado;

import com.milprogramadores.model.PdfCreatorConstancia;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;

public class CertInfoToCreate extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAuth;
	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	private DAOManager dao = new DAOManager();

	public CertInfoToCreate(Alumno alumno) {
		setTitle("Autoridades");
		setBounds(100, 100, 450, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblAuthority = new JLabel("Nombre de autoridad: ");
			contentPanel.add(lblAuthority, "2, 2, right, default");
		}
		
		textFieldAuth = new JTextField();
		contentPanel.add(textFieldAuth, "4, 2, fill, default");
		textFieldAuth.setColumns(10);
		
		JLabel lblCareer = new JLabel("Carrera: ");
		contentPanel.add(lblCareer, "2, 4, right, default");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
		carreras = dao.getCarreraDAO().carrerasAlumno(alumno.getAlumno_id());
		for (Carrera c: carreras) {
			combomodel.addElement(c.getNombre());
		}
		comboBox.setModel(combomodel);
		comboBox.setRenderer(new PromptComboBoxRenderer("Seleccione carrera"));
		comboBox.setSelectedIndex(-1);
		
		contentPanel.add(comboBox, "4, 4, fill, default");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Crear");
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String autoridad = textFieldAuth.getText();
				if (comboBox.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una carrera para el certificado", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Carrera seleccionCarrera = carreras.get(comboBox.getSelectedIndex());
				if (autoridad.length() == 0) {
					JOptionPane.showMessageDialog(null, "Ingrese el nombre de la autoridad a presentar", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				InformacionCertificado info = new InformacionCertificado();
				info.setNombre(alumno.getAlumno_nombre());
				info.setApellido(alumno.getAlumno_apellido());
				info.setCarrera(seleccionCarrera.getNombre());
				info.setAlumno_lu(alumno.getAlumno_lu().toString());
				info.setAutoridad(autoridad);
				info.setFecha(MainWindow.today);
				
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(null);
				File file = saveFile.getSelectedFile();
				
				ArrayList<String> params = info.getListInfo();
				
				Certificado cert = new Certificado(params);
				PdfCreatorConstancia pdfFile = new PdfCreatorConstancia(cert, file);
				
				pdfFile.createPdf();
				JOptionPane.showMessageDialog(null, "Certificado creado con éxito", "Info", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		});
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		setLocationRelativeTo(null);
	}

}
