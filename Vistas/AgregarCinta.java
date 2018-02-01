package Vistas;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import Class.*;
import Controlador.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;
import java.awt.Color;

public class AgregarCinta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarCinta frame;
	private JTextField txtModelo;
	private JTextField txtMarca;
	private JTextField txtCate;
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
					frame = new AgregarCinta();
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
	public AgregarCinta() {
		setTitle("Agregar Cintas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 450, 340);
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
		contentPane.setLayout(null);
		btnVolver.setBounds(335, 147, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setBounds(40, 32, 60, 14);
		contentPane.add(lblNewLabel);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(40, 57, 60, 14);
		contentPane.add(lblMarca);

		JLabel lblCategoria = new JLabel("Categoria ");
		lblCategoria.setBounds(40, 82, 60, 14);
		contentPane.add(lblCategoria);

		txtModelo = new JTextField();
		txtModelo.setBounds(122, 29, 187, 20);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(122, 54, 187, 20);
		contentPane.add(txtMarca);

		txtCate = new JTextField();
		txtCate.setColumns(10);
		txtCate.setBounds(122, 79, 187, 20);
		contentPane.add(txtCate);

		JButton btnGuarddar = new JButton("Guardar");
		btnGuarddar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Cinta cin = new Cinta();

				if (txtModelo.getText().length() > 0) {
					if (txtMarca.getText().length() > 0) {
						if (txtCate.getText().length() > 0) {
							cin.setModelo(txtModelo.getText().toUpperCase());
							cin.setMarca(txtMarca.getText().toUpperCase());
							cin.setCategoria(txtCate.getText().toUpperCase());
							Cont_Cinta CC = new Cont_Cinta();

							ArrayList<Cinta> list = new ArrayList<>();
							list = CC.listar();

							for (Cinta cinta : list) {

								if (cinta.getModelo().equals(txtModelo.getText().toUpperCase())
										&& cinta.getMarca().equals(txtMarca.getText().toUpperCase())) {

									JOptionPane.showMessageDialog(null, "La cinta se Encuantra en la Lista");
									return;
								}
							}

							boolean valida = CC.IngresarCinta(cin);

							if (valida) {
								JOptionPane.showMessageDialog(null, "Guardada");
								txtCate.setText("");
								txtMarca.setText("");
								txtModelo.setText("");
								
							} else {
								JOptionPane.showMessageDialog(null, "No se Guardo");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ingrese Categoria");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ingrese Marca");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese Modelo");
				}
				LimpiarTabla();
				llenarTabla();

			}
		});
		btnGuarddar.setBounds(158, 110, 89, 23);
		contentPane.add(btnGuarddar);

		contentPane.setLayout(null);
		// ---------------------
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(56, 199, 308, 84);
		Tabla = new javax.swing.JTable();
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(false);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Modelo", "Marca", "Categoria" });

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

			String sql = "SELECT * FROM Cinta;";

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
