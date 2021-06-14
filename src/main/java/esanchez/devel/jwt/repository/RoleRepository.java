package esanchez.devel.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esanchez.devel.jwt.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
