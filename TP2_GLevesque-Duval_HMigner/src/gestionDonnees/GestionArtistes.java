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
	
	public ArrayList<Artiste> getListeArtistes(){
		return listeArtistes;
	}
	
	private ArrayList<Artiste> obtenirListeArtistes() {
		connexion = ControleConnexion.getConnexion();
		ArrayList<Artiste> liste = new ArrayList<Artiste>();
		String requete = "SELECT * FROM Artistes";
		try(Statement statement = connexion.createStatement();
				ResultSet jeuResultat = statement.executeQuery( requete )) {
				while(jeuResultat.next()) {
					int id = jeuResultat.getInt( "id" );
					String nom = jeuResultat.getString( "nom" );
					boolean membre = jeuResultat.getBoolean( "membre" );
					String photo = jeuResultat.getString( "photo" );
					liste.add( new Artiste(id, nom, membre, photo) );
				}
				statement.close();
				jeuResultat.close();
		} catch ( SQLException e ) {
			System.out.println( "Probleme de connexion" );
		}
		
		return liste;
		
	}
	
	public ArrayList<Artiste> rechercheArtiste(String nomRecherche){
		ArrayList<Artiste> liste = new ArrayList<Artiste>();
		connexion = ControleConnexion.getConnexion();
		String requete = "SELECT * FROM Artistes WHERE nom LIKE ?";
		try {
			PreparedStatement statement = connexion.prepareStatement(requete);
			statement.setString(1, "%" + nomRecherche + "%");
			ResultSet jeuResultat = statement.executeQuery();
			while(jeuResultat.next()) {
				int id = jeuResultat.getInt( "id" );
				String nom = jeuResultat.getString( "nom" );
				boolean membre = jeuResultat.getBoolean( "membre" );
				String photo = jeuResultat.getString( "photo" );
				liste.add( new Artiste(id, nom, membre, photo) );
			}
			statement.close();
			jeuResultat.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return liste;
	}
	
	public void modifierPhoto(String photo, int id) {
		connexion = ControleConnexion.getConnexion();
		String requete = "UPDATE Artistes SET photo = ? WHERE id = ?";
		try {
			PreparedStatement statement = connexion.prepareStatement(requete);
			statement.setString(1, photo);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void ajouterArtiste(Artiste artiste) {
		connexion = ControleConnexion.getConnexion();
		String requete = "INSERT INTO Artistes (id, nom, membre, photo) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement statement = connexion.prepareStatement( requete );
			statement.setInt( 1, artiste.getId());
			statement.setString( 2, artiste.getNom());
			statement.setBoolean( 3, artiste.getMembre());
			statement.setString( 4, artiste.getPhoto() );
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
