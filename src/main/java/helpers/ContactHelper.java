package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Contact;

public class ContactHelper extends Helper {
	private String sql;
	private ArrayList<Contact> contacts;
	private Integer result;

	public Integer store(Contact contact) {
		try {
			this.sql = "INSERT INTO contacts (id, name, phone, email) VALUES (?,?,?,?)";
			Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(this.sql);
			statement.setString(1, null);
			statement.setString(2, contact.getName());
			statement.setString(3, contact.getPhone());
			statement.setString(4, contact.getEmail());
			this.result = statement.executeUpdate();
			statement.close();
			connection.close();
			return this.result;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}

	public ArrayList<Contact> getAll() {
		try {
			this.sql = "SELECT * FROM contacts";
			Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(this.sql);
			ResultSet resultSet = statement.executeQuery();
			this.contacts = new ArrayList<Contact>();
			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setId(Integer.parseInt(resultSet.getString(1)));
				contact.setName(resultSet.getString(2));
				contact.setPhone(resultSet.getString(3));
				contact.setEmail(resultSet.getString(4));
				this.contacts.add(contact);
			}
			statement.close();
			connection.close();
			return this.contacts;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}

	public Contact findById(Contact contact) {
		try {
			this.sql = "SELECT * FROM contacts WHERE id = ?";
			Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(this.sql);
			statement.setInt(1, contact.getId());
			ResultSet result = statement.executeQuery();
			Contact resultContact = new Contact();
			while (result.next()) {
				resultContact.setId(Integer.parseInt(result.getString(1)));
				resultContact.setName(result.getString(2));
				resultContact.setPhone(result.getString(3));
				resultContact.setEmail(result.getString(4));
			}
			statement.close();
			connection.close();
			System.out.println("ID" + resultContact.getId().toString());
			System.out.println("NOME: " + resultContact.getName());
			return resultContact;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}

	public Integer update(Contact contact) {
		try {
			this.sql = "UPDATE contacts SET name = ?, phone = ?, email = ? WHERE id = ?";
			Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(this.sql);
			statement.setString(1, contact.getName());
			statement.setString(2, contact.getPhone());
			statement.setString(3, contact.getEmail());
			statement.setInt(4, contact.getId());
			this.result = statement.executeUpdate();
			statement.close();
			connection.close();
			return this.result;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}

	public Integer delete(Contact contact) {
		try {
			this.sql = "DELETE FROM contacts WHERE id = ?";
			Connection connection = this.getConnection();
			PreparedStatement statement = connection.prepareStatement(this.sql);
			statement.setInt(1, contact.getId());
			this.result = statement.executeUpdate();
			statement.close();
			connection.close();
			return this.result;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}
}
