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
        
        boolean condition = false;

        String Choix = "";

        while (!condition)
        {
            Scanner sc= new Scanner(System.in);
            System.out.println("Quel Plateau voulez-vous choisir?" );
            System.out.println("1- Plateau1");
            System.out.println("2- Plateau2");
            System.out.println("3- Centre");
            Choix=sc.nextLine();

            System.out.println("Le plateau choisit est " + Choix );
            
            //plateau 1
            if (Choix.equals("1")){
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau1());
                condition = this.plateau.existe_il_un_emplacement_vide_plateau1();
            }
            //plateau 2
            else if (Choix.equals("2")){
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau2());
                condition = this.plateau.existe_il_un_emplacement_vide_plateau2();
            }
            //centre
            else if (Choix.equals("3")){  
                // System.out.println(this.plateau.existe_il_un_emplacement_vide_centre());
                condition = this.plateau.existe_il_un_emplacement_vide_centre();
            }
            else
            {
               System.out.println("\nLes seules entrees possibles sont plateau1(1), plateau2(2), centre(3)" );
            }
        }


        Lettre Lettre_choisi = this.plateau.ChoisitLettreHasard();
        //il a choisi ce qui a Choix 
        Scanner sc= new Scanner(System.in);
        
        int indice;

        if (Choix.equals("1")){
            System.out.println("Quel Emplacement à vous choisir" );
            indice = -1; 
            while (indice<0 || indice>7)
            {
                System.out.println("Choisir entre 0 et 7 :");
                indice = Integer.parseInt(sc.nextLine());
            }
            this.plateau.modifie_une_lettre_plateau1(indice, Lettre_choisi);
        }

        //plateau 2
        else if (Choix.equals("2")){
            System.out.println("Quel Emplacement à vous choisir" );
            indice = -1; 
            while (indice<0 || indice>3)
            {
                System.out.println("Choisir entre 0 et 3 :");
                indice = Integer.parseInt(sc.nextLine());
            }
            // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau2());
            this.plateau.modifie_une_lettre_plateau2(indice, Lettre_choisi);
        }

        //centre
        else if (Choix.equals("3")){  
            // System.out.println(this.plateau.existe_il_un_emplacement_vide_centre());
            this.plateau.modifie_une_lettre_centre(Lettre_choisi);
        }
        
        
    }
    
    public void start()
    {
//        while (il existe parli l'')

        this.next_tour();
        this.next_tour();
        this.next_tour();
        this.next_tour();
        this.next_tour();
        this.next_tour();
        this.next_tour();
        this.next_tour();
        
    }
   
    
   
}
// Random(class) random(objet) = new Random()(constructeur);
