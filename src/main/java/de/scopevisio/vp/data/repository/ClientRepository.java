package de.scopevisio.vp.data.repository;

import de.scopevisio.vp.data.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
