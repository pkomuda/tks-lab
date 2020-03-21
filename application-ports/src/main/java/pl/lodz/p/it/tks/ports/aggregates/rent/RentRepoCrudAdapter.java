package pl.lodz.p.it.tks.ports.aggregates.rent;

import pl.lodz.p.it.tks.ports.infrastructure.rentports.AddRentPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.RemoveRentPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.UpdateRentPort;

public interface RentRepoCrudAdapter extends UpdateRentPort, AddRentPort, RemoveRentPort {

}
