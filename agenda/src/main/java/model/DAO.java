package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
// Modulo de conexao
//	Parametro de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagendaweb?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "apto1001";
	
//	Metodo de Conexao
	private Connection conectar() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
//		Teste de conexao
	public void testeConexao() {
		try {
			Connection conn = conectar();
			System.out.println(conn);
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
