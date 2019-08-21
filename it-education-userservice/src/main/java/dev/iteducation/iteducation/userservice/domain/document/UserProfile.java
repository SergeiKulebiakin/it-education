package dev.iteducation.iteducation.userservice.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document
public class UserProfile {

    @Id
    private String id;

    private String accountId;

    private String name;

    private String image;

    private String about;

    private String mentorId;

    private String studentId;

    public boolean isMentor() {
        return mentorId != null;
    }

    public boolean isStudent() {
        return studentId != null;
    }

}
