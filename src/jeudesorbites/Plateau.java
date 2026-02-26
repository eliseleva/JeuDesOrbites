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
            this.plateau1[i]= lettre_zero;
        }
        
        for (int i = 0; i < this.plateau2.length; i++) {
            this.plateau2[i]= lettre_zero;
        }
        
        this.centre[0] = lettre_zero;
        
        this.AjouterPremiereLettre();
        
        //Class_name name_variable = new Class_name(arg1, arg2, ....);
        

    }
      
    public final void AjouterPremiereLettre(){
        Random generateur =new Random();
        int nombreAlea = generateur.nextInt(13);
        
        Lettre alpha = this.tableau_lettre_grec.getalphabetMin(0);
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
                
        if (choixLettre==0){
            return this.tableau_lettre_grec.getalphabetMin(0);
        }
        else if(choixLettre==1){
            return this.tableau_lettre_grec.getalphabetMin(1);
        }
        if (choixLettre==2){
            return this.tableau_lettre_grec.getalphabetMaj(0);
        }
        if (choixLettre==3){
            return this.tableau_lettre_grec.getalphabetMaj(1);
        }
        if (choixLettre==4){
            return this.tableau_lettre_grec.getalphabetMaj(2);
        }
    }
    
    public boolean existe_il_un_emplacement_vide(){
        int n=0;
        while (n<plateau1.length){
            if (plateau1[n].est_vide()){
                return true;
                System.out.println("Le plateau a au moins un emaplecement vide");
            }
            n++;
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

        String text = "";
        text += "\n          /‾‾‾‾‾ " + p1_0 + " ‾‾‾‾‾\\";        // Nord P1
        text += "\n         /       |       \\";
        text += "\n       " + p1_1 + "        |        " + p1_7; // NO (1) et NE (7)
        text += "\n       /         |         \\";
        text += "\n      /      /‾‾ " + p2_0 + " ‾‾\\      \\";    // Nord P2
        text += "\n     |      |    |    |      |";
        text += "\n     |      |    |    |      |";
        text += "\n     " + p1_2 + " ---- " + p2_1 + " -- " + c + " -- " + p2_3 + " ---- " + p1_6; // Ouest (2/1) -> Centre -> Est (6/3)
        text += "\n     |      |    |    |      |";
        text += "\n     |      |    |    |      |";
        text += "\n      \\      \\__ " + p2_2 + " __/      /";    // Sud P2
        text += "\n       \\         |         /";
        text += "\n        " + p1_3 + "        |        " + p1_5; // SO (3) et SE (5)
        text += "\n         \\       |       /";
        text += "\n          \\_____ " + p1_4 + " _____/";        // Sud P1

        printWriter.println(text);
    }
    
}
