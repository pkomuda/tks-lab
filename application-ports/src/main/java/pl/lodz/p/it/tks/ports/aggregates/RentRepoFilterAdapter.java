package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.ports.infrastructure.rentports.AddRentPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.RemoveRentPort;
import pl.lodz.p.it.tks.ports.infrastructure.rentports.UpdateRentPort;
import pl.lodz.p.it.tks.ports.userinterface.rentports.FilterRentsPort;
import pl.lodz.p.it.tks.ports.userinterface.rentports.GetRentsPort;

public interface RentRepoFilterAdapter extends FilterRentsPort {

}
