package dev.iteducation.iteducation.userservice.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {

	@Id
	private String id;

	private String userProfileId;

	private List<String> themesWanted;

	private Long lessonsDone;


}
