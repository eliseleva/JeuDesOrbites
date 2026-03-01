package jeudesorbites;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author elise_admin
 */
public class Plateau {
   
    private Lettre[] plateau1 = new Lettre[8];// attribut
    private Lettre[] plateau2 = new Lettre[4];
    private Lettre[] centre = new Lettre[1];
    private TableauLettresGrecs tableau_lettre_grec;

    public Plateau() { // constructeur
        this.tableau_lettre_grec = new TableauLettresGrecs();

        Lettre lettre_zero = this.tableau_lettre_grec.getzero();

        for (int i = 0; i < this.plateau1.length; i++) {// Nombre d element list
            this.plateau1[i] = lettre_zero;
        }

        for (int i = 0; i < this.plateau2.length; i++) {
            this.plateau2[i] = lettre_zero;
        }

        this.centre[0] = lettre_zero;

        this.AjouterPremiereLettre();

        //Class_name name_variable = new Class_name(arg1, arg2, ....);
    }
    
    public Plateau(Lettre[] plateau1, Lettre[] plateau2, Lettre[] centre) { // constructeur initialisé
        this.tableau_lettre_grec = new TableauLettresGrecs();

        this.plateau1 = plateau1;
        this.plateau2 = plateau2;
        this.centre = centre;
    }
      
    public final void AjouterPremiereLettre(){
        Random generateur =new Random();
        int nombreAlea = generateur.nextInt(13);
        
        Lettre alpha = this.tableau_lettre_grec.getalphabetMinuscule(0);
        //méthode pr acceder a l'instance, instance Class TableauLettresGrecs: this.tableau_lettre_grec
        
        if (nombreAlea<=7){
            this.plateau1[nombreAlea]= alpha;
        }
        else if ( nombreAlea<12){
            this.plateau2[nombreAlea%8]= alpha; // attention car le plateau2 a seulement 4 elements
        }
        else
        {
        this.centre[0]=alpha;
        }
    }
    
    public Lettre ChoisitLettreHasard() {

        Random rand = new Random();
        int choixLettre = rand.nextInt(5);

        if (choixLettre == 0) {
            return this.tableau_lettre_grec.getalphabetMinuscule(0);
        } else if (choixLettre == 1) {
            return this.tableau_lettre_grec.getalphabetMinuscule(1);
        }

        // cas super particulier pour le compteur 
        // vu que le compteur de toutes les lettres majsucules est incrémenté à la fin du tour
        // on met -1, pour être sur qu'à la fin du tour le compteur des lettres qui viennent d'être rajouté est à 0
        else if (choixLettre == 2) {
            return this.tableau_lettre_grec.getalphabetMajuscule(0, -1);
        } else if (choixLettre == 3) {
            return this.tableau_lettre_grec.getalphabetMajuscule(1, -1);
        } else {
            return this.tableau_lettre_grec.getalphabetMajuscule(2, -1);
        }

    }

    public Lettre renvoie_lettre_plateau(int indice, String Choix_plateau) {
        // attention ceci n'est pas une copie 
        switch (Choix_plateau) {
            case "plateau1":
                return this.plateau1[indice];
            case "plateau2":
                return this.plateau2[indice];
            case "centre":
                return this.centre[indice];
            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }
    }

    public void modife_lettre_plateau(int indice, String Choix_plateau, Lettre lettre) {
        // attention ceci n'est pas une copie 
        switch (Choix_plateau) {
            case "plateau1":
                this.plateau1[indice] = lettre;
                break;
            case "plateau2":
                this.plateau2[indice] = lettre;
                break;
            case "centre":
                this.centre[indice] = lettre;
                break;
            default:
                throw new IllegalArgumentException("Mauvais symbol");
        }
    }

    public void mettre_emplacement_vide(int indice, String Choix_plateau){
        Lettre lettre_zero = this.tableau_lettre_grec.getzero();

        this.modife_lettre_plateau(indice, Choix_plateau, lettre_zero);
    }

