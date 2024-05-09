package batalla;

import java.util.ArrayList;

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
}
