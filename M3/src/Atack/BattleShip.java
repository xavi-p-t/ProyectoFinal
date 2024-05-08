package Atack;

public class BattleShip extends Ship{
	public BattleShip(int armor, int baseDamage) {
		super(armor, baseDamage);
		int temp = 2;
		armor = ARMOR_BATTLESHIP + (temp*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_BATTLESHIP + (temp*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100;
		setInitialArmor(armor);
	}
	
	public BattleShip() {
		super(ARMOR_BATTLESHIP, BASE_DAMAGE_BATTLESHIP);
	}

	@Override
	public int attack() {
		return BASE_DAMAGE_BATTLESHIP;
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_BATTLESHIP;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_BATTLESHIP;
	}

	@Override
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_BATTLESHIP;
	}

	@Override
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_BATTLESHIP;
	}

	@Override
	public void tekeDamage(int receivedDamage) {
		setArmor(getActualArmor() - receivedDamage);
	}
}
