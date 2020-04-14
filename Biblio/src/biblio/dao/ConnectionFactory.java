package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnectionAutocommit() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "biblio";
		String passwd = "biblio";
		String driverdb = "oracle.jdbc.driver.OracleDriver";
		Connection conn = null;

		try {
			Class.forName(driverdb);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("Problème de driver" + ex.getMessage());
		}

		return conn;
	}

	public static Connection getConnectionSansAutocommit() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "biblio";
		String passwd = "biblio";
		String driverdb = "oracle.jdbc.driver.OracleDriver";
		Connection conn = null;

		try {
			Class.forName(driverdb);
		} catch (ClassNotFoundException ex) {
			System.out.println("Problème de driver" + ex.getMessage());
		}

		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			System.out.println("Problème de connection à la BD" + e.getMessage());
		}

		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("PB JDBC" + e.getMessage());
			e.printStackTrace();
		}

		return conn;

	}

}
