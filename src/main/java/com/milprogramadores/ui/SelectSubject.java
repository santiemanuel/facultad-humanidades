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
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.tablemodel.MateriaTableModel;

public class SelectSubject extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Alumno alumno = new Alumno();
	private DAOManager dao = new DAOManager();
	private ArrayList<Materia> materias = new ArrayList<Materia>();

	public SelectSubject(final SubjectScreen screen, Usuario usuario, Carrera carrera) {
		
		if (!usuario.getRol_admin())
			alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
		
		setTitle("Materias");
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
			JLabel lblSubjects = new JLabel("Seleccione una materia:");
			contentPanel.add(lblSubjects, "2, 4");
		}
		
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
		
		if (!usuario.getRol_admin()) {
			materias = dao.getMateriaDAO().materiasFaltantes(alumno.getAlumno_id(), carrera.getCarrera_id());
		} else {
			materias = dao.getMateriaDAO().listarMaterias();
		}

		for (Materia m: materias) {
			combomodel.addElement(m.getNombre());
		}
		
		comboBox.setModel(combomodel);
		comboBox.setRenderer(new PromptComboBoxRenderer("Seleccione materia"));
		comboBox.setSelectedIndex(-1);
		
		contentPanel.add(comboBox, "2, 8, fill, default");
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Inscribir");
		
		if (usuario.getRol_admin()) {
			okButton.setText("Agregar");
		}
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int indMateria = comboBox.getSelectedIndex();
				Materia seleccion = materias.get(indMateria);
				
				if (!usuario.getRol_admin()) {
					dao.getAlumnoDAO().inscribirMateria(alumno, seleccion);
					JOptionPane.showMessageDialog(null, "Inscripto a " + seleccion.getNombre() + " con éxito.", "Completado", JOptionPane.INFORMATION_MESSAGE);
					screen.tablemodel = new MateriaTableModel();
					screen.tablemodel.updateModel(alumno, carrera);
					screen.table.setModel(screen.tablemodel);
					dispose();
				} else {
					Boolean existeMateria = dao.getCarreraDAO().existeMxc(seleccion, carrera);
					if (!existeMateria) {
						dao.getCarreraDAO().insertarMxc(seleccion, carrera);
						screen.tablemodel = new MateriaTableModel();
						screen.tablemodel.updateModel(carrera);
						screen.table.setModel(screen.tablemodel);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "La materia ya existe en esta carrera.", "Error", JOptionPane.ERROR);
					}
					
				}
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
