package defensa;

public class PlasmaCannon extends Defense{
	// contructor
	public PlasmaCannon(int Armor, int baseDamage) {
		super(Armor, baseDamage);
		//falta el nivel
		Armor = ARMOR_PLASMACANNON +(1*PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_PLASMACANNON +(1*PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY)*1000/100;
	}
	public PlasmaCannon() {
		super(ARMOR_PLASMACANNON,BASE_DAMAGE_PLASMACANNON);
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
		setArmor(ARMOR_PLASMACANNON);
	}
}
