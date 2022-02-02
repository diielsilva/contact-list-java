package routes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.ContactHelper;
import models.Contact;

@WebServlet(urlPatterns = { "/RouterController", "/index", "/addContact", "/myContacts", "/storeContact",
		"/deleteContact", "/updateContact", "/storeUpdateContact" })
public class RouterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String baseDir = "/WEB-INF/views/";

	public RouterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/RouterController":
			response.sendRedirect("index");
			break;
		case "/index":
			this.index(request, response);
			break;
		case "/addContact":
			this.addContact(request, response);
			break;
		case "/myContacts":
			this.myContacts(request, response);
			break;
		case "/updateContact":
			this.updateContact(request, response);
			break;
		default:
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/storeContact":
			this.storeContact(request, response);
			break;
		case "/deleteContact":
			this.deleteContact(request, response);
			break;
		case "/storeUpdateContact":
			this.storeUpdateContact(request, response);
			break;
		}
	}

	// METODOS DO TIPO GET
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.baseDir + "index.jsp");
		dispatcher.forward(request, response);
	}

	protected void addContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.baseDir + "add_contact.jsp");
		dispatcher.forward(request, response);
	}

	protected void myContacts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactHelper contactHelper = new ContactHelper();
		ArrayList<Contact> contacts = contactHelper.getAll();
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.baseDir + "my_contacts.jsp");
		request.setAttribute("contacts", contacts);
		dispatcher.forward(request, response);
	}

	protected void updateContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Contact contact = new Contact(id, null, null, null);
		ContactHelper contactHelper = new ContactHelper();
		contact = contactHelper.findById(contact);
		RequestDispatcher dispatcher = request.getRequestDispatcher(this.baseDir + "update_contact.jsp");
		request.setAttribute("contact", contact);
		dispatcher.forward(request, response);
	}

	// METODOS DO TIPO POST
	protected void storeContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			Contact contact = new Contact();
			ContactHelper contactHelper = new ContactHelper();
			String message = "";
			Integer result = 0;

			if (name == null || phone == null || email == null || name == "" || phone == "" || email == "") {
				message = "Desculpe, nao foi possivel adicionar o contato!";
			} else {
				contact.setName(name);
				contact.setPhone(phone);
				contact.setEmail(email);
				result = contactHelper.store(contact);
				if (result != 1 || result == null) {
					message = "Desculpe, nao foi possivel adicionar o contato!";
				} else {
					message = "Contato adicionado com sucesso!";
				}

			}
			response.sendRedirect("addContact?message=" + message);
		} catch (Exception error) {
			String message = "Desculpe, nao foi possivel adicionar o contato!";
			response.sendRedirect("addContact?message=" + message);
		}

	}

	protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Contact contact = new Contact(id, null, null, null);
			ContactHelper contactHelper = new ContactHelper();
			String message = "";
			Integer result = 0;

			result = contactHelper.delete(contact);
			if (result != 1) {
				message = "Desculpe, nao foi possivel remover o contato!";
			} else {
				message = "Contato removido com sucesso!";
			}
			response.sendRedirect("myContacts?message=" + message);
		} catch (Exception error) {
			String message = "Desculpe, nao foi possivel remover o contato!";
			response.sendRedirect("myContacts?message=" + message);
		}
	}

	protected void storeUpdateContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			Contact contact = new Contact();
			ContactHelper contactHelper = new ContactHelper();
			String message = "";
			Integer result = 0;

			if (name == null || phone == null || email == null || name == "" || phone == "" || email == "") {
				message = "Desculpe, nao foi possivel alterar o contato!";
			} else {
				contact.setId(id);
				contact.setName(name);
				contact.setPhone(phone);
				contact.setEmail(email);
				result = contactHelper.update(contact);
				if (result != 1) {
					message = "Desculpe, nao foi possivel alterar o contato!";
				} else {
					message = "Contato alterado com sucesso!";
				}
			}
			response.sendRedirect("updateContact?id=" + id + "&message=" + message);
		} catch (Exception error) {
			System.out.println(error);
		}

	}

}
