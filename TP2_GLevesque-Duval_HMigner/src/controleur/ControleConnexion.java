package controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ControleConnexion {

	private static Connection connexion;
	private static String url = "jdbc:sqlite:sqlite/BD/GabrielLDHugoM_biblioMusiques.db";

	public static void connecter() {
		try {
			if ( connexion == null || connexion.isClosed() ) {
				Class.forName( "org.sqlite.JDBC" );
				connexion = DriverManager.getConnection( url );
			}
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Pas de driver" );
		} catch ( SQLException e ) {
			System.out.println( "Problème de connexion" );
		}
	}

	public static void fermerConnexion() {
		try {
			if ( connexion != null && !connexion.isClosed() ) {
				connexion.close();
			}
		} catch ( SQLException e ) {
			System.out.println( "Problème de connexion" );
		}
	}

	public static Connection getConnexion() {
		return connexion;
	}
}
