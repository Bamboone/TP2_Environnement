package controleur;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gestionDonnees.Album;
import gestionDonnees.Artiste;
import gestionDonnees.GestionAlbums;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleAlbums;
import gestionDonnees.ModeleArtistes;
import vues.VueChoixTraitements;

public class AlbumsBoutonListener implements ActionListener {

	private JButton btnRecherche;
	private ModeleAlbums modele;
	private GestionAlbums gestionnaire;
	private JTextField txtRecherche;
	private JButton btnRemplacer;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnNouveau;
	private JButton btnAjouter;
	private JButton btnQuitter;
	private JTable table;
	private JLabel lblImage;
	private JTextField fieldNumero;
	private JTextField fieldTitre;
	private JList<Artiste> listeArtistes;
	private JFrame fenetre;
	private String imageTemp;
	private boolean imageModifiee = false;
	private JTextField fieldGenre;
	private JTextField fieldAnnee;

	public AlbumsBoutonListener( JButton btnRecherche, JButton btnRemplacer, JButton btnModifier,
			JButton btnSupprimer, JButton btnNouveau, JButton btnAjouter, JButton btnQuitter, JTextField txtRecherche,
			ModeleAlbums modele, JTable table, GestionAlbums gestionnaire, JLabel lblImage, JTextField fieldNumero,
			JTextField fieldTitre, JList<Artiste> listeArtistes, JFrame fenetre, JTextField fieldGenre, JTextField fieldAnnee ) {
		this.btnRecherche = btnRecherche;
		this.modele = modele;
		this.gestionnaire = gestionnaire;
		this.txtRecherche = txtRecherche;
		this.btnRemplacer = btnRemplacer;
		this.table = table;
		this.lblImage = lblImage;
		this.btnModifier = btnModifier;
		this.btnSupprimer = btnSupprimer;
		this.btnAjouter = btnAjouter;
		this.btnNouveau = btnNouveau;
		this.btnQuitter = btnQuitter;
		this.fieldNumero = fieldNumero;
		this.fieldTitre = fieldTitre;
		this.listeArtistes = listeArtistes;
		this.fenetre = fenetre;
		this.fieldGenre = fieldGenre;
		this.fieldAnnee = fieldAnnee;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {

		if ( e.getSource() == btnRecherche ) {

			modele.setDonnees( gestionnaire.rechercheAlbum( txtRecherche.getText() ) );
			modele.fireTableDataChanged();
//			clearInfos();
		} else if ( e.getSource() == btnRemplacer ) {
			JFileChooser choixFichier = new JFileChooser( System.getProperty( "user.dir" ) + "\\src\\images" );
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				Image image;
				File f = choixFichier.getSelectedFile();
				imageTemp = f.getName();
				image = new ImageIcon( ArtisteBoutonListener.class.getResource( "../images/" + imageTemp ) ).getImage()
						.getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
				lblImage.setIcon( new ImageIcon( image ) );
				imageModifiee = true;
			}
		} else if ( e.getSource() == btnQuitter ) {
			int response = JOptionPane.showConfirmDialog( null, "Voulez-vous vraiment quitter?", "Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			if ( response == JOptionPane.YES_OPTION ) {
				VueChoixTraitements accueil = new VueChoixTraitements();
				fenetre.dispose();
				accueil.setVisible( true );
				ControleConnexion.fermerConnexion();
			}
		} else if ( e.getSource() == btnNouveau ) {
			if ( !fieldTitre.isEnabled() ) {
//				toggleInfos();
			}
			fieldTitre.setText( "" );
			fieldNumero.setText( Integer.toString( modele.getRowCount() + 1 ) );
			fieldGenre.setText( "" );
			fieldAnnee.setText("");
			lblImage.setIcon( null );
			imageModifiee = false;
			btnAjouter.setEnabled( true );
			btnRemplacer.setEnabled( true );
		} else if ( e.getSource() == btnAjouter ) {
			Album album = new Album(Integer.parseInt( fieldNumero.getText() ), fieldTitre.getText(), 
					fieldGenre.getText(), Integer.parseInt( fieldAnnee.getText() ), imageModifiee ? imageTemp : "default.png", listeArtistes.getSelectedIndex() + 1);
			if(modele.ajouterDonnee( album )) {
				modele.fireTableDataChanged();
				gestionnaire.ajouterAlbum( album );
//				activerBoutons();
//				toggleInfos();
//				clearInfos();
			}else {
				JOptionPane.showMessageDialog(null, "Erreur, l'album au titre: " + fieldTitre.getText() + ", existe d�j�",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else if(e.getSource() == btnSupprimer) {
			int indice = table.getSelectedRow() + 1;
			modele.supprimerAlbum( indice - 1 );
			modele.fireTableDataChanged();
			gestionnaire.supprimerAlbum(indice);
//			activerBoutons();
//			clearInfos();
		} else if(e.getSource() == btnModifier) {
			int indice = table.getSelectedRow() + 1;
			String titre = fieldTitre.getText();
			int annee = Integer.parseInt( fieldAnnee.getText() );
			String genre = fieldGenre.getText();
			String photo = imageModifiee ? imageTemp : (String) modele.getValueAt( indice - 1, 5 );
			int artiste = listeArtistes.getSelectedIndex() + 1;
			Album album = new Album(Integer.parseInt(fieldNumero.getText()), titre, genre, annee, photo, artiste);
			modele.modifierAlbum( indice - 1, album );
			modele.fireTableDataChanged();
			gestionnaire.modifierAlbum( album, indice );
//			activerBoutons();
//			toggleInfos();
//			clearInfos();
		}

	}

	public void toggleInfos() {
		if ( fieldTitre.isEnabled() ) {
			fieldTitre.setEnabled( false );
		} else if ( !fieldTitre.isEnabled() ) {
			fieldTitre.setEnabled( true );
		}
		table.clearSelection();
		btnSupprimer.setEnabled( false );
	}
	
	public void activerBoutons() {
		btnModifier.setEnabled( false );
		btnSupprimer.setEnabled( false );
		btnAjouter.setEnabled( false );
		btnRemplacer.setEnabled( false );
	}
	
	public void clearInfos() {
		lblImage.setIcon( null );
		fieldNumero.setText( "" );
		fieldTitre.setText( "" );
		btnSupprimer.setEnabled( false );
	}

}
