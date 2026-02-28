
package jeudesorbites;

/**
 *
 * @author elise_admin
 */
public class TableauLettresGrecs {
    
    private Lettre[] alphabetMin = new Lettre[24];
    private Lettre[] alphabetMaj = new LettreMajuscule[3];
    private Lettre zero_letter;
    
    
    public TableauLettresGrecs() {
        this.alphabetMin[0]= new Lettre("  alpha ",1);
        this.alphabetMin[1]= new Lettre("  beta  ",2);
        this.alphabetMin[2]= new Lettre("  gamma " ,3);
        this.alphabetMin[3]= new Lettre(" delta  " ,4);
        this.alphabetMin[4]= new Lettre("epsilson" ,5);
        this.alphabetMin[5]= new Lettre("  zeta  " ,6);
        this.alphabetMin[6]= new Lettre("   eta  " ,7);
        this.alphabetMin[7]= new Lettre("  theta " ,8);
        this.alphabetMin[8]= new Lettre("  iota  " ,9);
        this.alphabetMin[9]= new Lettre("  kappa " ,10);
        this.alphabetMin[10]= new Lettre(" lambda " , 11);
        this.alphabetMin[11]= new Lettre("   mu   " , 12);
        this.alphabetMin[12]= new Lettre("   nu   " , 13);
        this.alphabetMin[13]= new Lettre("   xi   " , 14);
        this.alphabetMin[14]= new Lettre(" omicron" , 15);
        this.alphabetMin[15]= new Lettre("   pi   " , 16);
        this.alphabetMin[16]= new Lettre("   rho  " , 17);
        this.alphabetMin[17]= new Lettre("  sigma " , 18);
        this.alphabetMin[18]= new Lettre("  tau   " , 19);
        this.alphabetMin[19]= new Lettre(" upsilon" , 20);
        this.alphabetMin[20]= new Lettre("  phi   " , 21);
        this.alphabetMin[21]= new Lettre("   chi  " , 22);
        this.alphabetMin[22]= new Lettre("   psi  " , 23);
        this.alphabetMin[23]= new Lettre("  omega " , 24);
        

        
        this.alphabetMaj[0]= new LettreMajuscule("DELTA ", 30); // avec Class Lettre Maj
        this.alphabetMaj[1]= new LettreMajuscule("LAMBDA", 40);
        this.alphabetMaj[2]= new LettreMajuscule("SIGMA ", 50);
        
        
        this.zero_letter = new Lettre("XXXXXXXX", 0);
    }
    
    public Lettre getalphabetMinuscule(int n){
        return new Lettre(this.alphabetMin[n].getnom(), this.alphabetMin[n].getvaleur());// copie indépendnates
    }
   
    
    public LettreMajuscule getalphabetMajuscule(int n, int presence){
        return new LettreMajuscule(this.alphabetMaj[n].getnom(), this.alphabetMaj[n].getvaleur(), presence);// copies indépendnates
       
    }
    
    public Lettre getzero() {
        return new Lettre(this.zero_letter.getnom(), this.zero_letter.getvaleur()); // copies indépendnate
    }
    
    public Lettre next_lettre_majuscule_vs_minuscule(Lettre a)
    {
        if (a.est_ce_une_minuscule())
        {
            return this.getalphabetMinuscule(a.get_next_indice());
        }
        else
        {
            // cas ultra particulier, le compteur de temps de présence est à -1
            // car on vient à l'instant de rajouter la lettre dans le tour
            // or à la fin du tour on incrémente les compteurs de toutes les les majuscules
            // donc pour s'assurer que le compteur tombe sur 0 à la fin du tour, on met -1
            return this.getalphabetMajuscule(a.get_next_indice(), -1);
        }
    }

    public Lettre fusion(Lettre a, Lettre b, boolean symbol_plus_noir)
    {

        if (!symbol_plus_noir)
        {
            // le cas particulier si on à la dernière lettre (et donc on n'a 
            // pas de lettre suivant) [pour omega minsucule et SIGMA majsucule] est traité par 
            // la classe lettre avec la méthode est_fusionable
            // pour s'assurer que l'on ne tombe jamais dans ce cas ici (qui crasherai)

            // Quand on fusionne deux lettres identiques, on renvoie la lettre suivante dans le tableau
            // des majuscules ou des minuscules 
            // ce qui correspond à sa valeur initiale 
            return this.next_lettre_majuscule_vs_minuscule(a);
                    
            }
        else
        {
            // si on le symbole + noir, on peut fusionner des lettres diffèrentes 
            // et récupérer la suivante de celle qui a le plus de valeur
            if (a.getvaleur() > b.getvaleur()) 
            {
                return this.next_lettre_majuscule_vs_minuscule(a);
            }
            else
            {
                return this.next_lettre_majuscule_vs_minuscule(b);
            }
        }
    }
  
   
}