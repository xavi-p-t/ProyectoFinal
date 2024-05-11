package main;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Atack.*;
import defensa.*;
import batalla.Battle;
import partPlanetas.*;
import interfacesProj.*;


public class Main implements Variables{
	
	public static void main(String[] args) {
		new juego();

	}
}
class juego implements Variables{
	private boolean flag_0 = true;
	private boolean flag_00 = true;
	private boolean flag_00_atque = false;
	
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
    planeta miPlaneta = new planeta();
    
    juego(){
    	startGame();
    	
    	timer.schedule(task1, 180000,180000);
		timer.schedule(task2, 240000,240000);
		
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
    	}
    	opc.close();
    }
    
    private void startGame() {
    	miPlaneta.setArray();
        timer = new Timer();
        task1 = new TimerTask() {
            public void run() {
                flag_00 = false;
                System.out.println("\n\nNEW THREAD IS COMMING\n\n");
            }
        };
        task2 = new TimerTask() {
            public void run() {
                flag_00 = false;
                System.out.println("\n\nWE HAVE BEEN ATTACKED!!!\n\n");
            }
        };
    }
    
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
		}
		else if (num == 5){
			flag_02 = true;
			flag_021 = false;			
		}
		else {
			System.out.println("Invalid Option");
		}
    }
    
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
		}
		else if (num == 4){
			flag_02 = true;
			flag_022 = false;			
		}
		
		else {
			System.out.println("Invalid Option");
		}
    }
    
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
	

    
}

