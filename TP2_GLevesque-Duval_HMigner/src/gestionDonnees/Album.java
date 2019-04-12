package gestionDonnees;

public class Album {

	private int id;
	private String titre;
	private String genre;
	private int anneeSortie;
	private String couverture;
	private int idArtiste;
	
	public Album() {
		
	}
	
	public Album(int id, String titre, String genre, int anneeSortie, String couverture, int idArtiste) {
		this.id = id;
		this.titre = titre;
		this.genre = genre;
		this.anneeSortie = anneeSortie;
		this.couverture = couverture;
		this.idArtiste = idArtiste;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public int getAnneeSortie() {
		return anneeSortie;
	}
	
	public String getCouverture() {
		return couverture;
	}
	
	public int getIdArtiste() {
		return idArtiste;
	}
	
}
