package gestionDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			System.out.println( e.getMessage() );
		}

		return liste;

	}

	public String obtenirNomArtiste( int idArtiste ) {

		connexion = ControleConnexion.getConnexion();
		String requete = "SELECT nom FROM Artistes where id = ?";
		String nom = "";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, idArtiste );
			ResultSet jeuResultat = statement.executeQuery();
			nom = jeuResultat.getString( "nom" );
			jeuResultat.close();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}

		return nom;
	}

	public ArrayList<Artiste> rechercheArtiste( String nomRecherche ) {
		ArrayList<Artiste> liste = new ArrayList<Artiste>();
		connexion = ControleConnexion.getConnexion();
		String requete = "SELECT * FROM Artistes WHERE nom LIKE ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setString( 1, "%" + nomRecherche + "%" );
			ResultSet jeuResultat = statement.executeQuery();
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
			System.out.println( e.getMessage() );
		}
		return liste;
	}

	public void ajouterArtiste( Artiste artiste ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "INSERT INTO Artistes (id, nom, membre, photo) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, artiste.getId() );
			statement.setString( 2, artiste.getNom() );
			statement.setBoolean( 3, artiste.getMembre() );
			statement.setString( 4, artiste.getPhoto() );
			statement.executeUpdate();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}

	public void modifierArtiste( Artiste artiste, int indice ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "UPDATE Artistes SET nom = ?, membre = ?, photo = ? WHERE id = ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setString( 1, artiste.getNom() );
			statement.setBoolean( 2, artiste.getMembre() );
			statement.setString( 3, artiste.getPhoto() );
			statement.setInt( 4, indice );
			statement.executeUpdate();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}

	public void supprimerArtiste( int indice ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "DELETE FROM Artistes WHERE id = ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, indice );
			statement.executeUpdate();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}
}
