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
import javax.swing.JPasswordField;
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

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField emailField;
	private JPasswordField passwordField;
	private static DAOManager dao = new DAOManager();

	public LoginDialog() {

		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblEmail = new JLabel("Correo electr\u00F3nico");
			contentPanel.add(lblEmail, "2, 2");
		}
		{
			emailField = new JTextField();
			emailField.setText("aldo@gmail.com");
			contentPanel.add(emailField, "6, 2, fill, default");
			emailField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a");
			contentPanel.add(lblPassword, "2, 4");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "6, 4, fill, default");
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String correo = emailField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				JPanel panel = new JPanel();
				if ((correo.length() == 0) || (pass.length() == 0)) {		
					JOptionPane.showMessageDialog(panel, "Los campos no puede estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				 
				Usuario usuario = dao.getUsuarioDAO().usuarioPorCorreo(correo);
				
				
				if (usuario == null)
					JOptionPane.showMessageDialog(panel, "El usuario no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
				else 
				{
					Credencial credencial = dao.getCredencialDAO().credencialUsuario(usuario.getId_usuario());
					if (credencial.checkPassword(pass)) {
						if (!usuario.getRol_admin()) {
							Alumno alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
							MainWindow frame = new MainWindow(alumno);
							frame.setVisible(true);
							dispose();
							return;
						}
						System.out.println("Ingresando usuario Administrador");
						MainWindowAdmin frame = new MainWindowAdmin(usuario);
						frame.setVisible(true);				
						dispose();
					} else {
						JOptionPane.showMessageDialog(panel, "La contraseña es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				WelcomeScreen screen = new WelcomeScreen();
				screen.setVisible(true);
			}
			
		});
		setLocationRelativeTo(null);
	}

}
