<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Contact"%>
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
		<form
			action="storeUpdateContact?id=<%out.print(request.getParameter("id"));%>"
			method="POST">
			<%
			Contact contact = (Contact) request.getAttribute("contact");

			if (contact != null) {
				out.println("<input type='text' name='name' value='" + contact.getName() + "' placeholder='Nome'>");
				out.println("<input type='tel' name='phone' value='" + contact.getPhone() + "' placeholder='Telefone'>");
				out.println("<input type='email' name='email' value='" + contact.getEmail() + "' placeholder='Telefone'>");
			}
			%>
			<button type="submit">Alterar</button>
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