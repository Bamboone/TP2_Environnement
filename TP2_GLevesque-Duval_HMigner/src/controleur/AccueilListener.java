package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vues.*;

public class AccueilListener extends WindowAdapter implements ActionListener {

	private JButton valider;
	private JButton quitter;
	private JTextField nomUtilisateur;
	private JPasswordField mdp;
	private JFrame fenetre;

	public AccueilListener(JButton valider, JButton quitter, JTextField nomUtilisateur, JPasswordField mdp,
			JFrame fenetre) {
		this.valider = valider;
		this.quitter = quitter;
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.fenetre = fenetre;
	}
	
	private void quitter() {
		int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valider) {
			if (nomUtilisateur.getText().equals("administrateur")
					&& String.valueOf(mdp.getPassword()).equals("12345")) {
				VueChoixTraitements fenChoix = new VueChoixTraitements();
				fenetre.dispose();
				fenChoix.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Erreur les informations de connexion sont érronées",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == quitter) {
			quitter();

		}
	}
	
	@Override
	public void windowClosing(WindowEvent w) {
        quitter();
    }

}
