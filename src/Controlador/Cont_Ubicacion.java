package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import Class.Conexion;
import Class.Ubicacion;

public class Cont_Ubicacion {

private Connection conn = Conexion.getConnectio();
	
	
	public boolean ingresarUbicacion(Ubicacion ub) {
		
		try {			
			String sql = "INSERT INTO Ubicacion VALUES (NEXT VALUE FOR Sec_Ubicacion, ?);";
			PreparedStatement pstm = conn.prepareCall(sql);
			String sql2 = "INSERT INTO Destino VALUES (NEXT VALUE FOR Sec_Destino, ?);";
			PreparedStatement pstm2 = conn.prepareCall(sql2);
			pstm.setString(1, ub.getLugar());
			pstm2.setString(1, ub.getLugar());
			int x = pstm.executeUpdate();
			int y = pstm2.executeUpdate();
			if(x > 0 && y >0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
			 }
	
	}
	
	
	public ArrayList<Object[]> listar(){
		try {
			
			String sql = "SELECT * FROM Ubicacion ;";
	
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
	
	public ArrayList<Ubicacion> listUBI(){
		try {
			
			String sql = "SELECT * FROM Ubicacion ;";
	
	            PreparedStatement stm = conn.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	    
	           
	            ArrayList<Ubicacion> lista = new ArrayList<Ubicacion>();
	            while (rs.next()) {
	            	Ubicacion ubi = new Ubicacion();
	            	ubi.setIdubicacion(rs.getInt(1));
	            	ubi.setLugar(rs.getString(2));
	                
	                lista.add(ubi);

	            }
	            return lista;
			
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());
		}
		
		
	}
	
}
