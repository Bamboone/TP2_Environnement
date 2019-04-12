package vues;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Accueil extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField nomUtilisateur;
	private JTextField mdp;
	private JButton valider;
	private JButton quitter;

	public Accueil() {
		super("Connexion");
		setSize(400, 300);
		getContentPane().setLayout(null);
		JLabel titre = new JLabel("Connexion à l'application");
		titre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titre.setBounds(10, 0, 364, 72);
		getContentPane().add(titre);
		
		JLabel lblUtilisateur = new JLabel("Nom d'utilisateur");
		lblUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUtilisateur.setBounds(20, 83, 145, 23);
		getContentPane().add(lblUtilisateur);
		
		JLabel labelMdp = new JLabel("Mot de passe");
		labelMdp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelMdp.setBounds(20, 124, 145, 23);
		getContentPane().add(labelMdp);
		
		nomUtilisateur = new JTextField();
		nomUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nomUtilisateur.setBounds(125, 85, 145, 20);
		getContentPane().add(nomUtilisateur);
		nomUtilisateur.setColumns(10);
		
		mdp = new JTextField();
		mdp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mdp.setColumns(10);
		mdp.setBounds(125, 126, 145, 20);
		getContentPane().add(mdp);
		
		valider = new JButton("Valider");
		valider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		valider.setBounds(20, 188, 90, 25);
		getContentPane().add(valider);
		
		quitter = new JButton("Quitter");
		quitter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		quitter.setBounds(125, 188, 90, 25);
		getContentPane().add(quitter);
	}
	
	
}
