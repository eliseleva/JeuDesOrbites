
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Lettre {
    private String nom;
    private int score;
    
    public Lettre(String nom, int score){//initialise 
        this.nom= nom;
        this.score=score;
    }
      
    @Override
    public String toString(){
        return nom;
    }
    
    public boolean est_vide(){
    if (this.score==0){
        return true;
    }
    else {
        return false;
    }
    //         return this.score==0;// fonctionne car si this.score=0 alors forcement true, ts les autres cas sont false
    }
    
    
    public int getscore(){
        return this.score;
        
    }

    public String getnom(){
        return this.nom;
    }
    
    
            
}

 
//Class_name name_variable = new Class_name(arg1, arg2, ....);