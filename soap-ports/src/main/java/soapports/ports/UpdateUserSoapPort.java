package soapports.ports;

import pl.lodz.p.it.tks.soap.services.model.UserSoap;

public interface UpdateUserSoapPort {

    void updateUser(String username, UserSoap user);
}
