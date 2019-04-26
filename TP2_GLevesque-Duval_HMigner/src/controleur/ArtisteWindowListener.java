package controleur;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class ArtisteWindowListener extends WindowAdapter {
	
	public ArtisteWindowListener() {
		
	}

	@Override
	public void windowClosing( WindowEvent w ) {
		int response = JOptionPane.showConfirmDialog( null, "Voulez-vous vraiment quitter?", "Confirmation",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
		if ( response == JOptionPane.YES_OPTION ) {
			System.exit( 0 );

		}
	}

}
