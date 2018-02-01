package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


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
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class ModificarVarios extends JFrame {

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
	private JTextField txtCont;
	private int cont = 0;
	private Inventario inv = new Inventario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarVarios frame = new ModificarVarios();
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
	public ModificarVarios() {
		setTitle("Modificacion Masiva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 706, 590);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(10, 45, 670, 355);
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
						"Ubicacion", "Destino", "Valija", "Continuacion", "Observaciones", "Solicitado", "Responsable",
						"Servidor", "Lugar Requerido", "Mes y Año" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, true, false, true, true,
					true, false, false, false, true, true, true, true, true, false, true, false };

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
				if (Cod.length() == 0) {
					JOptionPane.showMessageDialog(null, "Ingrese Codigo");
					return;
				}
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
		btnBuscar.setBounds(164, 411, 139, 23);
		contentPane.add(btnBuscar);

		txtCod = new JTextField();
		txtCod.setBounds(10, 411, 142, 20);
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
		btnVolver.setBounds(591, 518, 89, 23);
		contentPane.add(btnVolver);

		JLabel lblCintasEncontradas = new JLabel("Cintas Encontradas");
		lblCintasEncontradas.setBounds(473, 414, 128, 16);
		contentPane.add(lblCintasEncontradas);

		txtCont = new JTextField();
		txtCont.setEditable(false);
		txtCont.setBounds(611, 411, 69, 22);
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

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel tm = (DefaultTableModel) Tabla.getModel();

					for (int i = 0; i < Tabla.getModel().getRowCount(); i++) {

						inv = new Inventario();
						String cod = String.valueOf(tm.getValueAt(i, 0)).toUpperCase();
						inv.setContenido(String.valueOf(((String) tm.getValueAt(i, 4)).toUpperCase()));
						inv.setRetencion(String.valueOf(((String) tm.getValueAt(i, 5)).toUpperCase()));
						inv.setValija(String.valueOf(((String) tm.getValueAt(i, 13)).toUpperCase()));
						inv.setContinuacion(String.valueOf(((String) tm.getValueAt(i, 14)).toUpperCase()));
						inv.setObservaciones(String.valueOf(((String) tm.getValueAt(i, 15)).toUpperCase()));
						inv.setSolicitado(String.valueOf(((String) tm.getValueAt(i, 16)).toUpperCase()));
						inv.setResponsable(String.valueOf(((String) tm.getValueAt(i, 17)).toUpperCase()));
						inv.setLugar_Requerido(String.valueOf(((String) tm.getValueAt(i, 19)).toUpperCase()));
						boolean valida = ActualizarInventario(inv, cod);
						if (valida) {
							JOptionPane.showMessageDialog(null, "Modificado Inventario Numero " + cod);
						}

					}

				} catch (Exception ex) {
					throw new IllegalArgumentException(ex.getMessage());
				}
			}
		});
		btnModificar.setBounds(315, 471, 120, 23);
		contentPane.add(btnModificar);
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

	public boolean ActualizarInventario(Inventario inv, String cod) {

		try {
			String sql = "update Inventario\r\n" + "  set  Contenido = ?,\r\n" + " 	retencion = ?,\r\n"
					+ "	Valija = ?,\r\n" + "	Continuacion = ?,\r\n" + "	Observaciones = ?,\r\n"
					+ "	Solicitado = ?,\r\n" + "	Responsable = ?,\r\n" + "	Lugar_Requerido = ?\r\n"
					+ "	where idInventario =? ";

			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, inv.getContenido());
			pstm.setString(2, inv.getRetencion());
			pstm.setString(3, inv.getValija());
			pstm.setString(4, inv.getContinuacion());
			pstm.setString(5, inv.getObservaciones());
			pstm.setString(6, inv.getSolicitado());
			pstm.setString(7, inv.getResponsable());
			pstm.setString(8, inv.getLugar_Requerido());
			pstm.setString(9, cod);

			int x = pstm.executeUpdate();
			return x > 0 ? true : false;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
