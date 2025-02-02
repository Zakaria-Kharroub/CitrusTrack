# CitrusTrack - Application de Gestion pour les Fermes de Citrons

## Contexte du Projet

**CitrusTrack** est une application conçue pour simplifier et optimiser la gestion des fermes de citrons. L'objectif est d'aider les fermiers à suivre efficacement la production, la récolte, et la vente de leurs produits tout en offrant une vue claire sur la productivité des arbres en fonction de leur âge.

---


---

![Thumbnail 1](https://github.com/Zakaria-Kharroub/CitrusTrack/blob/a7baaca58346cc6c923e612684beea8c82cb2bb6/CitrusTrack.webp)

---

## Fonctionnalités Principales

### Gestion des Fermes :
- Création, modification et consultation des informations d'une ferme (nom, localisation, superficie, date de création).
- **Recherche multicritère** via Criteria Builder.

### Gestion des Champs :
- Association de champs à une ferme avec une superficie définie.
- Respect des contraintes de superficie : 
  - La somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.

### Gestion des Arbres :
- Suivi des arbres : date de plantation, âge, champ d'appartenance.
- Calcul automatique de l'âge des arbres.
- Détermination de la productivité annuelle en fonction de l'âge :
  - Jeune (< 3 ans) : **2,5 kg/saison**.
  - Mature (3-10 ans) : **12 kg/saison**.
  - Vieux (> 10 ans) : **20 kg/saison**.

### Gestion des Récoltes :
- Suivi des récoltes par saison : hiver, printemps, été, automne.
- Enregistrement d'une seule récolte par saison (tous les 3 mois) pour chaque champ.

### Détails des Récoltes :
- Suivi des quantités récoltées par arbre pour chaque récolte.
- Association des détails de récolte à des arbres spécifiques.

### Gestion des Ventes :
- Enregistrement des ventes : date, prix unitaire, client, et récolte associée.
- Calcul automatique du revenu : **Revenu = quantité × prixUnitaire**.

---

## Contraintes

1. **Superficie Minimale et Maximale des Champs :**
   - Minimum : **0.1 hectare (1 000 m²)**.
   - Maximum : **50% de la superficie totale de la ferme**.

2. **Nombre de Champs par Ferme :** Maximum **10** champs.

3. **Espacement entre les Arbres :**
   - Densité maximale : **100 arbres par hectare (10 arbres/1 000 m²)**.

4. **Durée de Vie des Arbres :**
   - Productivité limitée à **20 ans**.

5. **Période de Plantation :**
   - Plantation autorisée uniquement entre **mars et mai**.

6. **Limites Saisonnières :**
   - Une seule récolte par champ et par saison.
   - Un arbre ne peut être récolté qu'une seule fois par saison.

---

## Exigences Techniques

- **Technologies Utilisées :**
  - **Backend :** Spring Boot pour créer l'API REST.
  - **Validation des Données :** Annotations Spring.
  - **Tests :** JUnit et Mockito pour les tests unitaires.
  - **Gestion des Entités :** Lombok et Builder Pattern.
  - **Mapping :** MapStruct pour la conversion entre les entités, DTO, et View Models.

- **Architecture Logicielle :**
  - Structure en couches (Controller, Service, Repository, Entity).
  - Gestion centralisée des exceptions.

---

## Objectifs

**CitrusTrack** vise à :
1. Simplifier la gestion quotidienne des fermes.
2. Assurer un suivi précis de la productivité.
3. Automatiser le calcul des revenus pour aider les fermiers à prendre des décisions éclairées.
4. Respecter les contraintes agricoles tout en offrant une solution logicielle robuste.

---

**CitrusTrack** : Parce que chaque citron compte 🍋.
