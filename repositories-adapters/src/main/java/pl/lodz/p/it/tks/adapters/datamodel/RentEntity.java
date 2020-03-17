package pl.lodz.p.it.tks.adapters.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.it.tks.adapters.datamodel.catalogs.CatalogEntity;
import pl.lodz.p.it.tks.adapters.datamodel.users.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Rent")
public @Data class RentEntity {

    @Id
    private UUID id;
    @ManyToOne
    private ClientEntity clientEntity;
    @ManyToOne
    private CatalogEntity catalogEntity;
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;
}
