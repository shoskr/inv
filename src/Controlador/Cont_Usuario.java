package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Class.Conexion;
import Class.*;

public class Cont_Usuario {

	private Connection conn = Conexion.getConnectio();

	public boolean ingresarUsuario(Usuario u) {

		try {
			String sql = "INSERT INTO Usuario(idUsuario, Nombre, clave, TipoUsuario_idTipoUsuario) VALUES (NEXT VALUE FOR Sec_Usuario, ?,?,?);";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, u.getNombre());
			pstm.setString(2, u.getClave());
			pstm.setInt(3, u.getTipo());
			int x = pstm.executeUpdate();
			return x > 0 ? true : false;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public ArrayList<Usuario> listar() {
		try {

			String sql = "SELECT * FROM Usuario;";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			ArrayList<Usuario> list = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setIdUsuario(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setClave(rs.getString(3));
				u.setTipo(rs.getInt(4));
				list.add(u);

			}
			return list;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public int validaUsuario(Usuario u) {
		try {
			int tipo = 0;
			String sql = "select TOP 1 TipoUsuario_idTipoUsuario from Usuario where Nombre = ? and  clave = ?;";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, u.getNombre());
			pstm.setString(2, u.getClave());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				tipo = rs.getInt(1);
			}
			return tipo;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public boolean EliminaraUsuario(String  nomb) {
		try {
			String sql = "delete from Usuario where Nombre = ? ;";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, nomb);
			int x = pstm.executeUpdate();
			return x > 0 ? true : false;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public ArrayList<Object> listarTipo() {
		try {

			String sql = "SELECT NombreUsuario FROM TipoUsuario;";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			while (rs.next()) {

				list.add(rs.getString(1));

			}
			return list;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public boolean ModificarUsuario(Usuario u) {
		try {
			String sql = "update Usuario set clave = ?,TipoUsuario_idTipoUsuario = ? where idUsuario = ?";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, u.getClave());
			pstm.setInt(2, u.getTipo());
			pstm.setInt(3, u.getIdUsuario());
			int x = pstm.executeUpdate();
			return x > 0 ? true : false;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
