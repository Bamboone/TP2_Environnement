package controleur;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;

public class MenuListener implements ActionListener{

	private JMenuItem aide;
	
	public MenuListener(JMenuItem aide) {
		this.aide = aide;
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {

		if(e.getSource() == aide) {
			try {
				File fichier = new File( "c:aideEnLigne.chm" );
				Desktop.getDesktop().open( fichier );
			}catch(IOException ex) {
				System.out.println( ex.getMessage() );
			}
		}
		
		
	}

	
	
}
