package vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ChoixTraitements extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton gestionArtistes;
	private JButton albums;
	private JButton quitter;

	public ChoixTraitements() {
		super("Menu");
		setSize( 400, 200 );
		
		JLabel lblMenuDeLapplication = new JLabel("Menu de l'application");
		lblMenuDeLapplication.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblMenuDeLapplication, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 5));
		
		gestionArtistes = new JButton("Gestion des Artistes");
		panel.add(gestionArtistes);
		
		albums = new JButton("Gestion des Albums");
		panel.add(albums);
		
		quitter = new JButton("Quitter");
		panel.add(quitter);
		
	}
	
	
}
