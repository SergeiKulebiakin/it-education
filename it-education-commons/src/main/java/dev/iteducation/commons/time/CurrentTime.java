package dev.iteducation.commons.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 21.08.2019.
 */
public interface CurrentTime {

	LocalTime MAX_TIME = LocalTime.MAX.truncatedTo(ChronoUnit.MICROS);

	/**
	 * Current time moment
	 *
	 * @return current time instant
	 */
	Instant nowInstant();

	/**
	 * Current server datetime
	 *
	 * @return current server datetime
	 */
	default LocalDateTime nowLocalDateTime() {
		return LocalDateTime.ofInstant(nowInstant(), getServerTimeZone());
	}

	/**
	 * Current server date
	 *
	 * @return current server date
	 */
	default LocalDate nowLocalDate() {
		return nowLocalDateTime().toLocalDate();
	}

	/**
	 * Current server time
	 *
	 * @return current server time
	 */
	default LocalTime nowLocalTime() {
		return nowLocalDateTime().toLocalTime();
	}

	/**
	 * Current server timezone
	 *
	 * @return server timezone
	 */
	ZoneOffset getServerTimeZone();

}
