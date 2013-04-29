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

public class Tp3 implements ActionListener {

	static ArrayList<ItemInventaire> itemList = new ArrayList<ItemInventaire>();

	JFrame fenetre;
	JComboBox<String> menu;
	JButton info;
	JButton ajouter;
	JButton retirer;
	JButton rechercher;
	JButton eliminer;
	JButton nouveau;
	JButton contenu;
	JButton enregistrer;
	JButton quitter;

	private Boolean estModifie;

	private static final int QUANTITE_NON_NUM = 1;
	private static final int QUANTITE_NEG = 2;
	private static final int QUANTITE_INSSUFISANTE = 3;
	private static final int PRIX_NON_NUM = 4;
	private static final int PRIX_NEGATIF = 5;
	private static final int SELECTIONNER_ITEM = 6;
	private static final int ITEM_PRESENT = 7;


	public Tp3() throws IOException {

		chargerItemDuFichierDansListe();
		estModifie = false;

		// ===== LA FENETRE =====
		fenetre = new JFrame("I n v e n t a i r e");
		fenetre.setBounds(200, 200, 400, 600);
		fenetre.setLayout(new FlowLayout());

		// ===== MENU DEROULANT =====
		menu = new JComboBox<String>();
		menu.addActionListener(this);
		menu.addItem("Selectionnez un item");
		for (int i = 0; i < itemList.size(); i++)
			menu.addItem(itemList.get(i).getDescription());

		// ===== BOUTONS =====

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

		contenu = new JButton("Contenu");
		contenu.setBounds(10, 190, 110, 20);
		contenu.addActionListener(this);

		enregistrer = new JButton("Enregistrer");
		enregistrer.setBounds(10, 220, 110, 20);
		enregistrer.addActionListener(this);

		quitter = new JButton("Quitter");
		quitter.setBounds(10, 250, 110, 20);
		quitter.addActionListener(this);

		// ===== JPanel HAUT (avec le menu deroulant) =====
		JPanel panneauHaut = new JPanel();
		panneauHaut.setBorder(BorderFactory.createLoweredBevelBorder());
		panneauHaut.setSize(400, 100);
		panneauHaut.add(menu);

		// ===== JPanel BAS (les boutons) =====
		JPanel panneauBas = new JPanel();
		panneauBas.setBorder(BorderFactory.createEtchedBorder());
		panneauBas.setSize(400, 500);
		panneauBas.setLayout(null);
		panneauBas.add(info);
		panneauBas.add(ajouter);
		panneauBas.add(retirer);
		panneauBas.add(rechercher);
		panneauBas.add(eliminer);
		panneauBas.add(nouveau);
		panneauBas.add(contenu);
		panneauBas.add(enregistrer);
		panneauBas.add(quitter);

		// ===== CONTENEUR =====
		Container contenuFenetre = fenetre.getContentPane();
		contenuFenetre.setLayout(new BorderLayout());
		contenuFenetre.add(panneauHaut, BorderLayout.NORTH);
		contenuFenetre.add(panneauBas);

		// Afficher la fenetre
		fenetre.setVisible(true);
	}


