package gestionDonnees;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeleAlbums extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Album> listeAlbums;

	private String[] lesTitres = { "Numéro", "Titre", "Genre", "Année" };

	public ModeleAlbums() {

	}

	public ModeleAlbums(ArrayList<Album> liste) {
		listeAlbums = liste;
	}

	@Override
	public int getColumnCount() {
		return lesTitres.length;
	}

	@Override
	public int getRowCount() {
		return listeAlbums.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listeAlbums.get( rowIndex ).getId();
		case 1:
			return listeAlbums.get(rowIndex).getTitre();
		case 2:
			return listeAlbums.get(rowIndex).getGenre();
		case 3:
			return listeAlbums.get(rowIndex).getAnneeSortie();
		case 4:
			return listeAlbums.get(rowIndex).getIdArtiste();
		case 5:
			return listeAlbums.get(rowIndex).getCouverture();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return lesTitres[columnIndex];
	}
	
	public Album getElement(int numLigne) {

		int id = (int) getValueAt(numLigne, 0);
		String titre = (String) getValueAt(numLigne, 1);
		String genre = (String) getValueAt(numLigne, 2);
		int annee = (int) getValueAt(numLigne, 3);
		String photo = (String) getValueAt(numLigne, 5);
		int idArtiste = (int) getValueAt(numLigne, 4);

		return new Album(id, titre, genre, annee, photo, idArtiste);
	}

	public void setDonnees(ArrayList<Album> donnees) {
		listeAlbums = donnees;
	}
	
	public boolean ajouterDonnee(Album album) {
		boolean albumManquant = false;
		if(!listeAlbums.contains(album)) {
			albumManquant = true;
			listeAlbums.add( album );
		}
		return albumManquant;
		
	}
	
	public void modifierAlbum(int indice, Album album) {
		listeAlbums.set(indice, album);
		fireTableRowsUpdated(indice, indice);
	}
	
	public void supprimerAlbum(int indice) {
		listeAlbums.remove( indice );
		fireTableRowsUpdated(indice, indice);
	}
}
