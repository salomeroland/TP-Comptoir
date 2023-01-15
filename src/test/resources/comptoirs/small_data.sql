-- Une catégorie avec deux produits
INSERT INTO Categorie(code, libelle, description) VALUES 
    ( 98, '2prods', 'Cette catégorie a 2 produits');

-- Une catégorie sans produits

INSERT INTO Categorie(code, libelle, description) VALUES 
    ( 99, '0prod', 'Cette catégorie n''a pas de produit');

INSERT INTO Produit(reference, nom, fournisseur, categorie_code, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible ) VALUES 
    ( 98, 'P98', 1, 98, '24 bouteilles (1 litre)', 95.00, 17, 40, 25, 0),
    ( 99, 'P99', 1, 98, '12 bouteilles (550 ml)', 50.00, 13, 70, 25, 0);

-- Un client sans commandes
INSERT INTO Client(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax) VALUES 
    ( '0COM', 'Ce client n''a pas de commande', 'Maria Anders', 'Représentant(e)', 'Obere Str. 57', 'Berlin', NULL, '12209', 'Allemagne', '030-0074321', '030-0076545');

-- Un client avec deux commandes
INSERT INTO Client(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax) VALUES 
    ( '2COM', 'Ce client a 2 commandes', 'Laurence Lebihan', 'Propriétaire', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', '91.24.45.40', '91.24.45.41');

INSERT INTO Commande(numero, client_code, saisiele, envoyeele, port, destinataire, adresse, ville, region, code_postal, pays, remise) VALUES 
    ( 99999, '2COM', '1994-11-16', '1994-11-21', 50.00, 'Bon app''', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', 0.00);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 99999, 98, 15);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 99999, 99, 10);

INSERT INTO Commande(numero, client_code, saisiele, envoyeele, port, destinataire, adresse, ville, region, code_postal, pays, remise) VALUES
    ( 99998, '2COM', '1994-11-29', '1994-12-09', 831.00, 'Bon app''', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', 0.00);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 99998, 98, 20);






