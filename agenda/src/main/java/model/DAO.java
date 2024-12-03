package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;
import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspendedOrBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
// Modulo de conexao
/** The driver. */
//	Parametro de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagendaweb?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "apto1001";

//	Connection conn = conectar();
//	PreparedStatement pst = conn.prepareStatement(driver);

/**
 * Conectar.
 *
 * @return the connection
 */
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

	/**
	 *  CRUD - Create_RUD.
	 *
	 * @param contato the contato
	 */
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

			String aniversarioSql = contato.getAniversario();
			
			// Verificando se há conteudo na campo aniversario
			if (aniversarioSql.length() > 7 && aniversarioSql.length() <= 10) {
				// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
				aniversarioSql = aniversarioSql.replaceAll("/", "");
				String ano = aniversarioSql.substring(4, 8);
				String mes = aniversarioSql.substring(2, 4);
				String dia = aniversarioSql.substring(0, 2);
				String aniversario = ano + mes + dia;
				
				if (aniversario.length() == 8) {
					pst.setString(4, aniversario);
				} else {
					pst.setString(4, "");
				}
				
			} else {
				pst.setString(4, null);
			}
			// EXECUTAR A QUERY
			pst.executeUpdate();
			
			// ENCERRAR CONEXÃO COM O BANCO !!!EXTREMAMENTE NECESSÁRIO
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD - C_Read_UD.
	 *
	 * @return the array list
	 */
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
				String email;
				String aniversario;

				if (rs.getString(4) == null) {
					email = "";
				} else {
					email = rs.getString(4);
				}
				
				if (rs.getString(5) == null) {
					aniversario = "";
				} else {
					String aniversarioSql = rs.getString(5);
	
					// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
					aniversarioSql = aniversarioSql.replaceAll("-", "");
					String dia = aniversarioSql.substring(6, 8);
					String mes = aniversarioSql.substring(4, 6);
					String ano = aniversarioSql.substring(0, 4);
					aniversario = dia + "/" + mes + "/" + ano;
				}

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

	/**
	 *  CRUD - CR_Update_D.
	 *
	 * @param contato the contato
	 */
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
				
				if (rs.getString(5) != null) {
					// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
					String aniversario = rs.getString(5);
					String aniversarioSql = aniversario.replaceAll("-", "");
					String dia = aniversarioSql.substring(6, 8);
					String mes = aniversarioSql.substring(4, 6);
					String ano = aniversarioSql.substring(0, 4);
					aniversario = dia + "/" + mes + "/" + ano;
					
					contato.setAniversario(aniversario);
				} else {
					contato.setAniversario("");
				}
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Atualizar contato.
	 *
	 * @param contato the contato
	 */
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
			
			if (contato.getAniversario().isEmpty()) {
				pst.setString(4, null);
			} else {	
				// Formatando a data do formato AAAA-MM-DD que vem do banco, para DD/MM/AAAA
				String aniversario = contato.getAniversario();
				String aniversarioFront = aniversario.replaceAll("/", "");
				String dia = aniversarioFront.substring(0, 2);
				String mes = aniversarioFront.substring(2, 4);
				String ano = aniversarioFront.substring(4, 8);
				aniversario = ano + mes + dia;

				pst.setString(4, aniversario);
			}
						
			int updated = pst.executeUpdate();
			
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  CRUD - CRU_Delete.
	 *
	 * @param contato the contato
	 */
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

	/**
	 * Contatos cadastrados.
	 *
	 * @return the string
	 */
	public String contatosCadastrados() {
		String sqlQtd = "SELECT COUNT(1) FROM contatos;";
				
		String cadastros = "";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(sqlQtd);
			ResultSet rs = pst.executeQuery();
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

