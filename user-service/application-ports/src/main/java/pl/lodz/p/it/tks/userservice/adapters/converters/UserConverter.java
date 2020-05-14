package pl.lodz.p.it.tks.userservice.adapters.converters;

import pl.lodz.p.it.tks.userservice.datamodel.UserEntity;
import pl.lodz.p.it.tks.userservice.datamodel.UserType;
import pl.lodz.p.it.tks.userservice.domainmodel.Type;
import pl.lodz.p.it.tks.userservice.domainmodel.User;

public class UserConverter {

    public static User entityToUser(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setActive(userEntity.isActive());
        if (userEntity.getType() == UserType.ADMIN) {
            user.setType(Type.ADMIN);
        } else if (userEntity.getType() == UserType.MANAGER) {
            user.setType(Type.MANAGER);
        } else if (userEntity.getType() == UserType.CLIENT) {
            user.setType(Type.CLIENT);
        }
        return user;
    }

    public static UserEntity userToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setActive(user.isActive());
        if (user.getType() == Type.ADMIN) {
            userEntity.setType(UserType.ADMIN);
        } else if (user.getType() == Type.MANAGER) {
            userEntity.setType(UserType.MANAGER);
        } else if (user.getType() == Type.CLIENT) {
            userEntity.setType(UserType.CLIENT);
        }
        return userEntity;
    }
}
