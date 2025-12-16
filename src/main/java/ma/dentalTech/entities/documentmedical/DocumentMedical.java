package ma.dentalTech.entities.documentmedical;

import ma.dentalTech.entities.common.BaseEntity;

import java.time.LocalDateTime;

/**
 * Entité représentant un document médical (radiographie, analyse, etc.)
 */
public class DocumentMedical extends BaseEntity {
    private Long id;
    private Long patientId; // Référence au patient
    private String typeDocument; // Ex: "Radiographie", "Analyse", "Certificat", etc.
    private String nomFichier;
    private String cheminFichier; // Chemin complet du fichier sur le système
    private LocalDateTime dateUpload;
    private Long uploadPar; // ID de l'utilisateur qui a uploadé le document
    private String description;

    // Constructeurs
    public DocumentMedical() {
        super();
        this.dateUpload = LocalDateTime.now();
    }

    public DocumentMedical(Long id, Long patientId, String typeDocument, 
                           String nomFichier, String cheminFichier) {
        super();
        this.id = id;
        this.patientId = patientId;
        this.typeDocument = typeDocument;
        this.nomFichier = nomFichier;
        this.cheminFichier = cheminFichier;
        this.dateUpload = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public LocalDateTime getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDateTime dateUpload) {
        this.dateUpload = dateUpload;
    }

    public Long getUploadPar() {
        return uploadPar;
    }

    public void setUploadPar(Long uploadPar) {
        this.uploadPar = uploadPar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

