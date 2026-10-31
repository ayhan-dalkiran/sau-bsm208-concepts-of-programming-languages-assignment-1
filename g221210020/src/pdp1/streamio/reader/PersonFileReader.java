/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Kişilerin saklandığı dosyayı okuyan sınıf.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import pdp1.simulation.Person;
import pdp1.simulation.Spacecraft;

public class PersonFileReader extends SimulationObjectFileReader {
	public PersonFileReader(String pathName) throws FileNotFoundException, SecurityException {
		super(pathName);
	}
	
	public LinkedHashMap<String, Person> readAllPeople(LinkedHashMap<String, Spacecraft> spacecrafts) {
		LinkedHashMap<String, Person> people = new LinkedHashMap<String, Person>();
		
		while (this.hasNext()) {
			Person person = this.readPerson(spacecrafts);
			people.put(person.getName(), person);
		}
		
		return people;
	}
	
	public Person readPerson(LinkedHashMap<String, Spacecraft> spacecrafts) {
		String name = this.readString();
		int age = this.readInt();
		int lifeRemainingInHours = this.readInt();
		String spacecraftName = this.readString();
		
		Spacecraft spacecraft = spacecrafts.get(spacecraftName);
		return new Person(name, age, lifeRemainingInHours, spacecraft);
	}
}