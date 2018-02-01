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

public class AgregarServidor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarServidor frame;
	private JTextField txtSer;
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
					frame = new AgregarServidor();
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
	public AgregarServidor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setTitle("Agregar Servidor");
		setBounds(100, 100, 450, 296);
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
		btnVolver.setBounds(287, 81, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(21, 32, 65, 14);
		contentPane.add(lblServidor);

		JButton button_1 = new JButton("Guardar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Servidor servi = new Servidor();

				if (txtSer.getText().length() > 0) {
					servi.setNombre(txtSer.getText().toUpperCase());

					Cont_Servidor cs = new Cont_Servidor();
					ArrayList<Servidor> list = new ArrayList<>();
					list = cs.listar();
					
					for (Servidor servidor : list) {
						if (servidor.getNombre().equals(txtSer.getText().toUpperCase())) {
							JOptionPane.showMessageDialog(null, "Servidor ya Existente");
							return;
						}
					}

					boolean valida = cs.ingresarServidor(servi);

					if (valida) {
						JOptionPane.showMessageDialog(null, "Guardo");
					} else {
						JOptionPane.showMessageDialog(null, "No Guardo");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Ingrese Servidor");
				}
				LimpiarTabla();
				llenarTabla();

			}
		});
		button_1.setBounds(39, 78, 113, 28);
		contentPane.add(button_1);

		txtSer = new JTextField();
		txtSer.setColumns(10);
		txtSer.setBounds(104, 29, 258, 20);
		contentPane.add(txtSer);
		
		
		// ---------------------
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(121, 124, 162, 100);
		Tabla = new javax.swing.JTable();
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(false);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Servidor",  });

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
			String sql = "SELECT * FROM Servidor;";

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
