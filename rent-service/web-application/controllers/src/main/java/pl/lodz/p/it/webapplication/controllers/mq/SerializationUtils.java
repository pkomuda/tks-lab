package pl.lodz.p.it.webapplication.controllers.mq;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@Slf4j
public class SerializationUtils {

    private static String toJsonString(UserWeb source) {
        JsonObject value = Json.createObjectBuilder()
                .add("username", source.getUsername())
                .add("password", source.getPassword())
                .add("firstName", source.getFirstName())
                .add("lastName", source.getLastName())
                .add("active", source.isActive())
                .build();
        return Json.createObjectBuilder()
                .add(source.getClass().getSimpleName(), value)
                .build()
                .toString();
    }

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

    public static byte[] serialize(Serializable source) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            if (source instanceof UserWeb) {
                out.writeObject(toJsonString((UserWeb) source));
            } else {
                out.writeObject(source);
            }
            out.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Objects.requireNonNull(bytes);
    }

    private static UserWeb deserialize(JsonObject source) {
        String userType = source.getString("type");
        UserWeb user = null;
        if (containsIgnoreCase(userType, "admin")) {
            user = new AdminWeb();
        } else if (containsIgnoreCase(userType, "manager")) {
            user = new ManagerWeb();
        } else if (containsIgnoreCase(userType, "client")) {
            user = new ClientWeb();
        }
        Objects.requireNonNull(user).setUsername(source.getString("username"));
        user.setPassword(source.getString("password"));
        user.setFirstName(source.getString("firstName"));
        user.setLastName(source.getString("lastName"));
        user.setActive(source.getBoolean("active"));
        return user;
    }

    public static UserWeb deserializeUser(byte[] source) {
        Object o = toObject(source);
        if (o instanceof String) {
            JsonReader reader = Json.createReader(new StringReader((String) o));
            JsonObject jsonObject = reader.readObject();
            return deserialize(jsonObject);
        } else {
            return null;
        }
    }

    public static List<UserWeb> deserializeUsers(byte[] source) {
        Object o = toObject(source);
        List<UserWeb> users = new ArrayList<>();
        if (o instanceof String) {
            JsonReader reader = Json.createReader(new StringReader((String) o));
            JsonArray jsonArray = reader.readArray();
            for (JsonValue jsonValue : jsonArray) {
                JsonObject jsonObject = jsonValue.asJsonObject();
                users.add(deserialize(jsonObject));
            }
        }
        return users;
    }
}
