package ma.dentalTech.repository.modules.patient.inMemDB_implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.dentalTech.entities.enums.Assurance;
import ma.dentalTech.entities.enums.Sexe;
import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.repository.modules.patient.api.PatientRepository;

/**
 * Implémentation en mémoire (in-memory) de PatientRepository
 * Utilisée pour les tests et le développement
 */
public class PatientRepositoryImpl implements PatientRepository {

    private final List<Patient> data = new ArrayList<>();

    public PatientRepositoryImpl() {
        // Données d'exemple : 3 patients d'aujourd'hui, 1 d'hier
        LocalDate today = LocalDate.now();
        
        Patient p1 = Patient.builder()
                .id(1L).nom("Amal").prenom("Z.")
                .email("amal@example.com").telephone("0611-111111")
                .dateNaissance(LocalDate.of(1995, 5, 12))
                .sexe(Sexe.Femme).assurance(Assurance.CNSS)
                .build();
        p1.setDateCreation(today); // dateCreation est hérité de BaseEntity, pas dans le builder
        data.add(p1);

        Patient p2 = Patient.builder()
                .id(2L).nom("Hassan").prenom("B.")
                .email("hassan@example.com").telephone("0622-222222")
                .dateNaissance(LocalDate.of(1989, 9, 23))
                .sexe(Sexe.Homme).assurance(Assurance.CNOPS)
                .build();
        p2.setDateCreation(today);
        data.add(p2);

        Patient p3 = Patient.builder()
                .id(3L).nom("Nour").prenom("C.")
                .email("nour@example.com").telephone("0633-333333")
                .dateNaissance(LocalDate.of(2000, 2, 2))
                .sexe(Sexe.Femme).assurance(Assurance.Autre)
                .build();
        p3.setDateCreation(today);
        data.add(p3);

        Patient p4 = Patient.builder()
                .id(4L).nom("Youssef").prenom("D.")
                .email("youssef@example.com").telephone("0644-444444")
                .dateNaissance(LocalDate.of(1992, 11, 1))
                .sexe(Sexe.Homme).assurance(Assurance.Aucune)
                .build();
        p4.setDateCreation(today.minusDays(1)); // hier → ne doit pas s'afficher
        data.add(p4);

        // Tri stable par id pour cohérence (findAll renverra trié par date desc via service)
        data.sort(Comparator.comparing(Patient::getId));
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(data); // Retourner une copie pour éviter les modifications externes
    }

    @Override
    public Optional<Patient> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return data.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }

    @Override
    public void create(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être null");
        }
        // Générer un ID si nécessaire
        if (patient.getId() == null) {
            long newId = data.stream()
                    .mapToLong(p -> p.getId() == null ? 0 : p.getId())
                    .max()
                    .orElse(0) + 1;
            patient.setId(newId);
        }
        // Définir la date de création si elle n'est pas définie
        if (patient.getDateCreation() == null) {
            patient.setDateCreation(LocalDate.now());
        }
        data.add(patient);
    }

    @Override
    public <S extends Patient> S save(S patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être null");
        }
        if (patient.getId() == null) {
            create(patient);
            return patient;
        } else {
            update(patient);
            return patient;
        }
    }

    @Override
    public <S extends Patient> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S entity : entities) {
            saved.add(save(entity));
        }
        return saved;
    }

    @Override
    public <S extends Patient> S update(S patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être null");
        }
        if (patient.getId() == null) {
            throw new IllegalArgumentException("L'ID du patient est requis pour la mise à jour");
        }
        
        for (int i = 0; i < data.size(); i++) {
            if (Objects.equals(data.get(i).getId(), patient.getId())) {
                data.set(i, patient);
                return patient;
            }
        }
        throw new RuntimeException("Patient avec ID " + patient.getId() + " introuvable");
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return data.stream().anyMatch(p -> Objects.equals(p.getId(), id));
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {
        if (entities == null) {
            return;
        }
        List<Long> idsToDelete = new ArrayList<>();
        for (Patient entity : entities) {
            if (entity != null && entity.getId() != null) {
                idsToDelete.add(entity.getId());
            }
        }
        data.removeIf(p -> p.getId() != null && idsToDelete.contains(p.getId()));
    }

    @Override
    public void delete(Patient patient) {
        if (patient != null && patient.getId() != null) {
            deleteById(patient.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        data.removeIf(p -> Objects.equals(p.getId(), id));
    }
}
