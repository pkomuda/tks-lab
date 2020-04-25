package pl.lodz.p.it.tks.rest.adapters.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.CatalogEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.users.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Rent")
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
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
