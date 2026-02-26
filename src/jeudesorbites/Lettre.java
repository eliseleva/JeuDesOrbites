
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
    
    public int getscore(){
        return this.score;
        
    }
    
    
            
}
