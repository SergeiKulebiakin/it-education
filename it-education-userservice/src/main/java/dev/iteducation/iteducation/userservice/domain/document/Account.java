package dev.iteducation.iteducation.userservice.domain.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("account")
public class Account {

    @Id
    private String id;
    private String email;
    private String name;
    private String password;

    public Account(String email, String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
