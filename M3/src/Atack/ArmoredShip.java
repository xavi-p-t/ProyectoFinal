package Atack;

public class ArmoredShip extends Ship{
	public ArmoredShip(int armor, int baseDamage) {
		super(armor, baseDamage);
		int temp = 2;
		armor = ARMOR_ARMOREDSHIP + (temp*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_ARMOREDSHIP + (temp*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100;
		setInitialArmor(armor);
	}
	
	public ArmoredShip() {
		super(ARMOR_ARMOREDSHIP, BASE_DAMAGE_ARMOREDSHIP);
	}

	@Override
	public int attack() {
		return BASE_DAMAGE_ARMOREDSHIP;
	}


	@Override
	public int getMetalCost() {
		return METAL_COST_ARMOREDSHIP;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_ARMOREDSHIP;
	}

	@Override
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ARMOREDSHIP;
	}

	@Override
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
	}

	@Override
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}

}
