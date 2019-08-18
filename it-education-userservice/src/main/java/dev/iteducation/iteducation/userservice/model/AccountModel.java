package dev.iteducation.iteducation.userservice.model;

import dev.iteducation.iteducation.userservice.domain.document.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountModel {

    private String id;
    private String email;
    private String name;

    public AccountModel(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
    }
}
