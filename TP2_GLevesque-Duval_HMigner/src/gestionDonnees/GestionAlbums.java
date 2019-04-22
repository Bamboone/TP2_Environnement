package gestionDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controleur.ControleConnexion;

public class GestionAlbums {

	private Connection connexion;
	
	private ArrayList<Album> listeAlbums;
	
	public GestionAlbums(int idArtiste) {
		listeAlbums = obtenirListeAlbumsArtiste(idArtiste);
	}
	
	public ArrayList<Album> getListeAlbumsArtiste(){
		return listeAlbums;
	}
	
	private ArrayList<Album> obtenirListeAlbumsArtiste(int idArtiste) {
		ControleConnexion.connecter();
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
		ControleConnexion.fermerConnexion();
		
		return liste;
		
	}
}
