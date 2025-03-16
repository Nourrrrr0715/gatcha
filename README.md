# Gatcha

## Membres du groupe : Inès ZAHEM et Nour El Houda EL BOUZ

## Installation et exécution

### 1. Cloner le projet

```bash
git clone <repo_url>
cd gatcha
```

### 2. Lancer l’application avec Docker

```bash
docker-compose up --build -d
```

L’ensemble des microservices sera lancé automatiquement.

### 3. Accès aux API

Une fois le projet lancé, les API seront accessibles aux adresses suivantes :

- **Authentification** : `http://localhost:8080/auth`
- **Joueurs** : `http://localhost:8081/player`
- **Monstres** : `http://localhost:8082/monster`
- **Invocation** : `http://localhost:8083/invocation`

## Architecture du projet

Le projet est structuré en plusieurs microservices distincts :

- **authentication/** : Gère les utilisateurs et leur authentification.
- **player/** : Gère les comptes joueurs, les niveaux et l’expérience.
- **monster/** : Gère les monstres et leurs statistiques.
- **invocation/** : Gère le tirage aléatoire des monstres et leur attribution aux joueurs.
- **docker-compose.yml** : Fichier de configuration permettant l’orchestration des services via Docker.

Chaque microservice contient les éléments suivants :

- **`src/`** : Code source du service.
- **`Dockerfile`** : Conteneurisation du service.
- **`pom.xml`** : Gestion des dépendances et configuration Maven.

## Fonctionnalités des API

### API d’authentification (`/auth`)
- Permet la connexion des utilisateurs avec un **identifiant** et un **mot de passe**.
- Génère un **token d’authentification** valide pour une durée d’une heure.
- Stocke les identifiants et les tokens en base de données.
- Vérifie la validité du token à chaque requête.
- Met à jour la date d’expiration du token à chaque requête authentifiée.

### API Joueur (`/player`)
- Permet la gestion des joueurs :
  - **Création et mise à jour d’un compte joueur**.
  - **Consultation des informations du profil joueur**.
  - **Gestion du niveau et de l’expérience**.
  - **Gestion de la liste de monstres possédés** (ajout et suppression de monstres).
  - **Calcul automatique de l’expérience nécessaire pour le passage au niveau suivant**.

### API Monstres (`/monster`)
- Permet la gestion des monstres :
  - **Stockage et récupération des caractéristiques des monstres**.
  - **Amélioration des compétences et montée en niveau des monstres**.
  - **Gestion des statistiques de combat** : points de vie, attaque, défense, vitesse.
  - **Attribution d’un monstre spécifique à un joueur**.
  - **Application de points d’amélioration à chaque montée de niveau**.

### API Invocation (`/invocation`)
- **Gère le tirage aléatoire d’un monstre en fonction d’un système de probabilité**.
- **Utilise une base de données contenant les probabilités d’invocation de chaque monstre**.
- **Génère un monstre aléatoire et l’attribue à un joueur**.
- **Vérifie la capacité maximale du joueur avant l’ajout d’un monstre**.
- **Assure la persistance des données en cas de problème réseau ou d’échec d’invocation**.

## Interface utilisateur

Le projet inclut une **interface web minimaliste** en JavaScript permettant aux joueurs d’invoquer un monstre en cliquant sur un bouton. L’interface effectue un appel à l’API `/invocation` et affiche les informations du monstre invoqué.

## Tests et validation

- **Chaque API est accompagnée de tests unitaires**.
- **Des tests spécifiques sont réalisés pour garantir la conformité du système d’invocation**.
- **Vérification de l’algorithme d’invocation sur un grand nombre d’essais afin de respecter les taux d’apparition définis**.

