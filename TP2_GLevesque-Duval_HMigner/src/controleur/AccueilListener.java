package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vues.ChoixTraitements;

public class AccueilListener implements ActionListener {

	private JButton valider;
	private JButton quitter;
	private JTextField nomUtilisateur;
	private JPasswordField mdp;
	private JFrame fenetre;

	public AccueilListener( JButton valider, JButton quitter, JTextField nomUtilisateur, JPasswordField mdp,
			JFrame fenetre ) {
		this.valider = valider;
		this.quitter = quitter;
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == valider ) {
			if ( nomUtilisateur.getText().equals( "administrateur" )
					&& String.valueOf( mdp.getPassword() ).equals( "12345" ) ) {
				ChoixTraitements fenChoix = new ChoixTraitements();
				fenetre.dispose();
				fenChoix.setVisible( true );
			} else {
				JOptionPane.showMessageDialog( null, "Erreur les informations de connexion sont érronées",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE );
			}
		} else if ( e.getSource() == quitter ) {
			System.exit( 0 );
		}
	}

}
