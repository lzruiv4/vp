package de.scopevisio.data.repository;

import de.scopevisio.data.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {

}
