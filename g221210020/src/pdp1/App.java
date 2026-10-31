/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Programın başlangıç methodunu içeren uygulama sınıfı.
 * Simulasyon neslenelerinin saklandığı dosyalar okunur,
 * simulasyona yüklenir ve simulasyon adım adım ilerletilip ekrana yazdırılır.
 * </p>
 */
package pdp1;

import java.io.IOException;

import pdp1.simulation.Simulation;
import pdp1.streamio.reader.PersonFileReader;
import pdp1.streamio.reader.PlanetFileReader;
import pdp1.streamio.reader.SpacecraftFileReader;
import pdp1.streamio.writer.SimulationWriter;

public class App {

	public static void main(String[] args) throws SecurityException, IOException, InterruptedException {
		var planetReader = new PlanetFileReader("./Gezegenler.txt");
		var planets = planetReader.readAllPlanets();
		planetReader.close();
		
		var spacecraftReader = new SpacecraftFileReader("./Araclar.txt");
		var spacecrafts = spacecraftReader.readAllSpacecrafts(planets);
		spacecraftReader.close();
		
		var personReader = new PersonFileReader("./Kisiler.txt");
		var people = personReader.readAllPeople(spacecrafts);
		personReader.close();
		
		var simulation = new Simulation(planets, spacecrafts, people, 1);
		var simulationWriter = new SimulationWriter(System.out, simulation);
		var processBuilder = new ProcessBuilder("cmd", "/c", "cls");
		processBuilder.inheritIO();
		while (!simulation.isDone()) {
			processBuilder.start().waitFor();
			simulation.step();
			simulationWriter.writeSimulation();
		}
		simulationWriter.writeSteps();
		simulationWriter.close();
	}
}
