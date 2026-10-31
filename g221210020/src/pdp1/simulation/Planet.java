/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Gezegene ait bilgileri tutan sınıf.
 * </p>
 */
package pdp1.simulation;

import pdp1.time.DateTime;
import pdp1.time.TimeSystem;

public class Planet implements SimulationObject {
	protected final String name;
	protected final TimeSystem timeSystem;
	protected DateTime dateTime;
	protected int population = 0;
	
	public String getName() {
		return name;
	}
	public TimeSystem getTimeSystem() {
		return timeSystem;
	}
	public DateTime getDateTime() {
		return dateTime;
	}
	public void incrementDateTimeBy(int hours) {
		if (hours < 0)
			throw new IllegalArgumentException("hours cannot be negative, got \"" + hours + "\"");
		
		this.dateTime = this.dateTime.AddHours(hours);
	}
	public int getPopulation() {
		return population;
	}
	public void updatePopulationBy(int amount) {
		this.population += amount;
	}

	public Planet(String name, TimeSystem timeSystem, DateTime dateTime) {
		if (dateTime.getTimeSystem() != timeSystem)
			throw new IllegalArgumentException("provided timeSystem and dateTime's timeSystem does not match");
		
		this.name = name;
		this.timeSystem = timeSystem;
		this.dateTime = dateTime;
	}
	
	@Override
	public void stepBy(int hours) {
		this.incrementDateTimeBy(hours);
	}
}
