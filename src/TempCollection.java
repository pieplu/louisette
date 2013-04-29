import java.io.*;
import java.util.ArrayList;

public class TempCollection {

	static ArrayList<ItemInventaire> itemList = new ArrayList<ItemInventaire>();


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
					ItemInventaire item = new ItemInventaire(desc, prix, quantiter);
					itemList.add(item);
				}
				desc = "";
			}
		}
		inventaireSauvegarder.close();
	}


	public static ItemInventaire trouverItemDansInventaire(String descItem) {
		ItemInventaire itemTrouver = null;

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getDescription().equals(descItem)) {
				itemTrouver = itemList.get(i);
			}
		}

		return itemTrouver;
	}


	public static boolean ajouterUnItem(String desc, double prix, int quantite) {
		boolean estAjouter = true;
		if (!itemList.contains(trouverItemDansInventaire(desc))) {
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


	public static void ajouterQuantiteItem(String desc, int quantite) {
		if (!(quantite <= 0)) {
			trouverItemDansInventaire(desc).setQuantite(
					quantite + trouverItemDansInventaire(desc).getQuantite());
		}
	}


	public static void retirerQuantiteItem(String desc, int quantite) {
		if (!(quantite <= 0)) {
			if ((trouverItemDansInventaire(desc).getQuantite() - quantite) < 0) {
				trouverItemDansInventaire(desc).setQuantite(0);
			} else {
				trouverItemDansInventaire(desc).setQuantite(
						quantite - trouverItemDansInventaire(desc).getQuantite());
			}
		}
	}


	public static void retirerItem(String desc) {
		if (itemList.contains(trouverItemDansInventaire(desc))) {
			itemList.remove(trouverItemDansInventaire(desc));
		}
	}


	public static void enregistrerInventaire() throws IOException {
		FileWriter f = new FileWriter("inventaireSave.txt");
		PrintWriter sortie = new PrintWriter(f);
		for (int i = 0; i < itemList.size(); i++) {
			String itemASauvegarderDansFichier = itemList.get(i).getQuantite() + ""
					+ itemList.get(i).getPrix() + "" + itemList.get(i).getDescription();
			sortie.println(itemASauvegarderDansFichier);
		}
		sortie.close();

	}


	public static void main(String[] args) throws IOException {

		chargerItemDuFichierDansListe();
		System.out.println(itemList);
		enregistrerInventaire();
	}

}
