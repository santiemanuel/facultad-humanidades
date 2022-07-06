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

public class SignUpStudent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldLu;
	private JTextField textFieldName;
	private JTextField textFieldSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignUpStudent dialog = new SignUpStudent();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignUpStudent() {
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
