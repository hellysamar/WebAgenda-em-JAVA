package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
// Modulo de conexao
//	Parametro de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagendaweb?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "apto1001";
	
//	Connection conn = conectar();
//	PreparedStatement pst = conn.prepareStatement(driver);
	
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
	/** TESTE DE CONEXAO */
//	public void testeConexao() {
//		try {
//			Connection conn = conectar();
//			System.out.println(conn);
//			conn.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	/** CRUD - Create_RUD */
	public void criarContato(JavaBeans contato) {
		String sqlCreate = "INSERT INTO contatos (nome, fone, email, aniversario) VALUES (?, ?, ?, ?)";
		
		try {
			// ABRINDO CONEXÃO
			Connection conn = conectar();
			// PREPARANDO A QUERY PARA EXECUÇÃO NO BANCO
			PreparedStatement pst = conn.prepareStatement(sqlCreate);
			// SUBSTITUINDO AS ? PELOS PARAMETROS
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getAniversario()); // nesse ponto já foi feito ajuste na data, através da classe JB 
			// EXECUTAR A QUERY
			pst.executeUpdate();
			// ENCERRAR CONEXÃO COM O BANCO !!!EXTREMAMENTE NECESSÁRIO
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/** CRUD - C_Read_UD */
	public ArrayList<JavaBeans> lerContatos() {
		// Criando obj para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		
		String sqlRead = "SELECT * FROM contatos ORDER BY nome";
		
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlRead);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				String aniversarioSql = rs.getString(5);
				
				// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
				aniversarioSql = aniversarioSql.replaceAll("-", "");
				String dia = aniversarioSql.substring(6, 8);
				String mes = aniversarioSql.substring(4, 6);
				String ano = aniversarioSql.substring(0, 4);
				String aniversario = dia + "/" + mes + "/" + ano;
				
				// POPULANDO O ARRAYLIST
				contatos.add(new JavaBeans(id, nome, fone, email, aniversario));
			}
			conn.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD - CR_Update_D */
	
	/** CRUD - CRU_Delete */

}
