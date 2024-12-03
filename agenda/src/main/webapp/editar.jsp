<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Agenda Web - Editar Contato</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.7.0/css/all.css">
</head>
      <body>
            <header>
                  <h1>AGENDA DE CONTATOS</h1>
            </header>
            <main>      
                  <form name="formContato" action="update">
                        <table>
                              <thead>
                                    <tr id="tr-head">
                                          <th>
                                                <i class="fa-solid fa-user"></i> 
                                                Nome *
                                          </th>
                                          <th>
                                                <i class="fa-solid fa-phone"></i> 
                                                Telefone *
                                          </th>
                                          <th>
                                                <i class="fa-solid fa-envelope"></i> 
                                                E-mail
                                          </th>
                                          <th>
                                                <i class="fa-solid fa-cake-candles"></i> 
                                                Aniversário
                                          </th>
                                    </tr>
                              </thead>
                              
                              <tbody>				
                                    <tr>
                                        <td><input type="text" name="name" placeholder="Nome e sobre nome" value="<%out.print(request.getAttribute("name"));%>" id="ipt-nome"></td>
                                        
                                        <td><input type="tel" name="fone" pattern="[0-9]+$" maxlength="15" placeholder="(99) 99999-9999" value="<%out.print(request.getAttribute("fone"));%>" id="ipt-fone"></td>
                                          
                                        <td><input type="email" name="mail" placeholder="text@mail.com" value="<%out.print(request.getAttribute("mail"));%>" id="ipt-mail"></td>
                                          
                                        <td><input type="text" name="birth" maxlength="10" placeholder="DD/MM/AAAA" value="<%out.print(request.getAttribute("birth"));%>" id="ipt-date" onchange=^validarData(this)></td>
                                    </tr>
                              </tbody>
                        </table>
                        
                        <div class="flex-button">
                              <div class="button btn-grn">
                                    <i class="fa-solid fa-user-check"></i> 
                                    <input type="button" value="Salvar Contato" onclick="validar()">
                              </div>
                              <div class="flex-button">
                                    <span>
                                          <i class="fa-solid fa-id-card-clip white"></i>
                                          Contato cadastrado no ID 
                                          <input type="text" name="id" id="desibled" class="ipt-id" readonly value="<%out.print(request.getAttribute("id"));%>">
                                    </span>
                              </div>
                        </div>
                  </form>     
                  <div class="button">
	               		<a class="btn-blue" onclick="voltar()">
	            				<i class="fa-regular fa-square-caret-left"></i>
	            				Voltar a agenda
	               		</a>
                  </div>            
            </main>
            <footer>
            </footer>

	      <script type="text/javascript" src="script/confirm.js"></script>
	      <script type="text/javascript" src="script/validador.js"></script>
      </body>
</html>