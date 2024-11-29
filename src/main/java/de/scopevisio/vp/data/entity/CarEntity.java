package de.scopevisio.vp.data.entity;

import de.scopevisio.vp.data.enums.CarType;
import de.scopevisio.vp.data.enums.RegionType;
import de.scopevisio.vp.data.model.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID carId;

    private CarType carType;

    private BigDecimal milesPerYear;

    private RegionType regionType;

    private BigDecimal versicherungspraemie;

    private String registeredPostalCode;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    /**
     * Convert entity to model
     */
    public Car entityToModel() {
        return new Car(carId, carType, milesPerYear, regionType, versicherungspraemie, registeredPostalCode);
    }
}
