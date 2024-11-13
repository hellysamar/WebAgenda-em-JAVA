<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Agenda Web - Editar contato</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
<style>
	#desibled {
		border: 1px solid red;
	}
</style>
</head>
<body>
	<h1>Editar Contato</h1>
	
	<form name="formContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="desibled" readonly value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="name" value="<%out.print(request.getAttribute("name"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="mail" value="<%out.print(request.getAttribute("mail"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="birth" value="<%out.print(request.getAttribute("birth"));%>"></td>
			</tr>
		</table>
		
		<input type="button" value="Salvar Contato" onclick="validar()">
	</form>
	
	<script type="text/javascript" src="script/validador.js"></script>
</body>
</html>