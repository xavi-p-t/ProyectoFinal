package defensa;

import interfacesProj.*;

// Clase defensa
public abstract class Defense implements Variables, MilitaryUnit {
	private int armor; // Sera la armadura restante
	private int initialArmor; // Será la armadura inicial
	private int baseDamage; // El poder de ataque
	
	// Constructor
	public Defense(int initialArmor, int baseDamage) {
		super();
		this.initialArmor = initialArmor;
		this.baseDamage = baseDamage;
	}
	
	// Getters and Setters
	public int getArmor() {
		return armor;
	}

	public int getInitialArmor() {
		return initialArmor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
}





