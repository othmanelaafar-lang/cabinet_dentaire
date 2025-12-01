-- Création de la base de données si elle n'existe pas
CREATE DATABASE IF NOT EXISTS cabinet_dentaire CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Sélection de la base de données
USE cabinet_dentaire;

-- FULL SCHEMA (users/staff/medecin/secretaire + roles ...
CREATE TABLE IF NOT EXISTS utilisateur (
                                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                           creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           last_modification_date TIMESTAMP NULL,
                                           created_by VARCHAR(64),
                                           updated_by VARCHAR(64),
                                           nom VARCHAR(120) NOT NULL,
                                           prenom VARCHAR(120) NOT NULL,
                                           email VARCHAR(160) NOT NULL UNIQUE,
                                           tel VARCHAR(40),
                                           adresse VARCHAR(255),
                                           cin VARCHAR(32) UNIQUE,
                                           sexe ENUM('HOMME','FEMME','AUTRE') DEFAULT 'AUTRE',
                                           login VARCHAR(64) NOT NULL UNIQUE,
                                           password_hash VARCHAR(120) NOT NULL,
                                           last_login_date TIMESTAMP NULL,
                                           date_naissance DATE NULL,
                                           actif BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS staff (
                                     id BIGINT PRIMARY KEY,
                                     salaire DECIMAL(12,2) DEFAULT 0,
                                     prime DECIMAL(12,2) DEFAULT 0,
                                     date_recrutement DATE,
                                     solde_conge INT DEFAULT 0,
                                     CONSTRAINT fk_staff_user FOREIGN KEY (id) REFERENCES utilisateur(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS medecin (
                                       id BIGINT PRIMARY KEY,
                                       specialite VARCHAR(120),
                                       agenda_medecin TEXT,
                                       CONSTRAINT fk_med_staff FOREIGN KEY (id) REFERENCES staff(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS secretaire (
                                          id BIGINT PRIMARY KEY,
                                          num_cnss VARCHAR(64),
                                          commission DECIMAL(12,2) DEFAULT 0,
                                          CONSTRAINT fk_sec_staff FOREIGN KEY (id) REFERENCES staff(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    libelle VARCHAR(80) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS utilisateur_role (
                                                utilisateur_id BIGINT NOT NULL,
                                                role_id BIGINT NOT NULL,
                                                PRIMARY KEY (utilisateur_id, role_id),
                                                CONSTRAINT fk_ur_user FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE CASCADE,
                                                CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- Tables pour la gestion des patients
CREATE TABLE IF NOT EXISTS patient (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       last_modification_date TIMESTAMP NULL,
                                       created_by VARCHAR(64),
                                       updated_by VARCHAR(64),
                                       numero_dossier VARCHAR(50) NOT NULL UNIQUE,
                                       nom VARCHAR(120) NOT NULL,
                                       prenom VARCHAR(120) NOT NULL,
                                       date_naissance DATE,
                                       lieu_naissance VARCHAR(120),
                                       sexe ENUM('HOMME','FEMME','AUTRE') DEFAULT 'AUTRE',
                                       adresse TEXT,
                                       code_postal VARCHAR(20),
                                       ville VARCHAR(100),
                                       pays VARCHAR(100) DEFAULT 'Maroc',
                                       telephone1 VARCHAR(40),
                                       telephone2 VARCHAR(40),
                                       email VARCHAR(160),
                                       profession VARCHAR(120),
                                       groupe_sanguin VARCHAR(10),
                                       assurance VARCHAR(120),
                                       numero_assurance VARCHAR(100),
                                       personne_urgence_nom VARCHAR(120),
                                       personne_urgence_telephone VARCHAR(40),
                                       personne_urgence_lien VARCHAR(80),
                                       notes TEXT,
                                       allergies TEXT,
                                       antecedents_medicaux TEXT,
                                       actif BOOLEAN DEFAULT TRUE
);

-- Table pour les rendez-vous
CREATE TABLE IF NOT EXISTS rendez_vous (
                                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                           creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           last_modification_date TIMESTAMP NULL,
                                           created_by VARCHAR(64),
                                           updated_by VARCHAR(64),
                                           patient_id BIGINT NOT NULL,
                                           medecin_id BIGINT NOT NULL,
                                           date_heure_debut DATETIME NOT NULL,
                                           date_heure_fin DATETIME NOT NULL,
                                           statut ENUM('PLANIFIE', 'CONFIRME', 'EN_COURS', 'TERMINE', 'ANNULE', 'ABSENT') DEFAULT 'PLANIFIE',
                                           motif_consultation TEXT,
                                           notes TEXT,
                                           CONSTRAINT fk_rv_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
                                           CONSTRAINT fk_rv_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE
);

-- Table pour les consultations
CREATE TABLE IF NOT EXISTS consultation (
                                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                            creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            last_modification_date TIMESTAMP NULL,
                                            created_by VARCHAR(64),
                                            updated_by VARCHAR(64),
                                            rendez_vous_id BIGINT,
                                            patient_id BIGINT NOT NULL,
                                            medecin_id BIGINT NOT NULL,
                                            date_consultation DATE NOT NULL,
                                            motif TEXT,
                                            diagnostic TEXT,
                                            traitement_prescrit TEXT,
                                            notes TEXT,
                                            montant_total DECIMAL(12,2) DEFAULT 0,
                                            regle BOOLEAN DEFAULT FALSE,
                                            CONSTRAINT fk_cons_rdv FOREIGN KEY (rendez_vous_id) REFERENCES rendez_vous(id) ON DELETE SET NULL,
                                            CONSTRAINT fk_cons_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
                                            CONSTRAINT fk_cons_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE
);

-- Table pour les actes médicaux
CREATE TABLE IF NOT EXISTS acte_medical (
                                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                            code VARCHAR(50) NOT NULL UNIQUE,
                                            libelle VARCHAR(255) NOT NULL,
                                            description TEXT,
                                            duree_moyenne INT, -- en minutes
                                            prix_unitaire DECIMAL(12,2) NOT NULL,
                                            actif BOOLEAN DEFAULT TRUE
);

-- Table pour les actes effectués lors d'une consultation
CREATE TABLE IF NOT EXISTS consultation_acte (
                                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                 consultation_id BIGINT NOT NULL,
                                                 acte_medical_id BIGINT NOT NULL,
                                                 quantite INT DEFAULT 1,
                                                 prix_unitaire DECIMAL(12,2) NOT NULL,
                                                 remise DECIMAL(12,2) DEFAULT 0,
                                                 notes TEXT,
                                                 CONSTRAINT fk_ca_consultation FOREIGN KEY (consultation_id) REFERENCES consultation(id) ON DELETE CASCADE,
                                                 CONSTRAINT fk_ca_acte FOREIGN KEY (acte_medical_id) REFERENCES acte_medical(id) ON DELETE CASCADE
);

-- Table pour le suivi des paiements
CREATE TABLE IF NOT EXISTS paiement (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        created_by VARCHAR(64),
                                        consultation_id BIGINT NOT NULL,
                                        montant DECIMAL(12,2) NOT NULL,
                                        mode_paiement ENUM('ESPECES', 'CARTE', 'VIREMENT', 'CHEQUE', 'AUTRE') NOT NULL,
                                        reference_paiement VARCHAR(100),
                                        notes TEXT,
                                        CONSTRAINT fk_paiement_consultation FOREIGN KEY (consultation_id) REFERENCES consultation(id) ON DELETE CASCADE
);

-- Table pour le stock et les fournitures
CREATE TABLE IF NOT EXISTS fournisseur (
                                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                           nom VARCHAR(120) NOT NULL,
                                           contact_nom VARCHAR(120),
                                           telephone VARCHAR(40),
                                           email VARCHAR(160),
                                           adresse TEXT,
                                           notes TEXT,
                                           actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS article (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       reference VARCHAR(100) NOT NULL UNIQUE,
                                       libelle VARCHAR(255) NOT NULL,
                                       description TEXT,
                                       categorie VARCHAR(100),
                                       prix_achat DECIMAL(12,2) NOT NULL,
                                       prix_vente DECIMAL(12,2) NOT NULL,
                                       quantite_stock INT DEFAULT 0,
                                       seuil_alerte INT DEFAULT 5,
                                       fournisseur_id BIGINT,
                                       date_dernier_achat DATE,
                                       actif BOOLEAN DEFAULT TRUE,
                                       CONSTRAINT fk_article_fournisseur FOREIGN KEY (fournisseur_id) REFERENCES fournisseur(id) ON DELETE SET NULL
);

-- Table pour les ordonnances
CREATE TABLE IF NOT EXISTS ordonnance (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          consultation_id BIGINT NOT NULL,
                                          date_emission DATE NOT NULL,
                                          notes TEXT,
                                          CONSTRAINT fk_ordonnance_consultation FOREIGN KEY (consultation_id) REFERENCES consultation(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS medicament_ordonnance (
                                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                     ordonnance_id BIGINT NOT NULL,
                                                     medicament_nom VARCHAR(255) NOT NULL,
                                                     posologie TEXT NOT NULL,
                                                     duree_traitement VARCHAR(100),
                                                     quantite INT,
                                                     CONSTRAINT fk_mo_ordonnance FOREIGN KEY (ordonnance_id) REFERENCES ordonnance(id) ON DELETE CASCADE
);

-- Table pour les documents médicaux
CREATE TABLE IF NOT EXISTS document_medical (
                                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                patient_id BIGINT NOT NULL,
                                                type_document VARCHAR(100) NOT NULL,
                                                nom_fichier VARCHAR(255) NOT NULL,
                                                chemin_fichier VARCHAR(512) NOT NULL,
                                                date_upload TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                upload_par BIGINT,
                                                description TEXT,
                                                CONSTRAINT fk_doc_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
                                                CONSTRAINT fk_doc_uploader FOREIGN KEY (upload_par) REFERENCES utilisateur(id) ON DELETE SET NULL
);

-- Table pour les paramètres de l'application
CREATE TABLE IF NOT EXISTS parametre (
                                         cle VARCHAR(100) PRIMARY KEY,
                                         valeur TEXT,
                                         description TEXT,
                                         modifiable BOOLEAN DEFAULT TRUE
);

-- Insertion des rôles de base
INSERT IGNORE INTO role (libelle) VALUES
                                      ('ADMIN'),
                                      ('MEDECIN'),
                                      ('SECRETAIRE'),
                                      ('ASSISTANT');

-- Insertion des paramètres par défaut
INSERT IGNORE INTO parametre (cle, valeur, description, modifiable) VALUES
                                                                        ('NOM_CABINET', 'Cabinet Dentaire', 'Nom du cabinet dentaire', TRUE),
                                                                        ('ADRESSE_CABINET', '', 'Adresse du cabinet', TRUE),
                                                                        ('TELEPHONE_CABINET', '', 'Numéro de téléphone du cabinet', TRUE),
                                                                        ('EMAIL_CABINET', '', 'Email de contact du cabinet', TRUE),
                                                                        ('HEURE_OUVERTURE', '08:00', 'Heure d''ouverture du cabinet', TRUE),
                                                                        ('HEURE_FERMETURE', '19:00', 'Heure de fermeture du cabinet', TRUE),
                                                                        ('DUREE_RDV_DEFAUT', '30', 'Durée par défaut d''un rendez-vous (en minutes)', TRUE),
                                                                        ('JOURS_OUVERTURE', 'LUN,MAR,MER,JEU,VEN,SAM', 'Jours d''ouverture du cabinet', TRUE);
