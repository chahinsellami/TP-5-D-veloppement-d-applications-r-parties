package metier;

import java.io.Serializable;
import java.util.Date;

public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int code;
    private double solde;
    private final Date dateCreation;

    public Compte(int code, double solde) {
        this(code, solde, new Date());
    }

    public Compte(int code, double solde, Date dateCreation) {
        this.code = code;
        this.solde = solde;
        this.dateCreation = new Date(dateCreation.getTime());
    }

    public int getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public Date getDateCreation() {
        return new Date(dateCreation.getTime());
    }

    @Override
    public String toString() {
        return String.format(
                "Compte{code=%d, solde=%.2f, dateCreation=%s}",
                code, solde, dateCreation);
    }
}
