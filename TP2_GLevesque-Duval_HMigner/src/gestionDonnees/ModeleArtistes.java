package gestionDonnees;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeleArtistes extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Artiste> listeArtistes;

	private String[] lesTitres = { "Numéro", "Nom", "Membre" };

	public ModeleArtistes() {

	}

	public ModeleArtistes( ArrayList<Artiste> liste ) {
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
	public Object getValueAt( int rowIndex, int columnIndex ) {
		switch ( columnIndex ) {
		case 0:
			return listeArtistes.get( rowIndex ).getId();
		case 1:
			return listeArtistes.get( rowIndex ).getNom();
		case 2:
			return ( listeArtistes.get( rowIndex ).getMembre() ? "Oui" : "Non" );
		case 3:
			return listeArtistes.get( rowIndex ).getPhoto();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName( int columnIndex ) {
		return lesTitres[columnIndex];
	}
}
