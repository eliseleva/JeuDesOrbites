
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Joueur {
    private int score;
    private int nb_symbole_rouge;
    private int nb_symbole_noir;
    private int nb_symbole_vert;

    public Joueur() {
        this.score = 0;

        this.nb_symbole_rouge = 10;
        this.nb_symbole_noir = 10;
        this.nb_symbole_vert = 10;
    }

    public String toString() {
        return "Score   " + String.format("%04d",this.score) + " pts";
    }

    public String toString_symboles() {
        String res = "";
        res += "+rouges [" + String.format("%02d",this.nb_symbole_rouge) + "]";
        res += "\n+noirs  [" + String.format("%02d",this.nb_symbole_noir) + "]";
        res += "\n+vers   [" + String.format("%02d",this.nb_symbole_vert) + "]";
        return res;
    }

    public void add_score_fusion(int score_fusion_lettre) {
        // a chaque fusion, on auglente le score et on l'affiche
        this.score += score_fusion_lettre;
    }

    public boolean can_use_symbol(String symbol) {
        boolean res = false;
        switch (symbol) {
            case "symbole_rouge":
                res = this.nb_symbole_rouge >= 1;
                break;

            case "symbole_noir":
                res = this.nb_symbole_noir >= 1;
                break;

            case "symbole_vert":
                res = this.nb_symbole_vert >= 1;
                break;

            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }
        return res;
    }
    
    public boolean can_use_one_symbol()
    {
        return this.can_use_symbol("symbole_rouge") || this.can_use_symbol("symbole_noir")
                || this.can_use_symbol("symbole_vert");
    }

    public void add_one_symbol(String symbol) {
    {
        switch(symbol){
            case "symbole_rouge":
                this.nb_symbole_rouge += 1;
                break;
    
            case "symbole_noir":
                this.nb_symbole_noir += 1;
                break;

            case "symbole_vert": 
                this.nb_symbole_vert += 1;
                break;
                
            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }            
    }
}

    public void use_one_symbol(String symbol) {
        switch(symbol){
            case "symbole_rouge":
                this.nb_symbole_rouge -= 1;
                break;
    
            case "symbole_noir":
                this.nb_symbole_noir -= 1;
                break;

            case "symbole_vert": 
                this.nb_symbole_vert -= 1;
                break;
                
            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }            
    }
}



