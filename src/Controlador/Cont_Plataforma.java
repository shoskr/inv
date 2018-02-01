package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Class.Conexion;
import Class.*;

public class Cont_Plataforma {

private Connection conn = Conexion.getConnectio();
	
	
	public boolean ingresarPlataforma(Plataforma p) {
		
		try {			
			String sql = "INSERT INTO Plataforma(idPlataforma, Nombre) VALUES (NEXT VALUE FOR Sec_Plataforma, ?);";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, p.getNomPlataforma());
			int x = pstm.executeUpdate();
			return  x>0 ? true:false;    
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
			 }
	
	}
	
	
	public ArrayList<Plataforma> listar(){
		try {
			
			String sql = "SELECT * FROM Plataforma ;";
	
	            PreparedStatement stm = conn.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            ArrayList<Plataforma> list = new ArrayList<Plataforma>();
	            while (rs.next()) {
	            	Plataforma pl = new Plataforma();
	            	pl.setIdPlataforma(rs.getInt(1));
	            	pl.setNomPlataforma(rs.getString(2));
	                list.add(pl);

	            }
	            return list;
			
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
		}
		
		
		
	}
	
}