    public boolean est_ce_adjacent(int indice1, String Choix_plateau1, int indice2, String Choix_plateau2) {

        // si les deux lettres sont sur plateau1, elles sont adjacentes si la distance 
        // absolue des indices est de 1(cas normal) ou de 7(cas ou on referme le cercle) 
        if (Choix_plateau1.equals("plateau1") && Choix_plateau2.equals("plateau1")) {
            int diff = Math.abs(indice1 - indice2);
            return  diff== 1 || diff == 7;
        }

        // pareil pour plateau2 sauf que le cas ou on referme le cercle est de 3
        else if (Choix_plateau1.equals("plateau2") && Choix_plateau2.equals("plateau2")) {
            int diff = Math.abs(indice1 - indice2);
            return diff == 1 || diff == 3;
        }

        // ona  le meme centre deux fois, on envoie false
        else if (Choix_plateau1.equals("centre") && Choix_plateau2.equals("centre")) {
            return false;
        }

        // on a plateau1 pour le 1er et plateau2 poiur le 2eme, alors on teste si les indices ont un facteur 2
        else if (Choix_plateau1.equals("plateau1") && Choix_plateau2.equals("plateau2")) {
            return 2*indice2 == indice1;
        }

        // on a plateau2 pour le 1er et plateau1 poiur le 2eme, alors on teste si les indices ont un facteur 2
        else if (Choix_plateau1.equals("plateau2") && Choix_plateau2.equals("plateau1")) {
            return 2*indice1 == indice2;
        }
        
        // on a plateau1 pour le 1er et centre pour le 2eme ou inversement on renvoie false
        else if ((Choix_plateau1.equals("plateau1") && Choix_plateau2.equals("centre"))
                || (Choix_plateau1.equals("centre") && Choix_plateau2.equals("plateau1"))) {
            return false;
        }

        // sinon on se trouve dans le cas où on a plateau2 et le centre, donc c'est forcement adjacent
        else {
            return true;
        }
        
    }

    public boolean est_ce_fusionnable(int indice1, String Choix_plateau1, int indice2, String Choix_plateau2,
            boolean symbol_plus_noir) {

        // attntion lettre_1 et lettre_2 ne sont pas des copies indépendates
        Lettre lettre_1 = this.renvoie_lettre_plateau(indice1, Choix_plateau1);
        Lettre lettre_2 = this.renvoie_lettre_plateau(indice2, Choix_plateau2);

        if (symbol_plus_noir) {
            // on s'assure que les conditions de fusion dans le cadre symbol noir sont réunies( lettres potentiellment différentes)
            //  et les lettres doivent etre adjacentes
            return lettre_1.est_fusionable(lettre_2, symbol_plus_noir)
                    && this.est_ce_adjacent(indice1, Choix_plateau1, indice2, Choix_plateau2);
        } 
        else {
            // on s'assure que les conditions de fusion classique sont réunies et qu'on n'utilise pas deux fois la même lettre
            return lettre_1.est_fusionable(lettre_2, symbol_plus_noir)
                    && (indice1 != indice2 || Choix_plateau1 != Choix_plateau2);
        }
    }
        

    public int fusion_lettre_symbole(int indice1, String Choix_plateau1, int indice2, String Choix_plateau2, boolean symbol_plus_noir) {
        // attntion fusion doit être une copie
        Lettre lettre1 = this.renvoie_lettre_plateau(indice1, Choix_plateau1);
        Lettre lettre2 = this.renvoie_lettre_plateau(indice2, Choix_plateau2);

        Lettre fusion = this.tableau_lettre_grec.fusion(lettre1, lettre2, symbol_plus_noir);
        
        int fusion_score = lettre1.getvaleur() + lettre2.getvaleur();

        this.modife_lettre_plateau(indice1, Choix_plateau1, fusion);

        Lettre lettre_zero = this.tableau_lettre_grec.getzero();
        this.modife_lettre_plateau(indice2, Choix_plateau2, lettre_zero);

        return fusion_score;
        }
    
    
    //Plateau 1
    public boolean existe_il_un_emplacement_vide_plateau1(){
        int n=0;
        while (n<plateau1.length){
            if (plateau1[n].est_vide()){
                return true;
            }
            n++;
        }
        return false;
    }
    
    //Plateau 2
    public boolean existe_il_un_emplacement_vide_plateau2(){
        int n=0;
        while (n<plateau2.length){
            if (plateau2[n].est_vide()){
                return true;
            }
            n++;
        }
        return false;
    }
    
    //centre
    public boolean existe_il_un_emplacement_vide_centre() {
        return centre[0].est_vide();
    }

    public boolean existe_il_un_emplacement_vide_ds_jeu() {
        return this.existe_il_un_emplacement_vide_plateau1() || this.existe_il_un_emplacement_vide_plateau2()
                || this.existe_il_un_emplacement_vide_centre();
    }

