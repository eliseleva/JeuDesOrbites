package jeudesorbites;
import java.util.Random;
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
    private int indice_depassement_score_100;

    private int next_rouge_indice;
    private int next_noir_indice;

    public Jeu() {      
 
        // Joueur 
        // compteur tour
        // reserve symbole
        // Lettres
        this.plateau = new Plateau();
        this.joueur = new Joueur();
        this.compteur_tour = 1;
        this.scanner = new Scanner(System.in);

        // pour les symboles
        Random generateur = new Random();
        this.indice_depassement_score_100 = 0;
        this.next_rouge_indice = generateur.nextInt(6) + 5; //entre 5 et 10
        this.next_noir_indice = generateur.nextInt(6) + 10; //entre 10 et 15
    }
       
    public String Choisir_un_des_3_plateau() {
        boolean condition = false;
        String Choix;
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
                this.message_erreur("[Mauvaise entree: " + Choix + "]");
            }

        }
        return res;
    }

    public void message_erreur(String message_erreur) {
        System.out.println("\nERROR : " + message_erreur);
        // on attend une entrée de l'utlisateur
        this.scanner.nextLine().trim();
    }

    public int choisir_indice(int max) {
        boolean condition = false;
        String Choix="";
        
        while (!condition) {
            System.out.println("\nChoisir emplacement [0-" + max +"]:");
            Choix = this.scanner.nextLine().trim();
            condition = Choix.matches("[0-" + max + "]");

            if (!condition) {
                this.message_erreur(Choix + " pas dans [0-" + max + "]");
            }
        }
        return Integer.parseInt(Choix);
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
                this.message_erreur("Le " + Choix_plateau +" n'a pas d'emplacement vide");
            }
        }

    return Choix_plateau;
    }


    public int selectionner_indice_selon_plateau(String Choix_plateau) {

        int indice;

        //plateau 1
        switch(Choix_plateau){
            case "plateau1":
                indice = this.choisir_indice(7);
                break;

            case "plateau2":
                indice = this.choisir_indice(3);
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
            System.out.println("\nSymboles Speciaux :");
            System.out.println(this.joueur.toString_symboles());
            System.out.println("Entree[passe], 0[+rouge], 1[+noir], 2[+vert]");
            Choix = this.scanner.nextLine().trim();
            switch (Choix) {
                case "0":

                    // BUG ICI a implementer un truc qui teste s'il existe deux lettres identique dans le jeu
                    condition = this.joueur.can_use_symbol("symbole_rouge");
                    if (!condition) {
                        this.message_erreur("[+rouge n'est pas utilisable à ce tour]");
                    }
                    break;

                case "1":
                    // BUG ICI a implementer un truc qui teste s'il existe deux lettres adjacentes
                    condition = this.joueur.can_use_symbol("symbole_noir");
                    if (!condition) {
                        this.message_erreur("[+noir n'est pas utilisable à ce tour]");
                    }
                    break;

                case "2":
                    // Pour pouvoir utiliser ce symbole, il doit y avoir au moins un emplacement libre dans le jeu
                    condition = this.joueur.can_use_symbol("symbole_vert")
                            && this.plateau.existe_il_un_emplacement_vide_ds_jeu();
                    if (!condition) {
                        this.message_erreur("[+vert n'est pas utilisable à ce tour]");
                    }
                    break;

                // on sort dans tous les autres cas
                default:
                    condition = true;
                    break;
            }
        }
        return Choix;
    }

    public void symbole_fusion_symbole_rouge_ou_noir(boolean symbol_plus_noir, String message_error_symbol, String message_affichage) {
        // methode pour la fusion avec le symbole +rouge et +noir

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
                String message_error = "La lettre [" + Choix_plateau1 + "," + indice1 + "] et La lettre [";
                message_error += Choix_plateau2 + "," + indice2
                        + "] ne sont pas fusionnable dans le cadre du symbole" + message_error_symbol;
                this.message_erreur(message_error);
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

    public void symbole_vert_deplacement_lettre() {
        
        System.out.println("\n== Suppression lettre [vert+] ==");

        boolean condition1 = false;
        boolean condition2 = false;

        String Choix_plateau1 = "";
        String Choix_plateau2 = "";

        int indice1 = -1;
        int indice2 = -1;

        while (!condition1) {

            System.out.println("\n======Choisir lettre non vide=====");
            Choix_plateau1 = this.Choisir_un_des_3_plateau();
            indice1 = this.selectionner_indice_selon_plateau(Choix_plateau1);
            condition1 = (!this.plateau.est_ce_une_lettre_vide(indice1, Choix_plateau1));
        
            if (!condition1) {
                this.message_erreur(Choix_plateau1 +"[" + indice1 +"] est vide");
            }
        }
        
        while (!condition2) {
            
            System.out.println("\n======Choisir un emplacement vide=====");
            Choix_plateau2 = this.Choisir_un_des_3_plateau();
            indice2 = this.selectionner_indice_selon_plateau(Choix_plateau2);
            condition2 = this.plateau.est_ce_une_lettre_vide(indice2, Choix_plateau2);
        
            if (!condition2) {
                this.message_erreur(Choix_plateau2 +"[" + indice2 +"] n est pas vide");
            }
        }
        
        this.plateau.deplacer_lettre(indice1, Choix_plateau1, indice2, Choix_plateau2);

    
        System.out.println("\n================== +vert " + Choix_plateau1 + "[" + indice1 + "] => " + Choix_plateau2 +"[" +indice2 + "]   =========================");
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

                    this.symbole_fusion_symbole_rouge_ou_noir(false, " rouge (identique, emplacements differents)", "rouge");
                    break;

                case "1":
                    this.joueur.use_one_symbol("symbole_noir");

                    this.symbole_fusion_symbole_rouge_ou_noir(true, " noir (adjacents uniquement)", "noir");
                    break;

                case "2":
                    this.joueur.use_one_symbol("symbole_vert");
                    this.symbole_vert_deplacement_lettre();
                    break;
                // on sort dans tous les autres cas
                default:
                    break;
            }
        }
    }

    public void fusions_en_cascade() {
        //realisation des fusions
        int fusion_score;
        int compteur_fusion = 1;
        while (this.plateau.existe_il_une_fusion_ds_tout_le_jeu()) {

            fusion_score = this.plateau.faire_une_fusion_ds_le_jeu();
            System.out.println(
                    "\n================== Fusion " + compteur_fusion + "[+" + fusion_score + "pts]==================");
            this.plateau.plateau_affichage();
            this.joueur.add_score_fusion(fusion_score);
            compteur_fusion += 1;
        }
    }
    
    public void ajout_symboles() {
        Random generateur = new Random();

        // symbole rouge tous les 5/10 tours
        if (this.compteur_tour == this.next_rouge_indice) {
            this.next_rouge_indice = this.compteur_tour + generateur.nextInt(6) + 5;
            this.joueur.add_one_symbol("symbole_rouge");  
        }

        // symbole noir tous les 10/15 tours
        if (this.compteur_tour == this.next_noir_indice) {
            this.next_noir_indice = this.compteur_tour + generateur.nextInt(6) + 10;
            this.joueur.add_one_symbol("symbole_noir");  
        }

        // symbole vert, tous les 5 tours des que le score depasse 100
        if (this.joueur.getScore() > 100) {
            // si c'est la première fois qu'on depasse 100, on garde en mémoire l'indice 
            if (this.indice_depassement_score_100 == 0) {
                this.indice_depassement_score_100 = this.compteur_tour;
            }
            // à partir de l'indice gardé en mémoire tel score>100 pour la 1er fois, tous les 5 tours, on donne un bonus
            if ((this.compteur_tour-this.indice_depassement_score_100)%5==0){
                this.joueur.add_one_symbol("symbole_vert");  
            } 
        }
    }

    public void next_tour()
    {

        System.out.println("\n==============================================");
        System.out.println("================== Tour " +  this.compteur_tour + " ==================");
        System.out.println("==============================================");

        // la méthode gère tout
        this.ajout_symboles();

        System.out.println(this.joueur.toString());
        System.out.println(this.joueur.toString_symboles());    
        
        // affichage jeu
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
        System.out.println("\nLancement du jeu des orbites ...");
        System.out.println("Appuyer pour continuer");
        
        this.scanner.nextLine().trim();
        
        // tant qu'il y a un emplacement vide dans le jeu, on contiue à jouer 
        while (this.plateau.existe_il_un_emplacement_vide_ds_jeu())
        {

            // on lance le tour de jeu
            this.next_tour();

            this.compteur_tour += 1;
        }

        System.out.println("\n===== Score final =====");
        System.out.println(this.joueur.toString());
        this.scanner.nextLine().trim();
    }

}

