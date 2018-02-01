package Class;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static Connection conn;

	public static Connection getConnectio() {
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(
					"jdbc:sqlserver://CLS1531135779O\\SQLEXPRESS;databaseName=InventarioCinta", "sa", "106721Oscar");

		} catch (Exception ex) {
			conn = null;
		}
		return conn;

	}



}
