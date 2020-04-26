package pl.lodz.p.it.tks.rest.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.it.model.catalogs.BookWeb;
import pl.lodz.p.it.tks.rest.model.BookRestModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@Testcontainers
public class GetBooksTest {

    private static Client client;
    private static Integer port8080;
    private static WebTarget target;
    @Container
    public static GenericContainer serviceOne = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/micro:jdk11")
                            .copy("restservices.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("restservices.war", Path.of("target", "restservices-1.0-SNAPSHOT.war"))
    )
            .withExposedPorts(8080, 4848, 6900)
            .waitingFor(Wait.forHttp("/payararest/resources/javaee8").forPort(8080).forStatusCode(200))
            .withLogConsumer(new Slf4jLogConsumer(log));
    @BeforeAll
    public static void init(){
        serviceOne.start();
        port8080 = serviceOne.getMappedPort(8080);
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:" + port8080 +"/payararest/resources/api");
    }
    @Test
    public void getBooksTest() {
        Response r = target
                .path("books")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(200,r.getStatus());
    }

    @Test
    public void getMoviesTest(){
        Response r = target
                .path("movies")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(r.getStatus(),200);
    }

//    @Test
//    public void checkBooksAmount(){
//        ArrayList<BookRestModel> books = client
//                .target("http://localhost:" + port8080 +"/payararest/resources/api")
//                .path("books")
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<>(){});
//        Assert.assertEquals(books.size(),3);
//    }


    @AfterAll
    public static void end(){
        serviceOne.stop();
    }
}
