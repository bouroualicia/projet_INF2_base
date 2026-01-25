# Smart Task Planner - Projet Jakarta EE

## Équipe du projet :
Ce projet a été réalisé par : **Alicia, Yelizaveta, Ilayda, Sarah et Amina**.

## Objectif :
Smart Task Planner est une application de gestion de tâches collaborative. Elle permet d'organiser le travail en gérant des utilisateurs, des équipes et des missions, tout en assurant une traçabilité totale via un système d'audit asynchrone (JMS).

---

## Guide de Démonstration (Commandes CURL)

### 1. Lancement du projet 
```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### 2. Vérification initiale (Système propre)
```bash
curl -X GET "http://localhost:8080/api/users"
```

### 3. Gestion de l'utilisateur Lucas
```bash
# Créer l'utilisateur Lucas (Notez l'ID reçu)
curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d '{"name":"Lucas", "email":"lucas@example.com"}'

# Supprimer Lucas (Remplacer X par l'ID reçu)
curl -X DELETE "http://localhost:8080/api/users/X"

# Recréer Lucas pour la suite 
curl -X POST "http://localhost:8080/api/users" -H "Content-Type: application/json" -d '{"name":"Lucas", "email":"lucas@example.com"}'
```

### 4. Gestion de l'Équipe
```bash
# Créer l'Equipe Alpha (Notez l'ID reçu)
curl -X POST "http://localhost:8080/api/teams" -H "Content-Type: application/json" -d '{"nameTeam":"Equipe Alpha"}'

# Renommer l'équipe en Team Omega (Remplacer Y par l'ID de la team)
curl -X PUT "http://localhost:8080/api/teams/Y" -H "Content-Type: application/json" -d '{"nameTeam":"Team Omega"}'

# Lister les équipes pour vérifier le changement
curl -X GET "http://localhost:8080/api/teams"
```

### 5. Création d'une Tâche liée (JPA & DTO)
*Remplacez X et Y par les IDs respectifs de Lucas et de la Team.*
```bash
curl -X POST "http://localhost:8080/api/tasks" \
     -H "Content-Type: application/json" \
     -d '{
          "nameTask": "Finaliser Projet",
          "description": "Test complet réussi",
          "idUser": X,
          "idTeam": Y
         }'
```

### 6. Vérification & Traçabilité (Audit JMS)
```bash
# Lister les tâches pour montrer la liaison JPA (User/Team non nuls)
curl -X GET "http://localhost:8080/api/tasks"

# Afficher l'audit final pour prouver la traçabilité asynchrone
curl -X GET "http://localhost:8080/api/audit"
```
