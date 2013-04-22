import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class Tp3 implements ActionListener{
	
		

	public Tp3(){
	
		// Definition de la fenetre
        JFrame fenetre = new JFrame("I n v e n t a i r e");
		fenetre.setBounds( 200, 200, 400, 600 );
		fenetre.setLayout ( new FlowLayout() );
		
		
		// Menu deroulant
		JComboBox menu = new JComboBox ();
		menu.addItem ( "Selectionnez un item" );
		menu.addItem ( "Choix 2" );
        
        
        // JPanel HAUT (avec le menu deroulant)
        JPanel panneauHaut = new JPanel ();
       // panneauHaut.setBorder ( BorderFactory.createLoweredBevelBorder() );
        panneauHaut.setSize ( 500, 60 );
        panneauHaut.add ( menu );
        
        
        // JPanel BAS (les boutons)
        JPanel panneauBas = new JPanel ();
      //  panneauBas.setBorder ( BorderFactory.createEtchedBorder() );
        panneauBas.setSize ( 300, 60 );
        panneauBas.add ( new JLabel ( "les boutons" ) );

        
        // Ajout des composants        
        Container contenuFenetre = fenetre.getContentPane();
        contenuFenetre.setBackground ( Color.blue );
        GridLayout disposition = new GridLayout(2, 1);  
        
        
        contenuFenetre.setLayout(disposition);
        contenuFenetre.add ( panneauHaut);
        contenuFenetre.add ( panneauBas);
        
        // Afficher la fenetre
        fenetre.setVisible ( true );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
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
