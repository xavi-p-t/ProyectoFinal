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
    
    private String menuPrinc = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
    private String menuPrincAtak = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n5)View Thread Comming\n0)Exit\n\nOption:";
    private String menuBuild = "Build\n1)Build Troops\n2)Build Defenses\n3)Go Back\nOption:";
    private String buildTroops = "Menu Build Troops\n\n1)Build Light Hunter\n2)Build Heavy Hunter\n3)Build Battle Ship\n4)Build Armored Ship\n5)Go Back\nOption:";
    private String buildDefenses = "Menu Build Defenses\n\n1)Build Missile Launcher\n2)Build Ion Cannon\n3)Build Plasma Cannon\n4)Go Back\nOption:";
    private String building = "Amount of Units\nAmount:";
    private String levelUp = "";
    
    private int num = 0;
    private int enemyMetal = METAL_BASE_ENEMY_ARMY;
    private int enemyDeut = DEUTERIUM_BASE_ENEMY_ARMY;
    private int enemyLevel = 0;
    private ArrayList<MilitaryUnit>[] enemyArmie = new ArrayList[4];
    
    planeta miPlaneta = new planeta();
    Battle pelea = new Battle();
    
    //juego principal
    juego(){
    	startGame();
    	
    	timer.schedule(task1, 1000,180000);
		timer.schedule(task2, 240000,240000);
		
    	while(flag_0) {
    		while (flag_00 & !ataque) {
    			menuInicial();
    		}
    		while (flag_00 & ataque) {
    			menuAtaque();
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
                System.out.println("\n\nNEW THREAD IS COMMING\n\n");
                createEnemyArmy();
            }
        };
        task2 = new TimerTask() {
            public void run() {
                ataque = false;
                System.out.println("\n\nWE HAVE BEEN ATTACKED!!!\n\n");
            }
        };
    }
    //menu 00
    private void menuInicial() {
    	
    		System.out.println(menuPrinc);
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
			else if (num == 0){
				
				flag_00 = false;
				flag_0 = false;
				timer.cancel();

			}
			else {
				System.out.println("Invalid Option");
			}
    	
    }
    //menu 00 cuando hay ataque
    private void menuAtaque() {
    	System.out.println(menuPrincAtak);
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
			viewThreat();
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
			for (int i = 0;i<miPlaneta.getArmy().length;i++) {
				for (int j = 0;j<miPlaneta.getArmy()[i].size();j++) {
					switch (i) {
					case 0:
						miPlaneta.getArmy()[i].set(j, 
								new LigthHunter(ARMOR_LIGTHHUNTER+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100,
								miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 1:
						miPlaneta.getArmy()[i].set(j, 
								new HeavyHunter(ARMOR_HEAVYHUNTER+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 2:
						miPlaneta.getArmy()[i].set(j, 
								new BattleShip(ARMOR_BATTLESHIP+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 3:
						miPlaneta.getArmy()[i].set(j, 
								new ArmoredShip(ARMOR_ARMOREDSHIP+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 4:
						miPlaneta.getArmy()[i].set(j, 
								new MissileLauncher(ARMOR_MISSILELAUNCHER+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 5:
						miPlaneta.getArmy()[i].set(j, 
								new IonCannon(ARMOR_IONCANNON+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					case 6:
						miPlaneta.getArmy()[i].set(j, 
								new PlasmaCannon(ARMOR_PLASMACANNON+(miPlaneta.getTechnologyDefense()*PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY)*1000/100,
										miPlaneta.getArmy()[i].get(j).attack()));
						break;
					}
					pelea.setPlanetArmy(miPlaneta.getArmy());
					
				}
				
			}
		}
		else if(num == 2) {
			try {
				miPlaneta.upgradeTechnologyAttack();
			} catch (ResourceException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			for (int i = 0;i<miPlaneta.getArmy().length;i++) {
				for (int j = 0;j<miPlaneta.getArmy()[i].size();j++) {
					switch (i) {
					case 0:
						miPlaneta.getArmy()[i].set(j, 
								new LigthHunter(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_LIGTHHUNTER+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100));
						break;
					case 1:
						miPlaneta.getArmy()[i].set(j, 
								new HeavyHunter(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_HEAVYHUNTER+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100));
						break;
					case 2:
						miPlaneta.getArmy()[i].set(j, 
								new BattleShip(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_BATTLESHIP+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100));
						break;
					case 3:
						miPlaneta.getArmy()[i].set(j, 
								new ArmoredShip(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_ARMOREDSHIP+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100));
						break;
					case 4:
						miPlaneta.getArmy()[i].set(j, 
								new MissileLauncher(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_MISSILELAUNCHER+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100));
						break;
					case 5:
						miPlaneta.getArmy()[i].set(j, 
								new IonCannon(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_HEAVYHUNTER+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100));
						break;
					case 6:
						miPlaneta.getArmy()[i].set(j, 
								new PlasmaCannon(miPlaneta.getArmy()[i].get(j).getActualArmor(),
								BASE_DAMAGE_PLASMACANNON+(miPlaneta.getTechnologyAtack()*PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY)*1000/100));
						break;
					}
					
				}
				
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
    //generar enemigos
    private void createEnemyArmy() {
    	int[] enemyProb = new int[4];
    	enemyProb[0] = 35;
    	enemyProb[1] = 25;
    	enemyProb[2] = 20;
    	enemyProb[3] = 20;
    	int metal = enemyMetal;
    	int deuterium = enemyDeut;
    	for (int i = 0;i<enemyArmie.length;i++) {
			enemyArmie[i] = new ArrayList<MilitaryUnit>();
		}
    	int numRandom;
    	int selected = 0;
    	while (enemyMetal >= METAL_COST_LIGTHHUNTER & enemyDeut >=DEUTERIUM_COST_LIGTHHUNTER) {
    		numRandom = (int) (Math.random()*100);
    		for (int i = 0;i< enemyProb.length;i++) {
				selected += enemyProb[i];
				if (selected>=numRandom ) {
					switch (i) {
					case 0:
						if (enemyMetal >= METAL_COST_LIGTHHUNTER & enemyDeut >=DEUTERIUM_COST_LIGTHHUNTER) {
							enemyArmie[0].add(new LigthHunter(ARMOR_LIGTHHUNTER+(enemyLevel*PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_LIGTHHUNTER+(enemyLevel*PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_LIGTHHUNTER;
							enemyDeut -=DEUTERIUM_COST_LIGTHHUNTER;
							//System.out.println("1 ligth hunter");
						}
						break;
					case 1:
						if (enemyMetal >= METAL_COST_HEAVYHUNTER & enemyDeut >=DEUTERIUM_COST_HEAVYHUNTER) {
							enemyArmie[1].add(new HeavyHunter(ARMOR_HEAVYHUNTER+(enemyLevel*PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_HEAVYHUNTER+(enemyLevel*PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_HEAVYHUNTER;
							enemyDeut -=DEUTERIUM_COST_HEAVYHUNTER;
							//System.out.println("1 Heavy hunter");
						}

						break;
					case 2:
						if (enemyMetal >= METAL_COST_BATTLESHIP & enemyDeut >=DEUTERIUM_COST_BATTLESHIP) {
							enemyArmie[2].add(new BattleShip(ARMOR_BATTLESHIP+(enemyLevel*PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_BATTLESHIP+(enemyLevel*PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_BATTLESHIP;
							enemyDeut -=DEUTERIUM_COST_BATTLESHIP;
							//System.out.println("1 Battle ship");
						}
						break;
					case 3:
						if (enemyMetal >= METAL_COST_ARMOREDSHIP & enemyDeut >=DEUTERIUM_COST_ARMOREDSHIP) {
							enemyArmie[3].add(new ArmoredShip(ARMOR_ARMOREDSHIP+(enemyLevel*PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100,
									BASE_DAMAGE_ARMOREDSHIP+(enemyLevel*PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)*1000/100));
							enemyMetal -= METAL_COST_ARMOREDSHIP;
							enemyDeut -= DEUTERIUM_COST_ARMOREDSHIP;
							//System.out.println("1 armored ship");
						}
						break;
					}
				}
			}
    	}
    	
    	enemyMetal = metal + 50000;
    	enemyDeut = deuterium+ 25000;
    	enemyLevel +=1;
    	pelea.setEnemyArmy(enemyArmie);
    	
    }
    //la tormenta que se acerca
    private void viewThreat() {
    	String mens = String.format("NEW THREAT COMING\n\n%-30s%d\n\n%-30s%d\n\n%-30s%d\n\n%-30s%d",
    			"Ligth Hunter",enemyArmie[0].size(),"Heavy Hunter",enemyArmie[1].size(),"Battle Ship",enemyArmie[2].size(),
    			"Armored Ship",enemyArmie[3].size());
    	System.out.println(mens);
    	
    }
    
}

