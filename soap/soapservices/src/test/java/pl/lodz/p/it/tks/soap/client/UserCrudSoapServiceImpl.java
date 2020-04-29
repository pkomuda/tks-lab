
package pl.lodz.p.it.tks.soap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserCrudSoapServiceImpl", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserCrudSoapServiceImpl {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.UpdateUserResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/updateUserRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/updateUserResponse")
    public void updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        UserSoap arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addClient", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddClient")
    @ResponseWrapper(localName = "addClientResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddClientResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addClientRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addClientResponse")
    public void addClient(
        @WebParam(name = "arg0", targetNamespace = "")
        ClientSoap arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addAdmin", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddAdmin")
    @ResponseWrapper(localName = "addAdminResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddAdminResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addAdminRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addAdminResponse")
    public void addAdmin(
        @WebParam(name = "arg0", targetNamespace = "")
        AdminSoap arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addManager", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddManager")
    @ResponseWrapper(localName = "addManagerResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.AddManagerResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addManagerRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserCrudSoapServiceImpl/addManagerResponse")
    public void addManager(
        @WebParam(name = "arg0", targetNamespace = "")
        ManagerSoap arg0);

}
