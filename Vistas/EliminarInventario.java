package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Class.*;

import javax.swing.ScrollPaneConstants;
import java.awt.Component;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;

public class EliminarInventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn = Conexion.getConnectio();
	private static DefaultTableModel mod = new DefaultTableModel();
	private JScrollPane jScrollPane1;
	private JTable Tabla;
	private JTextField txtCod;
	private JButton btnLimpiar;
	private ButtonGroup grupo = new ButtonGroup();
	private JRadioButton rdPlat;
	private JDateChooser Desde, Hasta;
	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JTextField txtCont;
	private int cont = 0;
	private JButton btnEliminarSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarInventario frame = new EliminarInventario();
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
	public EliminarInventario() {
		setTitle("Eliminar Del Inventario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 735, 561);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(14, 45, 695, 237);
		Tabla = new javax.swing.JTable();
		Tabla.setSelectionBackground(Color.WHITE);
		Tabla.setMaximumSize(new Dimension(11, 5));
		Tabla.setColumnSelectionAllowed(true);
		Tabla.setCellSelectionEnabled(true);
		Tabla.setAutoscrolls(true);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Tabla.setSurrendersFocusOnKeystroke(true);
		Tabla.setAutoCreateRowSorter(true);

		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

		mod = new DefaultTableModel(new Object[][] {},
				new String[] { "Inventario", "Modelo", "Marca", "Categoria", "Contenido", "Retencion",
						"Nombre plataforma", "Fecha Respaldo", "Fecha Expiracion", "Fecha Ultima Verificacion ", "Pais",
						"Almacenamiento", "Destino Actual", "Valija", "Continuacion", "Observaciones", "Solicitado", "Responsable",
						"Servidor", "Lugar Requerido", "Mes y Año" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, true, false, true, true, true,
					false, false, false, true, true, true, true, true, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};

		Tabla.setModel(mod);
		Tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(7).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(8).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(9).setPreferredWidth(150);
		Tabla.getColumnModel().getColumn(10).setPreferredWidth(80);
		Tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(12).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(13).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(14).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(15).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(16).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(17).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(18).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(19).setPreferredWidth(110);
		Tabla.getColumnModel().getColumn(20).setPreferredWidth(140);

		jScrollPane1.setViewportView(Tabla);
		contentPane.setLayout(null);

		contentPane.add(jScrollPane1);
		jScrollPane1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { Tabla }));

		JButton btnBuscar = new JButton("Buscar Codigo");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Cod = txtCod.getText().trim().toUpperCase();

				boolean valida = buscarinv(Cod);
				if (valida) {

				} else {
					boolean valida2 = buscarparte(Cod);
					if (valida2) {

					} else {
						buscarfin(Cod);
					}

				}

				txtCont.setText("" + cont);

				txtCod.setText("");

			}

		});
		btnBuscar.setBounds(168, 293, 139, 23);
		contentPane.add(btnBuscar);

		txtCod = new JTextField();
		txtCod.setBounds(14, 293, 142, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while (mod.getRowCount() > 0) {

					mod.removeRow(0);
					cont = 0;
					txtCont.setText("" + cont);
				}
			}
		});
		btnLimpiar.setBounds(574, 11, 89, 23);
		contentPane.add(btnLimpiar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnVolver.setBounds(14, 489, 114, 23);
		contentPane.add(btnVolver);

		rdPlat = new JRadioButton("Fecha Respaldo");
		rdPlat.setBackground(Color.WHITE);
		rdPlat.setSelected(true);
		rdPlat.setBounds(293, 354, 139, 23);
		contentPane.add(rdPlat);

		grupo.add(rdPlat);

		Desde = new JDateChooser();
		Desde.setBounds(438, 357, 120, 20);
		contentPane.add(Desde);

		Hasta = new JDateChooser();
		Hasta.setBounds(568, 357, 118, 20);
		contentPane.add(Hasta);

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(468, 388, 46, 14);
		contentPane.add(lblDesde);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(600, 388, 46, 14);
		contentPane.add(lblHasta);

		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desde = sdf.format(Desde.getDate());
				String Hastaa = sdf.format(Hasta.getDate());

				buscarFechas(desde, Hastaa);
				txtCont.setText("" + cont);

				Desde.setCalendar(null);
				Hasta.setCalendar(null);
			}
		});
		btnBuscar_1.setBounds(478, 423, 142, 23);
		contentPane.add(btnBuscar_1);

		JLabel lblCintasEncontradas = new JLabel("Cintas Para Eliminar");
		lblCintasEncontradas.setBounds(421, 295, 128, 16);
		contentPane.add(lblCintasEncontradas);

		txtCont = new JTextField();
		txtCont.setEditable(false);
		txtCont.setBounds(561, 295, 89, 22);
		contentPane.add(txtCont);
		txtCont.setColumns(10);

		txtCont.setText("" + cont);

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				mod.removeRow(Tabla.getSelectedRow());

				cont = cont - 1;
				txtCont.setText("" + cont);
			}
		});
		btnQuitar.setBounds(468, 10, 94, 24);
		contentPane.add(btnQuitar);

		JButton btnNewButton = new JButton("Eliminar lista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel tm = (DefaultTableModel) Tabla.getModel();
				if (Tabla.getModel().getRowCount() != 0) {

					int ax = JOptionPane.showConfirmDialog(null, "Se eliminaran " + cont + " Archivo/s, Esta seguro");
					if (ax == JOptionPane.YES_OPTION) {

						int count = 0;
						for (int i = 0; i < Tabla.getModel().getRowCount(); i++) {

							String cod = String.valueOf(tm.getValueAt(i, 0)).toUpperCase();

							boolean valida = Eliminar(cod);
							if (valida) {
								while (mod.getRowCount() > 0) {

									mod.removeRow(0);
									cont = 0;
									txtCont.setText("" + cont);
								}
							}
							count++;

						}
						JOptionPane.showMessageDialog(null, count + " Archivo/s Eliminados ");
					}

					else if (ax == JOptionPane.NO_OPTION) {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, " No existen archivos para Eliminar");
				}

			}
		});
		btnNewButton.setBounds(215, 423, 176, 23);
		contentPane.add(btnNewButton);
		
		btnEliminarSeleccionado = new JButton("Eliminar Seleccionado");
		btnEliminarSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel tm = (DefaultTableModel) Tabla.getModel();
				if (Tabla.getModel().getRowCount() != 0) {

					int ax = JOptionPane.showConfirmDialog(null, "Se eliminaran " + cont + " Archivo/s, Esta seguro");
					if (ax == JOptionPane.YES_OPTION) {

						String cod = String.valueOf(tm.getValueAt(Tabla.getSelectedRow(), 0)).toUpperCase();

							boolean valida = Eliminar(cod);
							if (valida) {
								while (mod.getRowCount() > 0) {

									mod.removeRow(Tabla.getSelectedRow());
									cont = cont- 1;
									txtCont.setText("" + cont);
								}
							
						

						}
						JOptionPane.showMessageDialog(null, " Archivo Eliminado ");
					}

					else if (ax == JOptionPane.NO_OPTION) {
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, " No existen archivos para Eliminar");
				}
			}
		});
		btnEliminarSeleccionado.setBounds(14, 353, 176, 24);
		contentPane.add(btnEliminarSeleccionado);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { jScrollPane1, Tabla }));
	}

	private boolean buscarinv(String cod) {
		try {
			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio\r\n" + "from inventario \r\n"
					+ "inner join Cinta\r\n" + "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
					+ "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, cod);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			if (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
				cont++;
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private boolean buscarparte(String cod) {
		try {

			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio\r\n" + "from inventario \r\n"
					+ "inner join Cinta\r\n" + "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
					+ "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  LIKE '" + cod + "%';";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			if (rs.next()) {
				while (rs.next()) {
					Object[] raws = new Object[rsm.getColumnCount()];
					for (int i = 0; i < raws.length; i++) {
						raws[i] = rs.getObject(i + 1);

					}
					mod.addRow(raws);
					cont++;
				}

				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void buscarfin(String cod) {
		try {

			String sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
					+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio\r\n" + "from inventario \r\n"
					+ "inner join Cinta\r\n" + "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
					+ "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
					+ "where Inventario.idInventario  LIKE '%" + cod + "';";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			if (rs.next()) {
				while (rs.next()) {
					Object[] raws = new Object[rsm.getColumnCount()];
					for (int i = 0; i < raws.length; i++) {
						raws[i] = rs.getObject(i + 1);

					}
					mod.addRow(raws);
					cont++;
				}

			} else {
				JOptionPane.showMessageDialog(null, "no se encontro resultado");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void buscarFechas(String des, String hast) {
		try {
			String sql = "";
			if (rdPlat.isSelected()) {
				sql = "select\r\n " + "    Inventario.idInventario,\r\n" + "	   Cinta.Modelo,\r\n"
						+ "	   Cinta.Marca,\r\n" + "	   Cinta.Categoria,\r\n" + "	   Inventario.Contenido,\r\n"
						+ "	   Inventario.retencion,\r\n" + "	   Plataforma.Nombre,\r\n"
						+ "	   Inventario.Fecha_Plataforma,\r\n" + "	   Inventario.Fecha_Exp,\r\n"
						+ "	   Inventario.Fecha_ultim,\r\n" + "	   Pais.Nombre,\r\n"
						+ "	   Ubicacion.Nombre_Ubicacion,\r\n" + "	   Destino.Nombre_Destino,\r\n"
						+ "	   Inventario.Valija,\r\n" + "	   Inventario.Continuacion,\r\n"
						+ "	   Inventario.Observaciones,\r\n" + "	   Inventario.Solicitado,\r\n"
						+ "	   Inventario.Responsable,\r\n" + "	   Servidor.Nombre,\r\n"
						+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio\r\n" + "from inventario \r\n"
						+ "inner join Cinta\r\n" + "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
						+ "inner join Plataforma\r\n"
						+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n"
						+ "inner join Pais\r\n" + "		on Pais.idPais = Inventario.Pais_idPais\r\n"
						+ "inner join Ubicacion\r\n"
						+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
						+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
						+ "		on Servidor.idServidor = Inventario.Servidor_idServidor\r\n"
						+ "where  Inventario.Fecha_Plataforma >= ? and Inventario.Fecha_Plataforma <= ?";

			}

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, des);
			stm.setString(2, hast);
			ResultSet rs = stm.executeQuery();

			int cont2 = 0;
			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				mod.addRow(raws);
				cont2++;
				cont++;
			}
			if (cont2 < 1) {
				JOptionPane.showMessageDialog(null, "No se encuentran resultados");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private boolean Eliminar(String cod) {

		try {
			String sql = "delete from Inventario where idInventario =? ";

			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, cod);

			int x = pstm.executeUpdate();
			return x > 0 ? true : false;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
