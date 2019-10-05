package segaBank;

import segaBank.DAL.ComptesDAO;
import segaBank.DAL.IDAO;
import segaBank.bo.Compte;
import segaBank.bo.CompteEpargne;
import segaBank.bo.ComptePayant;
import segaBank.bo.CompteSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        initProgram();
    }

    public static void initProgram() throws SQLException, IOException, ClassNotFoundException {
        int choix = 0;
        Scanner sc = new Scanner(System.in);

        while (choix < 1 || choix > 5) {
        afficherMenu();
        choix = sc.nextInt();
        }
        switch (choix){
            case 1 :
                voirCompte();
                break;
            case 2 :
                ajouterCompte();
                break;
            case 3 :
                supprimerCompte();
                break;
            case 4 :
                modifierCompte();
                break;
            case 5 :
                break;
        }
    }

    public static void afficherMenu(){
        System.out.println("Bienvenue dans l'application SegaBank \n Que voulez-vous faire ?");
        System.out.println(" 1 - Voir un compte ");
        System.out.println(" 2 - Ajouter un compte ");
        System.out.println(" 3 - Supprimer un compte ");
        System.out.println(" 4 - Modifier un compte ");
        System.out.println(" 5 - Fermer l'application ");
    }

    public static void voirCompte() throws SQLException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int typeCompte = 0;
        int idCompte = -1;
        while (typeCompte < 1 || typeCompte > 4){
            typeCompte = sc.nextInt();
            System.out.println("Quel type de compte souhaitez-vous ajouter ? ");
            System.out.println("1 - Compte simple ");
            System.out.println("2 - Compte epargne ");
            System.out.println("3 - Compte payant ");
            System.out.println("4 - Annuler ");
        }
        switch(typeCompte){
            case 1:
                IDAO<Long, Compte> daoSimple = new CompteSimpleDAO();
                idCompte = voirCompteFonction();
                if (idCompte == 0) {
                    System.out.println("Liste des comptes : ");
                    System.out.println(daoSimple.findAll());
                } else {
                    System.out.println("Compte recherché : ");
                    CompteSimple compteSimple = new CompteSimple(idCompte,0000);
                    daoSimple.select(compteSimple);
                }
                initProgram();
                break;
            case 2:
                IDAO<Long, Compte> daoEpargne = new CompteEpargneDAO();
                idCompte = voirCompteFonction();
                if (idCompte == 0) {
                    System.out.println("Liste des comptes : ");
                    System.out.println(daoEpargne.findAll());
                } else {
                    System.out.println("Compte recherché : ");
                    CompteEpargne compteEpargne = new CompteEpargne(idCompte,0000, 0);
                    daoEpargne.select(compteEpargne);
                }
                initProgram();
                break;
            case 3:
                IDAO<Long, Compte> daoPayant = new ComptePayantDAO();
                idCompte = voirCompteFonction();
                if (idCompte == 0) {
                    System.out.println("Liste des comptes : ");
                    System.out.println(daoPayant.findAll());
                } else {
                    System.out.println("Compte recherché : ");
                    ComptePayant comptePayant = new ComptePayant(idCompte,0000);
                    daoPayant.select(comptePayant);
                }
                initProgram();
                break;
        }
    }

    public static int voirCompteFonction(){
        int idCompte = -1;
        Scanner sc = new Scanner(System.in);
        while (idCompte < 0) {
            System.out.println("Sélectionnez l'id du compte que vous souhaitez afficher (entrer 0 pour lister tous les comptes)");
            idCompte = sc.nextInt();
        }
        return idCompte;
    }

    public static void ajouterCompte() throws SQLException, IOException, ClassNotFoundException {
        int typeCompte = 0;
        int idCompte;
        double solde;

        Scanner sc = new Scanner(System.in);
        while (typeCompte < 1 || typeCompte > 4){
            typeCompte = sc.nextInt();
            System.out.println("Quel type de compte souhaitez-vous ajouter ? ");
            System.out.println("1 - Compte simple ");
            System.out.println("2 - Compte epargne ");
            System.out.println("3 - Compte payant ");
            System.out.println("4 - Annuler ");
        }
        switch (typeCompte){
            case 1 :
                IDAO<Long, Compte> daoSimple = new CompteSimpleDAO();
                double decouvert;
                // public CompteSimple(int id, double solde, double decouvert) {
                System.out.println("Solde du compte ? : ");
                solde = sc.nextDouble();
                System.out.println("Decouvert du compte ? : ");
                decouvert = sc.nextDouble();
                CompteSimple compteSimple = new CompteSimple(1,solde,decouvert);
                daoSimple.select(compteSimple);
                break;
            case 2 :
                IDAO<Long, Compte> daoEpargne = new CompteEpargneDAO();
                double tauxInteret;
                System.out.println("Solde du compte ? : ");
                solde = sc.nextDouble();
                System.out.println("Taux d'interets  du compte ? : ");
                tauxInteret = sc.nextDouble();
                CompteEpargne compteEpargne = new CompteEpargne(1,solde,tauxInteret);
                daoEpargne.select(compteEpargne);
                break;
            case 3 :
                IDAO<Long, Compte> daoPayant = new ComptePayantDAO();
                System.out.println("Solde du compte ? : ");
                solde = sc.nextDouble();
                ComptePayant comptePayant = new ComptePayant(1,solde);
                daoPayant.select(comptePayant);
                break;
            case 4 :
                initProgram();
                break;
        }
    }

    public static void supprimerCompte() throws SQLException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int idCompte = -1;
        int typeCompte = 0;
        String nomCompte = null;

        while(idCompte<1 && nomCompte!=null){
            System.out.println("Selectionnez l'id du compte a supprimer");
            idCompte = sc.nextInt();
        }
        while (typeCompte<1 || typeCompte>3){
            System.out.println("Type de compte : ");
            System.out.println("1 - Compte simple ");
            System.out.println("2 - Compte epargne ");
            System.out.println("3 - Compte payant ");
            typeCompte = sc.nextInt();
        }
        switch (typeCompte){
            case 1 :
                IDAO<Long, Compte> daoSimple = new CompteSimpleDAO();
                CompteSimple compteSimple = new CompteSimple(idCompte,0000,0000);
                daoSimple.delete(compteSimple);
                break;
            case 2 :
                IDAO<Long, Compte> daoEpargne = new CompteEpargneDAO();
                CompteEpargne compteEpargne = new CompteEpargne(idCompte,0000,0000);
                daoEpargne.delete(compteEpargne);
                break;
            case 3 :
                IDAO<Long, Compte> daoPayant = new ComptePayantDAO();
                ComptePayant comptePayant = new ComptePayant(idCompte,0000);
                daoPayant.delete(comptePayant);
                break;
        }
    }

    public static void modifierCompte() throws SQLException, IOException, ClassNotFoundException {
        int typeCompte = 0;
        int idCompte;
        double solde;

        Scanner sc = new Scanner(System.in);
        while (typeCompte < 1 || typeCompte > 4){
            typeCompte = sc.nextInt();
            System.out.println("Quel type de compte souhaitez-vous ajouter ? ");
            System.out.println("1 - Compte simple ");
            System.out.println("2 - Compte epargne ");
            System.out.println("3 - Compte payant ");
            System.out.println("4 - Annuler ");
        }
        switch (typeCompte){
            case 1 :
                IDAO<Long, Compte> daoSimple = new CompteSimpleDAO();
                double decouvert;
                // public CompteSimple(int id, double solde, double decouvert) {
                System.out.println("Nouveau solde du compte ? : ");
                solde = sc.nextDouble();
                System.out.println("Nouveau découvert du compte ? : ");
                decouvert = sc.nextDouble();
                CompteSimple compteSimple = new CompteSimple(1,solde,decouvert);
                daoSimple.update(compteSimple);
                break;
            case 2 :
                IDAO<Long, Compte> daoEpargne = new CompteEpargneDAO();
                double tauxInteret;
                System.out.println("Nouveau solde du compte ? : ");
                solde = sc.nextDouble();
                System.out.println("Nouveau taux d'interet du compte ? : ");
                tauxInteret = sc.nextDouble();
                CompteEpargne compteEpargne = new CompteEpargne(1,solde,tauxInteret);
                daoEpargne.update(compteEpargne);
                break;
            case 3 :
                IDAO<Long, Compte> daoPayant = new ComptePayantDAO();
                System.out.println("Nouveau solde du compte ? : ");
                solde = sc.nextDouble();
                ComptePayant comptePayant = new ComptePayant(1,solde);
                daoPayant.update(comptePayant);
                break;
            case 4 :
                initProgram();
                break;
        }
    }
}
