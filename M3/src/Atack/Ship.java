package Atack;
import interfacesProj.*;

abstract class Ship implements MilitaryUnit, Variables{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	
	public Ship(int armor, int baseDamage) {
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
	}
	
	public abstract int attack();

	@Override
	public void tekeDamage(int receivedDamage) {
		this.armor -= receivedDamage;
		if (this.armor < 0) {
			this.armor = 0;
		}
		
	}

	@Override
	public int getActualArmor() {
		return this.armor;
	}

	@Override
	public int getMetalCost() {
		return 0;
	}

	@Override
	public int getDeuteriumCost() {
		return 0;
	}

	@Override
	public int getChanceGeneratinWaste() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChanceAttackAgain() {
		return 0;
	}

	@Override
	public void resetArmor() {
		this.armor = this.initialArmor;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getInitialArmor() {
		return initialArmor;
	}

	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	


	
}

class LigthHunter extends Ship {
	
	public LigthHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
		int temp = 2;
		armor = ARMOR_LIGTHHUNTER + (temp*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100;
		baseDamage = BASE_DAMAGE_LIGTHHUNTER + (temp*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100;
		setInitialArmor(armor);
	}
	
	public LigthHunter() {
		super(ARMOR_LIGTHHUNTER, BASE_DAMAGE_LIGTHHUNTER);
	}
	
	
	
	
	
	@Override
	public int attack() {
		return 0;
	}

	@Override
	public int getMetalCost() {
		return Variables.METAL_COST_LIGTHHUNTER;
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
	
	
}

class HeavyHunter extends Ship {
	
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
		return 0;
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

class BattleShip extends Ship {

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
		return 0;
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

class ArmoredShip extends Ship{

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
		return 0;
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
