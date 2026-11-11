# TP5 - Service de banque avec Java RMI et JNDI

Application répartie individuelle réalisée pour l'activité 5-2. Le serveur
central gère des comptes bancaires et expose deux méthodes distantes :

- `creerCompte(Compte c)` : crée un compte si son code n'existe pas ;
- `getInfoCompte(int code)` : retourne les informations d'un compte.

## Structure

```text
src/
├── metier/Compte.java
├── rmiService/IBanque.java
├── rmiService/BanqueImpl.java
├── rmiServer/BanqueServer.java
├── rmiClient/BanqueClient.java
└── jndi.properties
```

`Compte` est sérialisable. Le serveur utilise un registre RMI local et publie
le service avec `InitialContext`. Le client effectue sa recherche avec JNDI,
sans utiliser `Naming.lookup()`.

## Compilation

Depuis le dossier du projet :

```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory out | Out-Null
javac -d out src\metier\Compte.java src\rmiService\IBanque.java src\rmiService\BanqueImpl.java src\rmiServer\BanqueServer.java
jar cf banqueClient.jar -C out metier\Compte.class -C out rmiService\IBanque.class
javac -cp banqueClient.jar -d out src\rmiClient\BanqueClient.java
Copy-Item src\jndi.properties out\jndi.properties
```

Le fichier `banqueClient.jar` contient uniquement `Compte` et `IBanque`, comme
demandé dans l'énoncé. La classe `BanqueImpl` reste uniquement côté serveur.

## Exécution

Terminal 1 :

```powershell
java -cp out rmiServer.BanqueServer
```

Terminal 2 :

```powershell
java -cp "out;banqueClient.jar" rmiClient.BanqueClient
```

Résultat attendu :

```text
Compte cree avec succes : Compte{code=1001, solde=1500.00, ...}
Echec : le code 1001 existe deja.
Compte{code=1001, solde=1500.00, ...}
Aucun compte ne correspond au code 9999.
```

## Captures d'écran

Ajouter ici les captures du projet, de la compilation, du serveur, du client,
et de la vérification de la communication RMI avec JNDI avant le dépôt.

## Dépôt

Le lien de ce repository doit être remis sur l'espace ENT.
