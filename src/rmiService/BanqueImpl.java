package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import metier.Compte;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {
    private static final long serialVersionUID = 1L;

    private final transient Map<Integer, Compte> comptes = new ConcurrentHashMap<>();

    public BanqueImpl() throws RemoteException {
    }

    @Override
    public String creerCompte(Compte compte) throws RemoteException {
        if (compte == null) {
            return "Echec : le compte ne peut pas etre null.";
        }

        Compte compteExistant = comptes.putIfAbsent(compte.getCode(), compte);
        if (compteExistant != null) {
            return "Echec : le code " + compte.getCode() + " existe deja.";
        }
        return "Compte cree avec succes : " + compte;
    }

    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte compte = comptes.get(code);
        if (compte == null) {
            return "Aucun compte ne correspond au code " + code + ".";
        }
        return compte.toString();
    }
}
