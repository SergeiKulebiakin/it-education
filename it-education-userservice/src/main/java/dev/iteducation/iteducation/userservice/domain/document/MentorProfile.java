package dev.iteducation.iteducation.userservice.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author Sergey Kulebyakin (Sergey.Kulebyakin@lanit-tercom.com) created on 21.08.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorProfile {

	@Id
	private String id;

	private String userProfileId;

	private Long lessonsDone;

	private Long rate;

}
