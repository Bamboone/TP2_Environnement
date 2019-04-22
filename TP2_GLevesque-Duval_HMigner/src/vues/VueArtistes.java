package vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controleur.ArtisteBoutonListener;
import controleur.ArtisteMouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import gestionDonnees.Album;
import gestionDonnees.GestionArtistes;
import gestionDonnees.ModeleArtistes;
import renderers.RendererGras;
import renderers.RendererMembre;

import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JList;

public class VueArtistes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField fieldRecherche;
	private JTable tableArtistes;
	private JTextField fieldNumero;
	private JTextField fieldNom;
	
	public VueArtistes() {
		super("Gestion des artistes");
		setSize(800, 600);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panneauRecherche = new JPanel();
		panneauRecherche.setBounds(10, 11, 764, 85);
		getContentPane().add(panneauRecherche);
		panneauRecherche.setLayout(null);
		
		JLabel lblRecherche = new JLabel("Rechercher un artiste");
		lblRecherche.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblRecherche.setBounds(10, 11, 172, 31);
		panneauRecherche.add(lblRecherche);
		
		fieldRecherche = new JTextField();
		fieldRecherche.setBounds(10, 43, 382, 31);
		panneauRecherche.add(fieldRecherche);
		fieldRecherche.setColumns(10);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRecherche.setBounds(396, 45, 126, 29);
		panneauRecherche.add(btnRecherche);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(628, 45, 126, 29);
		panneauRecherche.add(btnQuitter);
		btnQuitter.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		JPanel panneauArtistes = new JPanel();
		panneauArtistes.setBounds(10, 97, 764, 259);
		getContentPane().add(panneauArtistes);
		panneauArtistes.setLayout(null);
		
		GestionArtistes gestionnaire = new GestionArtistes();
		ModeleArtistes modele = new ModeleArtistes(gestionnaire.getListeArtistes());
		tableArtistes = new JTable(modele);
		tableArtistes.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		tableArtistes.getColumnModel().getColumn(0).setCellRenderer(new RendererGras());
		tableArtistes.getColumnModel().getColumn(2).setCellRenderer(new RendererMembre());
		JScrollPane scroll = new JScrollPane(tableArtistes);
		scroll.setBounds(155, 11, 388, 237);
		panneauArtistes.add(scroll);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		lblArtistes.setBounds(24, 5, 112, 52);
		panneauArtistes.add(lblArtistes);
		
		JLabel imageArtiste = new JLabel();
		imageArtiste.setBounds(10, 56, 135, 119);
		panneauArtistes.add(imageArtiste);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRemplacer.setBounds(10, 197, 135, 45);
		panneauArtistes.add(btnRemplacer);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnNouveau.setBounds(592, 12, 135, 40);
		panneauArtistes.add(btnNouveau);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnAjouter.setBounds(592, 73, 135, 40);
		panneauArtistes.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnModifier.setBounds(592, 135, 135, 40);
		panneauArtistes.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSupprimer.setBounds(592, 199, 135, 40);
		panneauArtistes.add(btnSupprimer);
		
		JPanel panneauInfos = new JPanel();
		panneauInfos.setBounds(10, 359, 764, 201);
		getContentPane().add(panneauInfos);
		panneauInfos.setLayout(null);
		
		JLabel lblInfos = new JLabel("Informations");
		lblInfos.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		lblInfos.setBounds(12, 2, 162, 52);
		panneauInfos.add(lblInfos);
		
		JLabel lblNumero = new JLabel("Num\u00E9ro");
		lblNumero.setBounds(12, 65, 57, 31);
		panneauInfos.add(lblNumero);
		lblNumero.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNom.setBounds(12, 99, 57, 31);
		panneauInfos.add(lblNom);
		
		JLabel lblMembre = new JLabel("Membre");
		lblMembre.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblMembre.setBounds(12, 136, 57, 31);
		panneauInfos.add(lblMembre);
		
		fieldNumero = new JTextField();
		fieldNumero.setBounds(75, 72, 237, 20);
		panneauInfos.add(fieldNumero);
		fieldNumero.setColumns(10);
		
		fieldNom = new JTextField();
		fieldNom.setColumns(10);
		fieldNom.setBounds(75, 106, 237, 20);
		panneauInfos.add(fieldNom);
		
		JCheckBox checkBoxMembre = new JCheckBox("");
		checkBoxMembre.setSize(new Dimension(200, 200));
		checkBoxMembre.setBounds(75, 142, 21, 23);
		panneauInfos.add(checkBoxMembre);
		
		JList<Album> listeAlbums = new JList<Album>();
		listeAlbums.setBounds(334, 27, 208, 153);
		panneauInfos.add(listeAlbums);
		
		JPanel panneauImageAlbum = new JPanel();
		panneauImageAlbum.setBounds(591, 37, 135, 119);
		panneauInfos.add(panneauImageAlbum);
		
		tableArtistes.addMouseListener(new ArtisteMouseListener(tableArtistes, fieldNumero, fieldNom, checkBoxMembre, listeAlbums, modele, imageArtiste));
		ArtisteBoutonListener boutonListener = new ArtisteBoutonListener(btnRecherche, btnRemplacer, fieldRecherche, modele, tableArtistes, gestionnaire, imageArtiste);
		btnRecherche.addActionListener(boutonListener);
		btnRemplacer.addActionListener(boutonListener);
	}
}
