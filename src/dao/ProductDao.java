package dao;

import java.io.*;
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

import beans.Product;

public class ProductDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();

	public ProductDao() {

	}

	public ProductDao(List<Product> products) {
		this.products = products;
	}

	/*
	 * this metod adding a new product to the list
	 * 
	 * @params object of Product
	 */
	public void adding(Product newProduct) throws IOException, ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		// INSERT INTO `product`(`id_product`, `nazov`, `kategoria`, `cena`)
		// VALUES

		String insertTableSQL = "INSERT INTO `product`" + "(`id_product`, `nazov`, `kategoria`, `cena`) " + "VALUES"
				+ "('" + newProduct.getId_tovar() + "','" + newProduct.getNazov() + "','" + newProduct.getKategoria()
				+ "','" + newProduct.getCena() + "')";

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
	 * this metod removing product from index
	 * 
	 * @params index - index in list where the product will be deleted
	 */
	public void remove(int index) throws ClassNotFoundException, IOException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String deleteTableSQL = "DELETE FROM `product` WHERE `product`.`id_product` = " + index + "";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(deleteTableSQL);
			statement.execute(deleteTableSQL);

			// execute delete SQL stetement statement.execute(deleteTableSQL);

			System.out.println("Record is deleted from vyrobca table!");

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
		return "" + products + "";
	}

	/*
	 * this metod saving the list of products to file products.txt
	 */

	public void saveToFile() throws IOException, FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("products.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(products);

		oos.close();
	}

	/*
	 * this metod loading the list of products from file products.txt
	 */

	public void loadFromFile() throws ClassNotFoundException, IOException, FileNotFoundException, SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * from product";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			System.out.println("id******nazov*********kategoria*********cena");
			while (rs.next()) {

				String id_product = rs.getString("id_product");
				String nazov = rs.getString("nazov");
				String kategoria = rs.getString("kategoria");
				String cena = rs.getString("cena");

				System.out.print(id_product);
				System.out.print(" \t" + nazov + "\t ");
				System.out.print("\t " + kategoria + " \t ");
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
