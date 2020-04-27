package pl.lodz.p.it.tks.rest.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
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
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.model.BookRestModel;

import javax.swing.text.html.parser.Parser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItem;
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

    @Test
    public void checkBooksAmount(){
//        JsonPath jsonPath = given()
//                .when()
//                .get("http://localhost:" + port8080 +"/payararest/resources/api/books")
//                .then()
//                .assertThat()
//                .statusCode(Response.Status.OK.getStatusCode())
//                .assertThat()
//                .extract().body().jsonPath();

//        List<BookRestModel> returnedbooks = jsonPath.getList("",BookRestModel.class);
        BookRestModel[] books = given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().get("http://localhost:" + port8080 +"/payararest/resources/api/books").as(BookRestModel[].class);
//        Response r = (Response) given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .when()
//                .get("http://localhost:" + port8080 +"/payararest/resources/api/books")
//                .then()
//                .body("", hasItem("The Shining"));
//        assertEquals(200,r.getStatus());
//        for(int i=0; i<3; i++){
//            System.out.println(returnedbooks.get(i).getAuthor());
//        }
//        ArrayList<BookRestModel> books = client
//                .target("http://localhost:" + port8080 +"/payararest/resources/api")
//                .path("books")
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<>(){});
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

    @AfterAll
    public static void end(){
        serviceOne.stop();
    }
}
