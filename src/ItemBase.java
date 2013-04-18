
/**
 * 131-INF2120-010 TP3
 * 
 * D��finit le minimum pour un item : son prix et sa description.
 * Il n'est pas possible de modifier la description de l'item.
 *
 * @author  Louise Laforest
 * @version 2013-04-02
 */

public abstract class ItemBase {
    
    private double prix; // prix �� l'unit��
    private String description; // description de l'item

    /**
     * Construit un item sans description et prix 0
     * @param desc la description
     */
    public ItemBase () {
        this ( "", 0 );
    }


    /**
     * Construit un item avec la description sp��cifi��e et le prix 0
     * @param desc la description
     */
    public ItemBase ( String desc ) {
        this ( desc, 0 );
    }

    /**
     * Construit un item avec la description et le prix pass��s en param��tres.
     * @param desc la description
     * @param prix le prix
     */
    public ItemBase ( String desc, double prix ) {
        this.description = desc;
        this.prix = prix;
    }

    /**
     * Retourne la description de l'item.
     * @return la description de l'item
     */
    public String getDescription () {
        return this.description;
    }
    
    /**
     * Retourne le prix de l'item.
     * @return le prix de l'item
     */
    public double getPrix () {
        return this.prix;
    }
    
    /**
     * Modifie le prix de l'item.
     * @param nouveauPrix le nouveau prix de l'item
     */
    public void setPrix ( double nouveauPrix ) {
        this.prix = nouveauPrix;
    }
    
    /**
     * D��termine si deux items sont ��gaux
     * Les classes d��rivant de cette classe d��cideront ce qui fait que deux items sont ��gaux.
     * @param nouveauPrix le nouveau prix de l'item
     */
    public abstract boolean equals ( Object o );
}
