
import java.util.ArrayList;


/**
* Ce fichier contient l'interface de la classe ...
* ...
* @author Alexis Pieplu
* Code permanent : PIEA07058900
* Courriel : pieplu.alexis@courrier.uqam.ca
* Cours : INF2120-
* @version 2013-03-04
*/
public class MultiEnsembleTp1<T> implements TDAMultiEnsemble<T>{

    private ArrayList<T> ensemble;
    private int nbElemDistincts = 0;
    
    
    /**
     * Constructeur par default
     */
    public MultiEnsembleTp1 (){
        ensemble = new ArrayList();
    }
    
    /**
     * Constructeur avec un element a ajoute
     * @param element element a ajoute 
     */
    public MultiEnsembleTp1 ( T element){
        ensemble = new ArrayList();
        ajouter(element);
    }

    /**
     * Retourne true si le multiEnsemble est vide.
     * @return true si ce multiEnsemble est vide, false sinon
     */
    @Override
    public boolean estVide (){
        return ensemble.isEmpty();
    }

    /**
     * Vide ce multiEnsemble
     */
    @Override
    public void vider (){
        ensemble.clear();
    }
    
    /**
     * Retourne le nombre d'elements distincts
     * @return le nombre d'elements distincts de ce multiEnsemble
     */
    @Override
    public int cardDistincts (){
        ArrayList<T> tempo = new ArrayList();
        for (int i = 0; i < ensemble.size(); i++)
            if( ! tempo.contains(ensemble.get(i)))
                tempo.add(ensemble.get(i));
        return tempo.size();
    }
    
    /**
     * Retourne le nombre total d'elements
     * @return le nombre total d'elements incluant les doublons
     */
    @Override
    public int cardTotale (){
        return ensemble.size();
    }

    /**
     * Retourne true si ce multiEnsemble contient au moins une occurrence de l'element
     * @param element element a verifier
     * @return true si element est dans le multiEnsemble (au moins une occurrence), false sinon
     */
    @Override
    public boolean estElement ( T element ){
        return ensemble.contains(element);
    }

    /**
     * Retourne le nombre d'occurrences de l'element dans ce multiEnsemble
     * @param element element a verifier
     * @return le nombre d'occurrences d'element dans ce multiEnsemble
     */
    @Override
    public int nbOccurrences ( T element ){
        int nbOcu = 0;
        for(int i =0; i < ensemble.size() ; i++)
            if(ensemble.get(i).equals(element))
                nbOcu++;
        return nbOcu;     
    }
    
    /**
     * Cherche element dans le multiEnsemble.
     * @param element element e chercher
     * @return une occurrence de l'element trouve si au moins une occurrence, null si absent
     */
    @Override
    public T trouverElement ( T element ){
        if(!ensemble.contains(element))
            element = null; 
        return element;
    }

    /**
     * Ajoute une occurrence de cet element au multiEnsemble
     * @param element element a ajouter
     * @return le nombre d'occurrences de element dans le multiEnsemble APRES l'ajout
     */
    @Override
    public int ajouter ( T element ) throws RuntimeException{
        int nbOccu = 0;
        try{
            ensemble.add(element);
            nbOccu = nbOccurrences (element);
        }catch(RuntimeException e){
            nbOccu = 0;
        }
        return nbOccu;
    }

    /**
     * Ajoute nbCopies occurrences de cet element au multiEnsemble.
     * nbCopies ne doit pas etre negatif.
     * @param element element a ajouter
     * @param nbCopies nombre d'occurrences a ajouter
     * @throws NombreOccurrencesException si nbCopies est negatif.
     * @return le nombre d'occurrences de element dans le multiEnsemble APRES l'ajout
     */
    @Override
    public int ajouter ( T element, int nbCopies ) throws RuntimeException{
        int nbOccu = 0;
        
        if( nbCopies < 0 )
            throw new NombreOccurrencesException("ERREUR! Entrez un nombre d'elements positif");
            
        try{
            for(int i=0; i < nbCopies; i++)
                ensemble.add(element);
            nbOccu = nbOccurrences (element);
        }catch(RuntimeException e){
            nbOccu = 0;
        } 
        return nbOccu;
    }
    

