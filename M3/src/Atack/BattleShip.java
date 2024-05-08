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
