package jeudesorbites;
import java.util.Scanner;

/**
 *
 * @author elise_admin
 */
public class Jeu {

    private int compteur_tour;
    private Plateau plateau;
    private Joueur joueur;
    private Scanner scanner;

    public Jeu() {      
 
        // Joueur 
        // compteur tour
        // reserve symbole
        // Lettres
        this.plateau = new Plateau();
        this.joueur = new Joueur();
        this.compteur_tour = 0;
        this.scanner = new Scanner(System.in);
    }
       
    public String Choisir_un_des_3_plateau() {
        boolean condition = false;
        String Choix = "";
        String res = "";

        while (!condition) {
            System.out.println("Choisir plateau:");
            System.out.println(" - 1 [Plateau1]");
            System.out.println(" - 2 [Plateau2]");
            System.out.println(" - 3 [Centre]");
            Choix = this.scanner.nextLine().trim();

            if (Choix.equals("1") || Choix.equals("2") || Choix.equals("3")) {
                condition = true;
                switch (Choix) {
                    case "1":
                        res = "plateau1";
                        break;
                    case "2":
                        res = "plateau2";
                        break;
                    case "3":
                        res = "centre";
                        break;
                }
            } else {
                System.out.println("Mauvaise entree: " + Choix + "\n");
            }

        }
        return res;
    }
    
    public String choisir_un_plateau_ou_un_emplacement_vide_existe(){
        boolean condition = false;
        String Choix_plateau = "";

        while (!condition) {
            Choix_plateau = this.Choisir_un_des_3_plateau();

            //plateau 1
            switch(Choix_plateau){
                case "plateau1":
                condition = this.plateau.existe_il_un_emplacement_vide_plateau1();
                break;
            //plateau 2
            case "plateau2":
                condition = this.plateau.existe_il_un_emplacement_vide_plateau2();
                break;
            //centre
            case "centre":
                condition = this.plateau.existe_il_un_emplacement_vide_centre();
                break;
            }

            if (!condition) {
                System.out.println("Le " + Choix_plateau +" n'a pas d'emplacement vide");
            }
        }

    return Choix_plateau;
    }


    public int selectionner_indice_selon_plateau(String Choix_plateau) {

        int indice;

        //plateau 1
        switch(Choix_plateau){
            case "plateau1":
                indice = -1;
                while (indice < 0 || indice > 7) {
                    System.out.println("Choisir emplacement [0-7]:");
                    indice = Integer.parseInt(this.scanner.nextLine().trim());
                }
                break;

            case "plateau2":
                indice = -1;
                while (indice < 0 || indice > 3) {
                    System.out.println("Choisir emplacement [0-3]:");
                    indice = Integer.parseInt(this.scanner.nextLine().trim());
                }
                break;

            case "centre":
                indice = 0;
                break;
            default:
                throw new IllegalArgumentException("Mauvais symbol");
            }
        return indice;
    }


     
    public void ajouter_lettre_dans_jeu(String Choix_plateau, Lettre Lettre_choisi) {

        int indice = this.selectionner_indice_selon_plateau(Choix_plateau);

        switch (Choix_plateau) {
            case "plateau1":
                this.plateau.ajouter_lettre_plateau1_trigonometrique(indice, Lettre_choisi);
                break;
            case "plateau2":
                this.plateau.ajouter_lettre_plateau2_trigonometrique(indice, Lettre_choisi);
                break;
            case "centre":
                this.plateau.ajouter_lettre_centre_trigonometrique(Lettre_choisi);
                break;
            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }

        System.out.println("\n================= Placement [" + Lettre_choisi + "] ================");
        this.plateau.plateau_affichage();
    }

    public String choisir_symbole_a_utiliser_ou_non()
    {
        boolean condition = false;
        String Choix = "";

        // on demande jusqu'à avoir un symbole valide 
        while (!condition) {
            System.out.println("\nSymboles Speciaux[Gestion apparition PAS IMPLEMENTE] :");
            System.out.println(this.joueur.toString_symboles());
            System.out.println("Entree[passe], 0[symbole_rouge], 1[symbole_noir], 2[symbole_vert, PAS IMPLENTEE]");
            Choix = this.scanner.nextLine().trim();
            switch (Choix) {
                case "0":
                    // renvoi true si on peut utiliser ce symbol
                    condition = this.joueur.can_use_symbol("symbole_rouge");
                    break;

                case "1":
                    condition = this.joueur.can_use_symbol("symbole_noir");
                    break;

                case "2":
                    condition = this.joueur.can_use_symbol("symbole_vert");
                    break;

                // on sort dans tous les autres cas
                default:
                    condition = true;
                    break;
            }
        }
        return Choix;
    }

