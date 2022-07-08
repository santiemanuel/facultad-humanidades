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
import com.milprogramadores.model.Credencial;
import com.milprogramadores.model.Usuario;

public class SignUpUser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAOManager dao = new DAOManager();
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;
	private JTextField textFieldRepPassword;

	public static void main(String[] args) {
		try {
			SignUpUser dialog = new SignUpUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SignUpUser() {
		setTitle("Registro de nuevo usuario");
		setBounds(100, 100, 450, 200);
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
			JLabel lblEmail = new JLabel("Correo Electrónico");
			contentPanel.add(lblEmail, "2, 2, right, default");
		}
		{
			textFieldEmail = new JTextField();
			contentPanel.add(textFieldEmail, "4, 2, default, fill");
			textFieldEmail.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Contraseña");
			contentPanel.add(lblPassword, "2, 4, right, default");
		}
		{
			textFieldPassword = new JPasswordField();
			contentPanel.add(textFieldPassword, "4, 4, fill, default");
			textFieldPassword.setColumns(10);
		}
		{
			JLabel lblRepeatPassword = new JLabel("Repetir Contraseña");
			contentPanel.add(lblRepeatPassword, "2, 6, right, default");
		}
		{
			textFieldRepPassword = new JPasswordField();
			contentPanel.add(textFieldRepPassword, "4, 6, fill, default");
			textFieldRepPassword.setColumns(10);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aceptar");
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = textFieldEmail.getText();
				String pass = textFieldPassword.getText();
				String repPass = textFieldRepPassword.getText();
				
				if (email.length() == 0 || pass.length() == 0 || repPass.length() == 0) {
					JOptionPane.showMessageDialog(null, "No deben haber campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Usuario usuario = new Usuario();
				Boolean validEmail = usuario.setEmail_usuario(email);
				usuario.setRol_admin(false);
				
				if (!validEmail) {
					JOptionPane.showMessageDialog(null, "El correo electrónico no es válido", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (!pass.equals(repPass)) {
					JOptionPane.showMessageDialog(null, "La repetición de contraseña no coincide", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Usuario data = dao.getUsuarioDAO().usuarioPorCorreo(email);
				
				if (data == null) {
					
					dao.getUsuarioDAO().agregarUsuario(usuario);
					Usuario userDeBase = dao.getUsuarioDAO().usuarioPorCorreo(email);
					
					Credencial credencial = new Credencial();
					credencial.setSalt_and_hash(pass);
					dao.getCredencialDAO().agregarCredencial(credencial, userDeBase);
					
					SignUpStudent screen = new SignUpStudent(userDeBase);
					screen.setVisible(true);
					dispose();
				} else
				{
					JOptionPane.showMessageDialog(null, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
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
