/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Tarih ve saat bilgisinin birlikte saklandığı sınıf.
 * </p>
 */
package pdp1.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateTime {
	private final TimeSystem timeSystem;
	private final LocalDate date;
	private final int hour;

	public TimeSystem getTimeSystem() {
		return this.timeSystem;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public int getHour() {
		return this.hour;
	}

	public DateTime(TimeSystem timeSystem, LocalDate date, int hour) {
		if (hour < 0 || hour >= timeSystem.getHoursPerDay()) {
			throw new IllegalArgumentException("hour has to be within range [0, " + timeSystem.getHoursPerDay() + "), got \"" + hour + "\"");
		}

		this.timeSystem = timeSystem;
		this.date = date;
		this.hour = hour;
	}

	public DateTime AddHours(int hours) {
		if (hours < 0) {
			throw new IllegalArgumentException("hours cannot be negative, got \"" + hours + "\"");
		}

		int hourSum = this.hour + hours;
		LocalDate newDate = this.date.plusDays(hourSum / this.timeSystem.getHoursPerDay());

		return new DateTime(this.timeSystem, newDate, hourSum % this.timeSystem.getHoursPerDay());
	}
	
	public int GetHoursUntil(DateTime futureDate) {
		int days;
		
		if (this.date.isBefore(futureDate.date)) {
			days = (int)this.date.datesUntil(futureDate.date).count();
		}
		else {
			days = -(int)futureDate.date.datesUntil(this.date).count();
		}
		int hours = (futureDate.getHour() - this.hour) + (days * this.timeSystem.hoursPerDay);
		
		return hours;
	}

	protected final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.uuuu");

	@Override
	public String toString() {
		return this.toStringTimeOnly() + " " + this.toStringDateOnly();
	}

	public String toStringDateOnly() {
		return this.date.format(dateFormat);
	}

	public String toStringTimeOnly() {
		return this.hour + ":00:00";
	}
}
