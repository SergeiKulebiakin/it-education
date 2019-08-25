package dev.iteducation.commons.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * {@inheritDoc}
 */
public class CurrentTimeImpl implements CurrentTime {

	private final Instant now;

	private final static ZoneOffset SERVER_TIME_ZONE = ZoneOffset.of("+00:00");

	public CurrentTimeImpl() {
		now = null;
	}

	public static LocalDateTime getServerDateTime() {
		return LocalDateTime.now(SERVER_TIME_ZONE);
	}

	public static LocalDate getServerDate() {
		return LocalDate.now(SERVER_TIME_ZONE);
	}

	/**
	 * Проверка пересечения временных интервалов
	 *
	 * @param start1 - начало первого интервала
	 * @param end1   - конец первого интервала
	 * @param start2 - начало второго интервала
	 * @param end2   - конец первого интервала
	 * @return - true, если даты пересекаются, false - если нет
	 */
	public static boolean isDatesIntersects(LocalDateTime start1,
											LocalDateTime end1,
											LocalDateTime start2,
											LocalDateTime end2) {
		return !start1.isAfter(end2) && !end1.isBefore(start2);
	}

	public static LocalDateTime getStartOfTheDay(LocalDateTime dateTime) {
		return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
	}

	public static LocalDateTime getEndOfTheDay(LocalDateTime dateTime) {
		return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX).truncatedTo(ChronoUnit.MICROS);
	}

	public static LocalDateTime getStartOfTheDay(LocalDate date) {
		return date.atStartOfDay();
	}

	public static LocalDateTime getEndOfTheDay(LocalDate date) {
		return date.atTime(LocalTime.MAX).truncatedTo(ChronoUnit.MICROS);
	}

	@Override
	public Instant nowInstant() {
		if(now == null) {
			return Instant.now();
		} else {
			return now;
		}
	}

	@Override
	public ZoneOffset getServerTimeZone() {
		return SERVER_TIME_ZONE;
	}

}
