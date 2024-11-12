package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/main", "/create"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
  
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
//		System.out.println(action);
		
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/create")) {
			criarContato(request, response);
		} else {
			response.sendRedirect("/index.html");
		}
		
		
		/** TESTE CONEXAO */
//		dao.testeConexao();
	}
	
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("agenda.jsp");
		// CRIANDO OBJ QUE RECEBERÁ OS DADOS JAVABEANS
		ArrayList<JavaBeans> lista = dao.lerContatos();
		
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getId());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//			System.out.println(lista.get(i).getEmail());
//			System.out.println(lista.get(i).getAniversario());
//		}
		
		// Encaminhar a lista do documento para o .Jsp
		request.setAttribute("contatos", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response);
	}
	
	// Criar contato
	protected void criarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** TESTE DE RECEBIMENTO */
//		System.out.println(request.getParameter("name"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("mail"));
//		System.out.println(request.getParameter("birth"));
		
		// SETANDO VARIÁVEIS JavaBeans
		contato.setNome(request.getParameter("name"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("mail"));
		contato.setAniversario(request.getParameter("birth"));
		
		// INVOCANDO O METODO criarContato passando o objeto Contato
		dao.criarContato(contato);
		
		// REDIRECIONANDO PARA A PÁGINA agenda.jsp
		response.sendRedirect("main");
	}

}
