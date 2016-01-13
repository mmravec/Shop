
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Scanner;

import controllers.OrderController;
import controllers.ProductController;
import controllers.UserController;

public class Eshop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void menuAdmin() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		ProductController products = new ProductController();
		OrderController orders = new OrderController();
		Scanner sc = new Scanner(System.in);
		String option = "";

		while (option != "q") {
			System.out.println("Vitajte v E-shope");
			System.out.println("Pokracujte stlacenim klavesy a Enter");
			System.out.println("a -  Pre pridanie noveho produktu do systemu ");
			System.out.println("r -  Pre odstranenie produktu zo systemu ");
			System.out.println("l -  Pre zobrazenie vsetkych produktov ");
			System.out.println("p -  Pre pridanie objednavky ");
			System.out.println("o -  Pre zobrazenie vsetkych objednavok");
			System.out.println("q -  Pre ukoncenie programu");
			option = sc.nextLine();
			switch (option) {
			case "a": {
				products.add();

				break;
			}
			case "r": {
				System.out.println("zadaj index na ktorom chces odstranit produkt");
				products.removeAtIndex(sc.nextInt());
				break;
			}

			case "l": {
				products.listing();
				break;
			}
			case "p": {
				orders.add();

				break;
			}
			case "o": {
				orders.listing();

				break;
			}

			case "q": {
				option = "q";
				break;
			}
			default:
				break;

			}

		}
		// sc.close();
	}

	public static void menuUnknown() throws IOException, ClassNotFoundException, SQLException {
		UserController users = new UserController();

		Scanner sc = new Scanner(System.in);
		String option = "";

		while (option != "q") {
			System.out.println("Vitajte v E-shope");
			System.out.println("Pokracujte stlacenim klavesy a Enter");
			System.out.println("p -  Pre prihlasenie do systemu ");
			System.out.println("r -  Pre registraciu noveho uzivatela do systemu ");
			System.out.println("q -  Pre ukoncenie programu");
			option = sc.nextLine();
			switch (option) {

			case "r": {
				users.add();

				break;
			}

			case "p": {
				int choice = users.logOn();
				System.out.println(choice);
				if (choice == 1) {
					menuAdmin();
				}
				if (choice == 2) {
					System.out.println("uspesne prihlasenie");
					menuUser();
				}

				if (choice == 0) {
					System.out.println("neuspesne prihlasenie \n");

				}

				break;
			}

			case "q": {
				option = "q";
				break;
			}
			default:
				break;

			}

		}

	}

	public static void menuUser() throws IOException, ClassNotFoundException, SQLException {
		ProductController products = new ProductController();
		OrderController orders = new OrderController();
		Scanner sc = new Scanner(System.in);
		String option = "";

		while (option != "q") {
			System.out.println("Vitajte v E-shope");
			System.out.println("Pokracujte stlacenim klavesy a Enter");
			System.out.println("l -  Pre zobrazenie vsetkych produktov ");
			System.out.println("p -  Pre pridanie objednavky ");
			System.out.println("o -  Pre zobrazenie vsetkych objednavok");
			System.out.println("q -  Pre odhlasenie");
			option = sc.nextLine();
			switch (option) {
			case "l": {
				products.listing();
				break;
			}
			case "p": {
				orders.add();

				break;
			}
			case "o": {
				orders.listing();

				break;
			}

			case "q": {
				option = "q";
				break;
			}
			default:
				break;

			}

		}
		// sc.close();
	}

}
/*
 * adding a new Product to the system removing existing Product from the system
 * listing all Products ability to order a Product which results in creating an
 * Order for that Product listing all Orders We need a concept of a User in our
 * system so that we can track who orders our products and to protect our system
 * from unauthorized manipulations (e.g. product manipulations should be
 * restricted to administrators only) enhance the data model with User we need
 * to distinguish between User as a customer and User as a administrator We need
 * to enhance our system functionality with basic authentication so before
 * anyone is using our application he needs to login. provide register
 * functionality which will create a new user in our system provide login
 * functionality where after successful authentication user is able to use the
 * system only users with admin rights can manipulate (add and remove) Products
 * in the system We need to make the data in our system persistent Introduce DAO
 * interface for every entity in our system (UserDAO, ProductDAO, OrderDAO) with
 * CRUD functionality Implement all DAOs as a local file storage (e.g.
 * UserLocalFileDAO) where this implementations will simply store to and load
 * from a file on a disk All system functionality in our system needs to be
 * switched to use these data access objects e.g. when retrieving list of
 * products we should simply call productDAO.findAll();
 *
 */