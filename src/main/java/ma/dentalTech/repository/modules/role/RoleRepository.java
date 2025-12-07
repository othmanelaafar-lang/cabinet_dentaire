package ma.dentalTech.repository.modules.role;

import ma.dentalTech.entities.role.Role;
import ma.dentalTech.repository.common.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    // Méthodes spécifiques au Role peuvent être ajoutées ici
}
