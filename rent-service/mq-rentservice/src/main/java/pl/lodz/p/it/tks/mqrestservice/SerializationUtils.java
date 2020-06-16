package pl.lodz.p.it.tks.mqrestservice;

import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.tks.rest.domainmodel.users.Admin;
import pl.lodz.p.it.tks.rest.domainmodel.users.Client;
import pl.lodz.p.it.tks.rest.domainmodel.users.Manager;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@Slf4j
public class SerializationUtils {

//    private static JsonObject toJsonObject(User source) {
//        return Json.createObjectBuilder()
//                .add("username", source.getUsername())
//                .add("password", source.getPassword())
//                .add("firstName", source.getFirstName())
//                .add("lastName", source.getLastName())
//                .add("active", source.isActive())
//                .add("type", source.getType().name())
//                .build();
//    }
//
//    private static String toJsonArray(List<User> source) {
//        JsonArrayBuilder builder = Json.createArrayBuilder();
//        for (User user : source) {
//            builder.add(toJsonObject(user));
//        }
//        return builder.build().toString();
//    }

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
            User user =null;
            if(userType=="AdminWeb"){
                 user =new Admin();
            }else if(userType=="ClientWeb"){
                 user = new Client();
            }else if(userType=="ManagerWeb"){
                 user = new Manager();
            }
            user.setUsername(jsonObject.getString("username"));
            user.setFirstName(jsonObject.getString("firstName"));
            user.setLastName(jsonObject.getString("lastName"));
            user.setActive(jsonObject.getBoolean("active"));
            return user;
        } else {
            return null;
        }
    }

//    public static byte[] serialize(Serializable source) {
//        byte[] bytes = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
//            if (source instanceof User) {
//                out.writeObject(toJsonObject((User) source).toString());
//            } else {
//                out.writeObject(source);
//            }
//            out.flush();
//            bytes = bos.toByteArray();
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//        return Objects.requireNonNull(bytes);
//    }
//
//    public static byte[] serializeList(List<User> source) {
//        byte[] bytes = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
//            out.writeObject(toJsonArray(source));
//            out.flush();
//            bytes = bos.toByteArray();
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//        return Objects.requireNonNull(bytes);
//    }
}
