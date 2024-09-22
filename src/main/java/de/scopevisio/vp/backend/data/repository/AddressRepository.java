package de.scopevisio.vp.backend.data.repository;

import de.scopevisio.vp.backend.data.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
