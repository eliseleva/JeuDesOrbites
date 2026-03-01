
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Lettre {
    private String nom;
    private int valeur;
    
    public Lettre(String nom, int valeur){//initialise 
        this.nom= nom;
        this.valeur=valeur;
    }
      
    @Override
    public String toString(){
        return this.nom;
    }
    
    public boolean est_vide() {
        if (this.valeur == 0) {
            return true;
        } else {
            return false;
        }
        //         return this.valeur==0;// fonctionne car si this.valeur=0 alors forcement true, ts les autres cas sont false
    }
    
    public boolean est_fusionable(Lettre x, boolean symbole_plus_noir)
    {

        if (symbole_plus_noir) {
            // on n'a pas de condition sur l'égalité des lettres
            return !this.est_vide() & !x.est_vide() & (this.valeur != 24 && this.valeur != 50) & (x.valeur != 24 && x.valeur != 50);
        }
        else{
             // on test si les deux sont egals et pas vide
            // on teste aussi si l'une des lettres n'est ni sigma ni omega car ce sont des lettres
            // que l'on ne peut pas fusionner car elles sont à la fin de
            // "l'alphabet" 
            return this.nom.equals(x.nom) & !this.est_vide() & (this.valeur!=24 && this.valeur!=50) & (x.valeur!=24 && x.valeur!=50);
        }
    }
    
    public int getvaleur() {
        return this.valeur;

    }

    public int get_next_indice(){
        return this.valeur;
        
    }

    public String getnom() {
        return this.nom;
    }
    
    public boolean est_ce_une_minuscule(){
        return this.getvaleur()<=24;
    }
    
            
}

 
//Class_name name_variable = new Class_name(arg1, arg2, ....);