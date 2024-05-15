package batalla;

import java.util.ArrayList;

import interfacesProj.MilitaryUnit;
import interfacesProj.Variables;

public class Battle implements Variables {
	//Nuestra flota
	private ArrayList<MilitaryUnit>[] planetArmy;
	//flota enemiga
	private ArrayList<MilitaryUnit>[] enemyArmy;
	//array de dos filas, primera fila nuestro ejercito, ejercito enemigo segunda fila, 
	//poner siete columnas
	private ArrayList[][] armies;
	//guardar desarrollo de pelea
	private String battleDevelopment;
	//coste de los ejercitos, el primero es el metal y deuterio del nuestro,
	//el segundo es el del enemigo
	private int[][] initialCostFleet;
	//la pelea acaba cuando solo queda el 20% o menos
	private int initialNumberUnitsPlanet,initialNumberUnitsEnemy;
	//residuos generados
	private int[] wasteMetalDeuterium;
	//para calcular las perdidas de cada ejercito
	private int[] enemyDrops, planetDrops;
	// perdida de recursos, {perdida metal,perdida deuterio,perdida metal + 5*perdida deuterio}
	// el tercero es la perdida total
	private int[][] resourcesLooses;
	//como el de armys pero en este se veran las que teniamos al principio
	private int[][] initialArmys;
	//array para ver en todo momento las unidades, orden:
//	actualNumberUnitsPlanet[0] --> cazadores ligeros
//	actualNumberUnitsPlanet[0] --> cazadores pesados
//	actualNumberUnitsPlanet[0] --> Naves de battalla
//	actualNumberUnitsPlanet[0] --> Acorazados
//	actualNumberUnitsPlanet[0] --> Lanzamisiles
//	actualNumberUnitsPlanet[0] --> Cañones de iones
//	actualNumberUnitsPlanet[0] --> Cañones de Plasma
	private int[] actualNumberUnitsPlaneta,actualNumberUnitsEnemy;
	
	private ArrayList<String> arBattles;
	
	private ArrayList<String> arBattlesDevelopment;
	
	public Battle() {
		super();
		this.arBattles = new ArrayList<String>();
		this.arBattlesDevelopment = new ArrayList<String>();
		this.planetArmy = new ArrayList[7];
		this.enemyArmy = new ArrayList[4];
		this.armies = new ArrayList[2][7];
		this.initialCostFleet = new int[2][2];
		this.wasteMetalDeuterium = new int[2];
		this.enemyDrops = new int[4];
		this.planetDrops = new int[7];
		this.resourcesLooses = new int[2][3];
		this.initialArmys = new int[2][7];
		this.actualNumberUnitsPlaneta = new int[7];
		this.actualNumberUnitsEnemy = new int[4];
	}
	
	
	public void setArmies() {
		for (int i = 0;i<this.armies.length;i++) {
			if (i == 0) {
				armies[i] = this.planetArmy.clone();
			}
			else {
				armies[i] = this.enemyArmy.clone();
			}
		}
	}
	public ArrayList[][] getArmies() {
		return armies;
	}


	public void setArBattles() {
		if (this.arBattlesDevelopment.size() < 5) {
			this.arBattlesDevelopment.add(getBattleDevelopment());
		}
		else {
			this.arBattlesDevelopment.remove(0);
			this.arBattlesDevelopment.add(getBattleDevelopment());
		}
	}
	
	public void setArBattlesDevelopment() {
		if (this.arBattles.size() < 5) {
			this.arBattles.add(getBattleDevelopment());
		}
		else {
			this.arBattles.remove(0);
			this.arBattles.add(getBattleDevelopment());
		}
	}
	
	public String getBattleDevelopment() {
		return battleDevelopment;
	}


	public void setBattleDevelopment(String battleDevelopment) {
		this.battleDevelopment = battleDevelopment;
		
	}
	
	//metodos enseñar lo de batalla
	public String getBattleReport(int battles) {
		return this.arBattles.get(battles);
	}
	public String getBattleDevelopment(int battles) {
		return this.arBattlesDevelopment.get(battles);
	}
	
