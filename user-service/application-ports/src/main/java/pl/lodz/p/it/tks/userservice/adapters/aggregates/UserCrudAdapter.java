package pl.lodz.p.it.tks.userservice.adapters.aggregates;

import pl.lodz.p.it.tks.userservice.adapters.ports.output.AddUserPort;
import pl.lodz.p.it.tks.userservice.adapters.ports.output.UpdateUserPort;

public interface UserCrudAdapter extends AddUserPort, UpdateUserPort {

}
