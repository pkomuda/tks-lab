package pl.lodz.p.it.webmodel.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public abstract @Data class UserWeb {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private boolean active;

    public String getActivity() {
        if (active) {
            return "Active";
        } else {
            return "Inactive";
        }
    }
}