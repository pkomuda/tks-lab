package pl.lodz.p.it.tks.soap.client.tests;

import org.junit.jupiter.api.Test;
import pl.lodz.p.it.tks.soap.client.UserGetSoapServiceImplService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetSoapServiceTest {

    @Test
    public void getClientsTest() {
        UserGetSoapServiceImplService service = new UserGetSoapServiceImplService();
        assertEquals(3, service.getUserGetSoapServiceImplPort().getClients().size());
    }
}
