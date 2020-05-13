package pl.lodz.p.it.tks.rest.ports.infrastructure.rentports;

import pl.lodz.p.it.tks.rest.domainmodel.Rent;

public interface AddRentPort {

    void addRent(Rent rent);
}
