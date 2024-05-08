package Atack;



public class LigthHunter extends Ship{
	public LigthHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
		//falta lo del nivel
		int temp = 2;
		armor = ARMOR_LIGTHHUNTER + (temp*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_LIGTHHUNTER + (temp*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100;
	}
	
	public LigthHunter() {
		super(ARMOR_LIGTHHUNTER, BASE_DAMAGE_LIGTHHUNTER);
	}
	
	@Override
	public int attack() {
		return BASE_DAMAGE_LIGTHHUNTER;
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_LIGTHHUNTER;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_LIGTHHUNTER;
	}

	@Override
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_LIGTHHUNTER;
	}

	@Override
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
	}

	@Override
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}
	
}
