# Brief 10 - ThymeLeaf

- Utilisation Spring Security
  * Page login / register
- Une page avec une liste d'objets Thymeleaf (th:each) (boucle d'affichage composant carte cliquable)
- Un formulaire (autre que login et register) avec route POST
- Une page dynamique si connecté ou non
- Une page publique
- Les autres pages (authenticated)

## Installation
1. **Clôner le dépôt afin de récupérer le projet**

    ```bash
   git clone https://github.com/tcht-marie/brief10-thymeleaf-springsecurity.git


2. **Configurer la base de données PostgreSQL**

- Se connecter à PostgreSQL
- ``psql -U postgres -h localhost``
- Créer la base de données forknow :
- ``CREATE DATABASE spring-security;``

3. **Builder le projet** :
```bash
./gradle build
```

## Construire le projet

- Utiliser la commande ``./gradlew bootrun``
- Ouvrir un navigateur et aller sur http://localhost:8080


## Utilisation du site
- La page home est accessible par tout le monde
- Pour pouvoir naviguer vers les autres pages, il faut se créer un compte utilisateur en allant sur la page "register"
- Une fois le compte crée, il faut se connecter avec la page "login"
- Accéder à la page mon compte en cliquant sur son nom d'utilisateur
- Il est possible d'ajouter un restaurant ainsi que des produits
- Vous pouvez maintenant consulter la liste des restaurants
- La liste des produits proposés par le restaurant


### Fonctionnalités non gérées
- la liaison entre produits et restaurants ne se fait pas automatiquement. Il faut le faire via ldc directement dans postgres :
  - Exemple --> product avec l'id 1 inséré dans le restaurant avec l'id 202 :
  - ``INSERT INTO product_restaurant (product_id, restaurant_id) VALUES (1, 202);``
- Par contre, si le restaurant n'a aucun produit, c'est une page d'erreur qui s'affiche
- la page Cart (panier) n'est pas gérée non plus