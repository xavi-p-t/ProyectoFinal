package Atack;

import interfacesProj.Variables;

public class LigthHunter extends Ship{
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
		return BASE_DAMAGE_LIGTHHUNTER;
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
