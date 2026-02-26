package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class LettreMajuscule extends Lettre{
    private int TempPrecense;

    public LettreMajuscule(String nom, int score){
        super(nom, score);
        this.TempPrecense = 0;
        
    }
    
    public void Increment_Temps_presence(){
        this.TempPrecense = this.TempPrecense + 1;
    }
    
     
    @Override
    public int getscore(){  
    // ajoute Override car on est dans la classe fille et on
    //redefini/surcharge une methode qui est presente dans la classe mere
        return super.getscore() + this.TempPrecense - (this.TempPrecense%10); // reste division euclidienne
        
    }   
}
