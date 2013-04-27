
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;




public class Tp3 implements ActionListener{
	
	static ArrayList<ItemInventaire> itemList = new ArrayList<ItemInventaire>();
	
	  JFrame fenetre;
	  JComboBox menu;
	  JButton info;
	  JButton ajouter;
	  JButton retirer;
	  JButton rechercher;
	  JButton eliminer;
	  JButton nouveau;
	  JButton enregistrer;
	  JButton quitter;

	public Tp3() throws IOException{
		
		chargerItemDuFichierDansListe();
		
		// =====   LA FENETRE   =====
        fenetre = new JFrame("I n v e n t a i r e");
		fenetre.setBounds( 200, 200, 400, 600 );
		fenetre.setLayout ( new FlowLayout() );
		
		
		// =====   MENU DEROULANT   =====
		menu = new JComboBox ();
		menu.addActionListener(this);
		menu.addItem ( "Selectionnez un item" );
		
		for(int i = 0; i<itemList.size();i++){
			menu.addItem ( itemList.get(i).getDescription() );
		}
		
		
		//  =====   BOUTONS   =====
		
		 info = new JButton("Informations");
		info.setBounds(10, 10, 110, 20);
		info.addActionListener(this);
		
		 ajouter = new JButton("Ajouter");
		ajouter.setBounds(10, 40, 110, 20);
		ajouter.addActionListener(this);
		
		 retirer = new JButton("Retirer");
		retirer.setBounds(10, 70, 110, 20);
		retirer.addActionListener(this);
		
		 rechercher = new JButton("Rechercher");
		rechercher.setBounds(10, 100, 110, 20);	
		rechercher.addActionListener(this);
		
		 eliminer = new JButton("Eliminer");
		eliminer.setBounds(10, 130, 110, 20);
		eliminer.addActionListener(this);
		
		 nouveau = new JButton("Nouveau");
		nouveau.setBounds(10, 160, 110, 20);
		nouveau.addActionListener(this);
		

		
		 enregistrer = new JButton("Enregistrer");
		enregistrer.setBounds(10, 190, 110, 20);		
		enregistrer.addActionListener(this);
		
		 quitter = new JButton("Quitter");
		quitter.setBounds(10, 220, 110, 20);
		quitter.addActionListener(this);
		
        
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
	
	/**
	 * Charge le fichier texte de sauvergarde dans un BufferedReader
	 * @return BufferedReader
	 * @throws FileNotFoundException
	 */
	public static BufferedReader chargerFichierText() throws FileNotFoundException {
		BufferedReader listeTemporaire = null;
		try {
			String path = "inventaire.txt";
			listeTemporaire = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier de sauvegarde est absent.");
		}
		
		return listeTemporaire;
	}

	/**
	 * Chargement initial d'un objet BufferedReader contenant la liste d'item dans une ArrayList contenant tous les items
	 * @throws IOException
	 */
	public static void chargerItemDuFichierDansListe() throws IOException {
		BufferedReader inventaireSauvegarder = chargerFichierText();
		String itemTemp = "";

		int quantiter = 0;
		double prix = 0;
		String desc = "";

		while (!(itemTemp == null)) {
			itemTemp = inventaireSauvegarder.readLine();
			if (!(itemTemp == null)) {
				itemTemp = itemTemp.trim().replaceAll(" +", " ");
				int indice = 1;
				String tempQuantite = "";
				String tempPrix = "";
				for (int i = 0; i < itemTemp.length(); i++) {
					if (indice == 1) {
						if (itemTemp.substring(i, i + 1).equals(" ")) {
							indice = 2;
							quantiter = Integer.parseInt(tempQuantite);
						} else {
							tempQuantite += itemTemp.substring(i, i + 1);
						}
					} else if (indice == 2) {
						if (itemTemp.substring(i, i + 1).equals(" ")) {
							indice = 3;
							prix = Double.parseDouble(tempPrix);
						} else {
							tempPrix += itemTemp.substring(i, i + 1);
						}
					} else {
						desc += itemTemp.substring(i, i + 1);
					}
				}
				if (!(desc.equals("")) && !(desc.equals(" "))) {
					ItemInventaire item = new ItemInventaire(desc, prix,
							quantiter);
					itemList.add(item);
				}
				desc = "";
			}
		}
		inventaireSauvegarder.close();
	}

	/**
	 * retourne l'item descItem dans la liste d'item, retourne null si pas trouver
	 * @param descItem
	 * @return
	 */
	public static ItemInventaire trouverItemDansInventaire(String descItem) {
		ItemInventaire itemTrouver = null;
		
		for(int i = 0; i < itemList.size() ; i++){
			if(itemList.get(i).getDescription().equals(descItem)){
				itemTrouver = itemList.get(i);
			}
		}

		return itemTrouver;
	}

	/**
	 * ajoute un item contenant les descriptions dans la liste d'item si la description n'est pas déjà présente et si la description n'est pas vide ou null
	 * @param desc
	 * @param prix
	 * @param quantite
	 * @return true si ajouter, false sinon
	 */
	public static boolean ajouterUnItem(String desc, double prix, int quantite) {
		boolean estAjouter = true;
		if (!itemList.contains(trouverItemDansInventaire(desc)) && !(desc.equals("")) && !(desc.equals(" ")) && !(desc == null)) {
			if (prix <= 0 || quantite < 0) {
				estAjouter = false;
			} else {
				itemList.add(new ItemInventaire(desc, prix, quantite));
			}
		} else {
			estAjouter = false;
		}

		return estAjouter;
	}

	/**
	 * Augmente la quantite d'un item desc dans la liste d'inventaire si l'item est présent
	 * @param desc
	 * @param quantite
	 */
	public static void ajouterQuantiteItem(String desc, int quantite) {
		boolean itemPresent = false;
		
		for (int i = 0;i < itemList.size();i++){
			if(itemList.get(i).getDescription().equals(desc)){
				itemPresent = true;
			}
		}
		
		if (!(quantite <= 0) && itemPresent == true) {
			trouverItemDansInventaire(desc).setQuantite(
					quantite + trouverItemDansInventaire(desc).getQuantite());
		}
	}

	/**
	 * Retire la quantite d'un item desc dans la liste d'inventaire si l'item est présent et le met a 0 si la quantite serait negative
	 * @param desc
	 * @param quantite
	 */
	public static void retirerQuantiteItem(String desc, int quantite) {
		
		boolean itemPresent = false;
		
		for (int i = 0;i < itemList.size();i++){
			if(itemList.get(i).getDescription().equals(desc)){
				itemPresent = true;
			}
		}
		
		if (!(quantite <= 0) && itemPresent == true) {
			if ((trouverItemDansInventaire(desc).getQuantite() - quantite) < 0) {
				trouverItemDansInventaire(desc).setQuantite(0);
			} else {
				trouverItemDansInventaire(desc)
						.setQuantite(
								quantite
										- trouverItemDansInventaire(desc)
												.getQuantite());
			}
		}
	}

	/**
	 * retire l'item desc de la liste d'item si present
	 * @param desc
	 */
	public static void retirerItem(String desc) {
		if (itemList.contains(trouverItemDansInventaire(desc))) {
			itemList.remove (trouverItemDansInventaire(desc));
		}
	}
	
	/**
	 * enregistre la liste d'item dans le fichier texte de sauvegarde
	 * @throws IOException
	 */
	public static void enregistrerInventaire () throws IOException{
		FileWriter f = new FileWriter ("inventaireSave.txt");
		PrintWriter sortie = new PrintWriter ( f );
		for(int i = 0;i < itemList.size();i++){
			String itemASauvegarderDansFichier = itemList.get(i).getQuantite() + "" + itemList.get(i).getPrix() + "" + itemList.get(i).getDescription();
			sortie.println(itemASauvegarderDansFichier);
		}
		sortie.close();
		
	}

	public static String monterUnItem(ItemInventaire item){
		return "Description : " + item.getDescription() + "\nPrix : " + item.getPrix () + "\nQuantité : " + item.getQuantite();
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		
		Object source = event.getSource();
		
		if(source == info){
			if (!menu.getSelectedItem().equals("Selectionnez un item"))
			JOptionPane.showMessageDialog(null, monterUnItem(trouverItemDansInventaire((String)menu.getSelectedItem())) , "Informations" , JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(source == ajouter){
			if (!menu.getSelectedItem().equals("Selectionnez un item")){
				ItemInventaire itemSelect = trouverItemDansInventaire((String)menu.getSelectedItem());
				String quantite = JOptionPane.showInputDialog(null, monterUnItem(itemSelect) + "\nQuantité à ajouté : \n" , "Saisie" , JOptionPane.INFORMATION_MESSAGE);
				
				int quant;
				
				if (!(quantite == null)){
					try{
						quant = Integer.parseInt(quantite);
						if(quant <= 0){
							JOptionPane.showMessageDialog(null, "Quantité négative ou null" , "Erreur" , JOptionPane.ERROR_MESSAGE);
						}else{
							ajouterQuantiteItem( itemSelect.getDescription(), quant);
						}
					}catch (NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Quantité non-numérique" , "Erreur" , JOptionPane.ERROR_MESSAGE);
					}
					
				}

			}else{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un item" , "Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(source == retirer){
			if (!menu.getSelectedItem().equals("Selectionnez un item")){
				ItemInventaire itemSelect = trouverItemDansInventaire((String)menu.getSelectedItem());
				String quantite = JOptionPane.showInputDialog(null, monterUnItem(itemSelect) + "\nQuantité à retiré : \n" , "Saisie" , JOptionPane.INFORMATION_MESSAGE);
				
				int quant;
				
				if (!(quantite == null)){
					try{
						quant = Integer.parseInt(quantite);
						if(quant <= 0){
							JOptionPane.showMessageDialog(null, "Quantité négative ou null" , "Erreur" , JOptionPane.ERROR_MESSAGE);
						} else if (itemSelect.getQuantite() < quant){
							JOptionPane.showMessageDialog(null, "Quantité insufisante en inventaire" , "Erreur" , JOptionPane.ERROR_MESSAGE);
						}else{
							retirerQuantiteItem( itemSelect.getDescription(), quant);
						}
					}catch (NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Quantité non-numérique" , "Erreur" , JOptionPane.ERROR_MESSAGE);
					}
					
				}

			}else{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un item" , "Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(source == rechercher){
			
				
				String recherche = JOptionPane.showInputDialog(null,"\nChaine à recherché : \n" , "Saisie" , JOptionPane.INFORMATION_MESSAGE);
				ItemInventaire itemSelect = trouverItemDansInventaire(recherche);
				
				if(itemSelect == null){
					JOptionPane.showMessageDialog(null, "Aucun item ne contient la chaîne " + recherche , "Résultat de la recherche de \"" + recherche +"\"" , JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Aucun item ne contient la chaîne " + recherche , "Résultat de la recherche de \"" + recherche +"\"" , JOptionPane.INFORMATION_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "Aucun item ne contient la chaîne " + recherche , "Résultat de la recherche de \"" + recherche +"\"" , JOptionPane.INFORMATION_MESSAGE);

		}
		
		if(source == eliminer){
			if (!menu.getSelectedItem().equals("Selectionnez un item")){
				ItemInventaire itemSelect = trouverItemDansInventaire((String)menu.getSelectedItem());
				
				JOptionPane.showMessageDialog(null, "êtes vous sûr de vouloir éliminer totalement \"" + itemSelect +"\" ?", "Saisie" , JOptionPane.WARNING_MESSAGE);

				
				int quant = 0;
				
				

						if(quant <= 0){
							JOptionPane.showMessageDialog(null, "Quantité négative ou null" , "Erreur" , JOptionPane.ERROR_MESSAGE);
						} else if (itemSelect.getQuantite() < quant){
							JOptionPane.showMessageDialog(null, "Quantité insufisante en inventaire" , "Erreur" , JOptionPane.ERROR_MESSAGE);
						}else{
							retirerQuantiteItem( itemSelect.getDescription(), quant);
						}
			

			}else{
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un item" , "Erreur" , JOptionPane.ERROR_MESSAGE);
			}
			
			
	}
		
	}
	
	
	public static void main ( String[] args ) throws IOException {
		new Tp3();
	}

}
