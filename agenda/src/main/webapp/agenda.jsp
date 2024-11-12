<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");

	/* for (int i = 0; i < lista.size(); i++) {
		out.println(lista.get(i).getId());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getFone());
		out.println(lista.get(i).getEmail());
		out.println(lista.get(i).getAniversario());
	} */
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Agenda Web</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">

</head>
<body>
	<h1 id="cor">Agenda de Contatos</h1>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Aniversário</th>
			</tr>
		</thead>
		
		<tbody>
			
			<%for (int i = 0; i < lista.size(); i++) { %>
				
				<tr>
					<td> <%=lista.get(i).getId() %> </td>
					<td> <%=lista.get(i).getNome() %> </td>
					<td> <%=lista.get(i).getFone() %> </td>
					<td> <%=lista.get(i).getEmail() %> </td>
					<td> <%=lista.get(i).getAniversario() %> </td>
				</tr>
				
			<%}  %>
			
		</tbody>
	</table>
	
	<a href="novo.html">Novo contato</a>
</body>
</html>