package pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import uiports.aggregates.userweb.UserWebCrudAdapter;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public @Data class AddUserController implements Serializable {

    @Inject
    private UserWebCrudAdapter userCrudAdapter;
    @Inject
    private Conversation conversation;
    private String userType;
    private String username;
    private String password;
    private boolean active;
    private String firstName;
    private String lastName;

    public String add() {
        beginConversation();
        return "add";
    }

    public String confirm(String userType) {
        switch (userType) {
            case "admin":
                userCrudAdapter.addUser(new AdminWeb(username, password, firstName, lastName, active));
                break;
            case "manager":
                userCrudAdapter.addUser(new ManagerWeb(username, password, firstName, lastName, active));
                break;
            case "client":
                userCrudAdapter.addUser(new ClientWeb(username, password, firstName, lastName, active));
                break;
        }
        endConversation();
        return "admin";
    }

    public String cancel() {
        return "cancel";
    }

    public String cancelConfirm() {
        return "cancelConfirm";
    }

    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    private void endConversation() {
        conversation.end();
    }
}
