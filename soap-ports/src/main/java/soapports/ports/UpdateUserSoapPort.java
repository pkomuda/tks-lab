package soapports.ports;

import pl.lodz.p.it.tks.soap.model.UserSoap;

public interface UpdateUserSoapPort {

    void updateUser(String username, UserSoap user);
}
