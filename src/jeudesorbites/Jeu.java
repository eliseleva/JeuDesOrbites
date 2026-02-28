package jeudesorbites;
import java.util.Scanner;

/**
 *
 * @author elise_admin
 */
public class Jeu {

    private Plateau plateau;
    private Joueur joueur;
    
    public Jeu() {      
 
        // Joueur 
        // compteur tour
        // reserve symbole
        // Lettres
          
        this.plateau = new Plateau();
        this.joueur = new Joueur();
    }
    
    public void next_tour()
    {
        this.plateau.plateau_affichage();
        
        Lettre Lettre_choisi = this.plateau.ChoisitLettreHasard();
        System.out.println("La lettre tirée au sort est :" + Lettre_choisi);

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
        
        //il a choisi ce qui a Choix 
        Scanner sc= new Scanner(System.in);
        
        int indice;

        if (Choix.equals("1")){
            System.out.println("Quel Emplacement à voulez-vous choisir pour :" + Lettre_choisi);
            indice = -1; 
            while (indice<0 || indice>7)
            {
                System.out.println("Choisir entre 0 et 7 :");
                indice = Integer.parseInt(sc.nextLine());
            }
            this.plateau.ajouter_lettre_plateau1_trigonometrique(indice, Lettre_choisi);
        }

        //plateau 2
        else if (Choix.equals("2")){
            System.out.println("Quel Emplacement à voulez-vous choisir pour :" + Lettre_choisi);
            indice = -1; 
            while (indice<0 || indice>3)
            {
                System.out.println("Choisir entre 0 et 3 :");
                indice = Integer.parseInt(sc.nextLine());
            }
            // System.out.println(this.plateau.existe_il_un_emplacement_vide_plateau2());
            this.plateau.ajouter_lettre_plateau2_trigonometrique(indice, Lettre_choisi);
        }

        //centre
        else if (Choix.equals("3")) {
            // System.out.println(this.plateau.existe_il_un_emplacement_vide_centre());
            this.plateau.ajouter_lettre_centre_trigonometrique(Lettre_choisi);
        }
        
        System.out.println("========== Le résultat de votre placement de:" +Lettre_choisi + " ========");
        this.plateau.plateau_affichage();

        //réalisation des fusions
        int score_fusion;
        int compteur_fusion=1;
        while (this.plateau.existe_il_une_fusion_ds_tout_le_jeu())
        {
            System.out.println("\n================== Fusion n°" + compteur_fusion + "=================="  );
            score_fusion = this.plateau.faire_une_fusion_ds_le_jeu();
            this.plateau.plateau_affichage();
            this.joueur.add_score_fusion(score_fusion);
            compteur_fusion += 1;
        }

        // A la fin, on doit incrémenter le compteur de toutes les lettres majuscules
        // bug possible sur les lettres majsucules heritées de la fusion de lettres ou celle
        //  qui vient d'être chsoisie et posée dans le jeu (Lettre_choisi)
        // (mais on a mis -1 comme compteur de temps pour cette raison sur ces lettres)
        this.plateau.incrementer_tous_les_compteurs_temps_majuscules();
    }
    
    public void start()
    {
        int compteur = 1;

        // tant qu'il y a un emplacement vide dans le jeu, on contiue à jouer 
        while (this.plateau.existe_il_un_emplacement_vide_ds_jeu())
        {
            System.out.println("\n==============================================");
            System.out.println("================== Tour n°" + compteur + " ==================");
            System.out.println("==============================================");
            System.out.println(this.joueur.toString());
            this.next_tour();

            compteur += 1;
        }

        System.out.println("\nLe score final est de :");
        System.out.println(this.joueur.toString());
    }
   
    
   
}

