package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import vues.Albums;
import vues.Artistes;

public class ChoixListener implements ActionListener {
	
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

	@Override
	public void actionPerformed( ActionEvent e ) {
		if(e.getSource() == btnArtistes) {
			Artistes fen = new Artistes();
			fenetre.dispose();
			fen.setVisible( true );
		}else if(e.getSource() == btnAlbums) {
			Albums fen = new Albums();
			fenetre.dispose();
			fen.setVisible( true );
		}else if(e.getSource() == btnQuitter) {
			System.exit( 0 );
		}
	}

}
