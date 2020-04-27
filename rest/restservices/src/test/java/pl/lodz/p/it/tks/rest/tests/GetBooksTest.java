package pl.lodz.p.it.tks.rest.tests;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.Assert;
import org.junit.jupiter.api.*;
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
import java.io.File;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@Slf4j
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetBooksTest {

    private static Client client;
    private static Integer port8080;
    private static WebTarget target;
    private static String path;
    RestAssuredConfig newConfig = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 2000));
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
        path = "http://localhost:" + port8080 +"/payararest/resources/api";
    }

    @Order(1)
    @Test
    public void getMovieTest() {
        MovieRestModel[] movies = RestAssured.given()
                .config(newConfig)
                .contentType(ContentType.JSON)
                .when()
                .get(path +"/movies")
                .as(MovieRestModel[].class);
        Assert.assertEquals(4, movies[1].getId());
        Assert.assertEquals("Trainspotting", movies[1].getTitle());
        Assert.assertEquals("Danny Boyle", movies[1].getDirector());
        Assert.assertEquals(1996, movies[1].getReleaseYear());
        Assert.assertEquals("DVD", movies[1].getFormat());
    }

    @Order(2)
    @Test
    public void checkMoviesAmount() {
        MovieRestModel[] movies = RestAssured.given()
                .config(newConfig)
                .contentType(ContentType.JSON)
                .when()
                .get(path +"/movies")
                .as(MovieRestModel[].class);
        Assert.assertEquals(3, movies.length);
    }

    @Order(3)
    @Test
    public void checkBooksAmount(){
        BookRestModel[] books = given()
                .config(newConfig)
                .contentType(ContentType.JSON)
                .when()
                .get(path+ "/books")
                .as(BookRestModel[].class);

        Assert.assertEquals(3,books.length);
        Assert.assertEquals("The Shining",books[0].getTitle());
        Assert.assertEquals("The Lord of the Rings", books[1].getTitle());
        Assert.assertEquals("What Could Possibly Go Wrong", books[2].getTitle());
    }

    @Order(4)
    @Test
    public void addBookRest() throws InterruptedException {
        File file = new File("F:/SEMESTR_V/TKS/tks_mkwa_czw_15_07/rest/restservices/src/test/java/pl/lodz/p/it/tks/rest/tests/book.json");
        BookRestModel bookRestModel = new BookRestModel(10,"A man with his dog","Bill Gates",1998);
        Response r = RestAssured.given()
                .config(newConfig)
                .contentType(ContentType.JSON)
                .body(file)
                .post(path +"/book")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(200,r.getStatusCode());
        Thread.sleep(3000);
    }

    @Order(5)
    @Test
    public void putBookRest() throws InterruptedException {
        File file = new File("F:/SEMESTR_V/TKS/tks_mkwa_czw_15_07/rest/restservices/src/test/java/pl/lodz/p/it/tks/rest/tests/book.json");
        BookRestModel bookRestModel = new BookRestModel(10,"A man with his dog","Bill Gates",1998);
        Response r = RestAssured.given()
                .config(newConfig)
                .contentType(ContentType.JSON)
                .body(file)
                .put(path +"/book/10")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(200,r.getStatusCode());
        Thread.sleep(3000);
        io.restassured.response.Response response =
                RestAssured.given()
                        .config(newConfig)
                        .contentType(ContentType.JSON)
                        .delete("http://localhost:" + port8080 +"/payararest/resources/api/catalog/10")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        Assert.assertEquals(200,response.getStatusCode());
        Thread.sleep(3000);
    }


    @AfterAll
    public static void end(){
        serviceOne.stop();
    }
}
