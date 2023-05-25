# Sport Center #
## Description du projet ##
Programme en console permettant de gérer un centre de sports

## Technologies utilisées ##
- Langage de programmation : Java SE
- ORM : Hibernate
- SGBDR : MySQL

## Fonctionnalités ##
- Créer un sport
- Créer et modifier les données d'un adhérent (prénom, nom, date de naissance, sport favori)
- Créer une activité (nom, jour, heure, âge minimum, nombre de place)
- Inscrire un adhérent en veillant à l'âge et au nombre de places encore disponibles.
- Afficher tous les adhérents, toutes les activités, toutes les activités dont il reste des places

## Structure du projet ##
### Entités ###
3 entités : FavoriteSport, Activity, User.

Relations : User -> Activity (many to many), User -> FavoriteSport (many to one).

### IHM ###
L'interface homme machine permet de demander et récupérer les saisies utilisateur, traiter les données (création ou modification d'objets) et appeler les méthodes des services.

### Service ###
3 services utilisés pour effectuer le CRUD de la base de données : SportService, ActivityService, UserService.

Chaque service implémente une interface Repository contenant les méthodes courantes.

Chaque service hérite de BaseService, qui gère la session.

