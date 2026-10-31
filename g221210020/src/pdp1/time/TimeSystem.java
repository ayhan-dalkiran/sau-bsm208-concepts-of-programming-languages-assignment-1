/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Zaman sistemi (bir günün kaç saat olduğu) bilgisini saklayan sınıf.
 * </p>
 */
package pdp1.time;

public final class TimeSystem {
	protected final int hoursPerDay;
	
	public int getHoursPerDay() {
		return hoursPerDay;
	}

	public TimeSystem(int hoursPerDay) {
		if (hoursPerDay < 1)
			throw new IllegalArgumentException("hoursPerDay can not be smaller than 1, got \"" + hoursPerDay + "\"");
		
		this.hoursPerDay = hoursPerDay;
	}
}
