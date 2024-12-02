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

			// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
			String aniversarioSql = contato.getAniversario();
			aniversarioSql = aniversarioSql.replaceAll("/", "");
			String ano = aniversarioSql.substring(4, 8);
			String mes = aniversarioSql.substring(2, 4);
			String dia = aniversarioSql.substring(0, 2);
			String aniversario = ano + mes + dia;

			pst.setString(4, aniversario);

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
	// SELECIONAR O CONTATO
	public void lerContato(JavaBeans contato) {
		String sqlReadUpdate = "SELECT * FROM contatos WHERE id = ?";

		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlReadUpdate);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
				// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
				String aniversario = rs.getString(5);
				String aniversarioSql = aniversario.replaceAll("-", "");
				String dia = aniversarioSql.substring(6, 8);
				String mes = aniversarioSql.substring(4, 6);
				String ano = aniversarioSql.substring(0, 4);
				aniversario = dia + "/" + mes + "/" + ano;
				
				contato.setAniversario(aniversario);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// ATUALIZAR O CONTATO
	public void atualizarContato(JavaBeans contato) {
		String sqlUpdate = "UPDATE contatos SET nome = ?, fone = ?, email = ?, aniversario = ? WHERE id = ?";

		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlUpdate);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(5, contato.getId());
			
			// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
			String aniversario = contato.getAniversario();
			String aniversarioFront = aniversario.replaceAll("/", "");
			String dia = aniversarioFront.substring(0, 2);
			String mes = aniversarioFront.substring(2, 4);
			String ano = aniversarioFront.substring(4, 8);
			aniversario = ano + mes + dia;
			
			pst.setString(4, aniversario);
						
			int updated = pst.executeUpdate();

			if (updated > 0) {
				// Apresenta mensagem de atualização realizada com sucesso
			}
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD - CRU_Delete */
	public void excluirContato(JavaBeans contato) {
		String sqlDelete = "DELETE FROM contatos WHERE id = ?";

		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, contato.getId());
			pst.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String contatosCadastrados() {
		String sqlQtd = "SELECT COUNT(1) FROM contatos;";
				
		String cadastros = "";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlQtd);
			ResultSet rs = pst.executeQuery();
			System.out.println("DAO verifica o valor armazenado no ResultSet antes do if " + rs);
			if (rs.next()) {
				cadastros = rs.getString(1);				
			}
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return cadastros;
	}
}

