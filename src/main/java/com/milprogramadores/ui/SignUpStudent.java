package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Credencial;
import com.milprogramadores.model.Usuario;

public class SignUpStudent extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOManager dao = new DAOManager();
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldLu;
	private JTextField textFieldName;
	private JTextField textFieldSurname;

	/**
	 * Create the dialog.
	 */
	public SignUpStudent(final Usuario usuario) {
		setTitle("Registro de nuevo alumno");
		setBounds(100, 100, 450, 170);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblLu = new JLabel("Libreta Universitaria");
			contentPanel.add(lblLu, "2, 2, right, default");
		}
		{
			textFieldLu = new JTextField();
			textFieldLu.setEditable(false);
			contentPanel.add(textFieldLu, "4, 2, fill, default");
			textFieldLu.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Nombre");
			contentPanel.add(lblName, "2, 4, right, default");
		}
		{
			textFieldName = new JTextField();
			contentPanel.add(textFieldName, "4, 4, fill, default");
			textFieldName.setColumns(10);
		}
		{
			JLabel lblSurname = new JLabel("Apellido");
			contentPanel.add(lblSurname, "2, 6, right, default");
		}
		{
			textFieldSurname = new JTextField();
			contentPanel.add(textFieldSurname, "4, 6, fill, default");
			textFieldSurname.setColumns(10);
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aceptar");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldName.getText();
				String apellido = textFieldSurname.getText();
				
				Integer nuevaLu = dao.getAlumnoDAO().nuevaLu();
				textFieldLu.setText(String.valueOf(nuevaLu));
				
				Alumno alumno = new Alumno();
				alumno.setAlumno_lu(nuevaLu);
				alumno.setId_usuario(usuario.getId_usuario());
				
				if (nombre.length() == 0 || apellido.length() == 0) {
					JOptionPane.showMessageDialog(null, "Complete los campos de nombre y apellido", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				alumno.setAlumno_nombre(nombre);
				alumno.setAlumno_apellido(apellido);
				
				dao.getAlumnoDAO().agregarAlumno(alumno);
				
				Integer id_alumno = dao.getAlumnoDAO().alumnoPorLu(nuevaLu);
				Alumno alumnoDeBase = dao.getAlumnoDAO().obtenerAlumno(id_alumno);
				
				MainWindow frame = new MainWindow(alumnoDeBase);
				frame.setVisible(true);
				dispose();
				return;

			}
		});
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Credencial credencial = dao.getCredencialDAO().obtenerCredencial(usuario.getId_usuario());
				
				dao.getCredencialDAO().eliminarCredencial(credencial.getId_credencial());
				dao.getUsuarioDAO().eliminarUsuario(usuario.getId_usuario());
				
				WelcomeScreen screen = new WelcomeScreen();
				dispose();
				screen.setVisible(true);
			}
			
		});
		
		
		
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setLocationRelativeTo(null);
	}
}
