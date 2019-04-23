package gestionDonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.ControleConnexion;

public class GestionArtistes {

	private Connection connexion;

	private ArrayList<Artiste> listeArtistes;

	public GestionArtistes() {
		listeArtistes = obtenirListeArtistes();
	}

	public ArrayList<Artiste> getListeArtistes() {
		return listeArtistes;
	}

	private ArrayList<Artiste> obtenirListeArtistes() {
		ControleConnexion.connecter();
		connexion = ControleConnexion.getConnexion();
		ArrayList<Artiste> liste = new ArrayList<Artiste>();
		String requete = "SELECT * FROM Artistes";
		try (Statement statement = connexion.createStatement();
				ResultSet jeuResultat = statement.executeQuery( requete )) {
			while ( jeuResultat.next() ) {
				int id = jeuResultat.getInt( "id" );
				String nom = jeuResultat.getString( "nom" );
				boolean membre = jeuResultat.getBoolean( "membre" );
				String photo = jeuResultat.getString( "photo" );
				liste.add( new Artiste( id, nom, membre, photo ) );
			}
			statement.close();
			jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( "Probleme de connexion" );
		}
		ControleConnexion.fermerConnexion();

		return liste;

	}
}