    public void ajouter_lettre_plateau1_trigonometrique(int indice, Lettre Lettre_choisi)
    {
        Lettre Lettre_stockage_inter;

        while (!Lettre_choisi.est_vide()) {
            Lettre_stockage_inter = this.plateau1[indice];
            this.plateau1[indice] = Lettre_choisi;

            Lettre_choisi = Lettre_stockage_inter;

            indice = (indice + 1) % this.plateau1.length;
        }
    }
    
    public void ajouter_lettre_plateau2_trigonometrique(int indice, Lettre Lettre_choisi)
    {
        Lettre Lettre_stockage_inter;

        while (!Lettre_choisi.est_vide()) {
            Lettre_stockage_inter = this.plateau2[indice];
            this.plateau2[indice] = Lettre_choisi;

            Lettre_choisi = Lettre_stockage_inter;

            indice = (indice + 1) % this.plateau2.length;
        }
    }
    
    public void ajouter_lettre_centre_trigonometrique(Lettre Lettre_choisi)
    {
        centre[0] = Lettre_choisi;
    }

    public boolean existe_il_une_fusion_ds_tout_le_jeu()
    {
        // on va devoir checker si une lettre est egal à celle qui suit
        //  en excluant le cas ou les lettres sont nulles

        int indice_next;

        for (int i = 0; i < this.plateau1.length; i++) {
            // on teste avec le suivant (y compris pour le dernier element )
            indice_next = (i + 1) % this.plateau1.length;

            // lettres qui se suivent dans plateau1
            if (this.plateau1[i].est_fusionable(this.plateau1[indice_next], false)) {
                return true;
            }

            // les ponts entre plateau1 et plateau2 correspondent
            //  aux éléments indices pairs sur plateau1
            if (i % 2 == 0) {
                if (this.plateau1[i].est_fusionable(this.plateau2[i / 2], false)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < this.plateau2.length; i++) {
            indice_next = (i + 1) % this.plateau2.length;

            // lettres qui se suivent dans plateau2
            if (this.plateau2[i].est_fusionable(this.plateau2[indice_next], false)) {
                return true;
            }

            // les ponts entre plateau2 et le centre
            if (this.centre[0].est_fusionable(this.plateau2[i], false)) {
                return true;
            }
        }

        // si on arrive, ici aucune fusions n'existent
        return false;
    }
    
    public int faire_une_fusion_ds_le_jeu()
    {
        // on fait la fusion est on renvoie le score de celle-ci

        int fusion_score = 0;
        int indice_next;
        boolean symbol_plus_noir = false;
        Lettre lettre_zero = this.tableau_lettre_grec.getzero();

        for (int i = 0; i < this.plateau1.length; i++) {
            // on teste avec le suivant (y compris pour le dernier element )
            indice_next = (i + 1) % this.plateau1.length;

            // lettres qui se suivent dans plateau1
            if (this.plateau1[i].est_fusionable(this.plateau1[indice_next], false)) {
                // on calcule score de fusion entre les deux lettres
                fusion_score = this.plateau1[i].getvaleur() + this.plateau1[indice_next].getvaleur();
                // on genere la lettre fusionne
                Lettre fusion = this.tableau_lettre_grec.fusion(this.plateau1[i], this.plateau1[indice_next],
                        symbol_plus_noir);
                // on remplace l'un des emplacement par la fusion des lettres et on libere l autre
                this.plateau1[i] = fusion;
                this.plateau1[indice_next] = lettre_zero;
                return fusion_score;
            }

            // les ponts entre plateau1 et plateau2 correspondent
            //  aux éléments indices pairs sur plateau1
            if (i % 2 == 0) {
                if (this.plateau1[i].est_fusionable(this.plateau2[i / 2], false)) {
                    fusion_score = this.plateau1[i].getvaleur() + this.plateau2[i / 2].getvaleur();
                    Lettre fusion = this.tableau_lettre_grec.fusion(this.plateau1[i], this.plateau2[i / 2],
                            symbol_plus_noir);
                    // on tente de rapprocher la fusion au centre 
                    this.plateau1[i] = lettre_zero;
                    this.plateau2[i / 2] = fusion;
                    return fusion_score;
                }
            }
        }

        for (int i = 0; i < this.plateau2.length; i++) {
            indice_next = (i + 1) % this.plateau2.length;

            // lettres qui se suivent dans plateau2
            if (this.plateau2[i].est_fusionable(this.plateau2[indice_next], false)) {
                fusion_score = this.plateau2[i].getvaleur() + this.plateau2[indice_next].getvaleur();
                Lettre fusion = this.tableau_lettre_grec.fusion(this.plateau2[i], this.plateau2[indice_next],
                        symbol_plus_noir);
                this.plateau2[i] = fusion;
                this.plateau2[indice_next] = lettre_zero;
                return fusion_score;
            }

            // les ponts entre plateau2 et le centre
            if (this.centre[0].est_fusionable(this.plateau2[i], false)) {
                fusion_score = this.centre[0].getvaleur() + this.plateau2[i].getvaleur();
                Lettre fusion = this.tableau_lettre_grec.fusion(this.centre[0], this.plateau2[i], symbol_plus_noir);
                this.centre[0] = fusion;
                this.plateau2[i] = lettre_zero;
                return fusion_score;
            }
        }

        // on ne devrait jamais arriver ici, donc on renvoie un score eleve pour signifier un bug 
        fusion_score = 1000000;
        return fusion_score;
    }
    

    public void incrementer_tous_les_compteurs_temps_majuscules()
    {
        for (int i = 0; i < this.plateau1.length; i++) {

            // si c'est une majuscule on incrémente
            if (!this.plateau1[i].est_ce_une_minuscule()) {
                LettreMajuscule lettremaj = (LettreMajuscule) this.plateau1[i];
                lettremaj.Increment_Temps_presence();
            }
        }

        for (int i = 0; i < this.plateau2.length; i++) {
            // si c'est une majuscule on incrémente
            if (!this.plateau2[i].est_ce_une_minuscule()) {
                LettreMajuscule lettremaj = (LettreMajuscule) this.plateau2[i];
                lettremaj.Increment_Temps_presence();
            }
        }
    
        if (!this.centre[0].est_ce_une_minuscule())
            {
                LettreMajuscule lettremaj = (LettreMajuscule) this.centre[0];
                lettremaj.Increment_Temps_presence();
            }    
    }
         
    public void plateau_affichage() {
        PrintWriter printWriter = new PrintWriter(System.out, true);

        // Plateau 1 (Extérieur - 8 positions)
        String p1_0 = this.plateau1[0].toString(); // NORD (0)
        String p1_1 = this.plateau1[1].toString(); // NORD-OUEST (1)
        String p1_2 = this.plateau1[2].toString();  // OUEST (2)
        String p1_3 = this.plateau1[3].toString();  // SUD-OUEST (3)
        String p1_4 = this.plateau1[4].toString();  // SUD (4)
        String p1_5 = this.plateau1[5].toString();  // SUD-EST (5)
        String p1_6 = this.plateau1[6].toString();  // EST (6)
        String p1_7 = this.plateau1[7].toString();  // NORD-EST (7)

        // Plateau 2 (Intérieur - 4 positions)
        String p2_0 = this.plateau2[0].toString();  // NORD (0)
        String p2_1 = this.plateau2[1].toString();  // OUEST (1)
        String p2_2 = this.plateau2[2].toString();  // SUD (2)
        String p2_3 = this.plateau2[3].toString();  // EST (3)

        String c = this.centre[0].toString();  // CENTRE

        String text = "\n";
        text += "\n           ___________                 ___________              |";
        text += "\n          /                " + p1_0 + "                \\            |";
        text += "\n         /                     |                    \\           |";
        text += "\n     " + p1_1 +  "                  |                 " + p1_7 + "       |";
        text += "\n       /          _______      |       _____            \\       |";
        text += "\n      /          /          " + p2_0 + "        \\            \\      |";
        text += "\n     |           |             |             |           |      |";
        text += "\n     |           |             |             |           |      |";
        text += "\n " + p1_2 + " -- " + p2_1 + "  --  " + c + "  -- " + p2_3 + "  --  " + p1_6 + "  |";
		text += "\n     |           |             |             |           |      |";
		text += "\n     |           |             |             |           |      |";
		text += "\n      \\          \\______    " + p2_2 + "    _____/          /       |";
		text += "\n       \\                       |                       /        |";
		text += "\n      " + p1_3 + "                 |                 " + p1_5 + "       |";
		text += "\n         \\                     |                    /           |";
        text += "\n          \\___________     " + p1_4 + "    ____________/            |";
        text += "\n";
		
        printWriter.println(text);
    }
    
}
