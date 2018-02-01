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
import java.awt.Color;

public class AgregarPlataforma extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarPlataforma frame;
	private JTextField txtPlataforma;
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

					frame = new AgregarPlataforma();
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
	public AgregarPlataforma() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setTitle("Agregar Plataforma");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 287);
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
		btnVolver.setBounds(290, 73, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblPlataforma = new JLabel("Plataforma");
		lblPlataforma.setBounds(20, 24, 65, 14);
		contentPane.add(lblPlataforma);

		JButton button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String plat = txtPlataforma.getText().toUpperCase();

				if (plat.length() > 0) {

					Plataforma pl = new Plataforma();
					Cont_Plataforma CPl = new Cont_Plataforma();
					pl.setNomPlataforma(plat);
					ArrayList<Plataforma> list = new ArrayList<>();
					list = CPl.listar();
					
					for (Plataforma plataforma : list) {
						if(plataforma.getNomPlataforma().equals(plat)) {
							JOptionPane.showMessageDialog(null, "Plataforma ya Existente");
							return;
						}
					}
					boolean valida = CPl.ingresarPlataforma(pl);
					if (valida) {
						JOptionPane.showMessageDialog(null, "Guardo");
					} else {
						JOptionPane.showMessageDialog(null, "No Guardo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingresar Plataforma");
				}
				LimpiarTabla();
				llenarTabla();

			}
		});
		button_1.setBounds(38, 70, 113, 28);
		contentPane.add(button_1);

		txtPlataforma = new JTextField();
		txtPlataforma.setColumns(10);
		txtPlataforma.setBounds(103, 21, 258, 20);
		contentPane.add(txtPlataforma);

		// ---------------------
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(126, 125, 162, 100);
		Tabla = new javax.swing.JTable();
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(false);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Plataforma", });

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
			String sql = "SELECT * FROM Plataforma;";

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
