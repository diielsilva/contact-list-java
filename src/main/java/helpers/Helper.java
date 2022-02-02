package helpers;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Helper {
	protected final String driver = "com.mysql.cj.jdbc.Driver";
	protected final String url = "jdbc:mysql://127.0.0.1:3306/final_contact_list?useTimezone=true&serverTimezone=UTC";
	protected final String user = "root";
	protected final String password = "12345Ab";

	protected Connection getConnection() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception error) {
			System.out.println(error);
			return null;
		}
	}
}
