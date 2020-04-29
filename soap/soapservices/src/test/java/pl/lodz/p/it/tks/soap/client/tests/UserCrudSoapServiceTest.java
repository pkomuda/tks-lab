package pl.lodz.p.it.tks.soap.client.tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.lodz.p.it.tks.soap.client.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserCrudSoapServiceTest {

        private UserCrudSoapServiceImpl userCrudSoapService = new UserCrudSoapServiceImplService().getUserCrudSoapServiceImplPort();
        private UserGetSoapServiceImpl userGetSoapService = new UserGetSoapServiceImplService().getUserGetSoapServiceImplPort();

    @Order(1)
    @Test
    public void addUserSoapTest(){

        AdminSoap adminSoap = new AdminSoap();
        adminSoap.setActive(true);
        adminSoap.setFirstName("Thierry");
        adminSoap.setLastName("Henry");
        adminSoap.setPassword("ball");
        adminSoap.setUsername("ThierryHenry12");
        this.userCrudSoapService.addAdmin(adminSoap);
        assertEquals("Henry",userGetSoapService.getAdmins().get(4).getLastName());
    }

    @Order(2)
    @Test
    public void updateUserSoapTest(){
        AdminSoap adminSoap = new AdminSoap();
        adminSoap.setActive(true);
        adminSoap.setFirstName("Thierry");
        adminSoap.setLastName("Henry9");
        adminSoap.setPassword("football");
        adminSoap.setUsername("ThierryHenry12");
        userCrudSoapService.updateUser("ThierryHenry12",adminSoap);
        assertEquals("Henry9",userGetSoapService.getAdmins().get(4).getLastName());
    }

}
