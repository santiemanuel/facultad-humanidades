package com.milprogramadores.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.milprogramadores.model.MesaExamen;

public class CreateExamDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	private ArrayList<Materia> materias = new ArrayList<Materia>();
	DefaultComboBoxModel<String> combomodelCareer = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> combomodelSubject = new DefaultComboBoxModel<String>();
	private DAOManager dao = new DAOManager();
	private JTextField textFieldDate;

	public CreateExamDialog() {
		setTitle("Crear Mesa");
		setBounds(100, 100, 450, 300);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblExamCareer = new JLabel("Carrera de la mesa: ");
		contentPanel.add(lblExamCareer, "2, 4, right, default");

		JComboBox<String> comboBoxCareer = new JComboBox<String>();
		contentPanel.add(comboBoxCareer, "4, 4, fill, default");
		
		carreras = dao.getCarreraDAO().listarCarreras();
		for (Carrera c: carreras) {
			combomodelCareer.addElement(c.getNombre());
		};
		comboBoxCareer.setModel(combomodelCareer);
		comboBoxCareer.setRenderer(new PromptComboBoxRenderer("Seleccione carrera"));
		comboBoxCareer.setSelectedIndex(-1);
		
		JLabel lblSubjectCareer = new JLabel("Materia de la mesa:");
		contentPanel.add(lblSubjectCareer, "2, 6, right, default");

		JComboBox<String> comboBoxSubject = new JComboBox<String>();
		
		comboBoxCareer.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					combomodelSubject.removeAllElements();
					@SuppressWarnings("unchecked")
					Integer index = ((JComboBox<String>) e.getSource()).getSelectedIndex();
					materias = dao.getCarreraDAO().materiasDeCarrera(carreras.get(index));
					for (Materia m: materias) {
						combomodelSubject.addElement(m.getNombre());
					}
					comboBoxSubject.setModel(combomodelSubject);
				}
			}
		});
		
		contentPanel.add(comboBoxSubject, "4, 6, fill, default");
	
		comboBoxSubject.setRenderer(new PromptComboBoxRenderer("Seleccione materia"));
		comboBoxSubject.setSelectedIndex(-1);

		JLabel lblNewLabel_2 = new JLabel("Fecha :");
		contentPanel.add(lblNewLabel_2, "2, 8, right, default");

		textFieldDate = new JTextField();
		contentPanel.add(textFieldDate, "4, 8, fill, default");
		textFieldDate.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String fecha = textFieldDate.getText();
				Boolean validDate = isValidDate(fecha);
				
				if (!validDate) {
					JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int indCarrera = comboBoxCareer.getSelectedIndex();
				int indMateria = comboBoxSubject.getSelectedIndex();
				if (indCarrera == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (indMateria == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una materia", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				java.sql.Date fechaSql = null;
				try {
					Date utilDate = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					utilDate = sdf.parse(fecha);
					fechaSql = new java.sql.Date(utilDate.getTime());
				} catch (Exception exd) {
					return;
				}

				Materia materia = materias.get(comboBoxSubject.getSelectedIndex());
				Carrera carrera = carreras.get(comboBoxCareer.getSelectedIndex()); 
				
				Integer id_mxc = dao.getExamenDAO().buscarMesaMxc(materia, carrera);
				
				Boolean existeMesa = dao.getExamenDAO().buscarMxcFecha(id_mxc, fechaSql);
				if (!existeMesa) {
					MesaExamen mesa = new MesaExamen();
					mesa.setFecha(fechaSql.toLocalDate());
					dao.getExamenDAO().agregarMesaExamen(mesa, materia, carrera);
					JOptionPane.showMessageDialog(null, "Mesa creada con éxito", "Error", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "La mesa ya existe en esta fecha", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public Boolean isValidDate(String fecha) {
		LocalDateTime ldt = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			 ldt = LocalDateTime.parse(fecha, formatter);
		     String result = ldt.format(formatter);
		      return result.equals(fecha);
		} catch (DateTimeParseException e) {
			try {	
				LocalDate ld = LocalDate.parse(fecha, formatter);
				String result = ld.format(formatter);
				return result.equals(fecha);
			} catch (DateTimeParseException exp) {
				return false;
			}
		}
	}

}
