package dev.iteducation.iteducation.userservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountCreationRequest {

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

}
