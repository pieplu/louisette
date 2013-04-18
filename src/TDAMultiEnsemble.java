/**
 * 131-INF2120-010
 * TP1
 *
 * Un multiEnsemble et un ensemble dans lequel un element peut apparaitre plusieurs fois.
 * Le nombre d'apparition d'un element est appele son nombre d'occurrences.
 * Le nombre d'occurrences d'un element est 0 si l'element est absent du multiEnsemble.
 * Si l'element est present, son nombre d'occurrences est un nombre strictement positif.
 * Le nombre d'occurrences d'un element ne peut jamais etre negatif.
 * 
 * @author  Louise Laforest 
 * @version 2013-01-30
 */


public interface TDAMultiEnsemble<T> {

    /**
     * Retourne true si le multiEnsemble est vide.
     * @return true si ce multiEnsemble est vide, false sinon
     */
    public abstract boolean estVide ();
    
    /**
     * Vide ce multiEnsemble
     */
    public abstract void vider ();
    
    /**
     * Retourne le nombre d'elements distincts
     * @return le nombre d'elements distincts de ce multiEnsemble
     */
    public abstract int cardDistincts ();

    /**
     * Retourne le nombre total d'elements
     * @return le nombre total d'elements incluant les doublons
     */
    public abstract int cardTotale ();

	/**
	 * Retourne true si ce multiEnsemble contient au moins une occurrence de l'element
	 * @param element element a verifier
	 * @return true si element est dans le multiEnsemble (au moins une occurrence), false sinon
	 */
	public abstract boolean estElement ( T element );
	
	/**
	 * Retourne le nombre d'occurrences de l'element dans ce multiEnsemble
	 * @param element element a verifier
	 * @return le nombre d'occurrences d'element dans ce multiEnsemble
	 */
	public abstract int nbOccurrences ( T element );
	
	/**
     * Cherche element dans le multiEnsemble.
     * @param element element e chercher
     * @return une occurrence de l'element trouve si au moins une occurrence, null si absent
     */
    public abstract T trouverElement ( T element );

    /**
	 * Ajoute une occurrence de cet element au multiEnsemble
	 * @param element element a ajouter
	 * @return le nombre d'occurrences de element dans le multiEnsemble APRES l'ajout
	 */
	public abstract int ajouter ( T element );
	
    /**
	 * Ajoute nbCopies occurrences de cet element au multiEnsemble.
	 * nbCopies ne doit pas etre negatif.
	 * @param element element a ajouter
	 * @param nbCopies nombre d'occurrences a ajouter
	 * @throws NombreOccurrencesException si nbCopies est negatif.
	 * @return le nombre d'occurrences de element dans le multiEnsemble APRES l'ajout
	 */
	public abstract int ajouter ( T element, int nbCopies );

	/**
	 * Retire une occurrence de cet element du multiEnsemble. Si l'element etait absent, il le demeure.
	 * Ceci veut dire que si l'on retire un element absent et qu'ensuite on l'ajoute, son nombre
	 * d'occurrences sera egal a 1.
	 * @param element element a retirer
	 * @return le nombre d'occurrences de element dans le multiEnsemble APRES le retrait
	 */
	public abstract int retirer ( T element );
	
	/**
	 * Retire nbCopies occurrences de cet element du multiEnsemble.
	 * nbCopies ne doit pas etre negatif.
	 * @param element element a retirer
	 * @param nbCopies nombre d'occurrences a retirer
	 * @throws NombreOccurrencesException si nbCopies est negatif.
	 * @return le nombre d'occurrences de element dans le multiEnsemble APRES le retrait
	 */
	public abstract int retirer ( T element, int nbCopies );
	
	/**
	 * Retire toutes les occurrences de element de ce multiEnsemble. Si l'element etait absent, il le demeure.
	 * @param element element a retirer
	 * @return le nombre d'occurrences de element dans le multiEnsemble AVANT son elimination
	 */
	public abstract int eliminer ( T element );

	/** 
	 * Ajoute e cet ensemble tous les elements de autre.  Le multiEnsemble autre sera vide par l'operation.
	 * Il s'agit donc d'un transfert des elements de autre dans cet ensemble.
	 * @param autre multiEnsemble dont les elements seront transferes dans cet ensemble
	 * @return le nombre total d'elements transferes (toutes les occurrences).
	 */
	public abstract int transfert ( TDAMultiEnsemble<T> autre );
	
    /**
     * Retourne la chaene correspondant au contenu du multiEnsemble.
     * La methode toString de T sera utilisee.
     * @return la chaine
     */
    public abstract String toString ();
    
    /**
     * Determine si ce multiEnsemble contient les memes elements en meme nombre que celui represente
     * par o.
     * @param o l'objet e comparer avec ce multiEnsemble
     * @return true si ce multiEnsemble contient les memes elements en meme nombre que o
     */
    public abstract boolean equals ( Object o );

		
} //TDAMultiEnsemble
