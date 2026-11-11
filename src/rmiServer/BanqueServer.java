package rmiServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.Context;
import javax.naming.InitialContext;

import rmiService.BanqueImpl;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            BanqueImpl banque = new BanqueImpl();

            Context context = new InitialContext();
            context.rebind("BanqueService", banque);

            System.out.println("Serveur RMI Banque demarre sur le port 1099.");
            Thread.currentThread().join();
        } catch (Exception exception) {
            System.err.println("Erreur du serveur RMI : " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
