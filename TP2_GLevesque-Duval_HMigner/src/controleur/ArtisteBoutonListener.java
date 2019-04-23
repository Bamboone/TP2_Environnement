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

public class ArtisteBoutonListener implements ActionListener{
	
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

	public ArtisteBoutonListener(JButton btnRecherche, JButton btnRemplacer, JButton btnModifier, JButton btnSupprimer, 
								JButton btnNouveau, JButton btnAjouter, JButton btnQuitter, JTextField txtRecherche, 
								ModeleArtistes modele, JTable table, GestionArtistes gestionnaire, JLabel lblImage, 
								JTextField fieldNumero, JTextField fieldNom, JCheckBox checkBoxMembre,
								JList<Album> listeAlbums, JFrame fenetre) {
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRecherche) {
			
			modele.setDonnees(gestionnaire.rechercheArtiste(txtRecherche.getText()));
			modele.fireTableDataChanged();
		}else if(e.getSource() == btnRemplacer) {
			if(!table.getSelectionModel().isSelectionEmpty()) {
				JFileChooser choixFichier = new JFileChooser(System.getProperty("user.dir")+"\\src\\images");
				if ( choixFichier.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ) {
					int indice = table.getSelectedRow();
					Artiste artiste = modele.getElement(indice);
					File f = choixFichier.getSelectedFile();
					artiste.setPhoto(f.getName());
					gestionnaire.modifierPhoto(artiste.getPhoto(), artiste.getId());
					modele.modifierArtiste(indice, artiste);
					modele.fireTableDataChanged();
					Image image;
					image = new ImageIcon(ArtisteBoutonListener.class.getResource( "../images/" + artiste.getPhoto() ) ).getImage().getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
					lblImage.setIcon(new ImageIcon(image));
					table.setRowSelectionInterval(indice, indice);
				}
			}
		}else if(e.getSource() == btnQuitter) {
			int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				VueChoixTraitements accueil = new VueChoixTraitements();
				fenetre.dispose();
				accueil.setVisible( true );
			}
		}else if(e.getSource() == btnNouveau) {
			if(!fieldNom.isEnabled()) {
				toggleInfos();
			}
			fieldNom.setText( "" );
			checkBoxMembre.setSelected( false );
			fieldNumero.setText( Integer.toString(modele.getRowCount() + 1) );
			DefaultListModel listModel = (DefaultListModel) listeAlbums.getModel();
			listModel.removeAllElements();
			lblImage.setIcon(null);
		}else if(e.getSource() == btnAjouter) {
			Artiste artiste = new Artiste(Integer.parseInt( fieldNumero.getText() ), fieldNom.getText(), checkBoxMembre.isSelected(), "default.png");
			modele.ajouterDonnee( artiste );
			modele.fireTableDataChanged();
			gestionnaire.ajouterArtiste( artiste );
		}
		
	}
	
	public void toggleInfos() {
		if(fieldNom.isEnabled()) {
			fieldNom.setEnabled( false );
		}else if(!fieldNom.isEnabled()) {
			fieldNom.setEnabled( true );
		}
		
		if(checkBoxMembre.isEnabled()) {
			checkBoxMembre.setEnabled( false );
		}else if(!checkBoxMembre.isEnabled()) {
			checkBoxMembre.setEnabled( true );
		}
	}

}
