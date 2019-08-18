package dev.iteducation.iteducation.userservice.domain.document;

import lombok.Data;

@Data
public class UserContact {

    private UserContactType type;
    private String value;

}
