package pl.lodz.p.it.tks.userservice.adapters.converters;

import pl.lodz.p.it.tks.userservice.datamodel.UserEntity;
import pl.lodz.p.it.tks.userservice.domainmodel.User;

public class UserConverter {

    public static User entityToUser(UserEntity userEntity) {
        return new User();
    }

    public static UserEntity userToEntity(User user) {
        return new UserEntity();
    }
}
