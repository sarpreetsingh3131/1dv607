package model;

public class Authentication {

	private final String username = "admin";
	private final String password = "workshop2";
	private boolean loggedIn = false;

	public Authentication() {

	}

	public void logIn(String username, String password) {
		if (username.equals(this.username) && password.equals(this.password))
			loggedIn = true;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}
}