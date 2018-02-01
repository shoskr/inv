package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.*;
import Controlador.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtusu;
	private JPasswordField txtpass;
	private JButton btnNewButton;
	private static Login frame;
	private int Cont = 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame = new Login();
					frame.setVisible(true);
					Inicio ini = new Inicio();
					ini.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 450, 236);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(79, 51, 66, 21);
		contentPane.add(lblUsuario);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(80, 82, 68, 21);
		contentPane.add(lblClave);

		txtusu = new JTextField();
		txtusu.setBounds(163, 53, 102, 20);
		contentPane.add(txtusu);
		txtusu.setColumns(10);

		txtpass = new JPasswordField();
		txtpass.setBounds(163, 81, 104, 20);
		contentPane.add(txtpass);

		btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					Cont_Usuario CU = new Cont_Usuario();
					Usuario usu = new Usuario();
					String Usuari = txtusu.getText();
					String Pass = Code(txtpass.getText());

					if (Cont < 3) {

						usu.setNombre(Usuari);
						usu.setClave(Pass);

						int acceso = CU.validaUsuario(usu);

						if (acceso > 0) {

							if (acceso == 1) {
								JOptionPane.showMessageDialog(null, "Usuario Valido " + Usuari);
								MenuAdmin adm = new MenuAdmin();
								adm.setVisible(true);
								MenuP men = new MenuP();
								men.setVisible(true);
								dispose();
							} else if (acceso == 2) {
								JOptionPane.showMessageDialog(null, "Usuario Valido " + Usuari);
								MenuP men = new MenuP();
								men.setVisible(true);
								dispose();

							}

						} else {
							Cont = Cont + 1;
							JOptionPane.showMessageDialog(null,
									"Usuario no Existe o credenciales Invalidas " + Cont + " Numero de intento");

							txtpass.setText("");
							txtusu.setText("");
							if (Cont == 3) {
								
								JOptionPane.showMessageDialog(null, "Limite Maximo De Intentos ");
								System.exit(0);
							}
						}
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());

				}
			}
		});
		btnNewButton.setBounds(294, 115, 89, 23);
		contentPane.add(btnNewButton);
	}

	private static String Code(String a) {
		Base64.Encoder encoder = Base64.getEncoder();
		String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8));
		return b;
	}
}
