package controleur;

import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import gestionDonnees.Album;
import gestionDonnees.Artiste;
import gestionDonnees.GestionAlbums;
import gestionDonnees.ModeleArtistes;

public class ArtisteMouseListener extends MouseAdapter {

	private JTable table;
	private JTextField txtId;
	private JTextField txtNom;
	private JCheckBox checkMembre;
	private ModeleArtistes modele;
	private JLabel lblImage;
	private DefaultListModel<Album> donnees = new DefaultListModel<>();
	private JList<Album> listeAlbums;

	public ArtisteMouseListener(JTable table, JTextField txtId, JTextField txtNom, JCheckBox checkMembre, ModeleArtistes modele, JLabel lblImage, JList<Album> listeAlbums) {
		this.table = table;
		this.txtId = txtId;
		this.txtNom = txtNom;
		this.checkMembre = checkMembre;
		this.modele = modele;
		this.lblImage = lblImage;
		this.listeAlbums = listeAlbums;
		listeAlbums.setModel( donnees );
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == table) {
			int numLigne;
			listeAlbums.clearSelection();
			numLigne = table.getSelectedRow();
			Artiste artiste = modele.getElement(numLigne);
			txtId.setText(String.valueOf(artiste.getId()));
			txtNom.setText(artiste.getNom());
			checkMembre.setSelected(artiste.getMembre());
			GestionAlbums gestion = new GestionAlbums(artiste.getId());
			donnees.clear();
			for(Album album : gestion.getListeAlbumsArtiste()) {
				donnees.addElement(album);
			}
			Image image = new ImageIcon(ArtisteMouseListener.class.getResource( "../images/"+artiste.getPhoto() )).getImage().getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
			lblImage.setIcon(new ImageIcon(image));
			
		}
	}
}
