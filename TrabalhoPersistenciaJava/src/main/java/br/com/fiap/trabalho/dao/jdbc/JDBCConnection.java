package br.com.fiap.trabalho.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JDBCConnection {
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			EntityManagerFactory factory = Persistence
					.createEntityManagerFactory("persistenceUnit");
			String URL = "jdbc:derby:simpleDB;create=true;user=TrabalhoPersistenciaJava;password=TrabalhoPersistenciaJava";
			String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return conn;
	}
}
