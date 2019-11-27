--FONCTIONNE SUR POSTGRESQL 9.1 & ++

-- ------------------------------------------------------
--
-- Structure de la table Fourniture
--
DROP TABLE IF EXISTS Fourniture;
CREATE TABLE fourniture (
  id_fourniture SERIAL,
  nom_fourniture varchar(30) NOT NULL,
  date_ajout_fourniture date NOT NULL,
  prix_unitaire_fourniture INTEGER NOT NULL,
  PRIMARY KEY (id_fourniture)
);

--
-- Déchargement des données de la table Fourniture
--

INSERT INTO fourniture (id_fourniture, nom_fourniture, date_ajout_fourniture, prix_unitaire_fourniture) VALUES
(1, 'Crayon', '2019-08-23', 1),
(2, 'Papier (ramette)', '2019-09-19', 5),
(3, 'Voiture', '2019-11-08', 15000),
(4, 'Ordinateur', '2019-10-23', 1000),
(5, 'Imprimante', '2019-08-21', 300),
(6, 'Clavier', '2019-11-13', 70),
(7, 'Souris', '2019-07-11', 30),
(8, 'Serveur NAS', '2019-11-12', 500),
(9, 'Etagère', '2019-08-04', 15);

-- --------------------------------------------------------

--
-- Structure de la table gerant
--
DROP TABLE IF EXISTS gerant;
CREATE TABLE gerant (
  id_gerant SERIAL,
  nom_gerant varchar(30) NOT NULL,
  prenom_gerant varchar(30) NOT NULL,
  id_statut INTEGER NOT NULL,
  remarques_gerant text NOT NULL,
  PRIMARY KEY (id_gerant)
);

--
-- Déchargement des données de la table gerant
--

INSERT INTO gerant (id_gerant, nom_gerant, prenom_gerant, id_statut, remarques_gerant) VALUES
(1, 'Breton', 'Thierry', 1, 'Un premier gérant.'),
(2, 'Jacquot', 'William', 2, 'Un deuxième gérant.'),
(3, 'Marcant', 'Aimé', 1, 'Un troisième gérant.'),
(4, 'Dialo', 'Omar', 2, 'Un quatrième gérant.'),
(5, 'Lefebvre', 'Benoit', 2, 'Un cinquième gérant.');

-- --------------------------------------------------------

--
-- Structure de la table Magasin
--
DROP TABLE IF EXISTS Magasin;
CREATE TABLE magasin (
  id SERIAL,
  nom_magasin varchar(100) NOT NULL,
  id_gerant INTEGER NOT NULL,
  adresse_magasin text NOT NULL,
  remarques_magasin text NOT NULL,
  PRIMARY KEY (id)
);

--
-- Déchargement des données de la table Magasin
--

INSERT INTO magasin (id, nom_magasin, id_gerant, adresse_magasin, remarques_magasin) VALUES
(1, 'Ollivander', 5, 'Quelque part dans le monde des sorciers.', 'Il existe pas vraiment en fait...'),
(2, 'Pokéshop', 2, 'Quelque part sur Khoto', 'Il existe pas vraiment non plus à vrai dire...');

-- --------------------------------------------------------

--
-- Structure de la table magasin_possede_fourniture
--
DROP TABLE IF EXISTS magasin_possede_fourniture;
CREATE TABLE magasin_possede_fourniture (
  id_magasin_possede_fourniture SERIAL,
  id_fourniture INTEGER NOT NULL,
  id_magasin INTEGER NOT NULL,
  quantite_stock INTEGER NOT NULL,
  PRIMARY KEY (id_magasin_possede_fourniture)
);

INSERT INTO magasin_possede_fourniture (id_magasin_possede_fourniture, id_fourniture, id_magasin, quantite_stock) VALUES
(1, 1, 2, 600),
(2, 2, 2, 300),
(3, 8, 2, 30),
(4, 2, 1, 7000),
(5, 5, 1, 3),
(6, 7, 2, 30),
(7, 3, 2, 50);

-- --------------------------------------------------------

--
-- Structure de la table statut
--
DROP TABLE IF EXISTS statut;
CREATE TABLE statut (
  id_statut SERIAL,
  nom_statut varchar(30) NOT NULL,
  PRIMARY KEY (id_statut)
);

--
-- Déchargement des données de la table statut
--

INSERT INTO statut (id_statut, nom_statut) VALUES
(1, 'Employé'),
(2, 'Propriétaire');
