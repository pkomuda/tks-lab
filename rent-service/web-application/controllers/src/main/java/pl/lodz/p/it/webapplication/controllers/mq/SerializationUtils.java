package pl.lodz.p.it.webapplication.controllers.mq;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.model.users.UserWeb;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

@Slf4j
public class SerializationUtils {

    private static String toJsonString(UserWeb user) {
        JsonObject value = Json.createObjectBuilder()
                .add("username", user.getUsername())
                .add("password", user.getPassword())
                .add("firstName", user.getFirstName())
                .add("lastName", user.getLastName())
                .add("active", user.isActive())
                .build();
        return Json.createObjectBuilder()
                .add(user.getClass().getSimpleName(), value)
                .build()
                .toString();
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
}
