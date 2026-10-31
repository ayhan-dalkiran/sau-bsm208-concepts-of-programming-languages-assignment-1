/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Uzay araçlarının saklandığı dosyayı okuyan sınıf.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import pdp1.simulation.Planet;
import pdp1.simulation.Spacecraft;
import pdp1.time.DateTime;

public class SpacecraftFileReader extends SimulationObjectFileReader {
	public SpacecraftFileReader(String pathName) throws FileNotFoundException, SecurityException {
		super(pathName);
	}
	
	public LinkedHashMap<String, Spacecraft> readAllSpacecrafts(LinkedHashMap<String, Planet> planets) {
		LinkedHashMap<String, Spacecraft> spacecrafts = new LinkedHashMap<String, Spacecraft>();
		
		while (this.hasNext()) {
			Spacecraft spacecraft = this.readSpacecraft(planets);
			spacecrafts.put(spacecraft.getName(), spacecraft);
		}
		
		return spacecrafts;
	}
	
	public Spacecraft readSpacecraft(LinkedHashMap<String, Planet> planets) {
		String name = this.readString();
		String sourcePlanetName = this.readString();
		String destinationPlanetName = this.readString();
		LocalDate exitDate = this.readLocalDate();
		int distanceLeftInHours = this.readInt();
		
		Planet sourcePlanet = planets.get(sourcePlanetName);
		Planet destinationPlanet = planets.get(destinationPlanetName);
		DateTime exitDateTime = new DateTime(sourcePlanet.getTimeSystem(), exitDate, 0);
		return new Spacecraft(name, sourcePlanet, destinationPlanet, exitDateTime, distanceLeftInHours);
	}
}