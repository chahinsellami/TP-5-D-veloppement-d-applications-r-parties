package rmiClient;

import javax.naming.InitialContext;

import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            IBanque banque = (IBanque) new InitialContext().lookup("BanqueService");

            Compte compte = new Compte(1001, 1500.0);
            System.out.println(banque.creerCompte(compte));
            System.out.println(banque.creerCompte(new Compte(1001, 2500.0)));
            System.out.println(banque.getInfoCompte(1001));
            System.out.println(banque.getInfoCompte(9999));
        } catch (Exception exception) {
            System.err.println("Erreur du client RMI : " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
