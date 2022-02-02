<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
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
		<%
		String message = request.getParameter("message");
		ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");

		if (message != null && message != "") {
			out.println("<p> " + message + " </p>");
		}

		if (contacts == null || contacts.size() == 0) {
			out.println("<p> Nenhum contato foi encontrado <p>");
		} else {
			out.println("<table>");
			out.println("<thead>");
			out.println("<tr>");
			out.println("<th> ID </th>");
			out.println("<th> NOME </th>");
			out.println("<th> TELEFONE </th>");
			out.println("<th> E-MAIL </th>");
			out.println("<th> ALTERAR </th>");
			out.println("<th> REMOVER </th>");
			out.println("</tr>");
			for (int index = 0; index < contacts.size(); index++) {
				out.println("<tr>");
				out.println("<td> " + contacts.get(index).getId().toString() + " </td>");
				out.println("<td> " + contacts.get(index).getName() + " </td>");
				out.println("<td> " + contacts.get(index).getPhone() + " </td>");
				out.println("<td> " + contacts.get(index).getEmail() + " </td>");
				out.println(
				"<td> <a href='updateContact?id=" + contacts.get(index).getId().toString() + "'> Alterar </a> </td>");
				out.println("<td> <form action='deleteContact?id=" + contacts.get(index).getId().toString()
				+ "' method='POST'> <button type='submit'>Remover</button> </form> </td>");
				out.println("</tr>");
			}
			out.println("</thead>");
			out.println("</table>");
		}
		%>
	</main>
</body>
</html>