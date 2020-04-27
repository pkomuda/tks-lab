package pl.lodz.p.it.tks.rest.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.it.tks.rest.model.BookRestModel;
import pl.lodz.p.it.tks.rest.model.MovieRestModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

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

    @Test
    public void checkBooksAmount() {
        BookRestModel[] books = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().get("http://localhost:" + port8080 +"/payararest/resources/api/books").as(BookRestModel[].class);
        Assert.assertEquals(3,books.length);
        Assert.assertEquals("The Shining",books[0].getTitle());
    }

    @Test
    public void addBookRest(){
        RestAssured.baseURI = "http://localhost:" + port8080 +"/payararest/resources/api/book";
        RequestSpecification requestSpecification =  given();
//                .body("book.json")
//                .contentType(MediaType.APPLICATION_JSON)
//                .post("http://localhost:" + port8080 +"/payararest/resources/api/book");
//        assertEquals(200,.getStatus());

    }

    @Test
    public void getMovieTest() {
        MovieRestModel movie = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:" + port8080 +"/payararest/resources/api/movie/4")
                .as(MovieRestModel.class);
        Assert.assertEquals(4, movie.getId());
        Assert.assertEquals("Trainspotting", movie.getTitle());
        Assert.assertEquals("Danny Boyle", movie.getDirector());
        Assert.assertEquals(1996, movie.getReleaseYear());
        Assert.assertEquals("DVD", movie.getFormat());
    }

    @Test
    public void checkMoviesAmount() {
        MovieRestModel[] movies = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("http://localhost:" + port8080 +"/payararest/resources/api/movies")
                .as(MovieRestModel[].class);
        Assert.assertEquals(3, movies.length);
    }

    @AfterAll
    public static void end(){
        serviceOne.stop();
    }
}