	//inicializar initialarmis
	public void  initInitialArmies() {
		for (int i = 0; i<2;i++) {
			for (int j = 0;j<this.planetArmy.length;j++) {
				if (i == 0) {
					this.initialArmys[i][j] = this.planetArmy[j].size();
				}
				else {
					this.initialArmys[i][j] = this.enemyArmy[j].size();
				}
			}
		}	
	}
	//mostrar perdidas
	public void updateResourcesLooses() {
		String mens = String.format("BATTLE STATICS\n\n%-30s%-10s%-10s%-30s%-10sDrops\n\n%-34s%-10d%-6d%-34s%-10d%-10d\n\n%-34s%-10d%-6d%-34s%-10d%-10d\n\n%-34s%-10d%-6d%-34s%-10d%-10d"
				+ "\n\n%-34s%-10d%-6d%-34s%-10d%-10d\n\n%-34s%-10d%-10d\n\n%-34s%-10d%-10d\n\n%-34s%-10d%-10d\n\n%s\n%-40sCost Enemy Armie\n\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n\n%s\n%-40sLoosses Army Enemy"
				+ "\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n\n%s\n%s\n%-15s%d\n%-15s%d",
				"Army Planet","Units","Drops","Initial Army Enemy","Units","Ligth Hunter",this.planetArmy[0].size(),this.planetDrops[0],"Ligth Hunter",this.enemyArmy[0],this.enemyDrops[0],
				"Heavy Hunter",this.planetArmy[1].size(),this.planetDrops[1],"Heavy Hunter",this.enemyArmy[1],this.enemyDrops[1],"Battle Ship",this.planetArmy[2].size(),this.planetDrops[2],"Battle Ship",this.enemyArmy[2],this.enemyDrops[2],"Armored Ship",this.planetArmy[3].size(),this.planetDrops[3],
				"Armored Ship",this.enemyArmy[3],this.enemyDrops[3],"Missile Launcher",this.planetArmy[4].size(),this.planetDrops[4],"Ion Cannon",this.planetArmy[5].size(),this.planetDrops[5],"Plasma Cannon",this.planetArmy[6].size(),this.planetDrops[6],"*".repeat(100),
				"Cost Army Planet","Metal:",this.initialCostFleet[0][0],"Metal:",this.initialCostFleet[1][0],"Deuterium:",this.initialCostFleet[0][1],"Deuterium:",this.initialCostFleet[1][1],"*".repeat(100),
				"Losses Army Planet","Metal:",this.resourcesLooses[0][0],"Metal:",this.resourcesLooses[1][0],"Deuterium:",this.resourcesLooses[0][1],"Deuterium",this.resourcesLooses[1][1]
				,"Weighted:",this.resourcesLooses[0][2],"Weighted:",this.resourcesLooses[1][2],"*".repeat(100), "Waste Generated:","Metal:",this.wasteMetalDeuterium[0],"Deuterium:",this.wasteMetalDeuterium[1]);
		System.out.println(mens);
	}
	//coste flota
	public void fleetResourceCost(ArrayList<MilitaryUnit>[] army) {
		int metalCost = 0;
		int deutCost = 0;
		for (int i = 0;i<army.length;i++) {
			switch(i) {
			case 0:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_LIGTHHUNTER;
					deutCost += army[i].size() * DEUTERIUM_COST_LIGTHHUNTER;	
				}
				break;
			case 1:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_HEAVYHUNTER;
					deutCost += army[i].size() * DEUTERIUM_COST_HEAVYHUNTER;
				}
				
				break;
			case 2:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_BATTLESHIP;
					deutCost += army[i].size() * DEUTERIUM_COST_BATTLESHIP;
				}
				
