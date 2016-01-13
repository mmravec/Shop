package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import beans.User;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	private List<User> users = new ArrayList<User>();

	public UserDao() {

	}

	public UserDao(List<User> users) {
		this.users = users;
	}

	/*
	 * this metod is opening and writing to file users.txt informations about
	 * new user
	 * 
	 * @params meno, priezvisko, vek, email, login, password
	 */

	public void signingIn(String meno, String priezvisko, int vek, String email, String login, String password)
			throws IOException, SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO user" + "(`login`, `password`, `meno`, `prizvysko`, `vek`, `email`)"
				+ "VALUES" + "('" + login + "','" + password + "','" + meno + "','" + priezvisko + "','" + vek + "','"
				+ email + "')";/*
								 * + "to_date('" + getCurrentTimeStamp() +
								 * "', 'yyyy/mm/dd hh24:mi:ss'))" ;
								 */

		// String insertTableSQL = "INSERT INTO vyrobca " + "VALUES ("+ id +", "
		// + nazov_vyrobcu + " ," + adresa +" )";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

		System.out.println("pridane !");
	}

	/*
	 * this metod is checking if exists user with entered login and password
	 * 
	 * @params login, password, sc
	 */

	public boolean loggingIn(String login, String password, Scanner sc) throws IOException, SQLException {
		// String dbUsername, dbPassword;
		Connection dbConnection = null;
		Statement statement = null;

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			String sql = "SELECT  login, password FROM user";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				// setId_usera(rs.getInt("id_user"));
				// System.out.println(getId_usera());
				// = rs.getInt("id_user");

				String first = rs.getString("login");
				String last = rs.getString("password");
				// System.out.println("toto usera: "+id_usera);
				if (first.equals(login) && last.equals(password)) {
					// System.out.println("toto je id usera: "+id_usera);
					return true;
				}

			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (statement != null)
					dbConnection.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (dbConnection != null)
					dbConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // end finally try
		return false;
	} // end try

	/*
	 * this metod adding a new product to the list
	 * 
	 * @params object of User
	 */
	public void adding(User newUser) {
		users.add(newUser);

	}

	/*
	 * this metod removing user from index
	 * 
	 * @params index
	 */
	public void remove(int index) {

		users.remove(index);
	}

	/*
	 * this metod is counting number of lines in file, because we need set id to
	 * new user
	 * 
	 * @params filename
	 */

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	@Override
	public String toString() {
		return "UserDao [users=" + users + "]";
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/shop?user=root&password=");
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}
