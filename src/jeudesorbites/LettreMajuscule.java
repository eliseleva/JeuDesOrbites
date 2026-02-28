package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class LettreMajuscule extends Lettre{
    private int TempPrecense;

    public LettreMajuscule(String nom, int valeur) {
        super(nom, valeur);
        this.TempPrecense = 0;
    }

    public LettreMajuscule(String nom, int valeur, int presence) {
        super(nom, valeur);
        this.TempPrecense = presence;
    }
    
    public void Increment_Temps_presence() {
        this.TempPrecense = this.TempPrecense + 1;
    }

    @Override
    public String toString() {

        // Pour garder un nombre constant de caractère 
        // l'affichage met un espace si le nombre de TempPrecense est entre 0-9 
        // sinon on enleve l'esapce
        // donc si TempPrecense<100 ou negatif, le nombre de caractère pour l'affichage sera constant
        if (this.TempPrecense>=0 && this.TempPrecense < 10)
        {
            return super.toString() + " " + this.TempPrecense;
        }
        else
        {
            return super.toString() + this.TempPrecense;
        }
        
    }
    
     
    @Override
    public int getvaleur() {
        // ajoute Override car on est dans la classe fille et on
        //redefini/surcharge une methode qui est presente dans la classe mere
        if  (this.TempPrecense>=0)
        {
            return super.getvaleur() + this.TempPrecense - (this.TempPrecense % 10);
        }
        else
        {
            // cas particulier quand le compteur de temps est à -1
            //  (car super.getvaleur() + (-1) - (-1%10) = super.getvaleur() - 10 )
            return super.getvaleur();
        }

    }
    
    @Override
    public int get_next_indice() {
        // Delta Majuscule [0] => Lambda Majusucle[1] => Sigma Majuscule[2]

        // si on a Delta
        // la valeur c'est 30 => 30/10 = 3
        // 3 - 3 = 0
        // 0 +1 = 1 
        // On a donc l'indice de Lambda
        return (super.getvaleur() / 10) - 3 + 1;

    }
    
}
