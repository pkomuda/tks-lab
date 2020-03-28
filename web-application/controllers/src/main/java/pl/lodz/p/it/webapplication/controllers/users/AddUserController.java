package main.java.pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.user.UserCrudService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ConversationScoped
public @Data class AddUserController implements Serializable {

    @Inject
    private UserCrudService userCrudService;
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
                userCrudService.addAdmin(username, active, firstName, lastName, password);
                break;
            case "manager":
                userCrudService.addManager(username, active, firstName, lastName, password);
                break;
            case "client":
                userCrudService.addClient(username, active, firstName, lastName, password);
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
