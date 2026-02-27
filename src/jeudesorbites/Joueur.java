
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Joueur {
    private String pseudo;
    private int score;

    public Joueur(String pseudo, int score){
        this.pseudo=pseudo;
        this.score=score;
    }


    public  String toString(){
        return "Le score de " + this.pseudo + " est " + String.valueOf(this.score);
    }


    public void add_score_fusion(int score_fusion_lettre){
        this.score+= score_fusion_lettre ;
    }

    




}
