package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Order;

public class OrderDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new ArrayList<Order>();

	public OrderDao() {
	}

	public OrderDao(List<Order> orders) {
		this.orders = orders;
	}

	/*
	 * this metod adding a new order to the list
	 * 
	 * @params object of Order
	 */
	public void adding(Order newOrder) throws ClassNotFoundException, IOException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		// INSERT INTO `product`(`id_product`, `nazov`, `kategoria`, `cena`)
		// VALUES

		String insertTableSQL = "INSERT INTO `order`" + "(`id_user`, `id_product`,`pocet_kusov`, `cena`) " + "VALUES"
				+ "('" + newOrder.getId_user() + "','" + newOrder.getId_product() + "','" + newOrder.getPocet_kusov()
				+ "','" + (newOrder.getCena() * newOrder.getCena()) + "')";

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
	}


	/*
	 * this metod is removing order from file order.txt
	 * 
	 * @params index - index in list where the order will be deleted
	 */

	public void remove(int index) throws ClassNotFoundException, IOException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String deleteTableSQL = "DELETE FROM `order` WHERE `id_order` = " + index + "";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(deleteTableSQL);
			statement.execute(deleteTableSQL);

			// execute delete SQL stetement statement.execute(deleteTableSQL);

			System.out.println("Record is deleted from order table!");

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

	}

	@Override
	public String toString() {
		return "Orders [orders=" + orders + "]";
	}

	/*
	 * this metod saving the list of orders to file orders.txt
	 */

	public void saveToFile() throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("orders.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(orders);
		oos.close();
	}

	/*
	 * this metod loading the list of orders from file orders.txt
	 */

	public void loadFromFile() throws ClassNotFoundException, IOException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * FROM `order` WHERE 1";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			System.out.println("id_order******id_product*****id_user******pocet_kusov***********cena");
			while (rs.next()) {

				//(`id_order`, `id_product`, `id_user`
				String id_order = rs.getString("id_order");
				String id_product = rs.getString("id_product");
				String id_user = rs.getString("id_user");
				String pocet_kusov = rs.getString("pocet_kusov");
				String cena = rs.getString("cena");

				System.out.print(id_order + " \t");
				System.out.print(" \t" + id_product + "\t");
				System.out.print("\t " + id_user + " \t ");
				System.out.print("\t " + pocet_kusov + " \t ");
				System.out.println("\t " + cena + " E");
			}

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
