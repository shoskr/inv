package Vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

import javax.swing.JComboBox;


import Class.*;
import Controlador.*;

import javax.swing.DefaultComboBoxModel;

import javax.swing.UIManager;

import java.util.Date;
import java.awt.Color;


public class ModificarInventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ModificarInventario frame;
	private JTextField txtcont;
	private JTextField txtreten;
	private JTextField txtVali;
	private JTextField txtcontinu;
	private JTextField txtobs;
	private JTextField txtSoli;
	private JTextField txtresp;
	private JTextField txtLug;
	private JComboBox<String> cboCinta, cboPlat, cboUbicacion, cboPais, cboServ, cbodesti, cbomes;
	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JTextField txtInv;
	private Connection conn = Conexion.getConnectio();
	private JDateChooser datefPlat, dateFExp, dateFult;
	private JTextField txtAnio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ModificarInventario();
					frame.setVisible(true);
					UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificarInventario() {
		setTitle("Modificacdor Datos de Inventarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 734, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(522, 313, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MenuP m = new MenuP();
				//m.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(btnVolver);

		JLabel lblCinta = new JLabel("Cinta");
		lblCinta.setBounds(17, 8, 142, 16);
		contentPane.add(lblCinta);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(17, 36, 142, 16);
		contentPane.add(lblContenido);

		JLabel lblRetencion = new JLabel("Retencion");
		lblRetencion.setBounds(17, 64, 142, 16);
		contentPane.add(lblRetencion);

		JLabel lblPlataforma = new JLabel("Plataforma");
		lblPlataforma.setBounds(17, 92, 142, 16);
		contentPane.add(lblPlataforma);

		JLabel lblFechaPlataforma = new JLabel("Fecha  Respaldo");
		lblFechaPlataforma.setBounds(17, 120, 142, 16);
		contentPane.add(lblFechaPlataforma);

		JLabel lblFechaExpiracion = new JLabel("Fecha Expiracion");
		lblFechaExpiracion.setBounds(17, 148, 142, 16);
		contentPane.add(lblFechaExpiracion);

		JLabel lblFechaUltima = new JLabel("Fecha Ultima Verificacion");
		lblFechaUltima.setBounds(17, 176, 142, 16);
		contentPane.add(lblFechaUltima);

		JLabel lblUbicacionBodega = new JLabel("Ubicacion Bodega");
		lblUbicacionBodega.setBounds(17, 204, 142, 16);
		contentPane.add(lblUbicacionBodega);

		JLabel lblDestinoActual = new JLabel("Pais");
		lblDestinoActual.setBounds(360, 6, 100, 16);
		contentPane.add(lblDestinoActual);

		JLabel lblValija = new JLabel("Valija");
		lblValija.setBounds(360, 34, 75, 16);
		contentPane.add(lblValija);

		JLabel lblContinuacion = new JLabel("Continuacion");
		lblContinuacion.setBounds(360, 57, 75, 16);
		contentPane.add(lblContinuacion);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(360, 79, 100, 16);
		contentPane.add(lblObservaciones);

		JLabel lblSolicitado = new JLabel("Solicitado");
		lblSolicitado.setBounds(360, 107, 75, 16);
		contentPane.add(lblSolicitado);

		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(360, 132, 75, 16);
		contentPane.add(lblResponsable);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(360, 160, 75, 16);
		contentPane.add(lblServidor);

		JLabel lblLugarRequerido = new JLabel("Lugar Requerido");
		lblLugarRequerido.setBounds(360, 188, 100, 16);
		contentPane.add(lblLugarRequerido);

		JLabel lblMesYAo = new JLabel("Mes y A\u00F1o");
		lblMesYAo.setBounds(360, 229, 75, 16);
		contentPane.add(lblMesYAo);

		cboCinta = new JComboBox<String>();
		cboCinta.setBounds(171, 5, 127, 21);
		contentPane.add(cboCinta);

		cboPlat = new JComboBox<String>();
		cboPlat.setBounds(171, 89, 127, 21);
		contentPane.add(cboPlat);

		cboUbicacion = new JComboBox<String>();
		cboUbicacion.setBounds(171, 201, 127, 21);
		contentPane.add(cboUbicacion);

		cboPais = new JComboBox<String>();
		cboPais.setBounds(491, 4, 120, 21);
		contentPane.add(cboPais);

		cboServ = new JComboBox<String>();
		cboServ.setBounds(491, 158, 120, 21);
		contentPane.add(cboServ);

		txtcont = new JTextField();
		txtcont.setBounds(171, 32, 127, 22);
		txtcont.setColumns(10);
		contentPane.add(txtcont);

		txtreten = new JTextField();
		txtreten.setBounds(171, 60, 127, 22);
		txtreten.setColumns(10);
		contentPane.add(txtreten);

		datefPlat = new JDateChooser();
		datefPlat.setBounds(171, 119, 127, 22);
		datefPlat.setDateFormatString("dd/MM/yyyy");
		contentPane.add(datefPlat);

		dateFExp = new JDateChooser();
		dateFExp.setBounds(171, 147, 127, 22);
		dateFExp.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateFExp);

		dateFult = new JDateChooser();
		dateFult.setBounds(171, 175, 127, 22);
		dateFult.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateFult);

		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setBounds(19, 326, 94, 24);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Cont_Inventario CI = new Cont_Inventario();

					Inventario inv = new Inventario();
					String idCinta[] = ((String) cboCinta.getSelectedItem()).split("-");
					String idC = idCinta[0].replaceAll(" ", "");
					int Cinta_idCinta = 0;
					if (idCinta[0] != ("Seleccionar")) {
						Cinta_idCinta = Integer.parseInt(idC);
					} else {
						Cinta_idCinta = 0;
					}
					;

					String Contenido = txtcont.getText().toUpperCase();
					String retencion = txtreten.getText().toUpperCase();

					String idOlat[] = ((String) cboPlat.getSelectedItem()).split("-");
					String idP = idOlat[0].replaceAll(" ", "");
					int Plataforma = 0;
					if (idOlat[0] != ("Seleccionar")) {
						Plataforma = Integer.parseInt(idP);
					} else {
						Plataforma = 0;
					}
					;
					String Fecha_Plataforma = (String) sdf.format(datefPlat.getDate());
					String Fecha_Exp = (String) sdf.format(dateFExp.getDate());
					String Fecha_ultim = (String) sdf.format(dateFult.getDate());
					String idpai[] = ((String) cboPais.getSelectedItem()).split("-");
					String idPa = idpai[0].replaceAll(" ", "");
					int Pais_idPais = 0;
					if (idpai[0] != ("Seleccionar")) {
						Pais_idPais = Integer.parseInt(idPa);
					} else {
						Pais_idPais = 0;
					}
					;
					String idubi[] = ((String) cboUbicacion.getSelectedItem()).split("-");
					String idUb = idubi[0].replaceAll(" ", "");
					int Ubicacion_Bodega = 0;
					if (idubi[0] != ("Seleccionar")) {
						Ubicacion_Bodega = Integer.parseInt(idUb);
					} else {
						Ubicacion_Bodega = 0;
					}
					;
					String idDEs[] = ((String) cbodesti.getSelectedItem()).split("-");
					String iddes = idDEs[0].replaceAll(" ", "");
					int Destino_Actual = 0;
					if (idDEs[0] != ("Seleccionar")) {
						Destino_Actual = Integer.parseInt(iddes);
					} else {
						Destino_Actual = 0;
					}
					;
					String Valija = txtVali.getText().toUpperCase();
					String Continuacion = txtcontinu.getText().toUpperCase();
					String Observaciones = txtobs.getText().toUpperCase();
					String Solicitado = txtSoli.getText().toUpperCase();
					String Responsable = txtresp.getText().toUpperCase();
					String idSer[] = ((String) cboServ.getSelectedItem()).split("-");
					String idser = idSer[0].replaceAll(" ", "");
					int Servidor = 0;
					if (idSer[0] != ("Seleccionar")) {
						Servidor = Integer.parseInt(idser);
					} else {
						Servidor = 0;
					}
					;

					String Lugar_Requerido = txtLug.getText().toUpperCase();
					
					int anio = Integer.parseInt(txtAnio.getText());					
					
					String mes_anio = cbomes.getSelectedItem() + " - " + anio;

					inv.setCinta_idCinta(Cinta_idCinta);
					inv.setContenido(Contenido);
					inv.setRetencion(retencion);
					inv.setPlataforma(Plataforma);
					inv.setFecha_Plataforma(Fecha_Plataforma);
					inv.setFecha_Exp(Fecha_Exp);
					inv.setFecha_ultim(Fecha_ultim);
					inv.setPais_idPais(Pais_idPais);
					inv.setUbicacion_Bodega(Ubicacion_Bodega);
					inv.setDestino_Actual(Destino_Actual);
					inv.setValija(Valija);
					inv.setContinuacion(Continuacion);
					inv.setObservaciones(Observaciones);
					inv.setResponsable(Responsable);
					inv.setServidor(Servidor);
					inv.setSolicitado(Solicitado);
					inv.setLugar_Requerido(Lugar_Requerido);
					inv.setMes_anio(mes_anio);

					String cod = txtInv.getText().toUpperCase();
					
					boolean valida = CI.ActualizarInventario(inv, cod);

					if (valida) {
						JOptionPane.showMessageDialog(null, "modificado");
					} else {
						JOptionPane.showMessageDialog(null, "No Modificado");
					}

					// restablecer cbo
					cboCinta.setSelectedIndex(0);
					cbodesti.setSelectedIndex(0);
					cboPais.setSelectedIndex(0);
					cboPlat.setSelectedIndex(0);
					cboServ.setSelectedIndex(0);
					cboUbicacion.setSelectedIndex(0);
					// restablecer txt
					// txtcod.setText("");
					txtcont.setText("");
					txtreten.setText("");
					txtVali.setText("");
					txtcontinu.setText("");
					txtobs.setText("");
					txtSoli.setText("");
					txtresp.setText("");
					txtLug.setText("");

					dateFult.setCalendar(null);
					datefPlat.setCalendar(null);
					dateFExp.setCalendar(null);
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
				}
			}

		});
		contentPane.add(btnNewButton);

		txtVali = new JTextField();
		txtVali.setBounds(491, 31, 120, 22);
		contentPane.add(txtVali);
		txtVali.setColumns(10);

		txtcontinu = new JTextField();
		txtcontinu.setBounds(491, 54, 120, 22);
		txtcontinu.setColumns(10);
		contentPane.add(txtcontinu);

		txtobs = new JTextField();
		txtobs.setBounds(491, 76, 120, 22);
		txtobs.setColumns(10);
		contentPane.add(txtobs);

		txtSoli = new JTextField();
		txtSoli.setBounds(491, 104, 120, 22);
		txtSoli.setColumns(10);
		contentPane.add(txtSoli);

		txtresp = new JTextField();
		txtresp.setBounds(491, 129, 120, 22);
		txtresp.setColumns(10);
		contentPane.add(txtresp);

		txtLug = new JTextField();
		txtLug.setBounds(491, 185, 120, 22);
		txtLug.setColumns(10);
		contentPane.add(txtLug);

		JLabel lblDestinoActua = new JLabel("Destino Actua");
		lblDestinoActua.setBounds(17, 232, 142, 16);
		contentPane.add(lblDestinoActua);

		cbodesti = new JComboBox<String>();
		cbodesti.setBounds(171, 234, 127, 21);
		contentPane.add(cbodesti);

		cbomes = new JComboBox<String>();
		cbomes.setBounds(491, 227, 120, 21);
		cbomes.setModel(new DefaultComboBoxModel<String>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
				"JULIO", "AGOSTO", "SEPTIEMBRE", "NOVIEMBRE", "DICIOEMBRE" }));
		contentPane.add(cbomes);

		CargarCbos();

		cboCinta.setSelectedItem("01 - PRUEBA - PRUEBA");

		txtInv = new JTextField();
		txtInv.setBounds(14, 278, 142, 22);
		contentPane.add(txtInv);
		txtInv.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(181, 277, 94, 24);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cod = txtInv.getText();
				
				buscarinv(cod);
				
			}
		});
		contentPane.add(btnBuscar);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(637, 226, 64, 22);
		contentPane.add(txtAnio);
		txtAnio.setColumns(10);

	}

	private void CargarCbos() {

		// Se cargan los cbo.

		cboCinta.addItem("Seleccionar");
		cbodesti.addItem("Seleccionar");
		cboPais.addItem("Seleccionar");
		cboPlat.addItem("Seleccionar");
		cboServ.addItem("Seleccionar");
		cboUbicacion.addItem("Seleccionar");

		
		Cont_Cinta CC = new Cont_Cinta();
		Cont_Pais CP = new Cont_Pais();
		Cont_Plataforma CPL = new Cont_Plataforma();
		Cont_Servidor CS = new Cont_Servidor();
		Cont_Ubicacion CU = new Cont_Ubicacion();

		ArrayList<Cinta> listCin = new ArrayList<>();
		listCin = CC.listar();
		ArrayList<Pais> listpais = new ArrayList<>();
		listpais = CP.listar();
		ArrayList<Servidor> listServ = new ArrayList<>();
		listServ = CS.listar();
		ArrayList<Plataforma> listPlat = new ArrayList<>();
		listPlat = CPL.listar();
		ArrayList<Ubicacion> listUbi = new ArrayList<>();
		listUbi = CU.listUBI();

		for (Ubicacion ubicacion : listUbi) {
			cboUbicacion.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
			cbodesti.addItem(ubicacion.getIdubicacion() + " - " + ubicacion.getLugar());
		}

		for (Plataforma plat : listPlat) {
			cboPlat.addItem(plat.getIdPlataforma() + " - " + plat.getNomPlataforma());

		}

		for (Servidor sserv : listServ) {
			cboServ.addItem(sserv.getIdServidor() + " - " + sserv.getNombre());
		}

		for (Pais pai : listpais) {

			cboPais.addItem(pai.getIdPais() + " - " + pai.getNombre());
		}

		for (Cinta cin : listCin) {
			cboCinta.addItem(cin.getIdCinta() + " - " + cin.getModelo() + " - " + cin.getMarca());
		 
		}

	}

	private void buscarinv(String cod) {
		try {
			String sql = "select * from inventario\r\n"
					+ "where Inventario.idInventario  = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, cod);
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				
				cboCinta.setSelectedIndex(rs.getInt(2));
				cbodesti.setSelectedIndex(rs.getInt(11));
				cboPais.setSelectedIndex(rs.getInt(9));
				cboPlat.setSelectedIndex(rs.getInt(5));
				cboServ.setSelectedIndex(rs.getInt(17));
				cboUbicacion.setSelectedIndex(rs.getInt(10));
				txtcont.setText(rs.getString(3));
				txtreten.setText(rs.getString(4));
				txtVali.setText(rs.getString(12));
				txtcontinu.setText(rs.getString(13));
				txtobs.setText(rs.getString(14));
				txtSoli.setText(rs.getString(15));
				txtresp.setText(rs.getString(16));
				txtLug.setText(rs.getString(18));
				String mesAnio = rs.getString(19);
				String div[] = mesAnio.split("-");			
				cbomes.setSelectedItem(div[0].replaceAll(" ", ""));
				txtAnio.setText(div[1].replaceAll(" ", ""));
				Date ful = rs.getDate(6);
				Date plat = rs.getDate(7);
				Date exp = rs.getDate(8);
				dateFult.setDate(ful);
				datefPlat.setDate(plat);
				dateFExp.setDate(exp);
				
				
			} else {

				JOptionPane.showMessageDialog(null, "No existe en inventario");

			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}
}
