package pl.lodz.p.it.tks.ports.infrastructure.rentports;

import pl.lodz.p.it.tks.domainmodel.Rent;

import java.util.UUID;

public interface UpdateRentPort {

    void updateRent(UUID id, Rent rent);
}
