package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Class.Conexion;
import Class.*;

public class Cont_Servidor {

private Connection conn = Conexion.getConnectio();
	
	
	public boolean ingresarServidor(Servidor s) {
		
		try {			
			String sql = "INSERT INTO Servidor(idServidor, Nombre) VALUES (NEXT VALUE FOR Sec_Servidor, ?);";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, s.getNombre());
			int x = pstm.executeUpdate();
			return  x>0 ? true:false;    
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
			 }
	
	}
	
	
	public ArrayList<Servidor> listar(){
		try {
			
			String sql = "SELECT * FROM Servidor  ;";
	
	            PreparedStatement stm = conn.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            ArrayList<Servidor> list = new ArrayList<Servidor>();
	            while (rs.next()) {
	            	Servidor s = new Servidor();
	            	s.setIdServidor(rs.getInt(1));
	            	s.setNombre(rs.getString(2));
	                list.add(s);

	            }
	            return list;
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());	
		}
		
		
		
	}
}
