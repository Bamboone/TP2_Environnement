package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;

import gestionDonnees.Artiste;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleArtistes;
import vues.Application;

public class ArtisteBoutonListener implements ActionListener{
	
	private JButton btnRecherche;
	private ModeleArtistes modele;
	private GestionArtistes gestionnaire;
	private JTextField txtRecherche;
	private JButton btnRemplacer;
	private JTable table;

	public ArtisteBoutonListener(JButton btnRecherche, JButton btnRemplacer, JTextField txtRecherche, ModeleArtistes modele, JTable table, GestionArtistes gestionnaire) {
		this.btnRecherche = btnRecherche;
		this.modele = modele;
		this.gestionnaire = gestionnaire;
		this.txtRecherche = txtRecherche;
		this.btnRemplacer = btnRemplacer;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRecherche) {
			
			modele.setDonnees(gestionnaire.rechercheArtiste(txtRecherche.getText()));
			modele.fireTableDataChanged();
		}
		
	}

}
