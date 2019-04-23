package vues;

import javax.swing.JFrame;
import controleur.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

public class VueAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nomUtilisateur;
	private JPasswordField mdp;
	private JButton valider;
	private JButton quitter;

	public VueAccueil() {
		super( "Connexion" );
		setSize( 400, 300 );
		setResizable( false );
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		setLocationRelativeTo( null );
		getContentPane().setLayout( null );
		JLabel titre = new JLabel( "Connexion à l'application" );
		titre.setFont( new Font( "Comic Sans MS", Font.PLAIN, 18 ) );
		titre.setBounds( 10, 0, 364, 72 );
		getContentPane().add( titre );

		JLabel lblUtilisateur = new JLabel( "Nom d'utilisateur" );
		lblUtilisateur.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		lblUtilisateur.setBounds( 20, 83, 145, 23 );
		getContentPane().add( lblUtilisateur );

		JLabel labelMdp = new JLabel( "Mot de passe" );
		labelMdp.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		labelMdp.setBounds( 20, 124, 145, 23 );
		getContentPane().add( labelMdp );

		nomUtilisateur = new JTextField();
		nomUtilisateur.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		nomUtilisateur.setBounds( 125, 85, 145, 20 );
		getContentPane().add( nomUtilisateur );
		nomUtilisateur.setColumns( 10 );

		mdp = new JPasswordField();
		mdp.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		mdp.setColumns( 10 );
		mdp.setBounds( 125, 126, 145, 20 );
		getContentPane().add( mdp );

		valider = new JButton( "Valider" );
		valider.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		valider.setBounds( 20, 188, 90, 25 );
		getContentPane().add( valider );

		quitter = new JButton( "Quitter" );
		quitter.setFont( new Font( "Comic Sans MS", Font.PLAIN, 12 ) );
		quitter.setBounds( 125, 188, 90, 25 );
		getContentPane().add( quitter );

		AccueilListener listener = new AccueilListener( valider, quitter, nomUtilisateur, mdp, this );
		valider.addActionListener( listener );
		quitter.addActionListener( listener );
		this.addWindowListener( listener );
	}

}
