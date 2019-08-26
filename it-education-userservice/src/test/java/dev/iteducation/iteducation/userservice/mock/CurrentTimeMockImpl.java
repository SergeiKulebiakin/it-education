package dev.iteducation.iteducation.userservice.mock;

import dev.iteducation.commons.time.CurrentTimeImpl;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 26.08.2019.
 */
public class CurrentTimeMockImpl extends CurrentTimeImpl {

	private static final LocalDateTime DEFAULT = LocalDateTime.of(2020, 3, 1, 9, 59);

	private LocalDateTime localDateTime = DEFAULT;

	/**
	 * Set datetime for tests.
	 *
	 * @param localDateTime datetime for tests
	 */
	public void set(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public void setDefault() { localDateTime = DEFAULT; }

	@Override
	public Instant nowInstant() {
		return localDateTime.toInstant(getServerTimeZone());
	}

}
