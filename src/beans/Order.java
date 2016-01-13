package beans;

import java.io.Serializable;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id_user;
	int id_product;
	int pocet_kusov;
	double cena;

	public Order() {

	}

	public Order(int id_user, int id_product, int pocet_kusov, double cena) {

		this.id_user = id_user;
		this.id_product = id_product;
		this.pocet_kusov = pocet_kusov;
		this.cena = cena;
	}

	/**
	 * @return the id_user
	 */
	public int getId_user() {
		return id_user;
	}

	/**
	 * @param id_user
	 *            the id_user to set
	 */
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	/**
	 * @return the id_product
	 */
	public int getId_product() {
		return id_product;
	}

	/**
	 * @param id_product
	 *            the id_product to set
	 */
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	/**
	 * @return the pocet_kusov
	 */
	public int getPocet_kusov() {
		return pocet_kusov;
	}

	/**
	 * @param pocet_kusov
	 *            the pocet_kusov to set
	 */
	public void setPocet_kusov(int pocet_kusov) {
		this.pocet_kusov = pocet_kusov;
	}

	/**
	 * @return the cena
	 */
	public double getCena() {
		return cena;
	}

	/**
	 * @param cena
	 *            the cena to set
	 */
	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Order [id_user=" + id_user + ", id_product=" + id_product + ", pocet_kusov=" + pocet_kusov + ", cena="
				+ cena + "]\n";
	}

}
