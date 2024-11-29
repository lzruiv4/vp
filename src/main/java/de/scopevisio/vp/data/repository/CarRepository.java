package de.scopevisio.vp.data.repository;

import de.scopevisio.vp.data.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

}
