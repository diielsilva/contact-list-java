<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="assets/style.css">
<title>Lista de Contatos</title>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="index">Home</a></li>
				<li><a href="addContact">Adicionar Contato</a></li>
				<li><a href="myContacts">Meus Contatos</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<form action="storeContact" method="POST">
			<input type="text" name="name" placeholder="Nome"> <input
				type="tel" name="phone" placeholder="Telefone"> <input
				type="email" name="email" placeholder="E-mail">
			<button type="submit">Adicionar</button>
		</form>
		<%
		String message = request.getParameter("message");
		if (message != null && message != "") {
			out.println("<p> " + message + " </p>");
		}
		%>
	</main>

</body>
</html>