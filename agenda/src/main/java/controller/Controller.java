package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = {"/main", "/create", "/select_contato", "/update", "/excluir_contato", "/report"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();
  
    /**
     * Instantiates a new controller.
     */
    public Controller() {
        super();
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
				
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/create")) {
			criarContato(request, response);
		} else if (action.equals("/select_contato")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/excluir_contato")) {
			excluirContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("/index.html");
		}
	}
	
	/**
	 *  LISTAR CONTATO.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CRIANDO OBJ QUE RECEBERÁ OS DADOS JAVABEANS
		ArrayList<JavaBeans> lista = dao.lerContatos();
		
		// Encaminhar a lista do documento para o .Jsp
		request.setAttribute("contatos", lista);
		
		// VERIFICA QUANTIDADE DE CONTATOS CADASTRADOS
		String cadastros = dao.contatosCadastrados();
		request.setAttribute("cadastros", cadastros);
		
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		
		rd.forward(request, response);
	}
	
	/**
	 *  CRIAR CONTATO.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void criarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	
	/**
	 *  EDITAR CONTATO.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Setando o código no JavaBeans para ser feita alteração no banco, atraves do id
		contato.setId(request.getParameter("id"));
		
		// Executando método para seleção do contato
		dao.lerContato(contato);
		
		// Setar campos com dados recuperados do banco
		request.setAttribute("id", contato.getId());
		request.setAttribute("name", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("mail", contato.getEmail());
		request.setAttribute("birth", contato.getAniversario());
//		
//		if (contato.getAniversario().isEmpty() || contato.getAniversario() == null) {
//			request.setAttribute("birth", "");
//		} else {
//			request.setAttribute("birth", contato.getAniversario());
//		}
//		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("name"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("mail"));
		contato.setAniversario(request.getParameter("birth"));
		
		dao.atualizarContato(contato);
		
		response.sendRedirect("main");		
	}
	
	/**
	 *  EXCLUIR CONTATO.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void excluirContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setId(request.getParameter("id"));		
		dao.excluirContato(contato);		
		response.sendRedirect("main");
	}
	
	/**
	 *  GERAR RELATORIO EM PDF COM A LIB itextPdf.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		
		try {
			// Tipo do conteudo
			response.setContentType("apllication/pdf");
			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// Criando documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrindo documento
			documento.open();
			documento.add(new Paragraph("LISTA DE CONTATOS:"));
			documento.add(new Paragraph(""));
			
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(4);
			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Aniversário"));
			// Adicionando colunas a tabela
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Populando a tabela com os dados do banco
			ArrayList<JavaBeans> lista = dao.lerContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
				tabela.addCell(lista.get(i).getAniversario());				
			}			
			
			// Adicionando tabela ao documento PDF
			documento.add(tabela);
			
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}

