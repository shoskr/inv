package Vistas;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Class.*;
import Controlador.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class AgregarPais extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarPais frame;
	private JTextField txtPais;
	private Connection conn = Conexion.getConnectio();
	private static DefaultTableModel mod;
	private JScrollPane jScrollPane1;
	private JTable Tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AgregarPais();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarPais() {
		setTitle("Agregar Pais");

		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarPais.class.getResource("/img/scot.png")));
		setBounds(100, 100, 450, 280);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnVolver.setBounds(299, 76, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblPais = new JLabel("Pais ");
		lblPais.setBounds(32, 27, 65, 14);
		contentPane.add(lblPais);

		JButton button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtPais.getText().length() > 0) {
					Pais pa = new Pais();
					pa.setNombre(txtPais.getText().toUpperCase());

					Cont_Pais CP = new Cont_Pais();

					ArrayList<Pais> list = new ArrayList<>();
					list = CP.listar();
					for (Pais pais : list) {
						if (pais.getNombre().equals(txtPais.getText().toUpperCase())) {
							JOptionPane.showMessageDialog(null, "Pais ya Ingresado");
							return;
						}
					}

					boolean valida = CP.ingresarPais(pa);

					if (valida) {
						JOptionPane.showMessageDialog(null, "Guardado");
						txtPais.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "No Guardo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingresar  pais");
				}
				LimpiarTabla();
				llenarTabla();

			}
		});
		button_1.setBounds(46, 73, 113, 28);
		contentPane.add(button_1);

		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(111, 24, 258, 20);
		contentPane.add(txtPais);

		// ---------------------
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(111, 124, 175, 100);
		Tabla = new javax.swing.JTable();
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(false);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Pais", });

		Tabla.setModel(mod);

		jScrollPane1.setViewportView(Tabla);
		contentPane.setLayout(null);

		llenarTabla();

		contentPane.add(jScrollPane1);
		jScrollPane1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { Tabla }));

		// --------------------------------

	}

	private void llenarTabla() {
		try {
			String sql = "SELECT * FROM Pais;";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private void LimpiarTabla() {
		while (mod.getRowCount() > 0) {

			mod.removeRow(0);
		}
	}
}
