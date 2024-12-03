<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
	@ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Agenda Web - Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.7.0/css/all.css">
</head>
      <body>
            <header>
                  <h1>AGENDA DE CONTATOS</h1>
            </header>
      <main>
            <div class="flex-button">
                  <div class="button">
                        <a class="btn-grn"  href="novo.html">
                              <i class="fa-solid fa-user-plus"></i> 
                              Novo Contato
                        </a>
                        <a class="btn-gry" href="report">
                              <i class="fa-solid fa-file-arrow-down"></i> 
                              Gerar Relatório
                        </a>
                  </div>
                  
                  <span>
              			<i class="fa-solid fa-id-card-clip white"></i>
              			<input type="text" name="id" id="desibled" class="ipt-id" readonly value="<%out.print(request.getAttribute("cadastros"));%>">
                        	contatos cadastrados                              	
                  </span>                  
            </div>
            
            <table>
                  <thead>
                        <tr id="tr-head">
                              <th>
                                    <i class="fa-solid fa-id-card-clip"></i> ID
                              </th>
                              <th>
                                    <i class="fa-solid fa-user"></i> 
                                    Nome
                              </th>
                              <th>
                                    <i class="fa-solid fa-phone"></i> 
                                    Telefone
                              </th>
                              <th>
                                    <i class="fa-solid fa-envelope"></i> 
                                    E-mail
                              </th>
                              <th>
                                    <i class="fa-solid fa-cake-candles"></i> 
                                    Aniversário
                              </th>
                              <th>
                                    <i class="fa-solid fa-screwdriver-wrench"></i> 
                                    Opções
                              </th>
                        </tr>
                  </thead>
                  
                  <tbody>	
                        <%
				for (int i = 0; i < lista.size(); i++) {
				%>			
                        <tr class="content-agenda">
                              <td> <%=lista.get(i).getId() %> </td>
                              <td> <%=lista.get(i).getNome() %> </td>
                              <td> <%=lista.get(i).getFone() %> </td>
                              <td> <%=lista.get(i).getEmail() %> </td>
                              <td> <%=lista.get(i).getAniversario() %> </td>
                              <td> <a class="btn-org"  href="select_contato?id=<%=lista.get(i).getId()%>">
                                    <i class="fa-solid fa-user-pen"></i>
                                    Editar
                              </a></td>
                              <td> <a class="btn-red" href="javascript: confirmar(<%=lista.get(i).getId()%>)">
                                    <i class="fa-solid fa-user-xmark"></i> 
                                    Excluir
                              </a></td>
                        </tr>	
                        <%
				}
				%>		
                  </tbody>
            </table>
            
            <div class="button">
           		<a class="btn-blue"  href="index.html">
                      <i class="fa-solid fa-house-chimney"></i>
                      Voltar a Tela inicial
                </a>
            </div>
      </main>
      <footer>
      </footer>

	<script type="text/javascript" src="script/confirm.js"></script>
      </body>
</html>
	
