package segaBank.bo;

public class CompteSimple extends Compte {
    private double decouvert;

    public CompteSimple(){}

    public CompteSimple(int id, double solde) {
        super(id, solde);
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompteSimple{");
        sb.append("decouvert=").append(decouvert);
        sb.append('}');
        return sb.toString();
    }
}
