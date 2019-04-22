package controleur;

import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import gestionDonnees.Artiste;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleArtistes;

public class ArtisteBoutonListener implements ActionListener{
	
	private JButton btnRecherche;
	private ModeleArtistes modele;
	private GestionArtistes gestionnaire;
	private JTextField txtRecherche;
	private JButton btnRemplacer;
	private JTable table;
	private JLabel lblImage;

	public ArtisteBoutonListener(JButton btnRecherche, JButton btnRemplacer, JTextField txtRecherche, ModeleArtistes modele, JTable table, GestionArtistes gestionnaire, JLabel lblImage) {
		this.btnRecherche = btnRecherche;
		this.modele = modele;
		this.gestionnaire = gestionnaire;
		this.txtRecherche = txtRecherche;
		this.btnRemplacer = btnRemplacer;
		this.table = table;
		this.lblImage = lblImage;
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
					Image image = new ImageIcon(ArtisteBoutonListener.class.getResource( "../images/"+artiste.getPhoto() )).getImage().getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
					lblImage.setIcon(new ImageIcon(image));
					table.setRowSelectionInterval(indice, indice);
				}
			}
			
			
		}
		
	}

}
