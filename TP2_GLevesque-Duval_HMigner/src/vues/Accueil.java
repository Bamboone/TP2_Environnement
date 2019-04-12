package vues;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Accueil extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField nomUtilisateur;
	private JPasswordField mdp;
	private JButton valider;
	private JButton quitter;

	public Accueil() {
		super( "Connexion" );
		setSize( 400, 300 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		getContentPane().setLayout( null );
		JLabel titre = new JLabel( "Connexion � l'application" );
		titre.setFont( new Font( "Tahoma", Font.PLAIN, 18 ) );
		titre.setBounds( 10, 0, 364, 72 );
		getContentPane().add( titre );

		JLabel lblUtilisateur = new JLabel( "Nom d'utilisateur" );
		lblUtilisateur.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		lblUtilisateur.setBounds( 20, 83, 145, 23 );
		getContentPane().add( lblUtilisateur );

		JLabel labelMdp = new JLabel( "Mot de passe" );
		labelMdp.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		labelMdp.setBounds( 20, 124, 145, 23 );
		getContentPane().add( labelMdp );

		nomUtilisateur = new JTextField();
		nomUtilisateur.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		nomUtilisateur.setBounds( 125, 85, 145, 20 );
		getContentPane().add( nomUtilisateur );
		nomUtilisateur.setColumns( 10 );

		mdp = new JPasswordField();
		mdp.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		mdp.setColumns( 10 );
		mdp.setBounds( 125, 126, 145, 20 );
		getContentPane().add( mdp );

		valider = new JButton( "Valider" );
		valider.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		valider.setBounds( 20, 188, 90, 25 );
		getContentPane().add( valider );

		quitter = new JButton( "Quitter" );
		quitter.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
		quitter.setBounds( 125, 188, 90, 25 );
		getContentPane().add( quitter );

		valider.addActionListener( this );
		quitter.addActionListener( this );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == valider ) {
			if ( nomUtilisateur.getText().equals( "administrateur" )
					&& String.valueOf( mdp.getPassword() ).equals( "12345" ) ) {
				ChoixTraitements fenChoix = new ChoixTraitements();
				this.dispose();
				fenChoix.setVisible( true );
			} else {
				JOptionPane.showMessageDialog( null, "Erreur les informations de connexion sont �rron�es",
						"Message d'erreur", JOptionPane.ERROR_MESSAGE );
			}
		} else if ( e.getSource() == quitter ) {
			System.exit( 0 );
		}

	}

}
