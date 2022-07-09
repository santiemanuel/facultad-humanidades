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
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.tablemodel.MateriaTableModel;

public class InsertSubject extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public InsertSubject(final SubjectScreen screen, Carrera carrera) {
		setTitle("Nueva Materia");
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
			JLabel lblCareerName = new JLabel("Nombre de Materia: ");
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
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de materia", "Error", JOptionPane.ERROR);
					return;
				}
				
				Boolean subjectExists = dao.getMateriaDAO().existeMateria(nombre);
				
				if (subjectExists) {
					JOptionPane.showMessageDialog(null, "La materia ya existe.", "Error", JOptionPane.ERROR);
					return;
				}
				Materia materia = new Materia();
				materia.setNombre(nombre);
				dao.getMateriaDAO().agregarMateria(materia);
				JOptionPane.showMessageDialog(null, "Materia creada con éxito.", "Completado", JOptionPane.INFORMATION_MESSAGE);
				
				Integer idMateria = dao.getMateriaDAO().materiaPorNombre(nombre);
				Materia materiaBase = dao.getMateriaDAO().obtenerMateria(idMateria);
				dao.getCarreraDAO().insertarMxc(materiaBase, carrera);
				screen.tablemodel = new MateriaTableModel();
				screen.tablemodel.updateModel(carrera);
				screen.table.setModel(screen.tablemodel);
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
