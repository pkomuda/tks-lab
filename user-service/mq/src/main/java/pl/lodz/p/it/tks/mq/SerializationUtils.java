package pl.lodz.p.it.tks.mq;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.userservice.domainmodel.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.Objects;

@Slf4j
public class SerializationUtils {

    public static Object deserialize(byte[] source) {
        Object o = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(source);
        try (ObjectInput in = new ObjectInputStream(bis)) {
            o = in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            log.error(e.getMessage());
        }
        return Objects.requireNonNull(o);
    }

    public static void toUser(Object source) {
        if (source instanceof String) {
            JsonReader reader = Json.createReader(new StringReader((String) source));
            JsonObject jsonObject = reader.readObject();
            log.info("UZYTKOWNIK " + jsonObject.keySet().iterator().next());
//            User user = new User();
//            switch (jsonObject.keySet().iterator().next()) {
//
//            }
//            user.setUsername(jsonObject.);
//            user.setPassword(jsonObject.getString("password"));
//            user.setFirstName(jsonObject.getString("firstName"));
//            user.setLastName(jsonObject.getString("lastName"));
//            user.setActive(jsonObject.getBoolean("active"));
        } /*else {
            return null;
        }*/
    }
}
