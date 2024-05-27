package batalla;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	
	public Battle() {
		super();
		
		this.planetArmy = new ArrayList[7];
		this.enemyArmy = new ArrayList[4];
		this.armies = new ArrayList[2][7];
		this.initialCostFleet = new int[2][2];
		this.wasteMetalDeuterium = new int[2];
		this.enemyDrops = new int[7];
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


	
	
	public String getBattleDevelopment() {
		return battleDevelopment;
	}

	
	public int[][] getInitialCostFleet() {
		return initialCostFleet;
	}


	public void setInitialCostFleet(int[][] initialCostFleet) {
		this.initialCostFleet = initialCostFleet;
	}


	public int[][] getInitialArmys() {
		return initialArmys;
	}


	public void setInitialArmys(int[][] initialArmys) {
		this.initialArmys = initialArmys;
	}


	public int[] getActualNumberUnitsPlaneta() {
		return actualNumberUnitsPlaneta;
	}


	public void setActualNumberUnitsPlaneta(int[] actualNumberUnitsPlaneta) {
		this.actualNumberUnitsPlaneta = actualNumberUnitsPlaneta;
	}


	public int[] getActualNumberUnitsEnemy() {
		return actualNumberUnitsEnemy;
	}


	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}


	public void setArmies(ArrayList[][] armies) {
		this.armies = armies;
	}


	public void setInitialNumberUnitsPlanet(int initialNumberUnitsPlanet) {
		this.initialNumberUnitsPlanet = initialNumberUnitsPlanet;
	}


	public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
		this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
	}


	public void setBattleDevelopment(String battleDevelopment) {
		this.battleDevelopment = battleDevelopment;
		
	}
	
	
	
	//inicializar initialarmis
	public void  initInitialArmies() {
		
		for (int j = 0;j<this.planetArmy.length;j++) {
			this.initialArmys[0][j] = this.planetArmy[j].size();
		}
		for (int j = 0;j<this.enemyArmy.length;j++) {
			this.initialArmys[1][j] = this.enemyArmy[j].size();
		}
		
	}
	
	public int[] getEnemyDrops() {
		return enemyDrops;
	}


	public void setEnemyDrops(int[] enemyDrops) {
		this.enemyDrops = enemyDrops;
	}


	public int[] getPlanetDrops() {
		return planetDrops;
	}


	public void setPlanetDrops(int[] planetDrops) {
		this.planetDrops = planetDrops;
	}
	

	public int[] getWasteMetalDeuterium() {
		return wasteMetalDeuterium;
	}


	public void setWasteMetalDeuterium(int[] wasteMetalDeuterium) {
		this.wasteMetalDeuterium = wasteMetalDeuterium;
	}


	public int[][] getResourcesLooses() {
		return resourcesLooses;
	}


	public void setResourcesLooses(int[][] resourcesLooses) {
		this.resourcesLooses = resourcesLooses;
	}


	//calcular losd drops
	public void resto() {
		
		for (int i = 0;i<armies.length;i++) {
	
			for (int j = 0;j<armies[i].length;j++) {
				if (i == 0) {
					//System.out.println(initialArmys[i][j]-armies[i][j].size());
					this.planetDrops[j] = initialArmys[i][j]-armies[i][j].size();
					//System.out.println("Plenet drops: "+planetDrops[i]);
				}
				else {
					//System.out.println(initialArmys[i][j]-armies[i][j].size());
					this.enemyDrops[j] = initialArmys[i][j]-armies[i][j].size();
					//System.out.println("enemy drops: "+enemyDrops[i]);
				}
			}
		}
		
		for (int i = 0;i<planetDrops.length;i++) {		
			resourcesLooses[0][0] += planetDrops[i]* METAL_COST_UNITS[i];
			resourcesLooses[0][1] += planetDrops[i]* DEUTERIUM_COST_UNITS[i];
			resourcesLooses[1][0] += enemyDrops[i]* METAL_COST_UNITS[i];
			resourcesLooses[1][1] += enemyDrops[i]* DEUTERIUM_COST_UNITS[i];		
		}
		int total = 0;
		for (int i = 0; i<resourcesLooses.length;i++) {
			total = 0;
			for (int j = 0; j<resourcesLooses[i].length;j++) {
				total += resourcesLooses[i][j];
			}
			resourcesLooses[i][2] = total;
		}
	}
	
	public void generateWaste(){
		int[] prob = new int[] {CHANCE_GENERATNG_WASTE_LIGTHHUNTER,CHANCE_GENERATNG_WASTE_HEAVYHUNTER,CHANCE_GENERATNG_WASTE_BATTLESHIP,CHANCE_GENERATNG_WASTE_ARMOREDSHIP,
				CHANCE_GENERATNG_WASTE_MISSILELAUNCHER,CHANCE_GENERATNG_WASTE_IONCANNON,CHANCE_GENERATNG_WASTE_PLASMACANNON};
		int totalMetal = 0;
		int totalDeut = 0;
		int rand;
		for (int i = 0;i<planetDrops.length;i++) {
			for (int j = 0;j<planetDrops[i];j++) {
				rand = (int) (Math.random()*100);
				if (rand <= prob[i]) {
					totalMetal += METAL_COST_UNITS[i]*0.7;
					totalDeut += DEUTERIUM_COST_UNITS[i]*0.7;
				}
			}
		}
		this.wasteMetalDeuterium[0] = totalMetal;
		this.wasteMetalDeuterium[1] = totalDeut;
	}
	//mostrar perdidas
	public void updateResourcesLooses() {
		
		
		String mens = String.format("BATTLE STATICS\n\n%-30s%-10s%-10s%-30s%-10sDrops\n\n","Army Planet","Units","Drops","Initial Army Enemy","Units");
		//System.out.println(armies[0][0].get(0).getClass().getName().substring(armies[0][0].get(0).getClass().getName().lastIndexOf(".") + 1));
		String[] nombre = new String[]{"Ligth Hunter","Heavy Hunter","Battle Ship","Armored Ship","Missile Launcher","Ion Cannon","Plasma Cannon"};
		String[] res = new String[]{"Metal","Deuterium","Weighted"};
		System.out.println(initialCostFleet[0][0]);
		for (int i = 0;i<initialArmys[0].length;i++) {
//			System.out.println("Plenet drops: "+planetDrops[i]);
//			System.out.println("enemy drops: "+enemyDrops[i]);
			if (i<4) {
				mens += String.format("%-34s%-10d%-6d%-34s%-10d%-10d\n\n", nombre[i],initialArmys[0][i],planetDrops[i],
						nombre[i],initialArmys[1][i],enemyDrops[i]);
			}
			else {
				mens += String.format("%-34s%-10d%-10d\n\n", nombre[i],initialArmys[0][i],planetDrops[i]);
			}
		}
		
		mens += String.format("%s\n%-50sCost Army Enemy\n\n","*".repeat(100),"Cost Army Planet");
		
		for (int i = 0;i<2;i++) {
			
			mens += String.format("%-15s%-35d%-15s%d\n",res[i]+":",initialCostFleet[0][i],res[i]+":",initialCostFleet[1][i]);
		}
		mens += String.format("\n%s\n%-50sLoosses Army Enemy\n","*".repeat(100),"Losses Army Planet");
		for (int i = 0;i<3;i++) {
			
			mens += String.format("%-15s%-35d%-15s%d\n",res[i]+":",resourcesLooses[0][i],res[i]+":",resourcesLooses[1][i]);
		}
		mens += String.format("\n%s\nWaste Generated\n%-15s%d\n%-15s%d","*".repeat(100),"Metal:",wasteMetalDeuterium[0],"Deuterium:",wasteMetalDeuterium[1]);
		//System.out.println(mens);
		if (resourcesLooses[0][2] < resourcesLooses[1][2]) {
			mens += "\n\nBattle Wined by Enemy, We lose";
		}
		else {
			mens += "\n\nBattle Wined by Planet, We collected rubble";
		}
		this.battleDevelopment = mens;
	}

	
	//coste flota
	public void fleetResourceCost(ArrayList<MilitaryUnit>[] army) {
		int metalCost = 0;
		int deutCost = 0;
		for (int i = 0;i<army.length;i++) {
			
			if (army[i] == null) {
				metalCost += 0;
				deutCost += 0;
			}
			else {
				metalCost += army[i].size() * METAL_COST_UNITS[i];
				deutCost += army[i].size() * DEUTERIUM_COST_UNITS[i];	
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
		if (army.length < 7) {
			res = (100* flotaRestante)/this.initialNumberUnitsEnemy;
		}
		else{
			res = (100* flotaRestante)/this.initialNumberUnitsPlanet;
		}
		return res;
	}
	
	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
		int calcProb = 0;
		int totalPorc = 0;
		int totalUnidades = 0;
		System.out.println("group defender el for");
		for (int i = 0;i<army.length;i++) {
			if(army[i] != null) {
				totalUnidades = army[i].size();
			}
		}
		if (totalUnidades == 0) {
			totalUnidades = 1;
		}
		ArrayList arprob = new ArrayList();
		System.out.println("group defender el segundo for");
		for (int i = 0;i<army.length;i++) {
			if(army[i] != null) {
				calcProb = army[i].size()*100;
			}			
			calcProb = calcProb/totalUnidades;
			arprob.add(calcProb);
			totalPorc += calcProb;
		}
		if (totalPorc == 0) {
			totalPorc = 100;
		}
		int defRandom;
		int selRandom = 0;
		int selected = 0;
		boolean seleccionado = false;
		boolean parar = false;
		System.out.println("group defender el while");
		while (!parar) {
			seleccionado = false;
			defRandom = (int) (Math.random()*totalPorc);
			System.out.println("group defender el tercer for");
			for (int i = 0;i<arprob.size();i++) {
				selRandom +=  (int) arprob.get(i);
				System.out.println("group defender el if tercer for");
				if (selRandom>= defRandom & army[i].size() > 0 & !seleccionado) {
					selected = i;
					seleccionado = true;
					parar = true;
				}				
			
		}
	}
		return selected;
	}
	//llamar al los atacantes
	public int getPlanetGroupAttacker() {
		
		int totalPorc = 0;
		for (int i = 0;i<CHANCE_ATTACK_PLANET_UNITS.length;i++) {

			totalPorc += CHANCE_ATTACK_PLANET_UNITS[i];
		}
		
		int atRandom;
		int selRandom = 0;
		int selected = 0;
		boolean seleccionado = false;
		boolean parar = false;
		while (!parar) {
			
			seleccionado = false;
			atRandom = (int) (Math.random()*totalPorc);
			for (int i = 0;i<CHANCE_ATTACK_PLANET_UNITS.length;i++) {
				selRandom += CHANCE_ATTACK_PLANET_UNITS[i];
				if (selRandom>= atRandom & armies[0][i].size() > 0  & !seleccionado) {
					selected = i;
					seleccionado = true;
					parar = true;
				}				
			
		}
	}
		return selected;
	}
	
	public int getEnemyGroupAttacker() {
		int totalPorc = 0;
		for (int i = 0;i<CHANCE_ATTACK_ENEMY_UNITS.length;i++) {

			totalPorc += CHANCE_ATTACK_ENEMY_UNITS[i];
		}
		int atRandom;
		int selRandom = 0;
		int selected = 0;
		boolean seleccionado = false;
		boolean parar = false;
		while (!parar) {
			seleccionado = false;
			atRandom = (int) (Math.random()*totalPorc);
			for (int i = 0;i<CHANCE_ATTACK_ENEMY_UNITS.length;i++) {
				selRandom += CHANCE_ATTACK_ENEMY_UNITS[i];
				if (selRandom>= atRandom & armies[1][i].size() > 0  & !seleccionado) {
					selected = i;
					seleccionado = true;
					parar = true;
				}				
			
		}
	}
		return selected;
	}
	
	public void resetArmyArmor() {
		for (ArrayList<MilitaryUnit> armyList : this.planetArmy) {
            if (armyList != null) {
                for (int i = 0;i<armyList.size();i++) {
                	armyList.get(i).resetArmor();
                }
            }
		}
	}

	
}
