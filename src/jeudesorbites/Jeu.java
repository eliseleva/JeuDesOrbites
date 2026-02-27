package jeudesorbites;
import java.util.Scanner;

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
    
    public void next_tour()
    {
        this.plateau.plateau_affichage();
        Lettre Lettre_choisi = this.plateau.ChoisitLettreHasard();
        
        
        boolean condition = false;
        
        while (!condition)
        {
            Scanner sc= new Scanner(System.in);
            System.out.println("Quel Plateau voulez-vous choisir?" );
            System.out.println("1- Plateau1");
            System.out.println("2- Plateau2");
            System.out.println("3- Centre");
            String Choix=sc.nextLine();

            System.out.println("Le plateau choisit est " + Choix );


            if (Choix.equals("1")){
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau1());
                condition = this.plateau.existe_il_un_emplacement_vide_plateau1();
            }
            else if (Choix.equals("2")){
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau2());
                condition = this.plateau.existe_il_un_emplacement_vide_plateau2();
            }
            else if (Choix.equals("3")){  
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_centre());
                condition = this.plateau.existe_il_un_emplacement_vide_centre();
            }
            else
            {
               System.out.println("\nLes seules entrees possibles sont plateau1(1), plateau2(2), centre(3)" );
            }
        }

        
        
    }
    
    public void start()
    {
//        while (il existe parli l'')
        this.next_tour();
        this.next_tour();
        
    }
   
    
   
}
// Random(class) random(objet) = new Random()(constructeur);
