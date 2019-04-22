package gestionDonnees;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeleArtistes extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Artiste> listeArtistes;

	private String[] lesTitres = { "Numéro", "Nom", "Membre" };

	public ModeleArtistes() {

	}

	public ModeleArtistes(ArrayList<Artiste> liste) {
		listeArtistes = liste;
	}

	@Override
	public int getColumnCount() {
		return lesTitres.length;
	}

	@Override
	public int getRowCount() {
		return listeArtistes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listeArtistes.get(rowIndex).getId();
		case 1:
			return listeArtistes.get(rowIndex).getNom();
		case 2:
			return (listeArtistes.get(rowIndex).getMembre() ? "Oui" : "Non");
		case 3:
			return listeArtistes.get(rowIndex).getPhoto();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return lesTitres[columnIndex];
	}

	public Artiste getElement(int numLigne) {

		int id = (int) getValueAt(numLigne, 0);
		String nom = (String) getValueAt(numLigne, 1);
		Boolean membre = (boolean) getValueAt(numLigne, 2).equals("Oui");
		String photo = (String) getValueAt(numLigne, 3);

		return new Artiste(id, nom, membre, photo);
	}

	public void setDonnees(ArrayList<Artiste> donnees) {
		listeArtistes = donnees;
	}
	
	public void modifierArtiste(int indice, Artiste artiste) {
		listeArtistes.set(indice, artiste);
		fireTableRowsUpdated(indice, indice);
	}
}
