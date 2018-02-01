package Vistas;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.*;
import Controlador.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EliminarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cont_Usuario CU = new Cont_Usuario();
	private static EliminarUsuario frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new EliminarUsuario();
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
	public EliminarUsuario() {
		setTitle("Eliminar Usuarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuP.class.getResource("/img/scot.png")));
		setBounds(100, 100, 292, 302);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario  :");
		lblUsuario.setBounds(16, 49, 72, 14);
		contentPane.add(lblUsuario);

		JComboBox<String> cboUsu = new JComboBox<String>();
		cboUsu.setBounds(98, 46, 120, 20);
		contentPane.add(cboUsu);

		ArrayList<Usuario> lista = new ArrayList<>();
		lista = CU.listar();

		for (Usuario usuario : lista) {

			cboUsu.addItem(usuario.getNombre() + "");
		}

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String Nomb = cboUsu.getSelectedItem().toString();

				ArrayList<Usuario> list = new ArrayList<>();
				list = CU.listar();
				for (int i = 0; i < list.size(); i++) {
					if (Nomb.equals("admin")) {
						JOptionPane.showMessageDialog(null, "No se Puede Eliminar Usuario");
						return;
					}
				}

				boolean valida = CU.EliminaraUsuario(Nomb);
				

				if (valida) {
					JOptionPane.showMessageDialog(null, "Usuario Eliminado");
					
						cboUsu.removeAllItems();
					
					
					ArrayList<Usuario> lista = new ArrayList<>();
					lista = CU.listar();
						cboUsu.addItem("Seleccionar");
					for (Usuario usuario : lista) {

						cboUsu.addItem(usuario.getNombre() + "");
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "No se Elimino Usuario");
				}
				
				
			}
		});
		btnEliminar.setBounds(95, 127, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			dispose();
			
			}
		});
		btnNewButton.setBounds(177, 230, 89, 23);
		contentPane.add(btnNewButton);
	}
}
