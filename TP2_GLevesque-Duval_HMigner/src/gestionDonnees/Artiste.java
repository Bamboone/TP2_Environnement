package gestionDonnees;

public class Artiste {

	private int id;
	private String nom;
	private boolean membre;
	private String photo;
	
	public Artiste() {
		
	}
	
	public Artiste(int id, String nom, boolean membre, String photo) {
		this.id = id;
		this.nom = nom;
		this.membre = membre; 
		this.photo = photo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean getMembre() {
		return membre;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		return nom;
		
	}
	
	@Override
	public boolean equals(Object objet) {
		Artiste artiste = (Artiste)objet;
		return nom.equalsIgnoreCase(artiste.getNom());
		
	}
}
