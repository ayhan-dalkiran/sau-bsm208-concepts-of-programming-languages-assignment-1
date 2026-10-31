/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Simulasyon verilerini tablo şeklinde çıkartan yazıcı sınıf.
 * </p>
 */
package pdp1.streamio.writer;

import java.io.IOException;
import java.io.OutputStream;

import pdp1.TableStringBuilder;
import pdp1.simulation.Simulation;
import pdp1.simulation.SpacecraftStatus;

public class SimulationWriter extends Writer {
	protected final Simulation simulation;
	protected final TableStringBuilder planetTable = new TableStringBuilder(" ".repeat(5));
	protected final TableStringBuilder spacecraftTable = new TableStringBuilder(" ".repeat(5));
	
	public SimulationWriter(OutputStream stream, Simulation simulation) {
		super(stream);
		this.simulation = simulation;
		this.InitilizePlanetTable();
		this.InitilizeSpacecraftTable();
	}
	
	protected void UpdatePlanetTable() {
		int j = 0;
		for (var planet : this.simulation.getPlanets().values()) {
			this.planetTable.setCellValue(1, j + 1, planet.getDateTime().toStringDateOnly());
			this.planetTable.setCellValue(2, j + 1, Integer.toString(planet.getPopulation()));
			j++;
		}
	}
	
	protected void InitilizePlanetTable() {
		this.planetTable.setActiveSize(3, this.simulation.getPlanets().size() + 1);
		this.planetTable.setCellValue(0, 0, "");
		this.planetTable.setCellValue(1, 0, "Tarih");
		this.planetTable.setCellValue(2, 0, "Nüfüs");
		
		int j = 0;
		for (var planet : this.simulation.getPlanets().values()) {
			this.planetTable.setCellValue(0, j + 1, planet.getName());
			j++;
		}
	}
	
	protected void updateSpacecraftTable() {
		int i = 0;
		for (var spacecraft : this.simulation.getSpacecrafts().values()) {
			String status =
					spacecraft.getStatus() == SpacecraftStatus.waiting ? "Bekliyor" :
					spacecraft.getStatus() == SpacecraftStatus.enroute ? "Yolda" : 
					spacecraft.getStatus() == SpacecraftStatus.arrived ? "Vardı" : "İMHA";
			this.spacecraftTable.setCellValue(i + 1, 1, status);
			this.spacecraftTable.setCellValue(i + 1, 4, Integer.toString(spacecraft.getDistanceLeftInHours()));
			i++;
		}
	}
	
	protected void InitilizeSpacecraftTable() {
		this.spacecraftTable.setActiveSize(this.simulation.getSpacecrafts().size() + 1, 6);
		this.spacecraftTable.setCellValue(0, 0, "Araç Adı");
		this.spacecraftTable.setCellValue(0, 1, "Durum");
		this.spacecraftTable.setCellValue(0, 2, "Çıkış");
		this.spacecraftTable.setCellValue(0, 3, "Varış");
		this.spacecraftTable.setCellValue(0, 4, "Hedefe Kalan Saat");
		this.spacecraftTable.setCellValue(0, 5, "Hedefe Varacağı Tarih");
		
		int i = 0;
		for (var spacecraft : this.simulation.getSpacecrafts().values()) {
			this.spacecraftTable.setCellValue(i + 1, 0, spacecraft.getName());
			this.spacecraftTable.setCellValue(i + 1, 2, spacecraft.getSourcePlanet().getName());
			this.spacecraftTable.setCellValue(i + 1, 3, spacecraft.getDestinationPlanet().getName());
			this.spacecraftTable.setCellValue(i + 1, 5, spacecraft.getDestinationPlanet().getDateTime().AddHours(spacecraft.getDistanceLeftInHours()).toStringDateOnly());
			i++;
		}
	}
	
	public void writePlanets() throws IOException {
		this.UpdatePlanetTable();
		this.write("Gezegenler:");
		this.write(System.lineSeparator());
		this.write(planetTable.toString());
		this.write(System.lineSeparator());
	}
	
	public void writeSpacecrafts() throws IOException {
		this.updateSpacecraftTable();
		this.write("Uzay Araçları:");
		this.write(System.lineSeparator());
		this.write(spacecraftTable.toString());
		this.write(System.lineSeparator());
	}
	
	public void writeSteps() throws IOException {
		this.write("Adım Sayısı: " + this.simulation.getStepsTaken());
		this.write(System.lineSeparator());
	}
	
	public void writeSimulation() throws IOException {
		this.writePlanets();
		this.writeSpacecrafts();
	}
}
