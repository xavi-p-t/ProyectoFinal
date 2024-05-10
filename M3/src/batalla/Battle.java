package batalla;

import java.util.ArrayList;
import java.util.Random;

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
	
	public void iniciarArays() {
		this.arBattles = new ArrayList<String>();
		this.arBattlesDevelopment = new ArrayList<String>();
		this.planetArmy = new ArrayList[7];
		this.enemyArmy = new ArrayList[7];
		this.armies = new ArrayList[2][7];
		this.wasteMetalDeuterium = new int[2];
		this.resourcesLooses = new int[2][7];
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
					this.initialArmys[i][j] = (int) this.planetArmy[j].clone();
				}
				else {
					this.initialArmys[i][j] = (int) this.enemyArmy[j].clone();
				}
			}
		}	
	}
	//mostrar perdidas
	public void updateResourcesLooses() {
		String mens = String.format("%-40sLoosses Army Enemy\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n\n%s\n%s\n%-15s%d\n%-15s%d",
				"Losses Army Planet","Metal:",this.resourcesLooses[0][0],"Metal:",this.resourcesLooses[1][0],"Deuterium:",this.resourcesLooses[0][1],"Deuterium",this.resourcesLooses[1][1]
				,"Weighted:",this.resourcesLooses[0][2],"Weighted:",this.resourcesLooses[1][2],"*".repeat(60), "Waste Generated:","Metal:",this.wasteMetalDeuterium[0],"Deuterium:",this.wasteMetalDeuterium[1]);
		System.out.println(mens);
	}
	//coste flota
	public void fleetResourceCost(ArrayList<MilitaryUnit>[] army) {
		int metalCost = 0;
		int deutCost = 0;
		for (int i = 0;i<army.length;i++) {
			switch(i) {
			case 0:
				metalCost += army[i].size() * METAL_COST_LIGTHHUNTER;
				deutCost += army[i].size() * DEUTERIUM_COST_LIGTHHUNTER;
				break;
			case 1:
				metalCost += army[i].size() * METAL_COST_HEAVYHUNTER;
				deutCost += army[i].size() * DEUTERIUM_COST_HEAVYHUNTER;
				break;
			case 2:
				metalCost += army[i].size() * METAL_COST_BATTLESHIP;
				deutCost += army[i].size() * DEUTERIUM_COST_BATTLESHIP;
				break;
			case 3:
				metalCost += army[i].size() * METAL_COST_ARMOREDSHIP;
				deutCost += army[i].size() * DEUTERIUM_COST_ARMOREDSHIP;
				break;
			case 4:
				metalCost += army[i].size() * METAL_COST_MISSILELAUNCHER;
				deutCost += army[i].size() * DEUTERIUM_COST_MISSILELAUNCHER;
				break;
			case 5:
				metalCost += army[i].size() * METAL_COST_IONCANNON;
				deutCost += army[i].size() * DEUTERIUM_COST_IONCANNON;
				break;
			case 6:
				metalCost += army[i].size() * METAL_COST_PLASMACANNON;
				deutCost += army[i].size() * DEUTERIUM_COST_PLASMACANNON;
				break;
			}
		}
		
	}
	
}
