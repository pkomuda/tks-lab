
package pl.lodz.p.it.tks.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.lodz.p.it.tks.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUserResponse_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getUserResponse");
    private final static QName _GetUser_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getUser");
    private final static QName _GetAdminsResponse_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getAdminsResponse");
    private final static QName _GetAdmins_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getAdmins");
    private final static QName _UserSoap_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "userSoap");
    private final static QName _GetClients_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getClients");
    private final static QName _GetClientsResponse_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getClientsResponse");
    private final static QName _GetManagers_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getManagers");
    private final static QName _GetManagersResponse_QNAME = new QName("http://client.soap.tks.it.p.lodz.pl/", "getManagersResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.lodz.p.it.tks.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetAdminsResponse }
     * 
     */
    public GetAdminsResponse createGetAdminsResponse() {
        return new GetAdminsResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetClients }
     * 
     */
    public GetClients createGetClients() {
        return new GetClients();
    }

    /**
     * Create an instance of {@link GetAdmins }
     * 
     */
    public GetAdmins createGetAdmins() {
        return new GetAdmins();
    }

    /**
     * Create an instance of {@link GetManagersResponse }
     * 
     */
    public GetManagersResponse createGetManagersResponse() {
        return new GetManagersResponse();
    }

    /**
     * Create an instance of {@link GetClientsResponse }
     * 
     */
    public GetClientsResponse createGetClientsResponse() {
        return new GetClientsResponse();
    }

    /**
     * Create an instance of {@link GetManagers }
     * 
     */
    public GetManagers createGetManagers() {
        return new GetManagers();
    }

    /**
     * Create an instance of {@link ClientSoap }
     * 
     */
    public ClientSoap createClientSoap() {
        return new ClientSoap();
    }

    /**
     * Create an instance of {@link AdminSoap }
     * 
     */
    public AdminSoap createAdminSoap() {
        return new AdminSoap();
    }

    /**
     * Create an instance of {@link ManagerSoap }
     * 
     */
    public ManagerSoap createManagerSoap() {
        return new ManagerSoap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdminsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getAdminsResponse")
    public JAXBElement<GetAdminsResponse> createGetAdminsResponse(GetAdminsResponse value) {
        return new JAXBElement<GetAdminsResponse>(_GetAdminsResponse_QNAME, GetAdminsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdmins }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getAdmins")
    public JAXBElement<GetAdmins> createGetAdmins(GetAdmins value) {
        return new JAXBElement<GetAdmins>(_GetAdmins_QNAME, GetAdmins.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSoap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "userSoap")
    public JAXBElement<UserSoap> createUserSoap(UserSoap value) {
        return new JAXBElement<UserSoap>(_UserSoap_QNAME, UserSoap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getClients")
    public JAXBElement<GetClients> createGetClients(GetClients value) {
        return new JAXBElement<GetClients>(_GetClients_QNAME, GetClients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getClientsResponse")
    public JAXBElement<GetClientsResponse> createGetClientsResponse(GetClientsResponse value) {
        return new JAXBElement<GetClientsResponse>(_GetClientsResponse_QNAME, GetClientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetManagers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getManagers")
    public JAXBElement<GetManagers> createGetManagers(GetManagers value) {
        return new JAXBElement<GetManagers>(_GetManagers_QNAME, GetManagers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetManagersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://client.soap.tks.it.p.lodz.pl/", name = "getManagersResponse")
    public JAXBElement<GetManagersResponse> createGetManagersResponse(GetManagersResponse value) {
        return new JAXBElement<GetManagersResponse>(_GetManagersResponse_QNAME, GetManagersResponse.class, null, value);
    }

}
