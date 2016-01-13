package beans;

public class User {

	int id_user;
	String meno;
	String priezvisko;
	int vek;
	String email;
	String login;
	String password;

	public User() {

	}

	public User(int id_user, String meno, String priezvisko, int vek, String email, String login, String password) {
		this.id_user = id_user;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.vek = vek;
		this.email = email;
		this.login = login;
		this.password = password;
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
	 * @return the meno
	 */
	public String getMeno() {
		return meno;
	}

	/**
	 * @param meno
	 *            the meno to set
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * @return the priezvisko
	 */
	public String getPriezvisko() {
		return priezvisko;
	}

	/**
	 * @param priezvisko
	 *            the priezvisko to set
	 */
	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	/**
	 * @return the vek
	 */
	public int getVek() {
		return vek;
	}

	/**
	 * @param vek
	 *            the vek to set
	 */
	public void setVek(int vek) {
		this.vek = vek;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the heslo
	 */
	public String getHeslo() {
		return password;
	}

	/**
	 * @param heslo the heslo to set
	 */
	public void setHeslo(String heslo) {
		this.password = heslo;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", meno=" + meno + ", priezvisko=" + priezvisko + ", vek=" + vek
				+ ", email=" + email + ", login=" + login + ", heslo=" + password + "]";
	}

	

}
