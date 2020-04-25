package pl.lodz.p.it.tks.rest.ports.aggregates.rent;

import pl.lodz.p.it.tks.rest.ports.infrastructure.rentports.AddRentPort;
import pl.lodz.p.it.tks.rest.ports.infrastructure.rentports.RemoveRentPort;
import pl.lodz.p.it.tks.rest.ports.infrastructure.rentports.UpdateRentPort;

public interface RentRepoCrudAdapter extends UpdateRentPort, AddRentPort, RemoveRentPort {

}
