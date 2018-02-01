package Vistas;

import java.awt.EventQueue;

import java.awt.Toolkit;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import java.awt.Color;

public class MenuP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MenuP frame = new MenuP();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public MenuP() {
		setTitle("Menu Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 299);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnListarTodo = new JButton("Listar Todo");
		btnListarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Listar_Inventario LI = new Listar_Inventario();
				LI.setVisible(true);
				
			}
		});
		btnListarTodo.setBounds(254, 11, 165, 23);
		contentPane.add(btnListarTodo);
		
		JButton btnBuscarubi = new JButton("Modificar Inventario");
		btnBuscarubi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarInventario mod = new ModificarInventario();
				mod.setVisible(true);
				
			}
		});
		btnBuscarubi.setBounds(254, 46, 165, 23);
		contentPane.add(btnBuscarubi);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnSalir.setBounds(286, 184, 133, 23);
		contentPane.add(btnSalir);
		
		JButton btnAgrgP = new JButton("Agregar Pais");
		btnAgrgP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarPais pais = new AgregarPais();
				pais.setVisible(true);
				
				
			
				
			}
		});
		btnAgrgP.setBounds(10, 11, 165, 23);
		contentPane.add(btnAgrgP);
		
		JButton btnAgregarCinta = new JButton("Agregar Cinta");
		btnAgregarCinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCinta cin = new AgregarCinta();
				cin.setVisible(true);
				
				
			}
		});
		btnAgregarCinta.setBounds(10, 45, 165, 23);
		contentPane.add(btnAgregarCinta);
		
		JButton btnAgregarPlataforma = new JButton("Agregar Plataforma");
		btnAgregarPlataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarPlataforma plat = new AgregarPlataforma();
				plat.setVisible(true);
				
				
			}
		});
		btnAgregarPlataforma.setBounds(10, 79, 165, 23);
		contentPane.add(btnAgregarPlataforma);
		
		JButton btnAgregarServidor = new JButton("Agregar  Servidor");
		btnAgregarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarServidor serv = new AgregarServidor();
				serv.setVisible(true);
				
				
			}
		});
		btnAgregarServidor.setBounds(10, 116, 165, 23);
		contentPane.add(btnAgregarServidor);
		
		JButton btnAgregarUbicacion = new JButton("Agregar Ubicacion");
		btnAgregarUbicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AgregarUbicacion abui = new AgregarUbicacion();
			abui.setVisible(true);
			
				
			}
		});
		btnAgregarUbicacion.setBounds(10, 150, 165, 23);
		contentPane.add(btnAgregarUbicacion);
		
		JButton btnAgregarInventario = new JButton("Agregar Inventario");
		btnAgregarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AgregarInventario inve = new AgregarInventario();
			inve.setVisible(true);
			
			
			}
		});
		btnAgregarInventario.setBounds(10, 184, 165, 23);
		contentPane.add(btnAgregarInventario);
		
		JButton btnModificarVarios = new JButton("Modificar Varios");
		btnModificarVarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarVarios mvari = new ModificarVarios();
				mvari.setVisible(true);
				
			}
		});
		btnModificarVarios.setBounds(254, 79, 165, 23);
		contentPane.add(btnModificarVarios);
	}
}
