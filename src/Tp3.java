import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class Tp3 implements ActionListener{
	
		

	public Tp3(){
	
// Definition de la fenetre
        
        JFrame uneFenetre = new JFrame( "Exemple avec JPanel" );
        uneFenetre.setBounds ( 100, 100, 500, 600 );
        uneFenetre.setLayout ( new FlowLayout() );  // Pour positionner correctement les composants
        
        // Definition d'un bouton
        
        JButton unBouton = new JButton ( "bouton" );
        unBouton.setBounds ( 400, 10, 85, 20 );
        unBouton.setFont ( new Font ( "Times", Font.ITALIC, 14 ) );
        
        // Definition d'un JTextField
        
        JTextField unChampTexte = new JTextField ( "Toto" );
        unChampTexte.setSize ( 300, 60 );
        unChampTexte.setFont ( new Font ( "Courier", Font.PLAIN, 48 ) );
        unChampTexte.setHorizontalAlignment ( JTextField.RIGHT ); // par rapport au JTextField

        
        // Definition d'un autre JTextField
        
        JTextField unChampTexteAutre = new JTextField ( "un champ de texte" );
        unChampTexteAutre.setSize ( 200, 20 );
        unChampTexteAutre.setLocation ( 400, 100 );
        unChampTexteAutre.setFont ( new Font ( "Times", Font.PLAIN, 24 ) );
        unChampTexteAutre.setHorizontalAlignment ( JTextField.LEFT ); // par rapport au JTextField

        
        // Definition d'un autre JLabel
        
        JLabel unLabelAutre = new JLabel ();
        unLabelAutre.setText ( "un JLabel" );
        unLabelAutre.setBounds ( 0, 200, 200, 50 );
        unLabelAutre.setFont ( new Font ( "Arial", Font.ITALIC, 24 ) );
        unLabelAutre.setHorizontalAlignment ( JLabel.CENTER );
        unLabelAutre.setVerticalAlignment ( JLabel.BOTTOM );
        
        // Definition d'un panneau JPanel
        
        JPanel unPanneau = new JPanel ();
        unPanneau.setLayout ( new BorderLayout() );
        
        unPanneau.setBorder ( BorderFactory.createLineBorder ( Color.BLUE ) );
        unPanneau.add ( new JLabel ( "Dans le panneau nord" ), BorderLayout.NORTH );
        unPanneau.add ( new JLabel ( "Dans le panneau sud" ), BorderLayout.SOUTH );
        unPanneau.add ( new JLabel ( "Dans le panneau est" ), BorderLayout.EAST );
        unPanneau.add ( new JLabel ( "Dans le panneau centre" ), BorderLayout.CENTER );
        unPanneau.add ( new JLabel ( "Dans le panneau ouest" ), BorderLayout.WEST );

        JPanel unPanneau2 = new JPanel ();
        unPanneau2.setBorder ( BorderFactory.createLoweredBevelBorder() );
        unPanneau2.add ( new JButton ( "coucou" ) );
        
        JPanel unPanneau3 = new JPanel ();
        unPanneau3.setBorder ( BorderFactory.createEtchedBorder() );
        unPanneau3.add ( new JLabel ( "coucou" ) );

        // Ajout des composants dans la fenetre
        
        Container contenuFenetre = uneFenetre.getContentPane();
        contenuFenetre.setBackground ( Color.orange );
        contenuFenetre.add ( unChampTexte );
        contenuFenetre.add ( unChampTexteAutre );
        contenuFenetre.add ( unLabelAutre );
        contenuFenetre.add ( unBouton );
        contenuFenetre.add ( unPanneau );
        contenuFenetre.add ( unPanneau2 );
        contenuFenetre.add ( unPanneau3 );
        
        // Affichage de la fenetre
        
        uneFenetre.setVisible ( true );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		JFrame fenetre = new JFrame("I n v e n t a i r e");
		fenetre.setBounds( 200, 200, 400, 600 );
		fenetre.setLayout (null);
		
		
		
		
		//	Menu deroulant 
		JComboBox menu = new JComboBox ();
		menu.addItem ( "Selectionnez un item" );
		menu.addItem ( "Choix 2" );
		
		//	Bouton afficher les infos
		JButton info = new JButton("Informations");
		info.setBounds(10, 10, 110, 20);
		
		//	Bouton ajouter
		JButton ajouter = new JButton("Ajouter");
		ajouter.setBounds(10, 40, 110, 20);
		
		//	Bouton retirer
		JButton retirer = new JButton("Retirer");
		retirer.setBounds(10, 70, 110, 20);
				
		//	Bouton rechercher 
		JButton rechercher = new JButton("Rechercher");
		rechercher.setBounds(10, 100, 110, 20);		
		
		//	Bouton eliminer 
		JButton eliminer = new JButton("Eliminer");
		eliminer.setBounds(10, 130, 110, 20);		
		
		//	Bouton nouveau 
		JButton nouveau = new JButton("Nouveau");
		nouveau.setBounds(10, 160, 110, 20);				
		
		//	Bouton contenu 
		JButton contenu = new JButton("Contenu");
		contenu.setBounds(10, 190, 110, 20);		
		
		//	Bouton enregistrer 
		JButton enregistrer = new JButton("Enregistrer");
		enregistrer.setBounds(10, 220, 110, 20);		
		
		//	Bouton quitter 
		JButton quitter = new JButton("Quitter");
		quitter.setBounds(10, 250, 110, 20);	
		
		
		//	Mise en page
		JPanel panneauHaut = new JPanel();
		panneauHaut.add(menu);
		panneauHaut.setLayout (null);
		
		JPanel panneauBas = new JPanel();
		panneauBas.add(info);
		panneauBas.add(ajouter);
		panneauBas.add(retirer);
		panneauBas.add(rechercher);
		panneauBas.add(eliminer);
		panneauBas.add(nouveau);
		panneauBas.add(contenu);
		panneauBas.add(enregistrer);
		panneauBas.add(quitter);
		panneauBas.setLayout (null);
		
		panneauBas.setVisible ( true );
		panneauHaut.setVisible ( true );

		//	Conteneur
		Container fenTp3 = fenetre.getContentPane();
		
		//fenTp3.add(panneauHaut);
		//fenTp3.add(panneauBas);
		
		fenTp3.add(menu);
		
			Ajouts boutons
		fenTp3.add(info);
		fenTp3.add(ajouter);
		fenTp3.add(retirer);
		fenTp3.add(rechercher);
		fenTp3.add(eliminer);
		fenTp3.add(nouveau);
		fenTp3.add(contenu);
		fenTp3.add(enregistrer);
		fenTp3.add(quitter);

		fenetre.setVisible ( true );
	*/
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main ( String[] args ) {
		new Tp3();
	}

}
