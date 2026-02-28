
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Joueur {
    private int score;

    public Joueur(){
        this.score=0;
    }

    public  String toString(){
        return "Le score du joueur est de :" + String.valueOf(this.score);
    }


    public void add_score_fusion(int score_fusion_lettre) {
        // a chaque fusion, on auglente le score et on l'affiche
        this.score += score_fusion_lettre;
        System.out.println(this.toString());
    }
}
