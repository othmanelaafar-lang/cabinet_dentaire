package ma.dentalTech.entities.charges;

import ma.dentalTech.entities.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entité représentant une charge/dépense du cabinet médical
 */
public class Charges extends BaseEntity {
    private Long idCharge;
    private Long cabinetMedicaleId; // Référence au cabinet médical
    private String titre;
    private String description;
    private BigDecimal montant;
    private LocalDateTime date;

    // Constructeurs
    public Charges() {
        super();
        this.date = LocalDateTime.now();
    }

    public Charges(Long idCharge, Long cabinetMedicaleId, String titre, 
                   String description, BigDecimal montant) {
        super();
        this.idCharge = idCharge;
        this.cabinetMedicaleId = cabinetMedicaleId;
        this.titre = titre;
        this.description = description;
        this.montant = montant;
        this.date = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getIdCharge() {
        return idCharge;
    }

    public void setIdCharge(Long idCharge) {
        this.idCharge = idCharge;
    }

    public Long getCabinetMedicaleId() {
        return cabinetMedicaleId;
    }

    public void setCabinetMedicaleId(Long cabinetMedicaleId) {
        this.cabinetMedicaleId = cabinetMedicaleId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
