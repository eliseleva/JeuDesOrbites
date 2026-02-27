
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
    
    public boolean est_vide(){
    if (this.valeur==0){
        return true;
    }
    else {
        return false;
    }
    //         return this.valeur==0;// fonctionne car si this.valeur=0 alors forcement true, ts les autres cas sont false
    }
    
    
    public int getvaleur(){
        return this.valeur;
        
    }

    public String getnom(){
        return this.nom;
    }
    
    
            
}

 
//Class_name name_variable = new Class_name(arg1, arg2, ....);