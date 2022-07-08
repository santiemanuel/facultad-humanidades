package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.tablemodel.CarreraTableModel;

public class SelectCareer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAOManager dao = new DAOManager();

	public SelectCareer(final CareerScreen screen, final Alumno alumno) {
		setTitle("Carreras");
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblCareers = new JLabel("Seleccione una carrera:");
			contentPanel.add(lblCareers, "2, 4");
		}

		final JComboBox<String> comboBox = new JComboBox<String>();
		final ArrayList<Carrera> carreras = dao.getCarreraDAO().carrerasFaltantes(alumno.getAlumno_id());
		DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
		for (Carrera c: carreras) {
			combomodel.addElement(c.getNombre());
		}
		
		comboBox.setModel(combomodel);	
		
		contentPanel.add(comboBox, "2, 8, fill, default");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Inscribir");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int indCarrera = comboBox.getSelectedIndex();
				Carrera seleccion = carreras.get(indCarrera);
				dao.getAlumnoDAO().inscribirCarrera(alumno, seleccion);
				JOptionPane.showMessageDialog(null, "Inscripto a " + seleccion.getNombre() + " con éxito.", "Completado", JOptionPane.INFORMATION_MESSAGE);
				
				screen.tablemodel = new CarreraTableModel();
				screen.tablemodel.updateModel(alumno);
				screen.table.setModel(screen.tablemodel);
				dispose();
			}
		});
		
		
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setLocationRelativeTo(null);
	}
	
	
}
