package pl.lodz.p.it.webmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.it.webmodel.catalogs.CatalogWeb;
import pl.lodz.p.it.webmodel.users.ClientWeb;


import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public @Data class RentWeb {

    private UUID id;
    private ClientWeb client;
    private CatalogWeb catalog;
    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    public RentWeb(UUID id) {
        this.id = id;
    }

    public RentWeb(ClientWeb client, CatalogWeb catalog) {
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
