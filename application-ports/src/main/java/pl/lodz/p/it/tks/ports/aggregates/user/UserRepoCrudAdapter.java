package pl.lodz.p.it.tks.ports.aggregates;

import pl.lodz.p.it.tks.ports.infrastructure.userports.AddUserPort;
import pl.lodz.p.it.tks.ports.infrastructure.userports.UpdateUserPort;

public interface UserRepoCrudAdapter extends AddUserPort, UpdateUserPort {
}