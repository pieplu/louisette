/**
 * 131-INF2120-010 TP3
 * 
 * Definit le minimum pour un item : son prix et sa description. Il n'est pas
 * possible de modifier la description de l'item.
 * 
 * @author Louise Laforest
 * @version 2013-04-02
 */

public abstract class ItemBase {

	private double prix; // prix a l'unite
	private String description; // description de l'item

	/**
	 * Construit un item sans description et prix 0
	 * 
	 * @param desc
	 *            la description
	 */
	public ItemBase() {
		this("", 0);
	}

	/**
	 * Construit un item avec la description specifiee et le prix 0
	 * 
	 * @param desc
	 *            la description
	 */
	public ItemBase(String desc) {
		this(desc, 0);
	}

	/**
	 * Construit un item avec la description et le prix passes en parametres.
	 * 
	 * @param desc
	 *            la description
	 * @param prix
	 *            le prix
	 */
	public ItemBase(String desc, double prix) {
		this.description = desc;
		this.prix = prix;
	}

	/**
	 * Retourne la description de l'item.
	 * 
	 * @return la description de l'item
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Retourne le prix de l'item.
	 * 
	 * @return le prix de l'item
	 */
	public double getPrix() {
		return this.prix;
	}

	/**
	 * Modifie le prix de l'item.
	 * 
	 * @param nouveauPrix
	 *            le nouveau prix de l'item
	 */
	public void setPrix(double nouveauPrix) {
		this.prix = nouveauPrix;
	}

	/**
	 * Determine si deux items sont egaux Les classes derivant de cette classe
	 * decideront ce qui fait que deux items sont egaux.
	 * 
	 * @param nouveauPrix
	 *            le nouveau prix de l'item
	 */
	public abstract boolean equals(Object o);
}
