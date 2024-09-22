package de.scopevisio.vp.backend.data.repository;

import de.scopevisio.vp.backend.data.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
