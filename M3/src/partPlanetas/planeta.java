package partPlanetas;

import java.util.ArrayList;

import interfacesProj.MilitaryUnit;
import interfacesProj.Variables;
import defensa.*;
import Atack.*;

public class planeta implements Variables{
	private int technologyDefense;
	private int technologyAtack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	private ArrayList<MilitaryUnit>[] army = new ArrayList[7];
	
	
	//metodos necesarios
	//upgrade tecnologia defensa
	public void upgradeTechnologyDefense() throws ResourceException {
		this.upgradeDefenseTechnologyDeuteriumCost = (this.technologyDefense*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST)+UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		if (this.deuterium < this.upgradeDefenseTechnologyDeuteriumCost) {
			throw new ResourceException("You need more deuterium to upgrade the defense technology");
		}
		else {
			this.deuterium -= this.upgradeDefenseTechnologyDeuteriumCost;
			this.technologyDefense += 1;
			System.out.println("Your technology level has been upgrade succesfuly, your current level is: "+this.technologyDefense);
		}
	}
	//upgrade tecnologia atake
	public void upgradeTechnologyAttack() throws ResourceException {
		this.upgradeAttackTechnologyDeuteriumCost = (this.technologyAtack*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST)+UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		if (this.deuterium < this.upgradeAttackTechnologyDeuteriumCost) {
			throw new ResourceException("You need more deuterium to upgrade the atack technology");
		}
		else {
			this.deuterium -= this.upgradeAttackTechnologyDeuteriumCost;
			this.technologyAtack += 1;
			System.out.println("Your technology level has been upgrade succesfuly, your current level is: "+this.technologyAtack);
		}
	}
	//constructor
	public planeta(int metal, int deuterium,ArrayList<MissileLauncher> misiles,ArrayList<IonCannon> ion,ArrayList<PlasmaCannon> plasma) {
		super();
		this.metal = metal;
		this.deuterium = deuterium;
		this.technologyDefense = 0;
		this.technologyAtack = 0;
		
	}
	//seters geters
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getDeuterium() {
		return deuterium;
	}

	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}

	public int getUpgradeDefenseTechnologyDeuteriumCost() {
		return upgradeDefenseTechnologyDeuteriumCost;
	}

	public void setUpgradeDefenseTechnologyDeuteriumCost(int upgradeDefenseTechnologyDeuteriumCost) {
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
	}

	public int getUpgradeAttackTechnologyDeuteriumCost() {
		return upgradeAttackTechnologyDeuteriumCost;
	}

	public void setUpgradeAttackTechnologyDeuteriumCost(int upgradeAttackTechnologyDeuteriumCost) {
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
	}
	
	
	
	
}
