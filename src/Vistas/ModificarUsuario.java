package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.*;
import Controlador.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ModificarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsu;
	private JPasswordField txtpss;
	private JPasswordField txtpass2;
	private Usuario usu = new Usuario();
	private Cont_Usuario CU = new Cont_Usuario();
	private static ModificarUsuario frame;
	private Connection conn = Conexion.getConnectio();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ModificarUsuario();
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
	public ModificarUsuario() {
		setTitle("Modificar Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 332, 322);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsu = new JTextField();
		txtUsu.setEditable(false);
		txtUsu.setBounds(159, 35, 116, 20);
		contentPane.add(txtUsu);
		txtUsu.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario                 :");
		lblUsuario.setBounds(13, 35, 103, 14);
		contentPane.add(lblUsuario);

		txtpss = new JPasswordField();
		txtpss.setBounds(159, 66, 116, 20);
		contentPane.add(txtpss);

		JLabel lblClave = new JLabel(" Clave                   :");
		lblClave.setBounds(13, 66, 103, 14);
		contentPane.add(lblClave);

		JComboBox<String> cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Sleccione" }));
		cboTipo.setBounds(159, 116, 116, 20);
		contentPane.add(cboTipo);

		ArrayList<Object> lista = new ArrayList<>();
		lista = CU.listarTipo();
		for (Object object : lista) {
			cboTipo.addItem("" + object);
		}

		JLabel lblTipoUsuario = new JLabel("Tipo Usuario         :");
		lblTipoUsuario.setBounds(13, 116, 116, 14);
		contentPane.add(lblTipoUsuario);

		JComboBox<String> cboUsu = new JComboBox<String>();
		cboUsu.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione" }));
		cboUsu.setBounds(13, 161, 116, 20);
		contentPane.add(cboUsu);

		ArrayList<Usuario> lista2 = new ArrayList<>();
		lista2 = CU.listar();
		for (Usuario usuario : lista2) {
			cboUsu.addItem("" + usuario.getNombre());
		}

		JButton btnNewButton = new JButton("Guardar Modificacion y Salir");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (txtpss.getText().length() >= 6 && txtpss.getText().length() <= 20) {
					if (txtpss.getText().equals(txtpass2.getText())) {
						usu = new Usuario();
						String tex = Code(txtpss.getText());
						usu.setClave(tex);
						usu.setTipo(cboTipo.getSelectedIndex());
						JOptionPane.showMessageDialog(null, "Usuario Modificado");
						
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Contraseñas no Coinciden");
						txtpss.setText("");
						txtpass2.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "la contraseña debe tener entre 6 a 20 caracteres");
					txtpss.setText("");
					txtpass2.setText("");
				}
			}
		});
		btnNewButton.setBounds(13, 195, 262, 23);
		contentPane.add(btnNewButton);

		JLabel lblReingresarClave = new JLabel(" reingresar clave  :");
		lblReingresarClave.setBounds(13, 91, 103, 14);
		contentPane.add(lblReingresarClave);

		JButton btnBuscar = new JButton("Seleccionar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nn = cboUsu.getSelectedItem() + "";

				try {

					String sql = "select * from Usuario where Nombre = ?;";

					PreparedStatement pstm = conn.prepareCall(sql);
					pstm.setString(1, nn);
					ResultSet rs = pstm.executeQuery();
					if (rs.next()) {
						txtUsu.setText(rs.getString(2));
						cboTipo.setSelectedIndex(rs.getInt(4));
					}

				} catch (Exception ex) {
					throw new IllegalArgumentException(ex.getMessage());
				}
			}
		});
		btnBuscar.setBounds(159, 160, 116, 23);
		contentPane.add(btnBuscar);

		txtpass2 = new JPasswordField();
		txtpass2.setBounds(159, 91, 116, 20);
		contentPane.add(txtpass2);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			MenuAdmin madmi = new MenuAdmin();
			madmi.setVisible(true);
			dispose();
			}
		});
		btnVolver.setBounds(100, 230, 94, 24);
		contentPane.add(btnVolver);

	}

	private static String Code(String a) {
		Base64.Encoder encoder = Base64.getEncoder();
		String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8));
		return b;
	}
}
