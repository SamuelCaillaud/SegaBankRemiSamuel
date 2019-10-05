package segaBank.bo;

public class ComptePayant extends Compte {
    private static final double PAY_PERCENT = 0.05;

    public ComptePayant(){}

    public ComptePayant(int id, double solde) {
        super(id, solde);
    }

    public static double getPayPercent() {
        return PAY_PERCENT;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
