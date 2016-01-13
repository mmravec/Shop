package beans;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id_product;
	String nazov;
	String kategoria;
	double cena;

	public Product() {

	}

	public Product(int id_product) {
		this.id_product = id_product;
	}

	public Product(int id_tovar, String nazov, String kategoria, double cena) {

		this.id_product = id_tovar;
		this.nazov = nazov;
		this.kategoria = kategoria;
		this.cena = cena;
	}

	/**
	 * @return the id_tovar
	 */
	public int getId_tovar() {
		return id_product;
	}

	/**
	 * @param id_tovar
	 *            the id_tovar to set
	 */
	public void setId_tovar(int id_tovar) {
		this.id_product = id_tovar;
	}

	/**
	 * @return the nazov
	 */
	public String getNazov() {
		return nazov;
	}

	/**
	 * @param nazov
	 *            the nazov to set
	 */
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	/**
	 * @return the kategoria
	 */
	public String getKategoria() {
		return kategoria;
	}

	/**
	 * @param kategoria
	 *            the kategoria to set
	 */
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
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
		return "Product [id_tovar=" + id_product + ", nazov=" + nazov + ", kategoria=" + kategoria + ", cena=" + cena
				+ "]\n";
	}

}
