package pl.lodz.p.it.tks.soap.client.tests;

import org.junit.jupiter.api.Test;
import pl.lodz.p.it.tks.soap.client.ManagerSoap;
import pl.lodz.p.it.tks.soap.client.UserFilterSoapServiceImpl;
import pl.lodz.p.it.tks.soap.client.UserFilterSoapServiceImplService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFilterSoapServiceTest {

    @Test
    public void filterUsers(){
        UserFilterSoapServiceImpl userFilterSoapService = new UserFilterSoapServiceImplService().getUserFilterSoapServiceImplPort();
        List<ManagerSoap> managers = userFilterSoapService.filterManagers("Jimmy");
        assertEquals("Jimmy",managers.get(0).getFirstName());
        assertEquals(1,managers.size());
    }

}

