package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class Jeu {

    private Plateau plateau;
    
    
    public Jeu() {      
 
        // Joueur 
        // compteur tour
        // reserve symbole
        // Lettres
  
        //
        
        this.plateau = new Plateau();
      
    }
    
    public void start()
    {
        this.plateau.plateau_affichage();
        
        Lettre Lettre_choisi = this.plateau.ChoisitLettreHasard();
    }
   
    
   
}
// Random(class) random(objet) = new Random()(constructeur);
