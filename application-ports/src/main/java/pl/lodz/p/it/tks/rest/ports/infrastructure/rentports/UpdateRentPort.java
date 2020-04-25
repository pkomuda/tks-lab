package pl.lodz.p.it.tks.rest.ports.infrastructure.rentports;

import pl.lodz.p.it.tks.rest.domainmodel.Rent;

import java.util.UUID;

public interface UpdateRentPort {

    void updateRent(UUID id, Rent rent);
}
