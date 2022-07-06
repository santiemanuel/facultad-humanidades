package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SignUpUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
			JLabel lblEmail = new JLabel("Correo Electr\u00F3nico");
			contentPanel.add(lblEmail, "2, 2, right, default");
		}
		{
			textFieldEmail = new JTextField();
			contentPanel.add(textFieldEmail, "4, 2, default, fill");
			textFieldEmail.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a");
			contentPanel.add(lblPassword, "2, 4, right, default");
		}
		{
			textFieldPassword = new JTextField();
			contentPanel.add(textFieldPassword, "4, 4, fill, default");
			textFieldPassword.setColumns(10);
		}
		{
			JLabel lblRepeatPassword = new JLabel("Repetir Contrase\u00F1a");
			contentPanel.add(lblRepeatPassword, "2, 6, right, default");
		}
		{
			textFieldRepPassword = new JTextField();
			contentPanel.add(textFieldRepPassword, "4, 6, fill, default");
			textFieldRepPassword.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}

}
