--FONCTIONNE SUR POSTGRESQL 9.1 & ++

-- ------------------------------------------------------
--
-- Structure de la table Fourniture
--
DROP TABLE IF EXISTS fourniture;
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

INSERT INTO fourniture (nom_fourniture, date_ajout_fourniture, prix_unitaire_fourniture) VALUES
('Crayon', '2019-08-23', 1),
('Papier (ramette)', '2019-09-19', 5),
('Voiture', '2019-11-08', 15000),
('Ordinateur', '2019-10-23', 1000),
('Imprimante', '2019-08-21', 300),
('Clavier', '2019-11-13', 70),
('Souris', '2019-07-11', 30),
('Serveur NAS', '2019-11-12', 500),
('Etagère', '2019-08-04', 15);

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

INSERT INTO gerant (nom_gerant, prenom_gerant, id_statut, remarques_gerant) VALUES
('Breton', 'Thierry', 1, 'Un premier gérant.'),
('Jacquot', 'William', 2, 'Un deuxième gérant.'),
('Marcant', 'Aimé', 1, 'Un troisième gérant.'),
('Dialo', 'Omar', 2, 'Un quatrième gérant.'),
('Lefebvre', 'Benoit', 2, 'Un cinquième gérant.');

-- --------------------------------------------------------

--
-- Structure de la table Magasin
--
DROP TABLE IF EXISTS magasin;
CREATE TABLE magasin (
  id SERIAL,
  nom_magasin varchar(100) NOT NULL,
  id_gerant INTEGER NOT NULL,
  adresse_magasin text NOT NULL,
  remarques_magasin text NOT NULL,
  CA int NOT NULL,
  PRIMARY KEY (id)
);

--
-- Déchargement des données de la table Magasin
--

INSERT INTO magasin (nom_magasin, id_gerant, adresse_magasin, remarques_magasin, CA) VALUES
('Ollivander', 5, 'Quelque part dans le monde des sorciers.', 'Il existe pas vraiment en fait...',500000);
INSERT INTO magasin (nom_magasin, id_gerant, adresse_magasin, remarques_magasin, CA) VALUES
('Pokéshop', 2, 'Quelque part sur Khoto', 'Il existe pas vraiment non plus à vrai dire...',15000);

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

INSERT INTO magasin_possede_fourniture (id_fourniture, id_magasin, quantite_stock) VALUES
(1, 2, 600),
(2, 2, 300),
(8, 2, 30),
(2, 1, 7000),
(5, 1, 3),
(7, 2, 30),
(3, 2, 50);

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

INSERT INTO statut (nom_statut) VALUES
('Employé'),
('Propriétaire');