    /**
     * Retire une occurrence de cet element du multiEnsemble. Si l'element etait absent, il le demeure.
     * Ceci veut dire que si l'on retire un element absent et qu'ensuite on l'ajoute, son nombre
     * d'occurrences sera egal a 1.
     * @param element element a retirer
     * @return le nombre d'occurrences de element dans le multiEnsemble APRES le retrait
     */
    @Override
    public int retirer ( T element ){
        ensemble.remove(element);
        return nbOccurrences(element);
    }
    


    /**
     * Retire nbCopies occurrences de cet element du multiEnsemble.
     * nbCopies ne doit pas etre negatif.
     * @param element element a retirer
     * @param nbCopies nombre d'occurrences a retirer
     * @throws NombreOccurrencesException si nbCopies est negatif.
     * @return le nombre d'occurrences de element dans le multiEnsemble APRES le retrait
     */
    @Override
    public int retirer ( T element, int nbCopies )throws NombreOccurrencesException{
        if( nbCopies < 0 )
            throw new NombreOccurrencesException("ERREUR! Entrez un nombre d'elements positif");
        for(int i=0; i < nbCopies; i++)
            ensemble.remove(element);
        return nbOccurrences(element);
    }
    

    /**
     * Retire toutes les occurrences de element de ce multiEnsemble. Si l'element etait absent, il le demeure.
     * @param element element a retirer
     * @return le nombre d'occurrences de element dans le multiEnsemble AVANT son elimination
     */
    @Override
    public int eliminer ( T element ){
        int nbElem = nbOccurrences(element);
        retirer(element, nbElem);
        return nbElem;
    }
    

    /**
     * Ajoute a cet ensemble tous les elements de autre.  Le multiEnsemble autre sera vide par l'operation.
     * Il s'agit donc d'un transfert des elements de autre dans cet ensemble.
     * @param autre multiEnsemble dont les elements seront transferes dans cet ensemble
     * @return le nombre total d'elements transferes (toutes les occurrences).
     */
    @Override
    public int transfert ( TDAMultiEnsemble<T> autre ){
        ArrayList<T> tempo = ((MultiEnsembleTp1)autre).ensemble;
        int nbAutre = tempo.size();
        for (int i = 0; i < nbAutre; i++) 
            ajouter(tempo.get(i));
        tempo.clear();
        return nbAutre; 
        }
     

    /**
     * Retourne la chaine correspondant au contenu du multiEnsemble.
     * La methode toString de T sera utilisee.
     * @return la chaine
     */
    @Override
    public String toString (){
        ArrayList<T> tempo = new ArrayList();
        
        for (int i = 0; i < ensemble.size(); i++)
            if (!(tempo.contains(ensemble.get(i))))  //pour ne pas bloquer la machine et afficher un trop grand nombre
                tempo.add(ensemble.get(i));
        
        String chaine = "";
        for (int i = 0; i < tempo.size(); i++)
            chaine += tempo.get(i) + "\n";
        return chaine;        
                
    }

    /**
     * Determine si ce multiEnsemble contient les memes elements en meme nombre que celui represente
     * par o.
     * @param o l'objet a comparer avec ce multiEnsemble
     * @return true si ce multiEnsemble contient les memes elements en meme nombre que o
     */
    @Override
    public boolean equals ( Object o ) throws RuntimeException{
        boolean bool = true;
        try{
            if (!verificationNull(o)){
                ArrayList<T> tempo = ((MultiEnsembleTp1)o).ensemble;
        
                bool = (!(tempo.isEmpty() || ensemble.isEmpty())); // Si une des listes est vide
        
                for (int i = 0; i < ensemble.size(); i++){
                    int index = tempo.indexOf(ensemble.get(i));
                    if ( index != (-1)){
                        if (nbOccurrences(tempo.get(index)) != nbOccurrences(ensemble.get(i))){
                            bool = false;
                        }
                    }else{
                        bool = false;
                    }
                }
            }else{
                bool = false;
            }
        }catch (RuntimeException e){
            bool = false;
        }
        return bool;       
    }
    
    /**
     * V��rifie le nullite de deux multi-enssemble. Celui-ci, et celui passe en parametre
     * @param o l'objet a comparer avec ce multiEnsemble
     * @return true si ce au moins un des deux multi-enssemble est null
     */
    private boolean verificationNull(Object o){
        return this.ensemble == null || (((MultiEnsembleTp1)o).ensemble) == null;
    }
}
