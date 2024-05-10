package main;

import java.util.Scanner;

import batalla.Battle;
import partPlanetas.*;


public class Main {

	public static void main(String[] args) {
		boolean flag_0 = true;
		boolean flag_00 = true;
		
		boolean flag_02 = false;
		boolean flag_021 = false;
		boolean flag_022 = false;
		
		boolean flag_03 = false;
		boolean flag_04 = false;
		boolean flag_05 = false;
		
		int num = 0;
		
		String menuPrinc = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
		String menuBuild = "Build\n1)Build Troops\n2)Build Defenses\n3)Go Back\nOption:";
		String buildTroops = "Menu Build Troops\n\n1)Build Light Hunter\n2)Build Heavy Hunter\n3)Build Battle Ship\n4)Build Armored Ship\n5)Go Back\nOption:";
		String buildDefenses = "Menu Build Defenses\n\n1)Build Missile Launcher\n2)Build Ion Cannon\n3)Build Plasma Cannon\n4)Go Back\nOption:";
		String building = "Amount of Units\nAmount:";
		String levelUp = "Upgrade Technology\n%-40s%d\n%-40s%d\n\n%-30s%-10s%-10dDeuterium\n%-30s%-10s%-10dDeuterium\n3)Go Back\n\nDeuterium resources = %d\nOption:";
		
		
		Scanner opc = new Scanner(System.in);
		
		planeta miPlaneta = new planeta(); 
		miPlaneta.setArray();
		while (flag_0) {
			while (flag_00) {
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
				}
				else {
					System.out.println("Invalid Option");
				}
			}
			while (flag_02) {
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
			while (flag_021) {
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
						e.getMessage();
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
						e.getMessage();
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
						e.getMessage();
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
						e.getMessage();
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
			while (flag_022) {
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
						e.getMessage();
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
						e.getMessage();
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
						e.getMessage();
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
			while (flag_03) {
				levelUp = String.format(levelUp,"Actual Defense Technology:",miPlaneta.getTechnologyDefense(),
						"Actual Atack Technology:",miPlaneta.getTechnologyAtack(),"1)Upgrade Defense Technology.","Cost:",
						miPlaneta.getUpgradeDefenseTechnologyDeuteriumCost(),"2)Upgrade Attack Technology.","Cost:",
						miPlaneta.getUpgradeAttackTechnologyDeuteriumCost(),miPlaneta.getDeuterium());
				System.out.println(buildDefenses);
				while (!opc.hasNextInt()) {
					System.out.println("Invalid Option");
					opc.nextLine();
				}
				num = opc.nextInt();
				if (num == 1) {
					
				}
				else if(num == 2) {
					
				}
				else if (num == 3) {
					
				}
				else {
					System.out.println("Invalid Option");
				}
			}
		}
	}

}
