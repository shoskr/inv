package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Class.Conexion;
import Class.Pais;

public class Cont_Pais {
	
	private Connection conn = Conexion.getConnectio();
	
	
	public boolean ingresarPais(Pais p) {
		
		try {			
			String sql = "INSERT INTO Pais(idPais, Nombre) VALUES (NEXT VALUE FOR Sec_Pais, ?)";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, p.getNombre());
			int x = pstm.executeUpdate();
			return  x>0 ? true:false;    
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
			 }
	
	}
	
	
	public ArrayList<Pais> listar(){
		try {
			
			String sql = "SELECT idPais, Nombre FROM Pais;";
	
	            PreparedStatement stm = conn.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            ArrayList<Pais> list = new ArrayList<Pais>();
	            while (rs.next()) {
	            	Pais pa = new Pais();
	            	pa.setIdPais(rs.getInt(1));
	            	pa.setNombre(rs.getString(2));
	                list.add(pa);

	            }
	            return list;
			
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
		}
		
		
		
	}
}
