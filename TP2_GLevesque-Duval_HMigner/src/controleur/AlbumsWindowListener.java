package controleur;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class AlbumsWindowListener extends WindowAdapter{
	
	public AlbumsWindowListener() {
		
	}

	@Override
	public void windowClosing( WindowEvent arg0 ) {
		int response = JOptionPane.showConfirmDialog( null, "Voulez-vous vraiment quitter?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
		if ( response == JOptionPane.YES_OPTION ) {
			ControleConnexion.fermerConnexion();
			System.exit( 0 );
		}
	}

}
