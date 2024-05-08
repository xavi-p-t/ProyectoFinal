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

// Clase MissileLauncher

class MissileLauncher extends Defense  {

	// contructor
	public MissileLauncher(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}

	// implementacion de las variables y militaryUnit

	public int attack() {
		return BASE_DAMAGE_MISSILELAUNCHER;
	}

	 
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}

	 
	public int getActualArmor() {
		return getArmor();
	}

	 
	public int getMetalCost() {
		return METAL_COST_MISSILELAUNCHER;
	}

	 
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_MISSILELAUNCHER;
	}

	 
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
	}

	 
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
	}

	 
	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	
}

//Clase MissileLauncher

class IonCannon extends Defense {

	// constructor
	public IonCannon(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}

	// implementacion de las variables y militaryUnit

	public int attack() {
		return BASE_DAMAGE_IONCANNON;
	}

	 
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);		
	}

	 
	public int getActualArmor() {
		return getArmor();
	}

	 
	public int getMetalCost() {
		return METAL_COST_IONCANNON;
	}

	 
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_IONCANNON;
	}

	 
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_IONCANNON;
	}

	 
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_IONCANNON;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());		
	}
	
}

//Clase MissileLauncher

class PlasmaCannon extends Defense{
	
	// contructor
	public PlasmaCannon(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	
	
	// implementacion de las variables y militaryUnit

	public int attack() {
		return BASE_DAMAGE_PLASMACANNON;
	}

	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}

	public int getActualArmor() {
		return getArmor();
	}
	
	public int getMetalCost() {
		return METAL_COST_PLASMACANNON;
	}
	
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_PLASMACANNON;
	}
	
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_PLASMACANNON;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_PLASMACANNON;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
		
	}
	
}
