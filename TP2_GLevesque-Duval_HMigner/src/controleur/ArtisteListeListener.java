package controleur;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gestionDonnees.Album;

public class ArtisteListeListener implements ListSelectionListener {

	private JList<Album> listeAlbums;
	private JLabel lblImage;

	public ArtisteListeListener( JList<Album> listeAlbums, JLabel lblImage ) {
		this.listeAlbums = listeAlbums;
		this.lblImage = lblImage;
	}

	@Override
	public void valueChanged( ListSelectionEvent e ) {

		if ( listeAlbums.getSelectedValue() != null ) {
			Image image = new ImageIcon( ArtisteMouseListener.class
					.getResource( "../images/" + listeAlbums.getSelectedValue().getCouverture() ) ).getImage()
							.getScaledInstance( 135, 119, Image.SCALE_SMOOTH );
			lblImage.setIcon( new ImageIcon( image ) );
		} else {
			lblImage.setIcon( null );
		}

	}

}
