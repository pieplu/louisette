import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class Tp3 implements ActionListener{
	
		

	public Tp3(){
	
		// =====   LA FENETRE   =====
        JFrame fenetre = new JFrame("I n v e n t a i r e");
		fenetre.setBounds( 200, 200, 400, 600 );
		fenetre.setLayout ( new FlowLayout() );
		
		
		// =====   MENU DEROULANT   =====
		JComboBox menu = new JComboBox ();
		menu.addItem ( "Selectionnez un item" );
		menu.addItem ( "Choix 2" );
		
		
		//  =====   BOUTONS   =====
		
		JButton info = new JButton("Informations");
		info.setBounds(10, 10, 110, 20);
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.setBounds(10, 40, 110, 20);
		
		JButton retirer = new JButton("Retirer");
		retirer.setBounds(10, 70, 110, 20);
		
		JButton rechercher = new JButton("Rechercher");
		rechercher.setBounds(10, 100, 110, 20);	
		
		JButton eliminer = new JButton("Eliminer");
		eliminer.setBounds(10, 130, 110, 20);	
		
		JButton nouveau = new JButton("Nouveau");
		nouveau.setBounds(10, 160, 110, 20);
		
 		JButton contenu = new JButton("Contenu");
		contenu.setBounds(10, 190, 110, 20);				

		JButton enregistrer = new JButton("Enregistrer");
		enregistrer.setBounds(10, 220, 110, 20);		

		JButton quitter = new JButton("Quitter");
		quitter.setBounds(10, 250, 110, 20);
        
        
        // JPanel HAUT (avec le menu deroulant)
        JPanel panneauHaut = new JPanel ();
        panneauHaut.setBorder ( BorderFactory.createLoweredBevelBorder() );
        panneauHaut.setSize ( 400, 100 );
        // AJOUT
        panneauHaut.add ( menu );
        
        
        // JPanel BAS (les boutons)
        JPanel panneauBas = new JPanel ();
        panneauBas.setBorder ( BorderFactory.createEtchedBorder() );
        panneauBas.setSize ( 400, 500 );
        panneauBas.setLayout(null);
        // AJOUT
        panneauBas.add(info);
        panneauBas.add(ajouter);
        panneauBas.add(retirer);
        panneauBas.add(rechercher);
        panneauBas.add(eliminer);
        panneauBas.add(nouveau);
        panneauBas.add(contenu);
        panneauBas.add(enregistrer);
        panneauBas.add(quitter);
        
        	

        
        //  =====   CONTENEUR   =====       
        Container contenuFenetre = fenetre.getContentPane();
        contenuFenetre.setLayout(new BorderLayout());
        contenuFenetre.add ( panneauHaut , BorderLayout.NORTH);
        contenuFenetre.add ( panneauBas);
        
        // Afficher la fenetre
        fenetre.setVisible ( true );
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main ( String[] args ) {
		new Tp3();
	}

}
