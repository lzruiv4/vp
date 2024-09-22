package de.scopevisio.vp.backend.data.repository;

import de.scopevisio.vp.backend.data.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

}
