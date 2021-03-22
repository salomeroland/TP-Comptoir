-- Une catégorie sans produits

INSERT INTO Categorie(code, libelle, description) VALUES 
    ( 2, 'Condiments', 'Sauces, assaisonnements et épices');

-- Une catégorie avec deux produits
INSERT INTO Categorie(code, libelle, description) VALUES 
    ( 1, 'Boissons', 'Boissons, cafés, thés, bières');

INSERT INTO Produit(reference, nom, fournisseur, categorie_code, quantite_par_unite, prix_unitaire, unites_en_stock, unites_commandees, niveau_de_reappro, indisponible ) VALUES 
    ( 2, 'Chang', 1, 1, '24 bouteilles (1 litre)', 95.00, 17, 40, 25, 0),
    ( 3, 'Aniseed Syrup', 1, 1, '12 bouteilles (550 ml)', 50.00, 13, 70, 25, 0);

-- Un client sans commandes
INSERT INTO Client(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax) VALUES 
    ( 'ALFKI', 'Alfreds Futterkiste', 'Maria Anders', 'Représentant(e)', 'Obere Str. 57', 'Berlin', NULL, '12209', 'Allemagne', '030-0074321', '030-0076545');

-- Un client avec deux commandes
INSERT INTO Client(code, societe, contact, fonction, adresse, ville, region, code_postal, pays, telephone, fax) VALUES 
    ( 'BONAP', 'Bon app''', 'Laurence Lebihan', 'Propriétaire', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', '91.24.45.40', '91.24.45.41');
INSERT INTO Commande(numero, client_code, saisiele, envoyeele, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livraison, pays_livraison, remise) VALUES 
    ( 10331, 'BONAP', '1994-11-16', '1994-11-21', 50.00, 'Bon app''', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', 0.00);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 10331, 2, 15);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 10331, 3, 10);

INSERT INTO Commande(numero, client_code, saisiele, envoyeele, port, destinataire, adresse_livraison, ville_livraison, region_livraison, code_postal_livraison, pays_livraison, remise) VALUES
    ( 10340, 'BONAP', '1994-11-29', '1994-12-09', 831.00, 'Bon app''', '12, rue des Bouchers', 'Marseille', NULL, '13008', 'France', 0.00);
INSERT INTO Ligne(commande_numero, produit_reference, quantite) VALUES ( 10340, 2, 20);






