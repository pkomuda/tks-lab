package pl.lodz.p.it.tks.mqrentservice;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@Slf4j
public class SerializationUtils {

    private static Object toObject(byte[] source) {
        Object o = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(source);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            o = in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            log.error(e.getMessage());
        }
        return Objects.requireNonNull(o);
    }

    public static User deserialize(byte[] source) {
        Object o = toObject(source);
        if (o instanceof String) {
            JsonReader reader = Json.createReader(new StringReader((String) o));
            JsonObject root = reader.readObject();
            String userType = root.keySet().iterator().next();
            JsonObject jsonObject = root.getJsonObject(userType);
            User user = null;
            if (containsIgnoreCase(userType, "admin")) {
                user = new Admin();

            } else if (containsIgnoreCase(userType, "client")) {
                user = new Client();

            } else if (containsIgnoreCase(userType, "manager")) {
                user = new Manager();
            }
            Objects.requireNonNull(user).setUsername(jsonObject.getString("username"));
            user.setFirstName(jsonObject.getString("firstName"));
            user.setLastName(jsonObject.getString("lastName"));
            user.setActive(jsonObject.getBoolean("active"));

            return user;

        } else {
            return null;
        }

    }
}
