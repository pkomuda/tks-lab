package pl.lodz.p.it.tks.mq;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.userservice.domainmodel.Type;
import pl.lodz.p.it.tks.userservice.domainmodel.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.Objects;

@Slf4j
public class SerializationUtils {

    private static boolean containsIgnoreCase(String str, String searchStr) {
        return str.toLowerCase().contains(searchStr.toLowerCase());
    }

    private static String toJsonString(User source) {
        return Json.createObjectBuilder()
                .add("username", source.getUsername())
                .add("password", source.getPassword())
                .add("firstName", source.getFirstName())
                .add("lastName", source.getLastName())
                .add("active", source.isActive())
                .add("type", source.getType().name())
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

    public static User deserialize(byte[] source) {
        Object o = toObject(source);
        if (o instanceof String) {
            JsonReader reader = Json.createReader(new StringReader((String) o));
            JsonObject root = reader.readObject();
            String userType = root.keySet().iterator().next();
            JsonObject jsonObject = root.getJsonObject(userType);
            User user = new User();
            user.setUsername(jsonObject.getString("username"));
            user.setPassword(jsonObject.getString("password"));
            user.setFirstName(jsonObject.getString("firstName"));
            user.setLastName(jsonObject.getString("lastName"));
            user.setActive(jsonObject.getBoolean("active"));
            for (Type type : Type.values()) {
                if (containsIgnoreCase(userType, type.name())) {
                    user.setType(type);
                }
            }
            return user;
        } else {
            return null;
        }
    }

    public static byte[] serialize(Serializable source) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            if (source instanceof User) {
                out.writeObject(toJsonString((User) source));
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
}
