/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Verilen SimulationObject'leri ile basit sanal gerçekleme sınıfı.
 * Tüm uzay araçları imha ya da hedefe ulaştıktan sonra kendisini durdurur.
 * </p>
 */
package pdp1.simulation;
import java.util.LinkedHashMap;

public final class Simulation {
	protected final LinkedHashMap<String, Planet> planets;
	protected final LinkedHashMap<String, Spacecraft> spacecrafts;
	protected final LinkedHashMap<String, Person> people;
	protected final int hoursPerStep;
	protected int stepsTaken = 0;
	protected boolean done = false;
	
	public LinkedHashMap<String, Planet> getPlanets() {
		return planets;
	}
	public LinkedHashMap<String, Spacecraft> getSpacecrafts() {
		return spacecrafts;
	}
	public LinkedHashMap<String, Person> getPeople() {
		return people;
	}
	public int getHoursPerStep() {
		return hoursPerStep;
	}
	public int getStepsTaken() {
		return this.stepsTaken;
	}
	public boolean isDone() {
		return this.done;
	}
	
	public Simulation(LinkedHashMap<String, Planet> planets, LinkedHashMap<String, Spacecraft> spacecrafts, LinkedHashMap<String, Person> people, int hoursPerStep) {
		this.planets = planets;
		this.spacecrafts = spacecrafts;
		this.people = people;
		this.hoursPerStep = hoursPerStep;
	}
	
	public void step() {
		if (this.done)
			return;
		
		for (var planet : this.planets.values()) {
			planet.stepBy(this.hoursPerStep);
		}
		for (var person : this.people.values()) {
			person.stepBy(this.hoursPerStep);
		}
		
		int incompleteAmount = 0;
		for (var spacecraft : this.spacecrafts.values()) {
			spacecraft.stepBy(this.hoursPerStep);
			
			if (spacecraft.status == SpacecraftStatus.waiting || spacecraft.status == SpacecraftStatus.enroute)
				incompleteAmount += 1;
		}
		
		if (incompleteAmount == 0)
			this.done = true;
		
		this.stepsTaken += 1;
	}
}
