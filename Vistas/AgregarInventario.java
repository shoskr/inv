package Vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

import javax.swing.JComboBox;


import Class.*;
import Controlador.*;
import java.awt.Cursor;
import java.awt.Color;

public class AgregarInventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarInventario frame;
	private JTextField txtcod;
	private JTextField txtcont;
	private JTextField txtreten;
	private JTextField txtVali;
	private JTextField txtcontinu;
	private JTextField txtobs;
	private JTextField txtSoli;
	private JTextField txtresp;
	private JTextField txtLug;
	private static JComboBox<String> cboCinta, cboPlat, cboUbicacion, cboPais, cboServ, cbodesti, cboAnio, cbomes;
	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
	private JDateChooser datefPlat = new JDateChooser();
	private JDateChooser dateFExp = new JDateChooser();
	private JDateChooser dateFult = new JDateChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AgregarInventario();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AgregarInventario() {
		setTitle("Agregar Inventario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 859, 420);
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
		btnVolver.setBounds(581, 328, 161, 23);
		contentPane.add(btnVolver);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(17, 6, 75, 16);
		contentPane.add(lblCodigo);

		JLabel lblCinta = new JLabel("Cinta");
		lblCinta.setBounds(17, 34, 107, 16);
		contentPane.add(lblCinta);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(17, 62, 107, 16);
		contentPane.add(lblContenido);

		JLabel lblRetencion = new JLabel("Retencion");
		lblRetencion.setBounds(17, 90, 107, 16);
		contentPane.add(lblRetencion);

		JLabel lblPlataforma = new JLabel("Plataforma");
		lblPlataforma.setBounds(17, 118, 107, 16);
		contentPane.add(lblPlataforma);

		JLabel lblFechaPlataforma = new JLabel("Fecha Plataforma");
		lblFechaPlataforma.setBounds(17, 146, 107, 16);
		contentPane.add(lblFechaPlataforma);

		JLabel lblFechaExpiracion = new JLabel("Fecha Expiracion");
		lblFechaExpiracion.setBounds(17, 174, 107, 16);
		contentPane.add(lblFechaExpiracion);

		JLabel lblFechaUltima = new JLabel("Fecha Ultima");
		lblFechaUltima.setBounds(17, 202, 107, 16);
		contentPane.add(lblFechaUltima);

		JLabel lblUbicacionBodega = new JLabel("Ubicacion Bodega");
		lblUbicacionBodega.setBounds(17, 230, 107, 16);
		contentPane.add(lblUbicacionBodega);

		JLabel lblDestinoActual = new JLabel("Pais");
		lblDestinoActual.setBounds(417, 8, 100, 16);
		contentPane.add(lblDestinoActual);

		JLabel lblValija = new JLabel("Valija");
		lblValija.setBounds(417, 36, 75, 16);
		contentPane.add(lblValija);

		JLabel lblContinuacion = new JLabel("Continuacion");
		lblContinuacion.setBounds(417, 59, 75, 16);
		contentPane.add(lblContinuacion);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(417, 81, 100, 16);
		contentPane.add(lblObservaciones);

		JLabel lblSolicitado = new JLabel("Solicitado");
		lblSolicitado.setBounds(417, 109, 75, 16);
		contentPane.add(lblSolicitado);

		JLabel lblResponsable = new JLabel("Responsable");
		lblResponsable.setBounds(417, 134, 75, 16);
		contentPane.add(lblResponsable);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(417, 162, 75, 16);
		contentPane.add(lblServidor);

		JLabel lblLugarRequerido = new JLabel("Lugar Requerido");
		lblLugarRequerido.setBounds(417, 190, 100, 16);
		contentPane.add(lblLugarRequerido);

		JLabel lblMesYAo = new JLabel("Mes y A\u00F1o");
		lblMesYAo.setBounds(417, 218, 75, 16);
		contentPane.add(lblMesYAo);

		cboCinta = new JComboBox();
		cboCinta.setBounds(147, 32, 196, 21);
		contentPane.add(cboCinta);

		cboPlat = new JComboBox();
		cboPlat.setBounds(147, 116, 196, 21);
		contentPane.add(cboPlat);

		cboUbicacion = new JComboBox();
		cboUbicacion.setBounds(147, 228, 196, 21);
		contentPane.add(cboUbicacion);

		cboPais = new JComboBox();
		cboPais.setBounds(548, 6, 194, 21);
		contentPane.add(cboPais);

		cboServ = new JComboBox();
		cboServ.setBounds(548, 160, 194, 21);
		contentPane.add(cboServ);

		txtcod = new JTextField();
		txtcod.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtcod.setBounds(147, 6, 196, 22);
		contentPane.add(txtcod);
		txtcod.setColumns(10);

		txtcont = new JTextField();
		txtcont.setColumns(10);
		txtcont.setBounds(147, 59, 196, 22);
		contentPane.add(txtcont);

		txtreten = new JTextField();
		txtreten.setColumns(10);
		txtreten.setBounds(147, 87, 196, 22);
		contentPane.add(txtreten);

		datefPlat = new JDateChooser();
		datefPlat.setDateFormatString("dd/MM/yyyy");
		datefPlat.setBounds(147, 146, 196, 22);
		contentPane.add(datefPlat);

		dateFExp = new JDateChooser();
		dateFExp.setDateFormatString("dd/MM/yyyy");
		dateFExp.setBounds(147, 174, 196, 22);
		contentPane.add(dateFExp);

		dateFult = new JDateChooser();
		dateFult.setDateFormatString("dd/MM/yyyy");
		dateFult.setBounds(147, 202, 196, 22);
		contentPane.add(dateFult);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (txtcod.getText().length() <= 0) {
						JOptionPane.showMessageDialog(null, "Asegurece de Ingresar Codigo de inventario");
						return;
					}

					if (cboCinta.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Cinta");
						return;
					}
					if (cboPlat.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Plataforma");
						return;
					}
					if (cboUbicacion.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Ubicacion");
						return;
					}
					if (cbodesti.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Destino");
						return;
					}
					if (cboPais.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Pais");
						return;
					}
					if (cboServ.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Servidor");
						return;
					}

					if (cbomes.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Mes");
						return;
					}

					if (cboAnio.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Debe Seleccionar Un Año");
						return;
					}

					Cont_Inventario CI = new Cont_Inventario();
					Inventario inv = new Inventario();
					String idInventario = txtcod.getText().toUpperCase();
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
					String mes_anio = cbomes.getSelectedItem() + " - " + cboAnio.getSelectedItem();

					inv.setIdInventario(idInventario);
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

					boolean valida = CI.ingresarInventario(inv);

					if (valida) {
						JOptionPane.showMessageDialog(null, "Guardo");
					} else {
						JOptionPane.showMessageDialog(null, "No Guardo");
					}

					// restablecer cbo
					cboCinta.setSelectedIndex(0);
					cbodesti.setSelectedIndex(0);
					cboPais.setSelectedIndex(0);
					cboPlat.setSelectedIndex(0);
					cboServ.setSelectedIndex(0);
					cboUbicacion.setSelectedIndex(0);
					// restablecer txt
					txtcod.setText("");
					txtcont.setText("");
					txtreten.setText("");
					txtVali.setText("");
					txtcontinu.setText("");
					txtobs.setText("");
					txtSoli.setText("");
					txtresp.setText("");
					txtLug.setText("");

					Calendar fecha = Calendar.getInstance();

					datefPlat.setDate(fecha.getTime());
					dateFExp.setDate(fecha.getTime());
					dateFult.setDate(fecha.getTime());
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
				}
			}

		});
		btnNewButton.setBounds(116, 327, 148, 24);
		contentPane.add(btnNewButton);

		txtVali = new JTextField();
		txtVali.setBounds(548, 33, 194, 22);
		contentPane.add(txtVali);
		txtVali.setColumns(10);

		txtcontinu = new JTextField();
		txtcontinu.setColumns(10);
		txtcontinu.setBounds(548, 56, 194, 22);
		contentPane.add(txtcontinu);

		txtobs = new JTextField();
		txtobs.setColumns(10);
		txtobs.setBounds(548, 78, 194, 22);
		contentPane.add(txtobs);

		txtSoli = new JTextField();
		txtSoli.setColumns(10);
		txtSoli.setBounds(548, 106, 194, 22);
		contentPane.add(txtSoli);

		txtresp = new JTextField();
		txtresp.setColumns(10);
		txtresp.setBounds(548, 131, 194, 22);
		contentPane.add(txtresp);

		txtLug = new JTextField();
		txtLug.setColumns(10);
		txtLug.setBounds(548, 187, 194, 22);
		contentPane.add(txtLug);

		JLabel lblDestinoActua = new JLabel("Destino Actual");
		lblDestinoActua.setBounds(17, 258, 107, 16);
		contentPane.add(lblDestinoActua);

		cbodesti = new JComboBox();
		cbodesti.setBounds(147, 261, 196, 21);
		contentPane.add(cbodesti);

		cbomes = new JComboBox();
		cbomes.setModel(new DefaultComboBoxModel(new String[] { "Mes", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO",
				"JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "NOVIEMBRE", "DICIOEMBRE" }));
		cbomes.setBounds(548, 216, 194, 21);
		contentPane.add(cbomes);

		cboAnio = new JComboBox();
		cboAnio.setBounds(754, 216, 62, 21);
		contentPane.add(cboAnio);

		CargarCbos();
	}

	private void CargarCbos() {

		// Se cargan los cbo.

		cboCinta.addItem("Seleccionar");
		cbodesti.addItem("Seleccionar");
		cboPais.addItem("Seleccionar");
		cboPlat.addItem("Seleccionar");
		cboServ.addItem("Seleccionar");
		cboUbicacion.addItem("Seleccionar");
		cboAnio.addItem("Año");
		

		for (int i = 1999; i <= 2099; i++) {
			cboAnio.addItem("" + i);
		}

		cboAnio.setSelectedIndex(0);
		cbomes.setSelectedIndex(0);

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

		Calendar fecha = Calendar.getInstance();

		datefPlat.setDate(fecha.getTime());
		dateFExp.setDate(fecha.getTime());
		dateFult.setDate(fecha.getTime());

	}
}