				break;
			case 3:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_ARMOREDSHIP;
					deutCost += army[i].size() * DEUTERIUM_COST_ARMOREDSHIP;
				}
				
				break;
			case 4:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_MISSILELAUNCHER;
					deutCost += army[i].size() * DEUTERIUM_COST_MISSILELAUNCHER;
				}
				
				break;
			case 5:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_IONCANNON;
					deutCost += army[i].size() * DEUTERIUM_COST_IONCANNON;
				}
				
				break;
			case 6:
				if (army[i] == null) {
					metalCost += 0;
					deutCost += 0;
				}
				else {
					metalCost += army[i].size() * METAL_COST_PLASMACANNON;
					deutCost += army[i].size() * DEUTERIUM_COST_PLASMACANNON;
				}
				
				break;
			}
		}
		if (army.length<7) {
			this.initialCostFleet[1][0] = metalCost;
			this.initialCostFleet[1][1] = deutCost;
		}
		else {
			this.initialCostFleet[0][0] = metalCost;
			this.initialCostFleet[0][1] = deutCost;
		}
		
	}
	
	public void initialFleetNumber(ArrayList<MilitaryUnit>[] army) {
		int totFlota = 0;
		for (int i = 0;i<army.length;i++) {
			if (army[i] == null) {
				totFlota += 0;
			}
			else {
				totFlota += army[i].size();
			}
		}
		if (army.length < 7) {
			this.initialNumberUnitsEnemy = totFlota;
		}
		else{
			this.initialNumberUnitsPlanet = totFlota;
		}
	}

	public ArrayList<MilitaryUnit>[] getPlanetArmy() {
		return planetArmy;
	}

	
	public void setPlanetArmy(ArrayList<MilitaryUnit>[] planetArmy) {
		this.planetArmy = planetArmy;
	}

	
	public ArrayList<MilitaryUnit>[] getEnemyArmy() {
		return enemyArmy;
	}

	
	public void setEnemyArmy(ArrayList<MilitaryUnit>[] enemyArmy) {
		this.enemyArmy = enemyArmy;
	}
	
	public int getInitialNumberUnitsPlanet() {
		return initialNumberUnitsPlanet;
	}

	public int getInitialNumberUnitsEnemy() {
		return initialNumberUnitsEnemy;
	}

	//para la pelea
	public int remainderPercentageFleet(ArrayList<MilitaryUnit>[] army) {
		int flotaRestante = 0;
		int res;
		for (int i = 0;i<army.length;i++) {
			if (army[i] != null) {
				flotaRestante += army[i].size();
			}
			
			
		}
		//System.out.println("VARIABLE FLOTA RESTANTE "+flotaRestante);
		
		
		if (army.length < 7) {
			//System.out.println("Initial number enemy "+initialNumberUnitsEnemy);
			//flotaRestante = this.initialNumberUnitsEnemy - flotaRestante;
			//System.out.println(flotaRestante);
			res = (100* flotaRestante)/this.initialNumberUnitsEnemy;
			
			//System.out.println(res);
			
		}
		else{
			//flotaRestante = this.initialNumberUnitsPlanet - flotaRestante;
			//System.out.println("Initial number planet "+initialNumberUnitsPlanet);
			res = (100* flotaRestante)/this.initialNumberUnitsPlanet;
			//System.out.println(res);
			
		}
		return res;
	}
	
	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
		int calcProb = 0;
		int totalPorc = 0;
		ArrayList arprob = new ArrayList();
		for (int i = 0;i<army.length;i++) {
			if(army[i] == null) {
				calcProb = 0;
			}
			else {
				calcProb = army[i].size()*100;
			}
			
			if (army.length<7) {
				calcProb = calcProb/this.initialNumberUnitsEnemy;
			}
			else {
				calcProb = calcProb/this.initialNumberUnitsPlanet;
			}
			arprob.add(calcProb);
			totalPorc += calcProb;
		}
		
		int defRandom;
		int selRandom = 0;
		int selected = 0;
		defRandom = (int) (Math.random()*totalPorc);
		for (int i = 0;i<arprob.size();i++) {
			selRandom +=  (int) arprob.get(i);
			if (selRandom>= defRandom & arprob.get(i) != null & selected == 0) {
				selected = i;
			}				
		}
		return selected;
	}
	//llamar al los atacantes
	public int getPlanetGroupAttacker() {
		int calcProb = 0;
		int totalPorc = 0;
		ArrayList arprob = new ArrayList();
		for (int i = 0;i<enemyArmy.length;i++) {
			if(enemyArmy[i] == null) {
				calcProb = 0;
			}
			else {
				calcProb = enemyArmy[i].size()*100;
			}
			
			if (enemyArmy.length<7) {
				calcProb = calcProb/this.initialNumberUnitsEnemy;
			}
			else {
				calcProb = calcProb/this.initialNumberUnitsPlanet;
			}
			arprob.add(calcProb);
			totalPorc += calcProb;
		}
		
		int defRandom;
		int selRandom = 0;
		int selected = 0;
		defRandom = (int) (Math.random()*totalPorc);
		for (int i = 0;i<arprob.size();i++) {
			selRandom +=  (int) arprob.get(i);
			if (selRandom>= defRandom & arprob.get(i) != null & selected == 0) {
				selected = i;
			}				
		}
		return selected;
	}
	
	public int getEnemyGroupAttacker() {
		int calcProb = 0;
		int totalPorc = 0;
		ArrayList arprob = new ArrayList();
		for (int i = 0;i<enemyArmy.length;i++) {
			if(enemyArmy[i] == null) {
				calcProb = 0;
			}
			else {
				calcProb = enemyArmy[i].size()*100;
			}
			
			if (enemyArmy.length<7) {
				calcProb = calcProb/this.initialNumberUnitsEnemy;
			}
			else {
				calcProb = calcProb/this.initialNumberUnitsPlanet;
			}
			arprob.add(calcProb);
			totalPorc += calcProb;
		}
		
		int defRandom;
		int selRandom = 0;
		int selected = 0;
		defRandom = (int) (Math.random()*totalPorc);
		for (int i = 0;i<arprob.size();i++) {
			selRandom +=  (int) arprob.get(i);
			if (selRandom>= defRandom & arprob.get(i) != null & selected == 0) {
				selected = i;
			}				
		}
		return selected;
	}
	
	public void resetArmyArmor() {
		//por hacer
	}

	
}
