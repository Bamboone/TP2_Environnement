package gestionDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.ControleConnexion;

public class GestionAlbums {

	private Connection connexion;

	private ArrayList<Album> listeAlbums;

	public GestionAlbums( int idArtiste ) {
		listeAlbums = obtenirListeAlbumsArtiste( idArtiste );
	}

	public GestionAlbums() {
		listeAlbums = obtenirListeAlbums();
	}

	public ArrayList<Album> getListeAlbumsArtiste() {
		return listeAlbums;
	}

	private ArrayList<Album> obtenirListeAlbumsArtiste( int idArtiste ) {
		connexion = ControleConnexion.getConnexion();
		ArrayList<Album> liste = new ArrayList<Album>();
		String requete = "SELECT * FROM Albums WHERE id_artiste = ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, idArtiste );
			ResultSet jeuResultat = statement.executeQuery();
			while ( jeuResultat.next() ) {
				int id = jeuResultat.getInt( "id" );
				String titre = jeuResultat.getString( "titre" );
				String genre = jeuResultat.getString( "genre" );
				int anneeSortie = jeuResultat.getInt( "annee_sortie" );
				String couverture = jeuResultat.getString( "couverture" );
				liste.add( new Album( id, titre, genre, anneeSortie, couverture, idArtiste ) );
			}
			statement.close();
			jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}

		return liste;

	}

	private ArrayList<Album> obtenirListeAlbums() {
		connexion = ControleConnexion.getConnexion();
		ArrayList<Album> liste = new ArrayList<Album>();
		String requete = "SELECT * FROM Albums";
		try {
			Statement statement = connexion.createStatement();
			ResultSet jeuResultat = statement.executeQuery( requete );
			while ( jeuResultat.next() ) {
				int id = jeuResultat.getInt( "id" );
				String titre = jeuResultat.getString( "titre" );
				String genre = jeuResultat.getString( "genre" );
				int anneeSortie = jeuResultat.getInt( "annee_sortie" );
				String couverture = jeuResultat.getString( "couverture" );
				int idArtiste = jeuResultat.getInt( "id_artiste" );
				liste.add( new Album( id, titre, genre, anneeSortie, couverture, idArtiste ) );
			}
			statement.close();
			jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}

		return liste;

	}

	public ArrayList<Album> rechercheAlbum( String titreRecherche ) {
		ArrayList<Album> liste = new ArrayList<Album>();
		connexion = ControleConnexion.getConnexion();
		String requete = "SELECT * FROM Albums WHERE titre LIKE ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setString( 1, "%" + titreRecherche + "%" );
			ResultSet jeuResultat = statement.executeQuery();
			while ( jeuResultat.next() ) {
				int id = jeuResultat.getInt( "id" );
				String titre = jeuResultat.getString( "titre" );
				String genre = jeuResultat.getString( "genre" );
				int annee = jeuResultat.getInt( "annee_sortie" );
				String photo = jeuResultat.getString( "couverture" );
				int idArtiste = jeuResultat.getInt( "id_artiste" );
				liste.add( new Album( id, titre, genre, annee, photo, idArtiste ) );
			}
			statement.close();
			jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
		return liste;
	}

	public void ajouterAlbum( Album album ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "INSERT INTO Albums (id, titre, genre, annee_sortie, couverture, id_artiste) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, album.getId() );
			statement.setString( 2, album.getTitre() );
			statement.setString( 3, album.getGenre() );
			statement.setInt( 4, album.getAnneeSortie() );
			statement.setString( 5, album.getCouverture() );
			statement.setInt( 6, album.getIdArtiste() );
			statement.executeUpdate();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}

	public void modifierAlbum( Album album, int indice ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "UPDATE Albums SET titre = ?, genre = ?, annee_sortie = ?, couverture = ?, id_artiste = ? WHERE id = ?";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setString( 1, album.getTitre() );
			statement.setString( 2, album.getGenre() );
			statement.setInt( 3, album.getAnneeSortie() );
			statement.setString( 4, album.getCouverture() );
			statement.setInt( 5, album.getIdArtiste() );
			statement.setInt( 6, indice );
			statement.executeUpdate();
			statement.close();
		} catch ( SQLException e ) {
			System.out.println( e.getMessage() );
		}
	}

	public void supprimerAlbum( int indice ) {
		connexion = ControleConnexion.getConnexion();
		String requete = "DELETE FROM Albums WHERE id = ?";
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
