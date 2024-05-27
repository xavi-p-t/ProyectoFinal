package defensa;

import interfacesProj.*;

// Clase defensa
public abstract class Defense implements Variables, MilitaryUnit {
	private int armor; // Sera la armadura restante
	private int initialArmor; // Será la armadura inicial
	private int baseDamage; // El poder de ataque
	
	// Constructor
	public Defense(int Armor, int baseDamage) {
		super();
		this.armor = Armor;
		this.baseDamage = baseDamage;
		this.initialArmor = Armor;
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

	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}

	@Override
	public String toString() {
		return "Defense [armor=" + armor + ", initialArmor=" + initialArmor + ", baseDamage=" + baseDamage + "]";
	}
	
	
	
}





