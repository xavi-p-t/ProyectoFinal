package Atack;

public class HeavyHunter extends Ship{
	public HeavyHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
		//falta nivel
		int temp = 2;
		armor = ARMOR_HEAVYHUNTER + (temp*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_HEAVYHUNTER + (temp*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100;
		setInitialArmor(armor);
	}
	
	public HeavyHunter() {
		super(ARMOR_HEAVYHUNTER, BASE_DAMAGE_HEAVYHUNTER);
	}

	@Override
	public int attack() {
		return BASE_DAMAGE_HEAVYHUNTER;
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_HEAVYHUNTER;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_HEAVYHUNTER;
	}

	@Override
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_HEAVYHUNTER;
	}

	@Override
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
	}

	@Override
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}
}
