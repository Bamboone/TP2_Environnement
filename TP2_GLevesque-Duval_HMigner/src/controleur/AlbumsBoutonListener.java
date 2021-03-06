package controleur;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import gestionDonnees.ModeleAlbums;
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
	private JTextField fieldArtiste;

	public AlbumsBoutonListener( JButton btnRecherche, JButton btnRemplacer, JButton btnModifier, JButton btnSupprimer,
			JButton btnNouveau, JButton btnAjouter, JButton btnQuitter, JTextField txtRecherche, ModeleAlbums modele,
			JTable table, GestionAlbums gestionnaire, JLabel lblImage, JTextField fieldNumero, JTextField fieldTitre,
			JList<Artiste> listeArtistes, JFrame fenetre, JTextField fieldGenre, JTextField fieldAnnee,
			JTextField fieldArtiste ) {
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
		this.fieldArtiste = fieldArtiste;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {

		if ( e.getSource() == btnRecherche ) {
			ArrayList<Album> liste = gestionnaire.rechercheAlbum( txtRecherche.getText() );
			if(liste.isEmpty() && !txtRecherche.getText().equals( "" )) {
				JOptionPane.showMessageDialog( null,
						"Erreur, aucun album correspond � " + txtRecherche.getText(),
						"Message d'erreur", JOptionPane.ERROR_MESSAGE );
				txtRecherche.setText( "" );
			}else {
				modele.setDonnees( liste );
				modele.fireTableDataChanged();
				clearInfos();
			}
		} else if ( e.getSource() == btnRemplacer ) {
			JFileChooser choixFichier = new JFileChooser( System.getProperty( "user.dir" ) + "\\src\\images\\album" );
			if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
				Image image;
				File f = choixFichier.getSelectedFile();
				imageTemp = f.getName();
				image = new ImageIcon( ArtisteBoutonListener.class.getResource( "../images/album/" + imageTemp ) ).getImage()
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

			btnModifier.setEnabled( false );
			activerInfos();

			clearInfos();
			fieldNumero.setText( Integer.toString( (int) modele.getValueAt( modele.getRowCount() - 1, 0 ) + 1 ) );
			Image image = new ImageIcon( ArtisteBoutonListener.class.getResource( "../images/album/" + "default.png" ) )
					.getImage().getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
			lblImage.setIcon( new ImageIcon( image ) );
			imageModifiee = false;
			btnAjouter.setEnabled( true );
			btnRemplacer.setEnabled( true );
			fieldAnnee.setForeground(Color.GRAY);
        	fieldAnnee.setText("AAAA");
		} else if ( e.getSource() == btnAjouter ) {
			if ( !fieldTitre.getText().trim().equals( "" ) ) {
				if ( validerAnnee() ) {
					if ( listeArtistes.getSelectedIndex() != -1 ) {
						Album album = new Album( Integer.parseInt( fieldNumero.getText() ), fieldTitre.getText(),
								fieldGenre.getText(), Integer.parseInt( fieldAnnee.getText() ),
								imageModifiee ? imageTemp : "default.png", listeArtistes.getSelectedIndex() + 1 );
						if ( modele.ajouterDonnee( album ) ) {
							modele.fireTableDataChanged();
							gestionnaire.ajouterAlbum( album );
							desactiverBoutons();
							desactiverInfos();
							clearInfos();
						} else {
							JOptionPane.showMessageDialog( null,
									"Erreur, l'album au titre: " + fieldTitre.getText() + ", existe d�j�",
									"Message d'erreur", JOptionPane.ERROR_MESSAGE );
						}
					} else {
						JOptionPane.showMessageDialog( null, "Erreur, veuillez s�lectionner un artiste",
								"Message d'erreur", JOptionPane.ERROR_MESSAGE );
					}

				} 
			}else {
				JOptionPane.showMessageDialog( null, "Erreur, impossible d'ajouter un album sans titre",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE );
			}

		} else if ( e.getSource() == btnSupprimer ) {

			int response = JOptionPane.showConfirmDialog( null, "Voulez-vous supprimer cet album?", "Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
			if ( response == JOptionPane.YES_OPTION ) {
				int id = (int) modele.getValueAt( table.getSelectedRow(), 0 );
				modele.supprimerAlbum( table.getSelectedRow() );
				modele.fireTableDataChanged();
				gestionnaire.supprimerAlbum( id );
				desactiverBoutons();
				clearInfos();
			}

		} else if ( e.getSource() == btnModifier ) {
			
			if ( validerAnnee() ) {
				
				int id = (int) modele.getValueAt( table.getSelectedRow(), 0 );
				
				String titre = fieldTitre.getText();
				int annee = Integer.parseInt( fieldAnnee.getText() );
				String genre = fieldGenre.getText();
				String photo = imageModifiee ? imageTemp : (String) modele.getValueAt( table.getSelectedRow(), 5 );
				int artiste = listeArtistes.getSelectedIndex() + 1;
				Album album = new Album( id , titre, genre, annee, photo,
						artiste );
				
				modele.modifierAlbum( table.getSelectedRow(), album );
				modele.fireTableDataChanged();
				gestionnaire.modifierAlbum( album, id );
				desactiverBoutons();
				desactiverInfos();
				clearInfos();
			}
		}

	}

	public void activerInfos() {
		fieldTitre.setEnabled( true );
		fieldTitre.setEnabled( true );
		fieldAnnee.setEnabled( true );
		fieldGenre.setEnabled( true );
		listeArtistes.setEnabled( true );
		table.clearSelection();
		btnSupprimer.setEnabled( false );
	}

	public void desactiverInfos() {
		fieldTitre.setEnabled( false );
		fieldTitre.setEnabled( false );
		fieldAnnee.setEnabled( false );
		fieldGenre.setEnabled( false );
		listeArtistes.setEnabled( false );
		table.clearSelection();
		btnSupprimer.setEnabled( false );
	}

	public void desactiverBoutons() {
		btnModifier.setEnabled( false );
		btnSupprimer.setEnabled( false );
		btnAjouter.setEnabled( false );
		btnRemplacer.setEnabled( false );
	}

	public void clearInfos() {
		lblImage.setIcon( null );
		fieldNumero.setText( "" );
		fieldTitre.setText( "" );
		fieldAnnee.setText( "" );
		fieldGenre.setText( "" );
		fieldArtiste.setText( "" );
		listeArtistes.clearSelection();
		btnSupprimer.setEnabled( false );
	}

	public boolean validerAnnee() {
		boolean valide = false;
		try {
			if ( fieldAnnee.getText().length() == 4 && Integer.parseInt( fieldAnnee.getText() ) > 0 ) {
				valide = true;
			}
		} catch ( NumberFormatException ex ) {
			valide = false;
		}
		if ( !valide ) {
			JOptionPane.showMessageDialog( null, "Erreur, l'ann�e est invalide", "Message d'erreur",
					JOptionPane.ERROR_MESSAGE );
		}
		return valide;
	}

}
