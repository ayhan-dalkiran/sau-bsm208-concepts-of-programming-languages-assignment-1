/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Kişiyi ait bilgileri tutan sınıf.
 * </p>
 */
package pdp1.simulation;

public class Person implements SimulationObject {
	protected final String name;
	protected final int age;
	protected int lifeRemainingInHours;
	protected final Spacecraft spacecraft;
	protected boolean dead = false;
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	public int getLifeRemainingInHours() {
		return lifeRemainingInHours;
	}
	
	public Spacecraft getSpacecraft() {
		return spacecraft;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public Person(String name, int age, int lifeRemainingInHours, Spacecraft spacecraft) {
		if (age < 0)
			throw new IllegalArgumentException("age cannot be negative, got \"" + age + "\"");
		
		if (lifeRemainingInHours < 0)
			throw new IllegalArgumentException("lifeRemainingInHours cannot be negative, got \"" + lifeRemainingInHours + "\"");
		
		this.name = name;
		this.age = age;
		this.lifeRemainingInHours = lifeRemainingInHours;
		this.spacecraft = spacecraft;
		spacecraft.updatePopulationBy(1);
	}

	public void updateLifeRemainingInHoursBy(int amount) {
		this.lifeRemainingInHours += amount;
	}

	@Override
	public void stepBy(int hours) {
		if (this.dead)
			return;
		
		this.updateLifeRemainingInHoursBy(-hours);
		
		if (this.lifeRemainingInHours <= 0) {
			this.dead = true;
			this.spacecraft.updatePopulationBy(-1);
		}
	}
}
