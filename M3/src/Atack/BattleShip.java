package Atack;

public class BattleShip extends Ship{
	public BattleShip(int armor, int baseDamage) {
		super(armor, baseDamage);
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
