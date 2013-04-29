public class ItemInventaire extends ItemBase {

	private int quantite; // quantite de l'item en stock


	public ItemInventaire() {
		super("", 0);
		this.quantite = 0;
	}


	public ItemInventaire(String desc, double prix, int quantite) {
		super(desc, prix);
		this.quantite = quantite;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public boolean equals(Object o) {
		boolean test = true;

		if (o instanceof ItemInventaire) {
			if (!((ItemInventaire) o).getDescription().equals(getDescription())) {
				test = false;
			}
			if (!(((ItemInventaire) o).getPrix() == getPrix())) {
				test = false;
			}

		} else {
			test = false;
		}

		return test;
	}


	public String toString() {
		return quantite + " " + getPrix() + " " + getDescription();
	}

}
