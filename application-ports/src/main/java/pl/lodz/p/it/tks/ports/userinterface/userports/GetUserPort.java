package pl.lodz.p.it.tks.ports.userinterface.userports;

import pl.lodz.p.it.tks.domainmodel.users.User;

public interface GetUserPort {

    User getUser(String username);
}
