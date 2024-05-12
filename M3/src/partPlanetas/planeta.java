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
		if (this.deuterium < this.upgradeDefenseTechnologyDeuteriumCost) {
			throw new ResourceException("You need more deuterium to upgrade the defense technology");
		}
		else {
			this.upgradeDefenseTechnologyDeuteriumCost += (this.upgradeDefenseTechnologyDeuteriumCost*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST)/100 ;
			this.deuterium -= this.upgradeDefenseTechnologyDeuteriumCost;
			this.technologyDefense += 1;
		}
	}
	//upgrade tecnologia atake
	public void upgradeTechnologyAttack() throws ResourceException {
		
		if (this.deuterium < this.upgradeAttackTechnologyDeuteriumCost) {
			throw new ResourceException("You need more deuterium to upgrade the atack technology");
		}
		else {
			this.upgradeAttackTechnologyDeuteriumCost += (this.upgradeAttackTechnologyDeuteriumCost*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST)/100;
			this.deuterium -= this.upgradeAttackTechnologyDeuteriumCost;
			this.technologyAtack += 1;
		}
	}
	
	//metodos para añadir estructuras y naves
	//light hunter
	public void newLigthHunter(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_LIGTHHUNTER | this.deuterium < DEUTERIUM_COST_LIGTHHUNTER) {
				System.out.println("you created "+i+" Light Hunters");
				throw new ResourceException("You need more resources to create a Light Hunter");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_LIGTHHUNTER;
				this.metal -= METAL_COST_LIGTHHUNTER;
				army[0].add(new LigthHunter());
			}
		}
	}
	//heavyhunter
	//ARMOR_LIGTHHUNTER+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100
	public void newHeavyHunter(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_HEAVYHUNTER | this.deuterium < DEUTERIUM_COST_HEAVYHUNTER) {
				System.out.println("you created "+i+" Heavy Hunter");
				throw new ResourceException("You need more resources to create a Heavy Hunter");
			}
			else {
				this.deuterium -= METAL_COST_HEAVYHUNTER;
				this.metal -= DEUTERIUM_COST_HEAVYHUNTER;
				army[1].add(new HeavyHunter(ARMOR_HEAVYHUNTER+(this.technologyDefense*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_HEAVYHUNTER+(this.technologyAtack*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	//battleship
	public void newBattleShip(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_BATTLESHIP | this.deuterium < DEUTERIUM_COST_BATTLESHIP) {
				System.out.println("you created "+i+" Battle Ship");
				throw new ResourceException("You need more resources to create a Battle Ship");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_BATTLESHIP;
				this.metal -= METAL_COST_BATTLESHIP;
				army[2].add(new BattleShip(ARMOR_BATTLESHIP+(this.technologyDefense*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_BATTLESHIP+(this.technologyAtack*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	//armmoredship
	public void newArmoredShip(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_ARMOREDSHIP | this.deuterium < DEUTERIUM_COST_ARMOREDSHIP) {
				System.out.println("you created "+i+" Armored Ship");
				throw new ResourceException("You need more resources to create a Armored Ship");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_ARMOREDSHIP;
				this.metal -= METAL_COST_ARMOREDSHIP;
				army[3].add(new ArmoredShip(ARMOR_ARMOREDSHIP+(this.technologyDefense*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_ARMOREDSHIP+(this.technologyAtack*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	//missilelauncher
	public void newMissileLauncher(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_MISSILELAUNCHER | this.deuterium < DEUTERIUM_COST_MISSILELAUNCHER) {
				System.out.println("you created "+i+" Missile Launchers");
				throw new ResourceException("You need more resources to create a Missile Launcher");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_MISSILELAUNCHER;
				this.metal -= METAL_COST_MISSILELAUNCHER;
				army[4].add(new MissileLauncher(ARMOR_MISSILELAUNCHER+(this.technologyDefense*PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_MISSILELAUNCHER+(this.technologyAtack*PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	//ioncannon
	public void newIonCannon(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_IONCANNON | this.deuterium < DEUTERIUM_COST_IONCANNON) {
				System.out.println("you created "+i+" Ion Cannons");
				throw new ResourceException("You need more resources to create a Ion Cannon");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_IONCANNON;
				this.metal -= METAL_COST_IONCANNON;
				army[5].add(new IonCannon(ARMOR_IONCANNON+(this.technologyDefense*PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_IONCANNON+(this.technologyAtack*PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	//plasma cannon
	public void newPlasmaCannon(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_PLASMACANNON | this.deuterium < DEUTERIUM_COST_PLASMACANNON) {
				System.out.println("you created "+i+" Plasma Cannons");
				throw new ResourceException("You need more resources to create a Plasma Cannon");
			}
			else {
				this.deuterium -= DEUTERIUM_COST_PLASMACANNON;
				this.metal -= METAL_COST_PLASMACANNON;
				army[6].add(new PlasmaCannon(ARMOR_PLASMACANNON+(this.technologyDefense*PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY)*1000/100,BASE_DAMAGE_PLASMACANNON+(this.technologyAtack*PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY)*1000/100));
			}
		}
	}
	
	// metodo print stats
	public void printStats() {
		String mens =String.format("Planet Stats:\n\nTECHNOLOGY\n\n%-30s%d\n%-30s%d\n\nDEFENSES\n\n%-30s%d\n%-30s%d\n%-30s%d\n\nFLEET\n\n%-30s%d\n%-30s%d\n%-30s%d\n%-30s%d\n\nRESOURCES\n\n%-30s%d\n%-30s%d",
				"Atack Technology",this.technologyAtack,"Defense Technology",this.technologyDefense,"Missile Launcher",army[4].size(),
				"Ion Cannon",army[5].size(),"Plasma Cannon",army[6].size(),"Light Hunter",army[0].size(),
				"Heavy Hunter",army[1].size(),"Battle Ship",army[2].size(),"Armored Ship",army[3].size(),
				"Metal",this.metal,"Deuterium",this.deuterium) ;
		System.out.println(mens);
	}
	

	//constructor
	public planeta(int metal, int deuterium) {
		super();
		this.metal = metal;
		this.deuterium = deuterium;
		this.technologyDefense = 0;
		this.technologyAtack = 0;
		this.upgradeAttackTechnologyDeuteriumCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		this.upgradeDefenseTechnologyDeuteriumCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST;
		
	}
	public planeta() {
		super();
		this.metal = 50000;
		this.deuterium = 25000;
		this.technologyDefense = 0;
		this.technologyAtack = 0;
		this.upgradeAttackTechnologyDeuteriumCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		this.upgradeDefenseTechnologyDeuteriumCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST;
		
	}
	
	public void setArray() {
		for (int i = 0;i<this.army.length;i++) {
			this.army[i] = new ArrayList<MilitaryUnit>();
		}
	}
	
	//seters geters
	
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
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
