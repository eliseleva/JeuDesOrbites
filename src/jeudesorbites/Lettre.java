
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
        // on teste si l'une des lettres n'est ni sigma majsucule ni omega car ce sont des lettres
        // que l'on ne peut pas fusionner car elles sont à la fin de "l'alphabet"
        // donc il n'y a pas de lettre suivante et ainsi la fusion est impossible 

        // on teste aussi que l'une lettre n'est pas nulle

        if (symbole_plus_noir) {
            // on n'a pas de condition sur l'égalité des lettres avec noir+
            return !this.est_vide() & !x.est_vide() & this.pas_egal_omega_ou_sigma_maj() & x.pas_egal_omega_ou_sigma_maj();
        } else {
            // Sinon on teste si les deux sont egales et l'une d'entre elle n'est pas vide
            return this.nom.equals(x.nom) & !this.est_vide() & this.pas_egal_omega_ou_sigma_maj() & x.pas_egal_omega_ou_sigma_maj();
        }
    }
    
    public boolean pas_egal_omega_ou_sigma_maj()
    {
        return this.valeur != 24 && this.valeur != 50;
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