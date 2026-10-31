/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Uzay aracına ait bilgileri saklayan sınıf.
 * </p>
 */
package pdp1.simulation;

import pdp1.time.DateTime;

public class Spacecraft implements SimulationObject {
	protected final String name;
	protected final Planet sourcePlanet;
	protected final Planet destinationPlanet;
	protected final DateTime exitDate;
	protected int distanceLeftInHours;
	protected int population = 0;
	protected SpacecraftStatus status = SpacecraftStatus.waiting;
	
	public String getName() {
		return name;
	}
	public Planet getSourcePlanet() {
		return sourcePlanet;
	}
	public Planet getDestinationPlanet() {
		return destinationPlanet;
	}
	public DateTime getExitDate() {
		return exitDate;
	}
	public int getDistanceLeftInHours() {
		return distanceLeftInHours;
	}
	public void updateDistanceLeftInHoursBy(int hours) {
		this.distanceLeftInHours += hours;
	}
	public int getPopulation() {
		return population;
	}
	public void updatePopulationBy(int amount) {
		this.population += amount;
		
		if (this.status == SpacecraftStatus.waiting) {
			this.sourcePlanet.updatePopulationBy(amount);
		}
		else if (this.status == SpacecraftStatus.arrived) {
			this.destinationPlanet.updatePopulationBy(amount);
		}
	}
	public SpacecraftStatus getStatus() {
		return this.status;
	}
	
	public Spacecraft(String name, Planet sourcePlanet, Planet destinationPlanet, DateTime exitDate, int distanceLeftInHours) {
		if (distanceLeftInHours < 0)
			throw new IllegalArgumentException("distanceLeftInHours cannot be negative, got \"" + distanceLeftInHours + "\"");
		
		this.name = name;
		this.sourcePlanet = sourcePlanet;
		this.destinationPlanet = destinationPlanet;
		this.exitDate = exitDate;
		this.distanceLeftInHours = distanceLeftInHours;
	}
	
	@Override
	public void stepBy(int hours) {
		if (this.status == SpacecraftStatus.destroyed)
			return;
		
		if (this.population == 0) {
			this.status = SpacecraftStatus.destroyed;
			return;
		}
		
		if (this.status == SpacecraftStatus.enroute) {
			this.updateDistanceLeftInHoursBy(-hours);
			
			if (this.distanceLeftInHours <= 0) {
				this.status = SpacecraftStatus.arrived;
				this.destinationPlanet.updatePopulationBy(this.population);
			}
		}
		
		if (this.status == SpacecraftStatus.waiting && (this.sourcePlanet.dateTime.GetHoursUntil(exitDate) <= 0)) {
			this.status = SpacecraftStatus.enroute;
			this.sourcePlanet.updatePopulationBy(-this.population);
		}
	}
}
