package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Atack.*;
import defensa.*;
import batalla.Battle;
import partPlanetas.*;
import interfacesProj.*;


public class Main{
	
	public static void main(String[] args) {
		new juego();

	}
}
class juego implements Variables{
	private boolean flag_0 = true;
	private boolean flag_00 = true;
	private boolean ataque = false;
	
	private boolean flag_02 = false;
	private boolean flag_021 = false;
	private boolean flag_022 = false;
	
	private boolean flag_03 = false;
	private boolean flag_04 = false;
	private boolean flag_05 = false;
	
	private Scanner opc = new Scanner(System.in);
    private Timer timer;
    private TimerTask task1;
    private TimerTask task2;
    
    private String menuMostr = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
    private String menuPrinc = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
    private String menuPrincAtak = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n5)View Thread Comming\n0)Exit\n\nOption:";
    private String menuBuild = "Build\n1)Build Troops\n2)Build Defenses\n3)Go Back\nOption:";
    private String buildTroops = "Menu Build Troops\n\n1)Build Light Hunter\n2)Build Heavy Hunter\n3)Build Battle Ship\n4)Build Armored Ship\n5)Go Back\nOption:";
    private String buildDefenses = "Menu Build Defenses\n\n1)Build Missile Launcher\n2)Build Ion Cannon\n3)Build Plasma Cannon\n4)Go Back\nOption:";
    private String building = "Amount of Units\nAmount:";
    private String levelUp = "";
    private String report = "Battle Reports\nThere is not reports to read\nPress enter to go back";
    
    private int num = 0;
    private int enemyMetal = METAL_BASE_ENEMY_ARMY;
    private int enemyDeut = DEUTERIUM_BASE_ENEMY_ARMY;
    private int enemyLevel = 0;
    private ArrayList<MilitaryUnit>[] enemyArmie = new ArrayList[4];
    private ArrayList reportes = new ArrayList();
    private ArrayList peleas = new ArrayList();
    
    planeta miPlaneta = new planeta();
    Battle pelea = new Battle();
    
    //juego principal
    juego(){
    	startGame();
    	try {
			miPlaneta.newLigthHunter(10);
		} catch (ResourceException e) {
			System.out.println(e.getMessage());
		}
//    	createEnemyArmy();
//    	pelea.setPlanetArmy(miPlaneta.getArmy().clone());
//        pelea.fleetResourceCost(miPlaneta.getArmy());
//        pelea.fleetResourceCost(enemyArmie);
//        pelea.initialFleetNumber(miPlaneta.getArmy());
//        pelea.initialFleetNumber(enemyArmie);
//        pelea.initInitialArmies();
//        pelea.setArmies();
//        lucha();
//        System.out.println("a");
//        pelea.updateResourcesLooses();
    	timer.schedule(task1, 120000,180000);
		timer.schedule(task2, 180000,180000);
		
    	while(flag_0) {
    		while (flag_00) {
    			menuInicial();
    		}
    		while (flag_02) {
    			segundoMenu();
    		}
    		while (flag_021) {
    			menuShips();
    		}
    		while (flag_022) {
    			menuDefensas();
    		}
    		while (flag_03) {
    			menuNivel();
    		}
    		while (flag_04) {
    			menuReporte();
    		}
    		
    	}
    	opc.close();
    }
    //necesario para iniciar los timers y el planeta
    private void startGame() {
    	miPlaneta.setArray();
        timer = new Timer();
        task1 = new TimerTask() {
            public void run() {
                ataque = true;
                menuMostr = menuPrincAtak;
                System.out.println("\n\nNEW THREAD IS COMMING\n\n");
                createEnemyArmy();
                if (flag_00) {
                	System.out.println(menuMostr);
                }
                
            }
        };
        task2 = new TimerTask() {
            public void run() {
                ataque = false;
                menuMostr = menuPrinc;
                System.out.println("\n\nWE HAVE BEEN ATTACKED!!!\n\n");
                
            	pelea.setPlanetArmy(miPlaneta.getArmy().clone());
                pelea.fleetResourceCost(miPlaneta.getArmy());
                pelea.fleetResourceCost(enemyArmie);
                pelea.initialFleetNumber(miPlaneta.getArmy());
                pelea.initialFleetNumber(enemyArmie);
                pelea.initInitialArmies();
                pelea.setArmies();
            	lucha();
                if (flag_00) {
                	System.out.println(menuMostr);
                }
            }
        };
    }
    //menu 00
    private void menuInicial() {
    	
    		System.out.println(menuMostr);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			num = opc.nextInt();
			
			if (num == 1) {
				miPlaneta.printStats();
			}
			else if (num == 2){
				flag_00 = false;
				flag_02 = true;
				
			}
			else if (num == 3){
				flag_00 = false;
				flag_03 = true;	
				
			}
			else if (num == 4){
				flag_00 = false;
				flag_04 = true;
				
			}
			else if (num == 5) {
				if (ataque) {
					viewThreat();
				}
				else {
					System.out.println("Invalid Option");
				}
			}
			else if (num == 0){
				
				flag_00 = false;
				flag_0 = false;
				timer.cancel();

			}
			else {
				System.out.println("Invalid Option");
			}
    	
    }
    //menu 02
    private void segundoMenu() {
    	System.out.println(menuBuild);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			flag_02 = false;
			flag_021 = true;
		}
		else if (num == 2){
			flag_02 = false;
			flag_022 = true;
		}
		else if (num == 3){
			flag_00 = true;
			flag_02 = false;			
		}
		
		else {
			System.out.println("Invalid Option");
		}
    }
    //menu 021
    private void menuShips(){
    	int amount = 0;
		System.out.println(buildTroops);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();
			try {
				miPlaneta.newLigthHunter(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 2){
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();
			try {
				miPlaneta.newHeavyHunter(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 3){
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();	
			try {
				miPlaneta.newBattleShip(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 4){
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();	
			try {
				miPlaneta.newArmoredShip(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 5){
			flag_02 = true;
			flag_021 = false;			
		}
		else {
			System.out.println("Invalid Option");
		}
    }
    //menu 022
    private void menuDefensas() {
    	int amount = 0;
		System.out.println(buildDefenses);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();
			try {
				miPlaneta.newMissileLauncher(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 2){
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();
			try {
				miPlaneta.newIonCannon(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 3){
			System.out.println(building);
			while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			amount = opc.nextInt();
			try {
				miPlaneta.newPlasmaCannon(amount);
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			pelea.setPlanetArmy(miPlaneta.getArmy());
		}
		else if (num == 4){
			flag_02 = true;
			flag_022 = false;			
		}
		
		else {
			System.out.println("Invalid Option");
		}
    }
    //menu 03
    private void menuNivel() {
    	levelUp = String.format("Upgrade Technology\n%-40s%d\n%-40s%d\n\n%-30s%-10s%-10dDeuterium\n%-30s%-10s%-10dDeuterium\n3)Go Back\n\nDeuterium resources = %d\nOption:","Actual Defense Technology:",miPlaneta.getTechnologyDefense(),
				"Actual Atack Technology:",miPlaneta.getTechnologyAtack(),"1)Upgrade Defense Technology.","Cost:",
				miPlaneta.getUpgradeDefenseTechnologyDeuteriumCost(),"2)Upgrade Attack Technology.","Cost:",
				miPlaneta.getUpgradeAttackTechnologyDeuteriumCost(),miPlaneta.getDeuterium());
		System.out.println(levelUp);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			try {
				miPlaneta.upgradeTechnologyDefense();
			} catch (ResourceException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
		}
		else if(num == 2) {
			try {
				miPlaneta.upgradeTechnologyAttack();
			} catch (ResourceException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
		}
		else if (num == 3) {
			flag_03 = false;
			flag_00 = true;
		}
		else {
			System.out.println("Invalid Option");
		}
	}
    //menu 04
    private void menuReporte() {
    	boolean salir = false;
    	String verPelea;
    	if (reportes.size() == 0) {
    		report = "Battle Reports\nThere is not reports to read\nPress enter to go back";
    		
    	}
    	else {
    		if (reportes.size() > 5) {
				reportes.remove(0);
				peleas.remove(0);
    		}
    		if (reportes.size() == 1) {
    			report = "Battle Reports\nSelect Report Read (0 go back): (1)";
    		}
    		else {
    			report = "Battle Reports\nSelect Report Read (0 go back): (1-"+reportes.size()+")\nOption >";
    		}
			
    	}
    	while (!salir) {
    		System.out.println(report);
	    	while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
			num = opc.nextInt();
			if (num == 0) {
				salir = true;
				flag_04 = false;
				flag_00 = true;
			}
			else if (num > 0 & num <= reportes.size()) {
				System.out.println(reportes.get(num-1));
				System.out.println("\n\n"+"#".repeat(70)+"\n\nView Battle development? (S/N)");
				while (!opc.hasNext()) {
					System.out.println("Invalid Option");
					opc.nextLine();
				}
				verPelea = opc.next();
				if (verPelea.equals("s") | verPelea.equals("S")) {
					System.out.println(peleas.get(num-1));
					
				}
			}
			else {
				System.out.println("Invalid Option");
			}
    	}
    }
    //generar enemigos
    private void createEnemyArmy() {
    	ArrayList enemyProb = new ArrayList();
    	enemyProb.add(35);
    	enemyProb.add(25);
    	enemyProb.add(20);
    	enemyProb.add(20);
    	int metal = enemyMetal;
    	int deuterium = enemyDeut;
    	for (int i = 0;i<enemyArmie.length;i++) {
			enemyArmie[i] = new ArrayList<MilitaryUnit>();
		}
    	int numRandom;
    	int selected = 0;
    	while (enemyMetal >= METAL_COST_LIGTHHUNTER & enemyDeut >=DEUTERIUM_COST_LIGTHHUNTER) {
    		numRandom = (int) (Math.random()*100);
    		//System.out.println(numRandom);
    		boolean select = false;
    		selected = 0;
    		for (int i = 0;i< enemyProb.size();i++) {
				selected = selected + (int) enemyProb.get(i);
				//System.out.println(selected);
				if (selected>=numRandom ) {
					
					if (i == 0 & !select) {
						if (enemyMetal >= METAL_COST_LIGTHHUNTER & enemyDeut >=DEUTERIUM_COST_LIGTHHUNTER) {
							enemyArmie[0].add(new LigthHunter(ARMOR_LIGTHHUNTER+(enemyLevel*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_LIGTHHUNTER+(enemyLevel*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_LIGTHHUNTER;
							enemyDeut -=DEUTERIUM_COST_LIGTHHUNTER;
							//System.out.println("1 ligth hunter");
							select = true;
						}
					}
					else if(i == 1 & !select) {
						if (enemyMetal >= METAL_COST_HEAVYHUNTER & enemyDeut >=DEUTERIUM_COST_HEAVYHUNTER) {
							enemyArmie[1].add(new HeavyHunter(ARMOR_HEAVYHUNTER+(enemyLevel*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_HEAVYHUNTER+(enemyLevel*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_HEAVYHUNTER;
							enemyDeut -=DEUTERIUM_COST_HEAVYHUNTER;
							//System.out.println("1 Heavy hunter");
							select = true;
						}
					}
						
					else if(i == 2 & !select) {
						if (enemyMetal >= METAL_COST_BATTLESHIP & enemyDeut >=DEUTERIUM_COST_BATTLESHIP) {
							enemyArmie[2].add(new BattleShip(ARMOR_BATTLESHIP+(enemyLevel*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_BATTLESHIP+(enemyLevel*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_BATTLESHIP;
							enemyDeut -=DEUTERIUM_COST_BATTLESHIP;
							//System.out.println("1 Battle ship");
							select = true;
						}
					}
					else if(i == 3 & !select) {
						if (enemyMetal >= METAL_COST_ARMOREDSHIP & enemyDeut >=DEUTERIUM_COST_ARMOREDSHIP) {
							enemyArmie[3].add(new ArmoredShip(ARMOR_ARMOREDSHIP+(enemyLevel*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_ARMOREDSHIP+(enemyLevel*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_ARMOREDSHIP;
							enemyDeut -= DEUTERIUM_COST_ARMOREDSHIP;
							//System.out.println("1 armored ship");
							select = true;
						}
					}
					
					}
				}
			}
    	
    	
    	enemyMetal = metal + 50000;
    	enemyDeut = deuterium+ 25000;
    	enemyLevel +=1;
    	pelea.setEnemyArmy(enemyArmie.clone());
    	
    }
    //ver enemigo
    private void viewThreat() {
    	String mens = String.format("NEW THREAT COMING\n\n%-30s%d\n\n%-30s%d\n\n%-30s%d\n\n%-30s%d",
    			"Ligth Hunter",enemyArmie[0].size(),"Heavy Hunter",enemyArmie[1].size(),"Battle Ship",enemyArmie[2].size(),
    			"Armored Ship",enemyArmie[3].size());
    	System.out.println(mens);
    	
    }
    //pelearse
    private void lucha() {
//    	int condicionPerdidaPlaneta = (int) (pelea.getInitialNumberUnitsPlanet()*20/100);
//    	int condicionPerdidaEnemy = (int) (pelea.getInitialNumberUnitsEnemy()*20/100);
    	boolean salir = false;
    	int gruAtacante = 0;
    	int gruDefensor = 0;
    	int atacante = 0;
    	int defensor = 0;
    	boolean pelear = true;
    	int atacar;
    	int empieza = (int) (Math.random()*2+1);
    	int contEmpieza = 0;
		boolean eliminado = false;
    	String mensaje = "";
    	while(!salir) {
    		mensaje += "\n"+"*".repeat(20)+"CHANGE ATTACKER"+"*".repeat(20)+"\n";
    		pelear = true;
    		eliminado = false;
    		if (empieza%2 == 0) {
    			gruAtacante = pelea.getEnemyGroupAttacker();
    			contEmpieza = 1;
    			mensaje += "Attacks fleet enemy: ";
    		}
    		else {
    			gruAtacante = pelea.getPlanetGroupAttacker();
    			contEmpieza = 0;
    			mensaje += "Attacks Planet: ";
    		}
//    		System.out.println(contEmpieza);
//    		System.out.println(empieza%2);
//    		System.out.println("Grupo atacante"+gruAtacante);
    		gruDefensor = pelea.getGroupDefender(pelea.getArmies()[empieza%2]);
//    		System.out.println("Grupo defensor"+ gruDefensor);
    		//seleccionamos quien es el tacante del grupo y quien se defiende
    		
    		atacante = (int) (Math.random()*pelea.getArmies()[contEmpieza][gruAtacante].size());
    		//para ver al atacante
    		mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" attacks ";
    		//System.out.println("Ataca : "+pelea.getArmies()[contEmpieza][gruAtacante].get(atacante));
    		defensor = (int) (Math.random()*pelea.getArmies()[empieza%2][gruDefensor].size());
    		//mas el defensor
    		mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+"\n";
    		
    		//			System.out.println("llega a pegarle");
//			System.out.println("atacante,grupo atacante = "+atacante+"- "+gruAtacante);
//			System.out.println("defensor,grupo grupo defensor = "+defensor+"- "+gruDefensor);
			//pillamos el enemy armie en la posicion defensor, luego tenemos que hacer el .get, luego take damage
    		// y por ultimo lo mismo con planeta pero usamos la funcion attak
//			System.out.println(pelea.getArmies()[empieza%2][gruDefensor].get(defensor));
			((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).tekeDamage(((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack());
    		while (pelear) {
    			mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" generates the damage = "+
    					((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack()+"\n"+
    					pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" stays with armor = "+
    					((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).getActualArmor()+"\n";
    			//paExcep = true;
//    			System.out.println("entra en el while de pelea");
    			atacar = (int) (Math.random()*100);
    			if (((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).getActualArmor() <= 0) {
    				mensaje += "We eliminate "+pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+"\n";
    				pelea.getArmies()[empieza%2][gruDefensor].remove(defensor);
    				
    				if (pelea.getArmies()[empieza%2][gruDefensor].isEmpty()) {
    					if (pelea.remainderPercentageFleet(pelea.getArmies()[1])<= 20 | pelea.remainderPercentageFleet(pelea.getArmies()[0])<= 20) {
    		    			salir = true;
    		    			pelear = false;
    		    			break;
    		    			//System.out.println("Saliendo");
    		    		}
    					
    					
    					gruDefensor = pelea.getGroupDefender(pelea.getArmies()[empieza%2]);
//    					System.out.println("Aqui no");
    				}
					defensor = (int) (Math.random()*pelea.getArmies()[empieza%2][gruDefensor].size());				
//					System.out.println("1 enemigo eliminado");
					eliminado = true;
				}
    			if (empieza%2 == 0) {
    				if (CHANCE_ATTACK_ENEMY_UNITS[gruAtacante]<=atacar) {
    					((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).tekeDamage(((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack());
//	    				System.out.println("Vuelves a pegar");
	    			}
	    			else {
	    				pelear = false;
	    			}
    			}
    			else {
    				if (CHANCE_ATTACK_PLANET_UNITS[gruAtacante]<=atacar) {
    					((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).tekeDamage(((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack());
//	    				System.out.println("Vuelves a pegar");
	    			}
	    			else {
	    				pelear = false;
	    			}
    			}
			}
    		if (eliminado) {
//    			System.out.println("porcentaje enemigo:");
//        		System.out.println(pelea.remainderPercentageFleet(pelea.getArmies()[1]));
//        		System.out.println("Porcentaje aliado");
//        		System.out.println(pelea.remainderPercentageFleet(pelea.getArmies()[0]));
//        		System.out.println("El enemigo no puede lleger a "+20+" y el planeta "+20);
	    		if (pelea.remainderPercentageFleet(pelea.getArmies()[1])<= 20 | pelea.remainderPercentageFleet(pelea.getArmies()[0])<= 20) {
	    			salir = true;
	    			//System.out.println("Saliendo");
	    		}
    		}
    		empieza += 1;
    	}
    //System.out.println(mensaje);
    pelea.resto();
    if (pelea.getResourcesLooses()[0][2]>pelea.getResourcesLooses()[1][2]) {
    	pelea.generateWaste();
    	miPlaneta.setMetal(miPlaneta.getMetal()+pelea.getWasteMetalDeuterium()[0]);
    	miPlaneta.setDeuterium(miPlaneta.getDeuterium()+pelea.getWasteMetalDeuterium()[1]);
    }
    else {
    	pelea.generateWaste();
    }
    pelea.updateResourcesLooses();
    peleas.add(mensaje);
    reportes.add(pelea.getBattleDevelopment());
    }
}
