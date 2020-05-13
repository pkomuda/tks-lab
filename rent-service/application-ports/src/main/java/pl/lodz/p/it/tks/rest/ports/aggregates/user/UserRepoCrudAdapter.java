package pl.lodz.p.it.tks.rest.ports.aggregates.user;

import pl.lodz.p.it.tks.rest.ports.infrastructure.userports.AddUserPort;
import pl.lodz.p.it.tks.rest.ports.infrastructure.userports.UpdateUserPort;

public interface UserRepoCrudAdapter extends AddUserPort, UpdateUserPort {

}