    public void symbole_fusion(boolean symbol_plus_noir, String message_error_symbol, String message_affichage) {

        boolean condition = false;

        String Choix_plateau1 = "";
        String Choix_plateau2 = "";

        int indice1 = -1;
        int indice2 = -1;

        int fusion_score;

        while (!condition) {
            System.out.println("\nFusion [+" + message_affichage + "] sur 1er lettre selectionne");

            Choix_plateau1 = this.Choisir_un_des_3_plateau();
            indice1 = this.selectionner_indice_selon_plateau(Choix_plateau1);

            Choix_plateau2 = this.Choisir_un_des_3_plateau();
            indice2 = this.selectionner_indice_selon_plateau(Choix_plateau2);

            if (this.plateau.est_ce_fusionnable(indice1, Choix_plateau1, indice2, Choix_plateau2, symbol_plus_noir)) {
                condition = true;
            } else {
                String message_error = "\nLa lettre [" + Choix_plateau1 + "," + indice1 + "] et La lettre [";
                message_error += Choix_plateau2 + "," + indice2
                        + "] ne sont pas fusionnable dans le cadre du symbole" + message_error_symbol;
                System.out.println(message_error);
                this.plateau.plateau_affichage();
            }
        }
        fusion_score = this.plateau.fusion_lettre_symbole(indice1, Choix_plateau1, indice2, Choix_plateau2,
                symbol_plus_noir);
        this.joueur.add_score_fusion(fusion_score);

        System.out.println(
                "\n================== Fusion +" + message_affichage + "[+" + fusion_score + "pts] ==================");
        this.plateau.plateau_affichage();
    }

    public void symbole_vert() {
        
        System.out.println("\nSuppression lettre [vert+]");
        String Choix_plateau = this.Choisir_un_des_3_plateau();
        int indice = this.selectionner_indice_selon_plateau(Choix_plateau);

        System.out.println("\n================== Suppresion +vert[" + Choix_plateau + "," + indice + "]  =========================");
        this.plateau.plateau_affichage();
    }


    public void symbol_speciaux()
    {
        // On va proposer au joueur d'utiliser un des symboles qu'il a en reserve
        // String symbole_choix;

        // s'il a un symbole utilisable
        if (this.joueur.can_use_one_symbol()) {
            
            // BUG ICI, on ne verifie pas qu'il existe bien dans le jeu la possibilite d'utiliser ce symbole
            // on verifie unqiuement qu'il existe dans la reserve du joueur 
            String Choix = this.choisir_symbole_a_utiliser_ou_non();

            // on enleve le symbol de la reserve et on l'utilise, en cas de mauvais caractère on sort
            switch (Choix) {
                case "0":
                    this.joueur.use_one_symbol("symbole_rouge");

                    this.symbole_fusion(false, " rouge (identique, emplacements differents)", "rouge");
                    break;

                case "1":
                    this.joueur.use_one_symbol("symbole_noir");

                    this.symbole_fusion(true, " noir (adjacents uniquement)", "noir");
                    break;

                case "2":
                    this.joueur.use_one_symbol("symbole_vert");
                    this.symbole_vert();
                    this.plateau.plateau_affichage();
                    break;
                // on sort dans tous les autres cas
                default:
                    break;
            }
        }
    }

    public void fusions_en_cascade(){
        //realisation des fusions
        int fusion_score;
        int compteur_fusion = 1;
        while (this.plateau.existe_il_une_fusion_ds_tout_le_jeu()) {
            
            fusion_score = this.plateau.faire_une_fusion_ds_le_jeu();
            System.out.println("\n================== Fusion " + compteur_fusion + "[+"+ fusion_score+"pts]==================");
            this.plateau.plateau_affichage();
            this.joueur.add_score_fusion(fusion_score);
            compteur_fusion += 1;
        }      
    }

    public void next_tour()
    {
        this.plateau.plateau_affichage();

        // on tire au sort une lettre
        Lettre Lettre_choisi = this.plateau.ChoisitLettreHasard();
        System.out.println("Lettre aleatoire: [" + Lettre_choisi + "]");

        // on choisi un plateau du jeu parmi (plateau1/plateau2/centre) avec un emplacement vide
        String Choix_plateau = this.choisir_un_plateau_ou_un_emplacement_vide_existe();

        // on ajoute une lettre sur le plateau choisi parmi (plateau1/plateau2/centre)
        this.ajouter_lettre_dans_jeu(Choix_plateau, Lettre_choisi);

        // on propose d'utiliser si le joueur en possede un symbole
        this.symbol_speciaux();

        // on fait les fusions en cascade
        this.fusions_en_cascade();

        // A la fin, on doit incrementer le compteur de toutes les lettres majuscules
        // bug possible sur les lettres majsucules heritees de la fusion de lettres ou celle
        //  qui vient d'être chsoisie et posee dans le jeu (Lettre_choisi)
        // (mais on a mis -1 comme compteur de temps pour cette raison sur ces lettres)
        this.plateau.incrementer_tous_les_compteurs_temps_majuscules();
    }
    
    public void start()
    {
        this.compteur_tour += 1;

        // tant qu'il y a un emplacement vide dans le jeu, on contiue à jouer 
        while (this.plateau.existe_il_un_emplacement_vide_ds_jeu())
        {
            System.out.println("\n==============================================");
            System.out.println("================== Tour " +  this.compteur_tour + " ==================");
            System.out.println("==============================================");
            System.out.println(this.joueur.toString());
            System.out.println(this.joueur.toString_symboles());
            
            this.next_tour();

            this.compteur_tour += 1;
        }

        System.out.println("\n===== Score final =====");
        System.out.println(this.joueur.toString());
    }
   
    
   
}

