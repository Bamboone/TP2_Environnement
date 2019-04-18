package vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import controleur.ChoixListener;

public class VueChoixTraitements extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton gestionArtistes;
	private JButton gestionAlbums;
	private JButton quitter;

	public VueChoixTraitements() {
		super("Menu");
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(10);
		setSize( 400, 200 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		
		JLabel lblMenuDeLapplication = new JLabel("Menu de l'application");
		lblMenuDeLapplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuDeLapplication.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(lblMenuDeLapplication, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 0, 5));
		
		gestionArtistes = new JButton("Gestion des Artistes");
		panel.add(gestionArtistes);
		
		gestionAlbums = new JButton("Gestion des Albums");
		panel.add(gestionAlbums);
		
		quitter = new JButton("Quitter");
		panel.add(quitter);
		
		ChoixListener gestionnaire = new ChoixListener(gestionArtistes, gestionAlbums, quitter, this);
		gestionArtistes.addActionListener( gestionnaire );
		gestionAlbums.addActionListener( gestionnaire );
		quitter.addActionListener( gestionnaire );
		
	}
	
	
}
