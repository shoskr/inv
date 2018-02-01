package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


public class MenuAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
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
	public MenuAdmin() {
		
		
		setBackground(Color.WHITE);
		setTitle("Menu  Administrador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuAdmin.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAgregarUsuario = new JButton("Agregar Usuario");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AgregarUsuario AU = new AgregarUsuario();
				AU.setVisible(true);
				
				
				
			}
		});
		btnAgregarUsuario.setBounds(59, 29, 157, 23);
		contentPane.add(btnAgregarUsuario);

		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminarUsuario Eli = new EliminarUsuario();
				Eli.setVisible(true);
				
			}
		});
		btnEliminarUsuario.setBounds(59, 63, 157, 23);
		contentPane.add(btnEliminarUsuario);

		JButton btnNewButton = new JButton("Modificar Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario mod = new ModificarUsuario();
				mod.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setBounds(59, 97, 157, 23);
		contentPane.add(btnNewButton);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnSalir.setBounds(99, 240, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnNewButton_1 = new JButton("Eliminar De Inventario");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EliminarInventario elim = new EliminarInventario();
				elim.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(59, 131, 157, 28);
		contentPane.add(btnNewButton_1);
	}
}
