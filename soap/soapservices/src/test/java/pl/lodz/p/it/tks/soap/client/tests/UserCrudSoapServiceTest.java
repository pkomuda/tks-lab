package pl.lodz.p.it.tks.soap.client.tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.lodz.p.it.tks.appservices.services.user.UserFilterService;
import pl.lodz.p.it.tks.appservices.services.user.impl.UserFilterServiceImpl;
import pl.lodz.p.it.tks.rest.domainmodel.users.User;
import pl.lodz.p.it.tks.soap.client.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserCrudSoapServiceTest {

        private UserCrudSoapServiceImpl userCrudSoapService = new UserCrudSoapServiceImplService().getUserCrudSoapServiceImplPort();
        private UserGetSoapServiceImpl userGetSoapService = new UserGetSoapServiceImplService().getUserGetSoapServiceImplPort();
    private UserGetSoapServiceImpl userGetSoapService2 = new UserGetSoapServiceImplService().getUserGetSoapServiceImplPort();


    @Order(1)
    @Test
    public void addUserSoapTest(){

        List<AdminSoap> adminSoapList = userGetSoapService.getAdmins();
        for(AdminSoap adminSoap : adminSoapList){
            String newUsername = UUID.randomUUID().toString();
            if (!newUsername.equals(adminSoap.getUsername())){
                AdminSoap newAdminSoap = new AdminSoap();
                newAdminSoap.setActive(true);
                newAdminSoap.setFirstName(newUsername);
                newAdminSoap.setLastName(newUsername);
                newAdminSoap.setPassword(newUsername);
                newAdminSoap.setUsername(newUsername );
                userCrudSoapService.addAdmin(newAdminSoap);
                break;
            }
        }
        assertEquals(adminSoapList.size()+1,userGetSoapService.getAdmins().size());
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
        assertEquals("Henry9",userGetSoapService.getUser("ThierryHenry12").getLastName());
        AdminSoap adminSoap2 = new AdminSoap();
        adminSoap2.setActive(true);
        adminSoap2.setFirstName("Thierry");
        adminSoap2.setLastName("Henry12");
        adminSoap2.setPassword("football");
        adminSoap.setUsername("ThierryHenry12");
        userCrudSoapService.updateUser("ThierryHenry12",adminSoap2);
        assertEquals("Henry12",userGetSoapService.getUser("ThierryHenry12").getLastName());
    }

}
