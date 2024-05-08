package defensa;

public class IonCannon extends Defense{

	public IonCannon(int Armor, int baseDamage) {
		super(Armor, baseDamage);
		//falta el nivel
		Armor = ARMOR_IONCANNON +(1*PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_IONCANNON +(1*PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY)*1000/100;
	}
	public IonCannon() {
		super(ARMOR_IONCANNON,BASE_DAMAGE_IONCANNON);
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
		setArmor(ARMOR_IONCANNON);
	}

}
