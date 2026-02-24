package jeudesorbites;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author elise_admin
 */
public class Plateau {
   
    public Lettre[] Plateau1 = new Lettre[8];// attribut

    public Plateau() { // constructeur
        for (int i = 0; i < 8; i++) {
            Plateau1[i] = null; 
        }
    }
    
    public void AjouterLettre(Lettre[] alphabetMin) {
        
        
        Random rand = new Random();
        int choixLettre = rand.nextInt(24);
        Lettre AjouterLettre = alphabetMin[choixLettre];
   
            }
        
        
        
        

            
    

// ajouter une lettre sur le plateau
    
    
    public void plateau_affichage(String[] args) {
        
        
        
		PrintWriter printWriter = new PrintWriter(System.out,true);
		
        String text = "";
        text += "\n          /‾‾‾‾‾‾ ‾‾‾‾‾‾\\          |";
        text += "\n         /        |        \\         |";
        text += "\n        p         |         p        |";
        text += "\n       /          |          \\       |";
        text += "\n      /      /‾‾‾ α ‾‾‾\\      \\      |";
        text += "\n     |       |    |    |       |     |";
        text += "\n     |       |    |    |       |     |";
        text += "\n     α ----- r -- c -- r ----- p     |";
        text += "\n     |       |    |    |       |     |";
        text += "\n     |       |    |    |       |     |";
        text += "\n      \\      \\___ ω ___/      /      |";
        text += "\n       \\          |          /       |";
        text += "\n        p         |         π        |";
        text += "\n         \\        |        /         |";
        text += "\n          \\______ p ______/          |";

        
        printWriter.println(text);
    }
}