	/**
	 * Charge le fichier texte de sauvergarde dans un BufferedReader
	 * 
	 * @return listeTemporaire
	 * @throws FileNotFoundException
	 */
	private static BufferedReader chargerFichierText() throws FileNotFoundException {
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
	 * Chargement initial d'un objet BufferedReader contenant la liste d'item
	 * dans une ArrayList contenant tous les items
	 * 
	 * @throws IOException
	 */
	private static void chargerItemDuFichierDansListe() throws IOException {
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
					ItemInventaire item = new ItemInventaire(desc, prix, quantiter);
					itemList.add(item);
				}
				desc = "";
			}
		}
		inventaireSauvegarder.close();
	}


	/**
	 * Enregistre la liste d'item dans le fichier texte de sauvegarde
	 * 
	 * @throws IOException
	 */
	private static void enregistrerInventaire() throws IOException {
		FileWriter f = new FileWriter("inventaireSave.txt");
		PrintWriter sortie = new PrintWriter(f);
		for (int i = 0; i < itemList.size(); i++) {
			String itemASauvegarderDansFichier = itemList.get(i).getQuantite() + " "
					+ itemList.get(i).getPrix() + " " + itemList.get(i).getDescription();
			sortie.println(itemASauvegarderDansFichier);
		}
		sortie.close();

	}


	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source == info)
			actionInfo();

		if (source == ajouter)
			actionAjouter();

		if (source == retirer)
			actionRetirer();

		if (source == rechercher)
			actionRechercher();

		if (source == eliminer)
			actionEliminer();

		if (source == contenu)
			for (int i = 0; i < itemList.size(); i++)
				System.out.println(itemList.get(i));

		if (source == enregistrer)
			actionEnregistrer();

		if (source == nouveau)
			actionNouveau();

		if (source == quitter)
			actionQuitter();

	}


	/**
	 * Methode qui regroupe les messages d'erreurs
	 * @param choix type du message d'erreur
	 */
	private void msgErreur(int choix) {
		switch (choix) {
		case (QUANTITE_NON_NUM):
			JOptionPane.showMessageDialog(null, "Quantite non-numerique", "Erreur", 
					JOptionPane.ERROR_MESSAGE);
			break;
		case (QUANTITE_NEG):
			JOptionPane.showMessageDialog(null, "Quantite negative ou null", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			break;
		case (QUANTITE_INSSUFISANTE):
			JOptionPane.showMessageDialog(null, "Quantite insufisante en inventaire", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			break;
		case (PRIX_NON_NUM):
			JOptionPane.showMessageDialog(null, "Prix non-numerique", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case (PRIX_NEGATIF):
			JOptionPane.showMessageDialog(null, "Prix negatif ou null", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case (SELECTIONNER_ITEM):
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un item", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			break;
		case (ITEM_PRESENT):
			JOptionPane.showMessageDialog(null, "L'item est deja dans l'inventaire", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			break;
	
		}
	
	}


	/**
	 * Methode d'action pour le bouton information
	 */
	private void actionInfo() {
		if (!menu.getSelectedItem().equals("Selectionnez un item"))
			JOptionPane.showMessageDialog(null,
					monterUnItem(trouverItemDansInventaire((String) menu.getSelectedItem())), "Informations",
					JOptionPane.INFORMATION_MESSAGE);
		else
			msgErreur(SELECTIONNER_ITEM);
	}


	/**
	 * Methode qui retourne un String d'un item pour GUI
	 * 
	 * @param item
	 * @return affichage de l'item
	 */
	private String monterUnItem(ItemInventaire item) {
		return "Description : " + item.getDescription() + "\nPrix : " + item.getPrix() + "\nQuantite : "
				+ item.getQuantite();
	}


	/**
	 * Methode qui trouve un item dans la liste et le retourne l'item a trouver
	 * 
	 * @param descItem
	 * @return retourne l'item descItem dans la liste, null sinon
	 */
	private ItemInventaire trouverItemDansInventaire(String descItem) {
		ItemInventaire itemTrouver = null;
		for (int i = 0; i < itemList.size(); i++)
			if (itemList.get(i).getDescription().equals(descItem))
				itemTrouver = itemList.get(i);
		return itemTrouver;
	}


	/**
	 * Methode d'action lors d'un ajout
	 */
	private void actionAjouter() {
		if (!menu.getSelectedItem().equals("Selectionnez un item")) {
			ItemInventaire itemSelect = trouverItemDansInventaire((String) menu.getSelectedItem());
			String quantite = JOptionPane.showInputDialog(null, monterUnItem(itemSelect)
					+ "\nQuantite a ajoute : \n", "Saisie", JOptionPane.INFORMATION_MESSAGE);

			int quant;

			if (!(quantite == null)) {
				try {
					quant = Integer.parseInt(quantite);
					if (quant <= 0) {
						msgErreur(QUANTITE_NEG);
					} else {
						ajouterQuantiteItem(itemSelect.getDescription(), quant);
						estModifie = true;
					}
				} catch (NumberFormatException e) {
					msgErreur(QUANTITE_NON_NUM);
				}

			}

		} else {
			msgErreur(SELECTIONNER_ITEM);
		}
	}


	/**
	 * Augmente la quantite d'un item desc dans la liste d'inventaire si l'item
	 * est present
	 * 
	 * @param desc
	 * @param quantite
	 */
	private void ajouterQuantiteItem(String desc, int quantite) {
		boolean itemPresent = false;

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getDescription().equals(desc)) {
				itemPresent = true;
			}
		}

		if (!(quantite <= 0) && itemPresent == true) {
			trouverItemDansInventaire(desc).setQuantite(
					quantite + trouverItemDansInventaire(desc).getQuantite());
		}
	}


	/**
	 * Methode d'action lors d'un retrait
	 */
	private void actionRetirer() {
		if (!menu.getSelectedItem().equals("Selectionnez un item")) {
			ItemInventaire itemSelect = trouverItemDansInventaire((String) menu.getSelectedItem());
			String quantite = JOptionPane.showInputDialog(null, monterUnItem(itemSelect)
					+ "\nQuantite a retirer : \n", "Saisie", JOptionPane.INFORMATION_MESSAGE);

			int quant;

			if (!(quantite == null)) {
				try {
					quant = Integer.parseInt(quantite);
					if (quant <= 0) {
						msgErreur(QUANTITE_NEG);
					} else if (itemSelect.getQuantite() < quant) {
						msgErreur(QUANTITE_INSSUFISANTE);
					} else if (itemSelect.getQuantite() == quant) {
						eliminer(itemSelect);
					} else {
						retirerQuantiteItem(itemSelect.getDescription(), quant);
						estModifie = true;
					}
				} catch (NumberFormatException e) {
					msgErreur(QUANTITE_NON_NUM);
				}
			}
		} else {
			msgErreur(SELECTIONNER_ITEM);
		}
	}


	/**
	 * Retire la quantite d'un item desc dans la liste d'inventaire si l'item
	 * est present et le met a 0 si la quantite serait negative
	 * 
	 * @param desc
	 * @param quantite
	 */
	private void retirerQuantiteItem(String desc, int quantite) {

		boolean itemPresent = false;

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getDescription().equals(desc)) {
				itemPresent = true;
			}
		}

		if (!(quantite <= 0) && itemPresent == true) {
			if ((trouverItemDansInventaire(desc).getQuantite() - quantite) < 0) {
				trouverItemDansInventaire(desc).setQuantite(0);
			} else {
				trouverItemDansInventaire(desc).setQuantite(
						quantite - trouverItemDansInventaire(desc).getQuantite());
			}
		}
	}


	/**
	 * Methode d'action pour la recherche
	 */
	private void actionRechercher() {
		int result = 0;
		ArrayList<ItemInventaire> temp = new ArrayList<ItemInventaire>();
		String recherche = JOptionPane.showInputDialog(null, "\nChaine a rechercher : \n", "Saisie",
				JOptionPane.INFORMATION_MESSAGE);
		System.out.print(recherche);
		if (recherche != "") {
			for (int i = 0; i < itemList.size(); i++) {
				result = itemList.get(i).getDescription().indexOf(recherche);
				if (result != -1)
					temp.add(itemList.get(i));
			}

			if (temp.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Aucun item ne contient la chaine " + recherche,
						"Resultat de la recherche de \"" + recherche + "\"", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String affResultat = "";
				for (int i = 0; i < temp.size(); i++)
					affResultat += temp.get(i).toString() + "\n";

				JOptionPane.showMessageDialog(null, "Resultat de la recherche de " + recherche + "\n"
						+ affResultat, "Resultat de la recherche de \"" + recherche + "\" \n",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}


	/**
	 * Methode d'action d'une elimination
	 */
	private void actionEliminer() {
		if (!menu.getSelectedItem().equals("Selectionnez un item")) {
			ItemInventaire itemSelect = trouverItemDansInventaire((String) menu.getSelectedItem());
			int choix = JOptionPane.showConfirmDialog(null, "etes vous sur de vouloir eliminer totalement \""
					+ itemSelect.getDescription() + "\" ?", "T'es-tu-sur?", JOptionPane.WARNING_MESSAGE);

			if (choix == JOptionPane.OK_OPTION)
				eliminer(itemSelect);
		} else {
			msgErreur(SELECTIONNER_ITEM);
		}
	}


	/**
	 * Methode qui elimine totalement un item
	 * 
	 * @param itemSelect
	 */
	private void eliminer(ItemInventaire itemSelect) {
		itemList.remove(itemSelect);
		menu.removeItem(itemSelect.getDescription());
		estModifie = true;
	}


	/**
	 * Methode d'action lors de l'ajout d'un nouvel item
	 */
	private void actionNouveau() {
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		JTextField desc = new JTextField(20);
		JTextField prix = new JTextField(20);
		JLabel descNom = new JLabel("Description :");
		myPanel.add(descNom);
		myPanel.add(desc);
		JLabel prixNom = new JLabel("Prix :");
		myPanel.add(prixNom);
		myPanel.add(prix);
		JLabel quantNom = new JLabel("Quantite :");
		myPanel.add(quantNom);

		String quantite = JOptionPane.showInputDialog(null, myPanel, "Nouvel item",
				JOptionPane.INFORMATION_MESSAGE);
		double prixFormat;
		int quant;

		boolean testSiItemPresent = false;
		for (int i = 0; i < itemList.size(); i++)
			if (itemList.get(i).getDescription().equals(desc.getText()))
				testSiItemPresent = true;

		if (testSiItemPresent) {
			msgErreur(ITEM_PRESENT);
		} else if (quantite != null) { // pour eviter un message d'erreur en cas
										// d'anulation
			try {
				prixFormat = Double.parseDouble(prix.getText());
				if (prixFormat <= 0) {
					msgErreur(PRIX_NEGATIF);
				} else {
					try {
						quant = Integer.parseInt(quantite);
						if (quant <= 0) {
							msgErreur(QUANTITE_NEG);
						} else {
							ajouterUnItem(desc.getText(), prixFormat, quant);
						}
					} catch (NumberFormatException e) {
						msgErreur(QUANTITE_NON_NUM);
					}
				}
			} catch (NumberFormatException e) {
				msgErreur(PRIX_NON_NUM);
			}

		}
	}


	/**
	 * ajoute un item contenant les descriptions dans la liste d'item
	 * 
	 * @param desc
	 * @param prix
	 * @param quantite
	 */
	private void ajouterUnItem(String desc, double prix, int quantite) {
		itemList.add(new ItemInventaire(desc, prix, quantite));
		menu.addItem(desc);
		estModifie = true;
	}


	/**
	 * Methode d'action du bouton enregistrer Qui valide le choix de
	 * l'utilisateur
	 */
	private void actionEnregistrer() {
		int reponse = JOptionPane.showConfirmDialog(enregistrer, "Voulez-vous enregistrer?");
		if (reponse == JOptionPane.YES_OPTION)
			gestionEnregistrement();
	}


	/**
	 * Methode qui enregistre le contenue de la liste actuel dans un fichier Et
	 * informe l'utilisateur de l'etat de cette action
	 */
	private void gestionEnregistrement() {
		try {
			enregistrerInventaire();
			JOptionPane.showMessageDialog(null, "Sauvegarde faite!");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Une erreur inconue est survenue");
		}
	}


	/**
	 * Methode qui premet, apres verification, de quitter le programme en
	 * proposant a l'utilisateur de l'enreistrer (si ce dernier a ete modife)
	 */
	private void actionQuitter() {
		if (estModifie) {
			int choix = JOptionPane.showConfirmDialog(null, "L'inventaire a ete modifie",
					"Risque de perte de donnees", JOptionPane.YES_NO_CANCEL_OPTION);

			if (choix == JOptionPane.OK_OPTION) {
				gestionEnregistrement();
				System.exit(0);
			} else if (choix == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}


	/**
	 * Main de notre programme
	 */
	public static void main(String[] args) throws IOException {
		new Tp3();
	}

}
