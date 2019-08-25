package dev.iteducation.iteducation.userservice.domain.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document("account")
public class Account {

    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private Boolean enabled;
    private List<UserRole> roles;

    public Account(String email, String name, String password, Boolean enabled, List<UserRole> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }
}
