package segaBank.bo;

public class CompteEpargne extends Compte{
    private double tauxInterets;

    public CompteEpargne(int id, double solde, double tauxInterets) {
        super(id, solde);
        this.tauxInterets = tauxInterets;
    }

    public double getTauxInterets() {
        return tauxInterets;
    }

    public void setTauxInterets(double tauxInterets) {
        this.tauxInterets = tauxInterets;
    }

    public  double calculInteret(double solde){
        return solde+solde*tauxInterets;
    }

}
