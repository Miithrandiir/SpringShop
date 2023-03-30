<div>
<center>
<h1>SpringShop</h1>
<p>HEBAN Simon & SILVAIN Ludwig</p>
<hr/>
</center>
</div>

# Installation
## Prérequis

Tout d'abord, il faut installer [Docker](https://docs.docker.com/get-docker/) et [Docker Compose](https://docs.docker.com/compose/install/) et Java 17.
Ensuite, il faut cloner le frontend avec la commande suivante :
```bash
make front 
```

## Lancement
Pour lancer le projet, il suffit de lancer les commandes suivantes :
```bash
make up
make front
```
Ces commandes auront pour effet de lancer la base de données et le cache redis ainsi que le frontend.

## Arrêt
Pour arrêter le projet, il suffit quitter en faisant `Ctrl+C` dans les deux terminaux ouverts.



# Découpage de l'application
L'application est découpée en 2 parties :
- Le frontend, qui est un projet React
- Le backend, qui est un projet Spring Boot

# Services annexes

Pour le backend, nous avons utilisé les services suivants :
- Un service de cache Redis
- Un service de base de données Postgresql

Voir le fichier `docker-compose.yml` pour plus d'informations.

# Couche métier

Il s'agit d'une application Spring Boot qui communique avec la base de données via des requêtes SQL.
On utilise des DTO, BO, DAO et Service pour séparer les différentes couches.

# Jenkins

Jenkins est utilisé pour la CI/CD. Il est accessible à l'adresse suivante : [https://jenkins.silvain.eu/job/Spring%20SHOP/](https://jenkins.silvain.eu/job/Spring%20SHOP/)
Nous avons créé un pipeline qui permet de lancer les tests et de construire l'application (Frontend et Backend).

# N-Tiers

L'application est découpée en 3 couches :
- La couche de présentation, qui est le frontend
- La couche métier, qui est le backend
- La couche de persistance, qui est la base de données


[lien de l'image SVG](.asset/architecture.svg) [lien de l'image JPG](.asset/architecture.jpg)

<img src=".asset/architecture.svg" alt="Architecture">



# Fonctionnalités

## Authentification

Pour l'authentification, nous avons opté pour une authentification par token JWT pour certaines routes. Les autres étant 
accessibles sans authentification. À noter que les utilisateurs sont stockés dans la base de données.

## Swagger

Swagger est utilisé pour documenter l'API. Il est accessible à l'adresse suivante : [http://localhost:8080/api](http://localhost:8080/api)

## Upload des images

Pour l'upload des images, nous avons créé un service qui permet de stocker les images dans un dossier sur le serveur : `fr.ulco.springshop.service.FileSystemStorageService`.

## Convertisseur Entity <-> BO

Afin de faciliter la conversion d'une entité ver un business object, nous avons créé un convertisseur générique qui
permet de convertir une entité en BO et inversement. Ce convertisseur est utilisé dans les services pour convertir les
entités en BO et inversement. Ces convertisseurs se trouvent dans le package `fr.ulco.springshop.model.conveter`.

## Cache

Nous avons utilisé un cache Redis pour stocker les produits. Ce cache est mis à jour à chaque modification d'un produit.