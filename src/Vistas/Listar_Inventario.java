package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Class.*;
import Controlador.*;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Desktop;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;

public class Listar_Inventario extends JFrame {

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
	private JButton btnExportarExcel;
	private JButton btnLimpiar;
	private ButtonGroup grupo = new ButtonGroup();
	private JRadioButton rdPlat, rdEx, rdUlt;
	private JDateChooser Desde, Hasta;
	private JComboBox<String> cbodest, cboubi, cboPlat, cboServ;
	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JButton btnBuscarPlat;
	private JButton btnBusServ;
	private JTextField txtCont;
	private int cont = 0;
	private JTextField txtArchivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar_Inventario frame = new Listar_Inventario();
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
	public Listar_Inventario() {
		setTitle("Listar Inventario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 706, 561);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setAutoscrolls(true);
		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		jScrollPane1.setBounds(10, 46, 674, 237);
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
						"Servidor", "Lugar Requerido", "Mes y Año" , "Estado"}) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, true, false, true, true, true,
					false, false, false, true, true, true, true, true, false, true, true, true };

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
		Tabla.getColumnModel().getColumn(21).setPreferredWidth(140);

		jScrollPane1.setViewportView(Tabla);
		contentPane.setLayout(null);

		llenarTabla();

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
					if(valida2) {
						
					}else {
						buscarfin(Cod);
					}

				}
				
				txtCont.setText("" + cont);

				txtCod.setText("");

			}

		});
		btnBuscar.setBounds(168, 327, 139, 23);
		contentPane.add(btnBuscar);

		txtCod = new JTextField();
		txtCod.setBounds(14, 327, 142, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);

		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String nombre = txtArchivo.getText().trim();
					
					String rutaArchivo = System.getProperty("user.home") + "/Desktop/"+nombre+".xls";

					
					
					File archivoXLS = new File(rutaArchivo);

					if (archivoXLS.exists()) {
						archivoXLS.delete();
					} else {

						archivoXLS.createNewFile();
					}

					Workbook libro = new HSSFWorkbook();

					FileOutputStream archivo = new FileOutputStream(archivoXLS);
					Sheet hoja = libro.createSheet("Inventario Cintas");
 
					
					
					for (int x = 0; x < mod.getRowCount() + 1; x++) {
				
						Row fila = hoja.createRow(x + 3);
						for (int c = 0; c < mod.getColumnCount(); c++) {

							Cell celda = fila.createCell(c + 2);

							if (x == 0) {
								celda.setCellValue(mod.getColumnName(c));
							} else {
								celda.setCellValue(mod.getValueAt(x - 1, c).toString());

							}
						}

					}

					libro.write(archivo);
					archivo.close();
					JOptionPane.showMessageDialog(null, "Ruta de archivo "+ rutaArchivo);
					Desktop.getDesktop().open(archivoXLS);
					

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
				}

			}
		});
		btnExportarExcel.setBounds(281, 11, 136, 23);
		contentPane.add(btnExportarExcel);

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
		btnVolver.setBounds(541, 459, 89, 23);
		contentPane.add(btnVolver);

		cbodest = new JComboBox<String>();
		cbodest.setBounds(14, 362, 142, 20);
		contentPane.add(cbodest);

		cboubi = new JComboBox<String>();
		cboubi.setBounds(14, 397, 142, 20);
		contentPane.add(cboubi);

		JButton btnNewButton = new JButton("Buscar Destino");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int des = cbodest.getSelectedIndex();
				buscarDes(des);
				txtCont.setText("" + cont);
				cbodest.setSelectedIndex(0);
			}
		});
		btnNewButton.setBounds(168, 361, 139, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Buscar Ubicacion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ubi = cboubi.getSelectedIndex();
				buscarUbi(ubi);
				txtCont.setText("" + cont);
				cboubi.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(168, 396, 139, 23);
		contentPane.add(btnNewButton_1);

		rdPlat = new JRadioButton("Fecha Respaldo");
		rdPlat.setSelected(true);
		rdPlat.setBounds(10, 429, 139, 23);
		contentPane.add(rdPlat);

		rdEx = new JRadioButton("Fecha Expiracion");
		rdEx.setBounds(151, 429, 135, 23);
		contentPane.add(rdEx);

		rdUlt = new JRadioButton("Fecha Ultima Verificacion");
		rdUlt.setBounds(286, 429, 135, 23);
		contentPane.add(rdUlt);

		grupo.add(rdPlat);
		grupo.add(rdEx);
		grupo.add(rdUlt);

		Desde = new JDateChooser();
		Desde.setBounds(14, 459, 120, 20);
		contentPane.add(Desde);

		Hasta = new JDateChooser();
		Hasta.setBounds(144, 459, 118, 20);
		contentPane.add(Hasta);

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(51, 481, 46, 14);
		contentPane.add(lblDesde);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(183, 481, 46, 14);
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
		btnBuscar_1.setBounds(285, 459, 89, 23);
		contentPane.add(btnBuscar_1);

		cboPlat = new JComboBox<String>();
		cboPlat.setBounds(319, 362, 157, 20);
		contentPane.add(cboPlat);

		cboServ = new JComboBox<String>();
		cboServ.setBounds(319, 397, 157, 20);
		contentPane.add(cboServ);

		btnBuscarPlat = new JButton("Buscar Plataforma");
		btnBuscarPlat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int plat = cboPlat.getSelectedIndex();
				buscaPlat(plat);
				txtCont.setText("" + cont);
				cboPlat.setSelectedIndex(0);
			}
		});
		btnBuscarPlat.setBounds(495, 359, 168, 23);
		contentPane.add(btnBuscarPlat);

		btnBusServ = new JButton("Buscar Servidor");
		btnBusServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int serv = cboServ.getSelectedIndex();
				buscaServ(serv);
				txtCont.setText("" + cont);

				cboServ.setSelectedIndex(0);
			}
		});
		btnBusServ.setBounds(495, 394, 168, 23);
		contentPane.add(btnBusServ);

		JLabel lblCintasEncontradas = new JLabel("Cintas Encontradas");
		lblCintasEncontradas.setBounds(421, 295, 128, 16);
		contentPane.add(lblCintasEncontradas);

		txtCont = new JTextField();
		txtCont.setEditable(false);
		txtCont.setBounds(561, 295, 89, 22);
		contentPane.add(txtCont);
		txtCont.setColumns(10);
		Cargarcbo();
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
		
		txtArchivo = new JTextField();
		txtArchivo.setBounds(8, 6, 122, 28);
		contentPane.add(txtArchivo);
		txtArchivo.setColumns(10);
		
		JLabel lblNombreArchivo = new JLabel("Nombre Busqueda");
		lblNombreArchivo.setBounds(140, 14, 118, 16);
		contentPane.add(lblNombreArchivo);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { jScrollPane1, Tabla }));
	}

	private void Cargarcbo() {
		cbodest.addItem("Seleccionar");
		cboubi.addItem("Seleccionar");
		cboPlat.addItem("Seleccionar");
		cboServ.addItem("Seleccionar");

		Cont_Ubicacion CU = new Cont_Ubicacion();
		Cont_Plataforma CPL = new Cont_Plataforma();
		Cont_Servidor CS = new Cont_Servidor();
		ArrayList<Ubicacion> listUbi = new ArrayList<>();
		listUbi = CU.listUBI();
		ArrayList<Servidor> listServ = new ArrayList<>();
		listServ = CS.listar();
		ArrayList<Plataforma> listPlat = new ArrayList<>();
		listPlat = CPL.listar();

		for (Ubicacion ubicacion : listUbi) {
			cboubi.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
			cbodest.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
		}

		for (Plataforma plat : listPlat) {
			cboPlat.addItem(plat.getIdPlataforma() + " - " + plat.getNomPlataforma());

		}

		for (Servidor sserv : listServ) {
			cboServ.addItem(sserv.getIdServidor() + " - " + sserv.getNombre());
		}
	}

	private void llenarTabla() {

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
					+ "	   Inventario.Lugar_Requerido,\r\n" + "	   Inventario.mes_anio,\r\n" 
					+ "	   Inventario.Estado\r\n"
					+ "from inventario \r\n"
					+ "inner join Cinta\r\n" + "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
					+ "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" + "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" + "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" + "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" + "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);
				}

				mod.addRow(raws);
				cont++;
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

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

	private void buscarDes(int dest) {
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
					+ "where Inventario.Destino_Actual  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, dest);
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

	private void buscarUbi(int ubi) {
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
					+ "where Inventario.Ubicacion_Bodega  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, ubi);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			int cont2 = 0;
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);

				}
				cont2++;
				mod.addRow(raws);
				cont++;

			}
			if (cont2 < 1) {
				JOptionPane.showMessageDialog(null, "No se encuentran resultados");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	private void buscaPlat(int plat) {
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
					+ "where Inventario.Plataforma_idPlataforma  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, plat);
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

	private void buscaServ(int serv) {
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
					+ "where Inventario.Servidor_idServidor  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, serv);
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

			} else if (rdEx.isSelected()) {
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
						+ "where  Inventario.Fecha_Exp >= ? and Inventario.Fecha_Exp <= ?";

			} else if (rdUlt.isSelected()) {
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
						+ "where  Inventario.Fecha_ultim >= ? and Inventario.Fecha_ultim <= ?";
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
}
