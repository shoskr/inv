package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Class.Cinta;
import Class.Conexion;


public class Cont_Cinta {

	
private Connection conn = Conexion.getConnectio();
	
	
	public boolean IngresarCinta(Cinta c) {
		
		try {			
			String sql = "INSERT INTO Cinta (idCinta, Modelo, Marca, Categoria) VALUES (NEXT VALUE FOR Sec_cinta, ?,?,?);";
			PreparedStatement pstm = conn.prepareCall(sql);
			pstm.setString(1, c.getModelo() );
			pstm.setString(2, c.getMarca());
			pstm.setString(3, c.getCategoria());
			int x = pstm.executeUpdate();
			return  x>0 ? true:false;    
		} catch (Exception e) {
			 throw new IllegalArgumentException(e.getMessage());	
			 }
	
	}
	
	
	public ArrayList<Cinta> listar(){
		try {
			
			String sql = "SELECT * FROM Cinta ;";
	
	            PreparedStatement stm = conn.prepareStatement(sql);
	            ResultSet rs = stm.executeQuery();
	            ArrayList<Cinta> list = new ArrayList<Cinta>();
	            while (rs.next()) {
	            	Cinta c = new Cinta();
	            	c.setIdCinta(rs.getInt(1));
	            	c.setModelo(rs.getString(2));
	            	c.setMarca(rs.getString(3));
	            	c.setCategoria(rs.getString(4));
	                list.add(c);

	            }
	            return list;
			
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());	
		}	
		
	}
}
