package vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Artistes extends JFrame implements ActionListener{
	private JTextField fieldRecherche;
	private JTable tableArtistes;
	
	public Artistes() {
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
		
		tableArtistes = new JTable();
		tableArtistes.setBounds(155, 11, 388, 237);
		panneauArtistes.add(tableArtistes);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblArtistes.setBounds(42, 11, 76, 40);
		panneauArtistes.add(lblArtistes);
		
		JPanel panneauImage = new JPanel();
		panneauImage.setBounds(10, 56, 135, 119);
		panneauArtistes.add(panneauImage);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnRemplacer.setBounds(10, 186, 135, 45);
		panneauArtistes.add(btnRemplacer);
	}

	@Override
	public void actionPerformed( ActionEvent arg0 ) {
		// TODO Auto-generated method stub
		
	}
}
