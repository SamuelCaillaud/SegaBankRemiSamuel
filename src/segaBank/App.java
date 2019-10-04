package segaBank;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        int choix = 0;
        int idCompte;
        Scanner sc = new Scanner(System.in);

        while (choix < 1 || choix > 5) {afficherMenu();}
        switch (choix){
            idCompte = sc;
            case 1 :
                voirCompte(idCompte);
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;

        }
    }


    public static void afficherMenu(){
        System.out.println("Bienvenue dans l'application SegaBank \n Que voulez-vous faire ?");
        System.out.println(" 1 - Voir un compte ");
        System.out.println(" 2 - Supprimer un compte ");
        System.out.println(" 3 - Ajouter un compte ");
        System.out.println(" 4 - Modifier un compte ");
        System.out.println(" 4 - Fermer l'application ");
    }

    public static void voirCompte(){

    }

}
