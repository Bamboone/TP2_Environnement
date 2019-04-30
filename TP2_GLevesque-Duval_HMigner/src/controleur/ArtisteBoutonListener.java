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
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleArtistes;
import vues.VueChoixTraitements;

public class ArtisteBoutonListener implements ActionListener {

	private JButton btnRecherche;
	private ModeleArtistes modele;
	private GestionArtistes gestionnaire;
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
	private JTextField fieldNom;
	private JCheckBox checkBoxMembre;
	private JList<Album> listeAlbums;
	private JFrame fenetre;
	private String imageTemp;
	private boolean imageModifiee = false;

	public ArtisteBoutonListener( JButton btnRecherche, JButton btnRemplacer, JButton btnModifier,
			JButton btnSupprimer, JButton btnNouveau, JButton btnAjouter, JButton btnQuitter, JTextField txtRecherche,
			ModeleArtistes modele, JTable table, GestionArtistes gestionnaire, JLabel lblImage, JTextField fieldNumero,
			JTextField fieldNom, JCheckBox checkBoxMembre, JList<Album> listeAlbums, JFrame fenetre ) {
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
		this.fieldNom = fieldNom;
		this.checkBoxMembre = checkBoxMembre;
		this.listeAlbums = listeAlbums;
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {

		if ( e.getSource() == btnRecherche ) {

			modele.setDonnees( gestionnaire.rechercheArtiste( txtRecherche.getText() ) );
			modele.fireTableDataChanged();
			clearInfos();
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
				btnModifier.setEnabled( false );
				activerInfos();
				clearInfos();
				Image image = new ImageIcon( ArtisteBoutonListener.class.getResource( "../images/" + "default.png" ) ).getImage()
						.getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
				lblImage.setIcon( new ImageIcon( image ) );
			fieldNumero.setText( Integer.toString( modele.getRowCount() + 1 ) );
			imageModifiee = false;
			btnAjouter.setEnabled( true );
			btnRemplacer.setEnabled( true );
		} else if ( e.getSource() == btnAjouter ) {
			Artiste artiste = new Artiste( Integer.parseInt( fieldNumero.getText() ), fieldNom.getText(),
					checkBoxMembre.isSelected(), imageModifiee ? imageTemp : "default.png" );
			if ( modele.ajouterDonnee( artiste ) ) {
				modele.fireTableDataChanged();
				gestionnaire.ajouterArtiste( artiste );
				desactiverBoutons();
				desactiverInfos();
				clearInfos();
			} else {
				JOptionPane.showMessageDialog( null, "Erreur, l'artiste " + fieldNom.getText() + " existe déjà",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE );
			}
		} else if ( e.getSource() == btnSupprimer ) {
			int indice = table.getSelectedRow() + 1;
			modele.supprimerArtiste( indice - 1 );
			modele.fireTableDataChanged();
			gestionnaire.supprimerArtiste( indice );
			desactiverBoutons();
			clearInfos();
		} else if ( e.getSource() == btnModifier ) {
			int indice = table.getSelectedRow() + 1;
			String nom = fieldNom.getText();
			boolean membre = checkBoxMembre.isSelected();
			String photo = imageModifiee ? imageTemp : (String) modele.getValueAt( indice - 1, 3 );
			Artiste artiste = new Artiste( Integer.parseInt( fieldNumero.getText() ), nom, membre, photo );
			modele.modifierArtiste( indice - 1, artiste );
			modele.fireTableDataChanged();
			gestionnaire.modifierArtiste( artiste, indice );
			desactiverBoutons();
			desactiverInfos();
			clearInfos();
		}

	}

	public void activerInfos() {

		fieldNom.setEnabled( true );

		checkBoxMembre.setEnabled( true );

		table.clearSelection();
		btnSupprimer.setEnabled( false );
	}

	public void desactiverInfos() {
		fieldNom.setEnabled( false );
		checkBoxMembre.setEnabled( false );
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
		fieldNom.setText( "" );
		checkBoxMembre.setSelected( false );
		( (DefaultListModel<Album>) listeAlbums.getModel() ).removeAllElements();
		btnSupprimer.setEnabled( false );
	}

}
