package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vues.*;

public class ChoixListener extends WindowAdapter implements ActionListener {
	
	private JButton btnArtistes;
	private JButton btnAlbums;
	private JButton btnQuitter;
	private JFrame fenetre;
	
	public ChoixListener(JButton art, JButton alb, JButton quit, JFrame fen) {
		btnArtistes = art;
		btnAlbums = alb;
		btnQuitter = quit;
		fenetre = fen;
	}
	
	private void quitter() {
		int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);

		}
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if(e.getSource() == btnArtistes) {
			ControleConnexion.connecter();
			VueArtistes fen = new VueArtistes();
			fenetre.dispose();
			fen.setVisible( true );
		}else if(e.getSource() == btnAlbums) {
			VueAlbums fen = new VueAlbums();
			fenetre.dispose();
			fen.setVisible( true );
		}else if(e.getSource() == btnQuitter) {
			quitter();
		}
	}
	
	@Override
	public void windowClosing(WindowEvent w) {
        quitter();
    }
}
