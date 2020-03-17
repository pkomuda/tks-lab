package pl.lodz.p.it.tks.domainmodel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public abstract @Data class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean active;

    public String getActivity() {
        if (active) {
            return "Active";
        } else {
            return "Inactive";
        }
    }
}
