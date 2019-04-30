package controleur;

import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

import gestionDonnees.Album;
import gestionDonnees.Artiste;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleAlbums;

public class AlbumsMouseListener extends MouseAdapter {

	private JTable table;
	private JTextField fieldId;
	private JTextField fieldTitre;
	private ModeleAlbums modele;
	private JLabel lblImage;
	private JList<Artiste> listeArtistes;
	private JButton btnRemplacer;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnAjouter;
	private JTextField fieldGenre;
	private JTextField fieldAnnee;
	private JTextField fieldArtiste;

	public AlbumsMouseListener( JTable table, JTextField txtId, JTextField txtNom, JTextField fieldGenre,
			JTextField fieldAnnee, JTextField fieldArtiste, ModeleAlbums modele, JLabel lblImage,
			JList<Artiste> listeArtistes, JButton btnAjouter, JButton btnModifier, JButton btnRemplacer,
			JButton btnSupprimer ) {
		this.table = table;
		this.fieldId = txtId;
		this.fieldTitre = txtNom;
		this.modele = modele;
		this.lblImage = lblImage;
		this.listeArtistes = listeArtistes;
		this.btnAjouter = btnAjouter;
		this.btnModifier = btnModifier;
		this.btnRemplacer = btnRemplacer;
		this.btnSupprimer = btnSupprimer;
		this.fieldGenre = fieldGenre;
		this.fieldAnnee = fieldAnnee;
		this.fieldArtiste = fieldArtiste;
	}

	public void mouseClicked( MouseEvent e ) {
		if ( e.getSource() == table ) {

			int numLigne;
			listeArtistes.clearSelection();
			numLigne = table.getSelectedRow();
			Album album = modele.getElement( numLigne );

			fieldId.setText( String.valueOf( album.getId() ) );
			fieldTitre.setText( album.getTitre() );
			fieldGenre.setText( album.getGenre() );
			fieldAnnee.setText( String.valueOf( album.getAnneeSortie() ) );
			fieldArtiste.setText( new GestionArtistes().obtenirNomArtiste( album.getIdArtiste() ) );

			Image image;
			try {
				image = new ImageIcon( ArtisteMouseListener.class.getResource( "../images/album/" + album.getCouverture() ) )
						.getImage().getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
			} catch ( Exception e2 ) {
				image = new ImageIcon( ArtisteBoutonListener.class.getResource( "../images/album/default.png" ) ).getImage()
						.getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
			}
			lblImage.setIcon( new ImageIcon( image ) );
			activerChamps();

			if ( e.getClickCount() == 2 ) {
				btnModifier.setEnabled( true );
				fieldTitre.setEnabled( true );
				fieldAnnee.setEnabled( true );
				fieldGenre.setEnabled( true );
				btnRemplacer.setEnabled( true );
				listeArtistes.setEnabled( true );
				listeArtistes.setSelectedIndex( album.getIdArtiste() - 1 );
			}

		} else if ( e.getSource() == listeArtistes ) {
			if ( e.getClickCount() == 2 ) {
				fieldArtiste.setText( listeArtistes.getSelectedValue().getNom() );
			}
		}

	}

	public void activerChamps() {
		btnModifier.setEnabled( false );
		btnSupprimer.setEnabled( true );
		btnAjouter.setEnabled( false );
		btnRemplacer.setEnabled( false );
		fieldTitre.setEnabled( false );
		fieldAnnee.setEnabled( false );
		fieldGenre.setEnabled( false );
		listeArtistes.setEnabled( false );
	}

}
