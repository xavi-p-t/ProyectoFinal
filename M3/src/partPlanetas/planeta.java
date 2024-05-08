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
	private ArrayList[] army = new ArrayList[7];
	
	
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
	public void newHeavyHunter(int n) throws ResourceException {
		for (int i = 0;i<n;i++) {
			if (this.metal < METAL_COST_HEAVYHUNTER | this.deuterium < DEUTERIUM_COST_HEAVYHUNTER) {
				System.out.println("you created "+i+" Heavy Hunter");
				throw new ResourceException("You need more resources to create a Heavy Hunter");
			}
			else {
				this.deuterium -= METAL_COST_HEAVYHUNTER;
				this.metal -= DEUTERIUM_COST_HEAVYHUNTER;
				army[1].add(new HeavyHunter());
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
				army[2].add(new BattleShip());
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
				army[3].add(new ArmoredShip());
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
				army[4].add(new MissileLauncher());
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
				army[5].add(new IonCannon());
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
				army[6].add(new PlasmaCannon());
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
		
	}
	public void setArray() {
		this.army[0] = new ArrayList<LigthHunter>();
		this.army[1] = new ArrayList<HeavyHunter>();
		this.army[2] = new ArrayList<BattleShip>();
		this.army[3] = new ArrayList<ArmoredShip>();
		this.army[4] = new ArrayList<MissileLauncher>();
		this.army[5] = new ArrayList<IonCannon>();
		this.army[6] = new ArrayList<PlasmaCannon>();
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
