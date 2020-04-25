package pl.lodz.p.it.tks.rest.tests;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.it.model.catalogs.BookWeb;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@Testcontainers
public class GetBooksTest {

    @Container
    private static GenericContainer serviceOne = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payaramicro11")
                            .copy("restservices.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("restservices.war", Path.of("target", "restservices-1.0-SNAPSHOT.war"))
    )
            .withNetwork(Network.SHARED)
            .withExposedPorts(8080, 4848, 6900)
            .waitingFor(Wait.forHttp("/restservices/resources/javaee8").forPort(8080).forStatusCode(200))
            .withLogConsumer(new Slf4jLogConsumer(log));

    @Test
    public void testServiceOne() {
        Client client = ResteasyClientBuilder.newClient();
        WebTarget base = client.target("http://localhost:8080/restservices/resources/api");
        List<BookWeb> books = base.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        assertEquals(3, books.size());
    }
}
