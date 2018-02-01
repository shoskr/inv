package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Class.*;
import Controlador.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class AgregarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static AgregarUsuario frameusu;
	private JTextField txtusu;
	private JPasswordField txtPass1;
	private JPasswordField txtPass2;
	private Cont_Usuario CU = new Cont_Usuario();
	private Usuario usu = new Usuario();
	private JComboBox<String> cboTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameusu = new AgregarUsuario();
					frameusu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setTitle("Agregar Usuario");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Usuario           :");
		lblNombre.setBounds(35, 46, 85, 14);
		getContentPane().add(lblNombre);

		JLabel lblClave = new JLabel("Clave              :");
		lblClave.setBounds(35, 71, 85, 14);
		getContentPane().add(lblClave);

		JLabel lblRepetirClave = new JLabel("Repetir Clave :");
		lblRepetirClave.setBounds(35, 96, 85, 14);
		getContentPane().add(lblRepetirClave);

		txtusu = new JTextField();
		txtusu.setBounds(130, 43, 86, 20);
		getContentPane().add(txtusu);
		txtusu.setColumns(10);

		txtPass1 = new JPasswordField();
		txtPass1.setBounds(130, 68, 86, 20);
		getContentPane().add(txtPass1);

		txtPass2 = new JPasswordField();
		txtPass2.setBounds(130, 93, 86, 20);
		getContentPane().add(txtPass2);

		JLabel lblTipoUsuario = new JLabel("Tipo Usuario   : ");
		lblTipoUsuario.setBounds(35, 123, 85, 14);
		getContentPane().add(lblTipoUsuario);

		cboTipo = new JComboBox<String>();
		cboTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccionar" }));
		cboTipo.setBounds(130, 120, 86, 20);
		getContentPane().add(cboTipo);

		ArrayList<Object> lista = new ArrayList<>();
		lista = CU.listarTipo();
		for (Object object : lista) {
			cboTipo.addItem("" + object);
		}

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int Tipo = cboTipo.getSelectedIndex();
				String Nombre = txtusu.getText().toUpperCase();
				ArrayList<Usuario> lista = new ArrayList<>();
				lista = CU.listar();

				for (Usuario usuario : lista) {
					if (Nombre.equals(usuario.getNombre())) {

						JOptionPane.showMessageDialog(null, "Usuario Existente");
						return;
					}
				}

				if (Tipo > 0 && txtPass1.getText().length() >= 6 && txtPass1.getText().length() <= 20) {
					if (txtPass1.getText().equals(txtPass2.getText())) {
						String Pass = Code(txtPass1.getText());
						usu = new Usuario();
						usu.setNombre(Nombre);
						usu.setClave(Pass);
						usu.setTipo(Tipo);
						CU.ingresarUsuario(usu);
						JOptionPane.showMessageDialog(null, "Usuario Ingresado");
						txtPass1.setText("");
						txtPass2.setText("");
						txtusu.setText("");
						cboTipo.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(null, "No Coinciden Las Contraseñas");
						txtPass1.setText("");
						txtPass2.setText("");
						txtusu.setText("");
						cboTipo.setSelectedIndex(0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Usuario");
					JOptionPane.showMessageDialog(null, "la contraseña debe tener entre 6 a 20 caracteres");
				}

			}
		});
		btnGuardar.setBounds(310, 42, 89, 23);
		getContentPane().add(btnGuardar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();

			}
		});
		btnVolver.setBounds(335, 228, 89, 23);
		contentPane.add(btnVolver);
	}

	private static String Code(String a) {
		Base64.Encoder encoder = Base64.getEncoder();
		String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8));
		return b;
	}

}
