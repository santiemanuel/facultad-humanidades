package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Carrera;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertCareer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCareer;
	DAOManager dao = new DAOManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertCareer dialog = new InsertCareer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertCareer() {
		setTitle("Nueva carrera");
		setBounds(100, 100, 450, 150);
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
			JLabel lblCareerName = new JLabel("Nombre de Carrera: ");
			contentPanel.add(lblCareerName, "2, 4, right, default");
		}
		{
			textFieldCareer = new JTextField();
			contentPanel.add(textFieldCareer, "4, 4, fill, default");
			textFieldCareer.setColumns(10);
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldCareer.getText();
				if (nombre.length() == 0) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de carrera", "Error", JOptionPane.ERROR);
					return;
				}
					
				Boolean careerExists = dao.getCarreraDAO().existeCarrera(nombre);
				if (careerExists) {
					JOptionPane.showMessageDialog(null, "La carrera ya existe.", "Error", JOptionPane.ERROR);
					return;
				}
				Carrera carrera = new Carrera();
				carrera.setNombre(nombre);
				dao.getCarreraDAO().agregarCarrera(carrera);
				JOptionPane.showMessageDialog(null, "Carrera creada con éxito.", "Completado", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();		
			}	
		});
		
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		
		setLocationRelativeTo(null);
	}

}
