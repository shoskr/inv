package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import Class.*;
import Class.Conexion;

public class Cont_Inventario {
	private Connection conn = Conexion.getConnectio();

	public boolean ingresarInventario(Inventario inv) {

		try {
			String sql = "insert into Inventario values" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?, ? );";

			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, inv.getIdInventario());
			pstm.setInt(2, inv.getCinta_idCinta());
			pstm.setString(3, inv.getContenido());
			pstm.setString(4, inv.getRetencion());
			pstm.setInt(5, inv.getPlataforma());
			pstm.setString(6, inv.getFecha_Plataforma());
			pstm.setString(7, inv.getFecha_Exp());
			pstm.setString(8, inv.getFecha_ultim());
			pstm.setInt(9, inv.getPais_idPais());
			pstm.setInt(10, inv.getUbicacion_Bodega());
			pstm.setInt(11, inv.getDestino_Actual());
			pstm.setString(12, inv.getValija());
			pstm.setString(13, inv.getContinuacion());
			pstm.setString(14, inv.getObservaciones());
			pstm.setString(15, inv.getSolicitado());
			pstm.setString(16, inv.getResponsable());
			pstm.setInt(17, inv.getServidor());
			pstm.setString(18, inv.getLugar_Requerido());
			pstm.setString(19, inv.getMes_anio());
			int x = pstm.executeUpdate();
			return x > 0 ? true : false;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public ArrayList<Object[]> listar() {
		try {

			String sql = "select\r\n "
					+ "    Inventario.idInventario,\r\n" 
					+ "	   Cinta.Modelo,\r\n" 
					+ "	   Cinta.Marca,\r\n"
					+ "	   Cinta.Categoria,\r\n" 
					+ "	   Inventario.Contenido,\r\n"
					+ "	   Inventario.retencion,\r\n" 
					+ "	   Plataforma.Nombre,\r\n"
					+ "	   Inventario.Fecha_Plataforma,\r\n" 
					+ "	   Inventario.Fecha_Exp,\r\n"
					+ "	   Inventario.Fecha_ultim,\r\n" 
					+ "	   Pais.Nombre,\r\n"
					+ "	   Ubicacion.Nombre_Ubicacion,\r\n" 
					+ "	   Destino.Nombre_Destino,\r\n"
					+ "	   Inventario.Valija,\r\n" 
					+ "	   Inventario.Continuacion,\r\n"
					+ "	   Inventario.Observaciones,\r\n" 
					+ "	   Inventario.Solicitado,\r\n"
					+ "	   Inventario.Responsable,\r\n" 
					+ "	   Servidor.Nombre,\r\n"
					+ "	   Inventario.Lugar_Requerido,\r\n" 
					+ "	   Inventario.mes_anio\r\n" 
					+ "from inventario \r\n"
					+ "inner join Cinta\r\n" 
					+ "     on Cinta.idCinta = Inventario.Cinta_idCinta\r\n"
					+ "inner join Plataforma\r\n"
					+ "		on Plataforma.idPlataforma = Inventario.Plataforma_idPlataforma\r\n" 
					+ "inner join Pais\r\n"
					+ "		on Pais.idPais = Inventario.Pais_idPais\r\n" 
					+ "inner join Ubicacion\r\n"
					+ "		on Ubicacion.idUbicacion = Inventario.Ubicacion_Bodega\r\n" 
					+ "inner join Destino\r\n"
					+ "		on Destino.idDestino = Inventario.Destino_Actual\r\n" 
					+ "inner join Servidor\r\n"
					+ "		on Servidor.idServidor = Inventario.Servidor_idServidor";

			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			ArrayList<Object[]> lista = new ArrayList<Object[]>();
			while (rs.next()) {
				Object[] raws = new Object[rsm.getColumnCount()];
				for (int i = 0; i < raws.length; i++) {
					raws[i] = rs.getObject(i + 1);
				}
				lista.add(raws);

			}
			return lista;

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public boolean ActualizarInventario(Inventario inv, String cod) {

		try {
			String sql = "update Inventario\r\n" + 
					"set Cinta_idCinta = ?,\r\n" + 
					"    Contenido = ?,\r\n" + 
					"	retencion = ?,\r\n" + 
					"	Plataforma_idPlataforma = ?,\r\n" + 
					"	Fecha_Plataforma = ?,\r\n" + 
					"	Fecha_Exp = ?,\r\n" + 
					"	Fecha_ultim = ?,\r\n" + 
					"	Pais_idPais = ?,\r\n" + 
					"	Ubicacion_Bodega = ?,\r\n" + 
					"	Destino_Actual = ?,\r\n" + 
					"	Valija = ?,\r\n" + 
					"	Continuacion = ?,\r\n" + 
					"	Observaciones = ?,\r\n" + 
					"	Solicitado = ?,\r\n" + 
					"	Responsable = ?,\r\n" + 
					"	Servidor_idServidor = ?,\r\n" + 
					"	Lugar_Requerido = ?,\r\n" + 
					"	mes_anio = ?\r\n" + 
					"	where idInventario =? ";

			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setInt(1, inv.getCinta_idCinta());
			pstm.setString(2, inv.getContenido());
			pstm.setString(3, inv.getRetencion());
			pstm.setInt(4, inv.getPlataforma());
			pstm.setString(5, inv.getFecha_Plataforma());
			pstm.setString(6, inv.getFecha_Exp());
			pstm.setString(7, inv.getFecha_ultim());
			pstm.setInt(8, inv.getPais_idPais());
			pstm.setInt(9, inv.getUbicacion_Bodega());
			pstm.setInt(10, inv.getDestino_Actual());
			pstm.setString(11, inv.getValija());
			pstm.setString(12, inv.getContinuacion());
			pstm.setString(13, inv.getObservaciones());
			pstm.setString(14, inv.getSolicitado());
			pstm.setString(15, inv.getResponsable());
			pstm.setInt(16, inv.getServidor());
			pstm.setString(17, inv.getLugar_Requerido());
			pstm.setString(18, inv.getMes_anio());
			pstm.setString(19, cod);
			int x = pstm.executeUpdate();
			return x > 0 ? true : false;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}
