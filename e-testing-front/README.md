# Bienvenue sur E-Testing


E-Testing est une application qui permet aux enseignants de proposer un examen sous forme de QCM électronique.
Il a la possibilité de les importer au format XML et de les exporter.  

##  Les technologies

### Back

* [Spring MVC](https://spring.io/)
* [Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/fr/)

### Front
* [Angular CLI](https://cli.angular.io/)
* [MaterializeCss](http://materializecss.com/)

## Installation

### Configuration de l'API REST

* [Java JDK Version 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [Eclipse Java EE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2)
	* Installer le plugin Spring Tools disponible sur le Markelplace d'eclipse.
* Clonez le projet dans votre entrepôt :

		git clone https://github.com/gtandu/e-testing.git
* Dans votre workspace d'Eclipse : **Import -> Maven -> Existing Maven Projects**
* Cliquez droit sur votre projet : **Run as -> Maven Install** pour télécharger les libs.

### Test de l'API Rest

* Vous pouvez tester l'api sans deployer le client Angular via l'application [Postman](https://www.getpostman.com/)
* Vous pouvez importer les requetes :  [Requetes Postman](https://www.getpostman.com/collections/6cd0109fd86a79fae341)
* **IMPORTANT TOUTES LES REQUETES NECESSITE UN TOKEN VALIDE**. Il faut donc le generer en premier et l'inclure dans le header de chaque requete. 

### Base de données

* Créer une base de données MySQL :
	* **Name** :  etesting
	* **Port** : 3306
	* **Username** : root
	* **Password** : root

* Vous pouvez adapter votre base de données (Adresse, port, nom, mot de passe) dans **src/main/resources/application-prod.properties**

		spring.datasource.url = jdbc:mysql://localhost:3306/etesting
		spring.datasource.username = root
		spring.datasource.password = root	
			
				
### Démarrer le serveur
* Sélectionnez votre projet
*  **Cliquez droit -> Run as -> Run Configurations**
*  Créer une nouvelle configuration
	* Main type : fr.etesting.etesting.ETestingApplication
	* Profile : dev (Si vous souhaitez utilisez la base de données mémoire)
	* Profile : prod (Pour utiliser votre propre base de données configuré au préalable.)
	* Terminer par **"Apply"** ensuite **"Run"**. 
*  Lorsque vous ouvrez la console, vous devriez voir  :

		2017-06-27 13:24:50 INFO  o.s.j.e.a.AnnotationMBeanExporter - Registering beans for JMX exposure on startup
		2017-06-27 13:24:50 INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer - Tomcat started on port(s): 8080 (http)
		2017-06-27 13:24:50 INFO  fr.etesting.etesting.ETestingApplication - Started ETestingApplication in 14.631 seconds (JVM running for 16.048)
			
*  Votre serveur est lancé

### Configuration du Front

* [Installer Node.js](https://nodejs.org/en/)
* [Installer Angular CLI](https://cli.angular.io/)
* Lancez la commande **npm install** dans le répertoire **e-testing-front**
* Puis saisissez la commande suivante pour demarrer le client :
**`ng serve`** 
* Vous devriez voir les informations suivantes :

	`webpack: Compiled successfully.`
      
* Votre serveur est lancé.
* Ouvrez votre navigateur et allez à l'adresse suivante : [http://localhost:4200/login](http://localhost:4200/login)
* Ouvrez le fichier suivant "**authentification.service.ts**" dans le dossier **e-testing-front/src/app/services/authentification.service.ts**
* Modifier l'adresse du serveur pour qu'elle pointe vers votre API REST

		constructor(private http: Http) {
			/../
        	this.server = 'http://localhost:8080';
        }

### Compte Utilisateur

**Compte n°1 (Admin)**

	User : g.tandu@hotmail.fr
	Password : edu

**Compte n°2**

	User : didier.courtaud@univ-evry.fr
	Password : edu

**Compte n°3**

	User : mapella.corentin@gmail.com
	Password : ens



