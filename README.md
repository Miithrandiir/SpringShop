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
make front-up
```
Ces commandes auront pour effet de lancer la base de données et le cache redis ainsi que le frontend.

## Arrêt
Pour arrêter le projet, il suffit de lancer la commande suivante :
```bash
make down
make front-down
```

# Architecture

## Découpage de l'application
L'application est découpée en 3 parties :
- Le frontend, qui est un projet React
- Le backend, qui est un projet Spring Boot
- La base de données, qui est une base de données PostgreSQL

## N-Tiers
L'application est découpée en 3 couches :
- La couche de présentation, qui est le frontend
- La couche métier, qui est le backend
- La couche de persistance, qui est la base de données

### Couche métier
Il s'agit d'une application Spring Boot qui communique avec la base de données via des requêtes SQL. 
On utilise des DTO, BO, DAO et Service pour séparer les différentes couches.

## Authentification
Pour l'authentification, nous avons opté pour une authentification par token JWT pour certaines routes. Les autres étant accessibles sans authentification.

## Swagger
Swagger est utilisé pour documenter l'API. Il est accessible à l'adresse suivante : [http://localhost:8080/api](http://localhost:8080/api)

## Image
Image trouvable dans le dossier `.asset` du projet. [lien de l'image](.asset/architecture.svg)
<img src=".asset/architecture.svg" alt="Architecture">