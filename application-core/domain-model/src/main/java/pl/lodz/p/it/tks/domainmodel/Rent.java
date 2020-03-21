package pl.lodz.p.it.tks.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.users.Client;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Rent {

    @NotNull
    private UUID id;
    @NotNull
    private Client client;
    @NotNull
    private Catalog catalog;
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    public Rent(UUID id) {
        this.id = id;
    }

    public Rent(Client client, Catalog catalog) {
        this.id = UUID.randomUUID();
        this.client = client;
        this.catalog = catalog;
    }

    public void setRentDateTime(int year, int month, int day, int hour, int minute) {
        this.rentDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }

    public void setReturnDateTime(int year, int month, int day, int hour, int minute) {
        this.returnDateTime = LocalDateTime.of(year, month, day, hour, minute);
    }
}
