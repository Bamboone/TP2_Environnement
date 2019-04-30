package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controleur.AlbumsBoutonListener;
import controleur.AlbumsMouseListener;
import controleur.AlbumsWindowListener;
import controleur.MenuListener;
import gestionDonnees.Artiste;
import gestionDonnees.GestionAlbums;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleAlbums;
import renderers.RendererGras;
import javax.swing.JList;

public class VueAlbums extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenu menuAide;
	private JTextField fieldRecherche;
	private JTextField fieldNumero;
	private JTextField fieldTitre;
	private JTextField fieldDate;
	private JTextField fieldGenre;
	private JTextField fieldArtiste;
	private DefaultListModel<Artiste> donnees = new DefaultListModel<>();

	public VueAlbums() {
		super( "Gestion des albums" );
		setSize( 800, 600 );
		getContentPane().setLayout( null );
		setResizable( false );
		setLocationRelativeTo( null );

		JMenuItem aide = new JMenuItem( "Aide en ligne" );
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds( 0, 0, 794, 21 );
		getContentPane().add( menuBar );
		menuAide = new JMenu( "Aide" );
		menuAide.add( aide );
		menuBar.add( menuAide );
		aide.addActionListener( new MenuListener( aide ) );

		JPanel panneauRecherche = new JPanel();
		panneauRecherche.setBounds( 10, 11, 764, 85 );
		getContentPane().add( panneauRecherche );
		panneauRecherche.setLayout( null );

		JLabel lblRecherche = new JLabel( "Rechercher un album" );
		lblRecherche.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		lblRecherche.setBounds( 10, 11, 172, 31 );
		panneauRecherche.add( lblRecherche );

		fieldRecherche = new JTextField();
		fieldRecherche.setBounds( 10, 43, 382, 31 );
		panneauRecherche.add( fieldRecherche );
		fieldRecherche.setColumns( 10 );

		JButton btnRecherche = new JButton( "Recherche" );
		btnRecherche.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnRecherche.setBounds( 396, 45, 126, 29 );
		panneauRecherche.add( btnRecherche );

		JButton btnQuitter = new JButton( "Quitter" );
		btnQuitter.setBounds( 628, 45, 126, 29 );
		panneauRecherche.add( btnQuitter );
		btnQuitter.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );

		JPanel panneauAlbums = new JPanel();
		panneauAlbums.setBounds( 10, 97, 764, 259 );
		getContentPane().add( panneauAlbums );
		panneauAlbums.setLayout( null );

		GestionAlbums gestionnaire = new GestionAlbums();
		ModeleAlbums modele = new ModeleAlbums( gestionnaire.getListeAlbumsArtiste() );
		JTable tableAlbums = new JTable( modele );
		tableAlbums.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		tableAlbums.getColumnModel().getColumn( 0 ).setCellRenderer( new RendererGras() );
		JScrollPane scroll = new JScrollPane( tableAlbums );
		scroll.setBounds( 155, 11, 388, 237 );
		panneauAlbums.add( scroll );

		JLabel lblAlbums = new JLabel( "Albums" );
		lblAlbums.setFont( new Font( "Comic Sans MS", Font.BOLD, 26 ) );
		lblAlbums.setBounds( 24, 5, 112, 52 );
		panneauAlbums.add( lblAlbums );

		JLabel imageAlbum = new JLabel();
		imageAlbum.setBounds( 10, 56, 135, 119 );
		panneauAlbums.add( imageAlbum );

		JButton btnRemplacer = new JButton( "Remplacer" );
		btnRemplacer.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnRemplacer.setBounds( 10, 197, 135, 45 );
		btnRemplacer.setEnabled( false );
		panneauAlbums.add( btnRemplacer );

		JButton btnNouveau = new JButton( "Nouveau" );
		btnNouveau.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnNouveau.setBounds( 592, 12, 135, 40 );
		panneauAlbums.add( btnNouveau );

		JButton btnAjouter = new JButton( "Ajouter" );
		btnAjouter.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnAjouter.setBounds( 592, 73, 135, 40 );
		btnAjouter.setEnabled( false );
		panneauAlbums.add( btnAjouter );

		JButton btnModifier = new JButton( "Modifier" );
		btnModifier.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnModifier.setBounds( 592, 135, 135, 40 );
		btnModifier.setEnabled( false );
		panneauAlbums.add( btnModifier );

		JButton btnSupprimer = new JButton( "Supprimer" );
		btnSupprimer.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		btnSupprimer.setBounds( 592, 199, 135, 40 );
		btnSupprimer.setEnabled( false );
		panneauAlbums.add( btnSupprimer );

		JPanel panneauInfos = new JPanel();
		panneauInfos.setBounds( 10, 359, 764, 201 );
		getContentPane().add( panneauInfos );
		panneauInfos.setLayout( null );

		JLabel lblInfos = new JLabel( "Informations" );
		lblInfos.setFont( new Font( "Comic Sans MS", Font.BOLD, 26 ) );
		lblInfos.setBounds( 12, 2, 162, 52 );
		panneauInfos.add( lblInfos );

		JLabel lblNumero = new JLabel( "Num\u00E9ro" );
		lblNumero.setBounds( 12, 65, 57, 31 );
		panneauInfos.add( lblNumero );
		lblNumero.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );

		JLabel lblTitre = new JLabel( "Titre" );
		lblTitre.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		lblTitre.setBounds( 12, 99, 57, 31 );
		panneauInfos.add( lblTitre );

		fieldNumero = new JTextField();
		fieldNumero.setEnabled( false );
		fieldNumero.setBounds( 75, 72, 237, 20 );
		panneauInfos.add( fieldNumero );
		fieldNumero.setColumns( 10 );

		fieldTitre = new JTextField();
		fieldTitre.setEnabled( false );
		fieldTitre.setColumns( 10 );
		fieldTitre.setBounds( 75, 106, 237, 20 );
		panneauInfos.add( fieldTitre );

		JLabel labelDate = new JLabel( "Date de sortie" );
		labelDate.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		labelDate.setBounds( 12, 139, 107, 31 );
		panneauInfos.add( labelDate );

		fieldDate = new JTextField( "AAAA" );
		fieldDate.setForeground( Color.GRAY );
		fieldDate.setEnabled( false );
		fieldDate.addFocusListener( new FocusListener() {
			@Override
			public void focusGained( FocusEvent e ) {
				if ( fieldDate.getText().equals( "AAAA" ) ) {
					fieldDate.setText( "" );
					fieldDate.setForeground( Color.BLACK );
				}
			}

			@Override
			public void focusLost( FocusEvent e ) {
				if ( fieldDate.getText().isEmpty() ) {
					fieldDate.setForeground( Color.GRAY );
					fieldDate.setText( "AAAA" );
				}
			}
		} );
		fieldDate.setBounds( 129, 146, 183, 20 );
		panneauInfos.add( fieldDate );

		JLabel labelGenre = new JLabel( "Genre" );
		labelGenre.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		labelGenre.setBounds( 365, 65, 57, 31 );
		panneauInfos.add( labelGenre );

		fieldGenre = new JTextField();
		fieldGenre.setEnabled( false );
		fieldGenre.setColumns( 10 );
		fieldGenre.setBounds( 421, 72, 237, 20 );
		panneauInfos.add( fieldGenre );

		GestionArtistes gestion = new GestionArtistes();
		for ( Artiste artiste : gestion.getListeArtistes() ) {
			donnees.addElement( artiste );
		}
		JList<Artiste> listeArtistes = new JList<Artiste>();
		listeArtistes.setModel( donnees );
		listeArtistes.setEnabled( false );
		JScrollPane scrollArtiste = new JScrollPane( listeArtistes );
		scrollArtiste.setBounds( 421, 108, 237, 65 );
		panneauInfos.add( scrollArtiste );

		JLabel labelArtistes = new JLabel( "Artistes" );
		labelArtistes.setFont( new Font( "Comic Sans MS", Font.BOLD, 14 ) );
		labelArtistes.setBounds( 356, 110, 57, 31 );
		panneauInfos.add( labelArtistes );

		fieldArtiste = new JTextField();
		fieldArtiste.setEnabled( false );
		fieldArtiste.setColumns( 10 );
		fieldArtiste.setBounds( 421, 181, 237, 20 );
		panneauInfos.add( fieldArtiste );

		AlbumsMouseListener mouseListener = new AlbumsMouseListener( tableAlbums, fieldNumero, fieldTitre, fieldGenre,
				fieldDate, fieldArtiste, modele, imageAlbum, listeArtistes, btnAjouter, btnModifier, btnRemplacer,
				btnSupprimer );
		
		tableAlbums.addMouseListener( mouseListener );
		listeArtistes.addMouseListener( mouseListener );
		
		addWindowListener( new AlbumsWindowListener() );
		
		AlbumsBoutonListener boutonListener = new AlbumsBoutonListener( btnRecherche, btnRemplacer, btnModifier,
				btnSupprimer, btnNouveau, btnAjouter, btnQuitter, fieldRecherche, modele, tableAlbums, gestionnaire,
				imageAlbum, fieldNumero, fieldTitre, listeArtistes, this, fieldGenre, fieldDate, fieldArtiste );
		
		btnRecherche.addActionListener( boutonListener );
		btnRemplacer.addActionListener( boutonListener );
		btnNouveau.addActionListener( boutonListener );
		btnQuitter.addActionListener( boutonListener );
		btnAjouter.addActionListener( boutonListener );
		btnSupprimer.addActionListener( boutonListener );
		btnModifier.addActionListener( boutonListener );
	}
}
