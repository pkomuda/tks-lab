package pl.lodz.p.it.webapplication.controllers.users;

import lombok.Data;
import pl.lodz.p.it.model.users.AdminWeb;
import pl.lodz.p.it.model.users.ClientWeb;
import pl.lodz.p.it.model.users.ManagerWeb;
import pl.lodz.p.it.model.users.UserWeb;
import pl.lodz.p.it.webapplication.controllers.mq.RabbitPublisher;
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
    private RabbitPublisher rabbitPublisher;

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
        UserWeb user = null;
        switch (userType) {
            case "admin":
                user = new AdminWeb(username, password, firstName, lastName, active);
                break;
            case "manager":
                user = new ManagerWeb(username, password, firstName, lastName, active);
                break;
            case "client":
                user = new ClientWeb(username, password, firstName, lastName, active);
                break;
        }
        rabbitPublisher.publish(user, "user.create");
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
