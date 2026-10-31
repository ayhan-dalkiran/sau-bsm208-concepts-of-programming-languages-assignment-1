/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Gezegenlerin saklandığı dosyayı okuyan sınıf.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import pdp1.simulation.Planet;
import pdp1.time.DateTime;
import pdp1.time.TimeSystem;

public class PlanetFileReader extends SimulationObjectFileReader {
	public PlanetFileReader(String pathName) throws FileNotFoundException, SecurityException {
		super(pathName);
	}
	
	public LinkedHashMap<String, Planet> readAllPlanets() {
		LinkedHashMap<String, Planet> planets = new LinkedHashMap<String, Planet>();
		
		while (this.hasNext()) {
			Planet planet = this.readPlanet();
			planets.put(planet.getName(), planet);
		}
		
		return planets;
	}
	
	public Planet readPlanet() {
		String name = this.readString();
		int hoursPerDay = this.readInt();
		LocalDate date = this.readLocalDate();
		
		TimeSystem timeSystem = new TimeSystem(hoursPerDay);
		return new Planet(name, timeSystem, new DateTime(timeSystem, date, 0));
	}
}
