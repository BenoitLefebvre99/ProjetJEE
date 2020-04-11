# Projetprogweb

 Bienvenue sur notre projet de programmation web.
 Membres de l'équipe : Jacquot William, Marcant Aimé, Diallo Omar.

## Pour démarrer le projet 

 Il est nécessaire d'exécuter le script WEB-INF/lib/progweb.sql . 
 Ce dernier contient la structure de la base de données mais aussi quelques exemples permettant de tester l'application.
 Il vous faut bien évidemment un serveur [Tomcat](https://tomcat.apache.org/download-70.cgi). 
 
 ### Il est important de modifier l'url dans contInteger.java pour faire fonctionner les includes

### La tâche est maintenant simplifée grâce au script WEB-INF/compilation.sh qui éteint puis démarre tomcat8 et compile les fichiers 


## Compilation des fichiers java (Optionnel si vous utilisez compilation.sh)

 Le projet a besoin de certaines librairies qui ne sont pas disponibles par défaut. Nous les avons ajouté au dossier WEB-INF/lib.

 Il faut donc ajouter ces lignes à votre .bashrc : 
```
 CLASSPATH=".:/home/benoitlefebvre99/tomcat8/webapps/projetprogweb/WEB-INF/lib/postgresql.jar:/home/benoitlefebvre99/tomcat8/webapps/projetprogweb/WEB-INF/lib/servlet-api.jar:/home/benoitlefebvre99/tomcat8/webapps/projetprogweb/WEB-INF/lib/commons-io-2.6.jar"
export CLASSPATH
```


 Puis éxécuter :` source .bashrc`

## Configuration de la VM

 Après avoir créer a la VM, il vous faut configurer le fichier /tomcat8/webapps/projetprogweb/WEB-INF/lib/bdd.java:
 1.  Ajoutez l'adresse IP de la VM dans le champ String ip du fichier bdd.java.
 2.  Recompilez le tout. `javac *.java`.
 3.  Exécutez le script progweb.sql.
 4.  Connectez vous en FTP sur un client (Filezila par exemple).
 5.  Copier le fichier /tomcat8/webapps/projetprogweb/WEB-INF/lib/progweb/.
 6.  Enfin éxécutez le fichier en ssh `\i progweb.sql`
 
## Erreur 
>  FATAL : no pg_hba.conf entry for host"x.x.x.x", user"tc", database"progweb", SSL off

Il faut se connecter en ssh sur votre VM et modifier le fichier pg_hba.conf.
Lien de dépannage : [lien](https://confluence.atlassian.com/jirakb/error-connecting-to-database-fatal-no-pg_hba-conf-entry-for-host-x-x-x-x-user-jiradbuser-database-jiradb-ssl-off-950801726.html)
