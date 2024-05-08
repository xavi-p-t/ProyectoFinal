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
