package segaBank.bo;

public class ComptePayant extends Compte {
    private static final double PAY_PERCENT = 0.05;

    public ComptePayant(int id, double solde) {
        super(id, solde);
    }

    public static double getPayPercent() {
        return PAY_PERCENT;
    }


}
