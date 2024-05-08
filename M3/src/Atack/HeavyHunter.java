package Atack;

public class HeavyHunter extends Ship{
	public HeavyHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
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
	public void tekeDamage(int receivedDamage) {
		super.tekeDamage(receivedDamage);
	}

	@Override
	public int getActualArmor() {
		return super.getActualArmor();
	}

	@Override
	public int getMetalCost() {
		return super.getMetalCost();
	}

	@Override
	public int getDeuteriumCost() {
		return super.getDeuteriumCost();
	}

	@Override
	public int getChanceGeneratinWaste() {
		return super.getChanceGeneratinWaste();
	}

	@Override
	public int getChanceAttackAgain() {
		return super.getChanceAttackAgain();
	}

	@Override
	public void resetArmor() {
		super.resetArmor();
	}
}
