package batalla;

import java.util.ArrayList;
import java.util.Random;

import interfacesProj.*;

public class Battle {
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
	
	private void iniciarArays() {
		this.arBattles = new ArrayList<String>();
		this.arBattlesDevelopment = new ArrayList<String>();
		this.planetArmy = new ArrayList[7];
		this.enemyArmy = new ArrayList[7];
		this.armies = new ArrayList[2][7];
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
	
	public void updateResourcesLooses() {
		String mens = String.format("%-40sLoosses Army Enemy\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n%-15s%-25d%-15s%d\n\n%s\n%s\n%-15s%d\n%-15s%d",
				"Losses Army Planet","Metal:",50,"Metal:",40,"Deuterium:",20,"Deuterium",20
				,"Weighted:",0,"Weighted:",0,"*".repeat(60), "Waste Generated:","Metal:",20,"Deuterium:",20);
		System.out.println(mens);
	}
	
}
