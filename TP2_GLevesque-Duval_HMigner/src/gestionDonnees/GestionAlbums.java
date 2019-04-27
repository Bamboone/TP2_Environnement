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
	
	public GestionAlbums(int idArtiste) {
		listeAlbums = obtenirListeAlbumsArtiste(idArtiste);
	}
	
	public GestionAlbums() {
		listeAlbums = obtenirListeAlbums();
	}

	public ArrayList<Album> getListeAlbumsArtiste(){
		return listeAlbums;
	}
	
	private ArrayList<Album> obtenirListeAlbumsArtiste(int idArtiste) {
		connexion = ControleConnexion.getConnexion();
		ArrayList<Album> liste = new ArrayList<Album>();
		String requete = "SELECT * FROM Albums WHERE id_artiste = ?";
		try{
			PreparedStatement statement = connexion.prepareStatement(requete);
			statement.setInt(1, idArtiste);
			ResultSet jeuResultat = statement.executeQuery();
				while(jeuResultat.next()) {
					int id = jeuResultat.getInt( "id" );
					String titre = jeuResultat.getString( "titre" );
					String genre = jeuResultat.getString( "genre" );
					int anneeSortie = jeuResultat.getInt( "annee_sortie" );
					String couverture = jeuResultat.getString( "couverture" );
					liste.add( new Album(id, titre, genre, anneeSortie, couverture, idArtiste) );
				}
				statement.close();
				jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( "Probleme de connexion" + e.getMessage());
		}

		return liste;
		
	}
	
	private ArrayList<Album> obtenirListeAlbums() {
		connexion = ControleConnexion.getConnexion();
		ArrayList<Album> liste = new ArrayList<Album>();
		String requete = "SELECT * FROM Albums";
		try{
			Statement statement = connexion.createStatement();
			ResultSet jeuResultat = statement.executeQuery(requete);
				while(jeuResultat.next()) {
					int id = jeuResultat.getInt( "id" );
					String titre = jeuResultat.getString( "titre" );
					String genre = jeuResultat.getString( "genre" );
					int anneeSortie = jeuResultat.getInt( "annee_sortie" );
					String couverture = jeuResultat.getString( "couverture" );
					int idArtiste = jeuResultat.getInt( "id_artiste" );
					liste.add( new Album(id, titre, genre, anneeSortie, couverture, idArtiste) );
				}
				statement.close();
				jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( "Probleme de connexion" + e.getMessage());
		}

		return liste;
		
	}
	
	
}
