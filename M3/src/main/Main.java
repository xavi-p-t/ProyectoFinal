package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private boolean menu = true;
	private boolean inicioSesion = true;
	private boolean selecPlanetas = false;
	
	private boolean flag_0 = false;
	private boolean flag_00 = false;
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
    private TimerTask task3;
    
    private String menuMostr = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
    private String menuPrinc = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n0)Exit\n\nOption:";
    private String menuPrincAtak = "Main Menu\n\n1)View Planet Stats\n2)Build\n3)Upgrade Technology\n4)View Battle Reports\n5)View Thread Comming\n0)Exit\n\nOption:";
    private String menuBuild = "Build\n1)Build Troops\n2)Build Defenses\n3)Go Back\nOption:";
    private String buildTroops = "Menu Build Troops\n\n1)Build Light Hunter\n2)Build Heavy Hunter\n3)Build Battle Ship\n4)Build Armored Ship\n5)Go Back\nOption:";
    private String buildDefenses = "Menu Build Defenses\n\n1)Build Missile Launcher\n2)Build Ion Cannon\n3)Build Plasma Cannon\n4)Go Back\nOption:";
    private String building = "Amount of Units\nAmount:";
    private String levelUp = "";
    private String report = "Battle Reports\nThere is not reports to read\nPress enter to go back";
    private String menuIni = "MAIN MENU\n1)Log In\n2)create Account\n0)Exit\n\nOpcion:";
    private String menuplaneta = "PLANETS MENU\n1)Create new Planet\n2)Select Planet\n0)Back\n\nOption:";
    
    private String planetName;
    
    private int userId;
    private int planetId;
    private int num = 0;
    private int enemyMetal = METAL_BASE_ENEMY_ARMY;
    private int enemyDeut = DEUTERIUM_BASE_ENEMY_ARMY;
    private int enemyLevel = 0;
    private ArrayList<MilitaryUnit>[] enemyArmie = new ArrayList[4];
    private ArrayList reportes = new ArrayList();
    private ArrayList peleas = new ArrayList();
    
    private Connection conexion;
    private Statement stmnt;
    private ResultSet rs;
    private PreparedStatement pst;
    
    private planeta miPlaneta;
    private Battle pelea = new Battle();
    
    //juego principal
    public juego(){
    	connectar();    	
		while (menu) {
			while(inicioSesion) {
				 menuniciarsesion();
			}
			while(selecPlanetas) {
				selPlanet();
			}
			timer.schedule(task1, 120000,180000);
    		timer.schedule(task2, 180000,180000);
    		timer.schedule(task3, 60000,60000);
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
			String[] recNaves = {"LIGHT_HUNTERS","HEAVY_HUNTERS","BATTLE_SHIPS","ARMORED_SHIPS","MISSILE_LAUNCHER","ION_CANNON","PLASMA_CANNON"};
			String update = "";
			int id = 1;
			String query = "";
			for (int i = 0; i< recNaves.length;i++) {
				update = "delete FROM "+recNaves[i]+" where planet_id = "+"\'"+planetId+"\'";
				try {
					//System.out.println(update);
					stmnt.executeUpdate(update);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!miPlaneta.getArmy()[i].isEmpty()) {
					for (int j = 0;j< miPlaneta.getArmy()[i].size();j++) {
						try {
							query = "select * from "+recNaves[i];
							try {
							rs = stmnt.executeQuery(query);
							while(rs.next()) {
								id = rs.getInt(1)+1;
							}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							update =  "INSERT INTO " + recNaves[i] + " (id, planet_id, armour, atack) VALUES (?, ?, ?, ?)";
							//System.out.println(update);
							pst = conexion.prepareStatement(update,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
							pst.setInt(1, id);
							pst.setInt(2, planetId);
							pst.setInt(3, miPlaneta.getArmy()[i].get(j).getActualArmor());
							pst.setInt(4, miPlaneta.getArmy()[i].get(j).attack());
							pst.executeUpdate();
//							 int rowsInserted = pst.executeUpdate();
//						        if (rowsInserted > 0) {
//						            System.out.println("Inserción exitosa en la tabla " + recNaves[i]);
//						        }
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
				
				}
			}
			update = "UPDATE Planet_stats SET " +
                    "resource_metal_amount = ?, " +
                    "resource_deuterion_amount = ?, " +
                    "technology_defense_level = ?, " +
                    "technology_attack_level = ?, " +
                    "battle_counter = ?, " +
                    "missile_launcher_remaining = ?, " +
                    "ion_cannon_remaining = ?, " +
                    "plasma_canon_remaining = ?, " +
                    "light_hunter_remaining = ?, " +
                    "heavy_hunter_remaining = ?, " +
                    "battleship_remaining = ?, " +
                    "armored_ship_remaining = ? " +
                    "WHERE planet_id = ?";
			try {
				pst = conexion.prepareStatement(update,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				pst.setInt(1, miPlaneta.getMetal());
				pst.setInt(2, miPlaneta.getDeuterium());
				pst.setInt(3, miPlaneta.getTechnologyDefense());
				pst.setInt(4, miPlaneta.getTechnologyAtack());
				pst.setInt(5, enemyLevel);
				pst.setInt(6, miPlaneta.getArmy()[4].size());
				pst.setInt(7, miPlaneta.getArmy()[5].size());
				pst.setInt(8, miPlaneta.getArmy()[6].size());
				pst.setInt(9, miPlaneta.getArmy()[0].size());
				pst.setInt(10, miPlaneta.getArmy()[1].size());
				pst.setInt(11, miPlaneta.getArmy()[2].size());
				pst.setInt(12, miPlaneta.getArmy()[3].size());
				pst.setInt(13, planetId);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (timer != null) {
				timer.cancel();	
			}
		}
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	opc.close();
    }
    //necesario para iniciar los timers y el planeta
    private void startGame() {
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
        task3 = new TimerTask() {
            public void run() {
                miPlaneta.setMetal(miPlaneta.getMetal()+10000);
                miPlaneta.setDeuterium(miPlaneta.getDeuterium()+5000);
            }
        };
    }
    //conectar
    private void connectar() {
    	String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String usuario = "admin_spacewars";
        String contraseña = "admin";
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            stmnt =	conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            System.out.println("Conexión establecida con éxito a Oracle.");
            // Realiza operaciones con la conexión aquí
//            conexion.close(); // Cierra la conexión cuando hayas terminado
        } catch (SQLException e) {
            System.out.println("Error al conectar con Oracle.");
            e.printStackTrace();
        }
    }
    //iniciar sesion menu
    private void menuniciarsesion() {
    	System.out.println(menuIni);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			iniciarSesion();
			
		}
		else if(num == 2) {
			crearCuenta();
		}
		else if(num == 0) {
			inicioSesion = false;
			menu = false;
		}
		else {
			System.out.println("Invalid Option");
		}
    }
    //INICIAR SESION
    private void iniciarSesion() {
    	boolean compusu = true;
    	boolean compcont = true;
    	String nombre = "";
    	String pasw;
    	String query ;
    	PreparedStatement ps;
    	while(compusu) {
    		System.out.println("User Name:");
    		while (!opc.hasNext()) {
    			System.out.println("Invalid Option");
    			opc.nextLine();
    		}
    		nombre = opc.next();
    		query = "Select * from Users where username = ?";
    		try {
    			ps = conexion.prepareStatement(query);
    			ps.setString(1, nombre);
    			rs = ps.executeQuery();
    			while (rs.next()) {
    				if (nombre.equals(rs.getString(2))) {
        				compusu = false;
        			}
    			}
    			
			} catch (SQLException e) {
				System.out.println("User name dont exist");
			}
    	}
    	while(compcont) {
    		System.out.println("User Password:");
    		while (!opc.hasNext()) {
    			System.out.println("Invalid Option");
    			opc.nextLine();
    		}
    		pasw = opc.next();
    		query = "Select * from Users where username = ? and user_password = ?";
    		try {
    			ps = conexion.prepareStatement(query);
    			ps.setString(1, nombre);
    			ps.setString(2, pasw);
    			rs = ps.executeQuery();
    			while (rs.next()) {
    				if (pasw.equals(rs.getString(3))) {
        				compcont = false;
        			}
    			}
			} catch (SQLException e) {
				System.out.println("User password dont match");
			}
    	}
    	try {
    		query = "Select * FROM Users where username = "+"\'"+nombre+"\'";
    		rs = stmnt.executeQuery(query);
    		while(rs.next()) {
    			//System.out.println(rs.getString(2));
        		userId = rs.getInt(1);
        		//System.out.println(userId);
    		}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	inicioSesion = false;
    	selecPlanetas = true;
    }
    //CREAR CUENTA
    private void crearCuenta() {
    	boolean compusu = true;
    	boolean compcont = true;
    	String nombre = "";
    	String cont = "";
    	String query ;
    	int id = 1;
    	boolean selid = false;
    	
    	while(compusu) {
    		System.out.println("New User Name:");
    		while (!opc.hasNext()) {
    			System.out.println("Invalid Option");
    			opc.nextLine();
    		}
    		nombre = opc.next();
    		query = "Select * FROM Users where username = "+"\'"+nombre+"\'";
    		try {
				rs = stmnt.executeQuery(query);
				if(!rs.next()) {
					compusu = false;
				}	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
    	}
    	while(compcont) {
    		System.out.println("New User Password:");
    		while (!opc.hasNext()) {
    			System.out.println("Invalid Option");
    			opc.nextLine();
    		}
    		cont = opc.next();
    		compcont = false;
    	}
    	String update = "INSERT INTO Users(user_id,username,user_password) VALUES (?,?,?)";
    	try {
    		query = "SELECT * FROM USERS";
			rs = stmnt.executeQuery(query);
			//System.out.println("peta la query");
			while(rs.next()) {
				//System.out.println("El while");
				
				
				id = rs.getInt(1)+1;		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println(id);
    	try {
    		//System.out.println("entra en el catch");
			PreparedStatement ps = conexion.prepareStatement(update,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, cont);
			ps.executeUpdate();
			//System.out.println("hace el insert");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	userId = id;
    	inicioSesion = false;
    	selecPlanetas = true;
    }
    //seleccionar uno de los planetas
    private void selPlanet() {
    	
    	System.out.println(menuplaneta);
		while (!opc.hasNextInt()) {
			System.out.println("Invalid Option");
			opc.nextLine();
		}
		num = opc.nextInt();
		if (num == 1) {
			newPlanet();
			startGame();
			selecPlanetas = false;
			flag_0 = true;
			flag_00 = true;
		}
		else if(num == 2) {
			selectExistPlanets();
			startGame();
			selecPlanetas = false;
			flag_0 = true;
			flag_00 = true;
		}
		else if(num == 0) {
			selecPlanetas = false;
			inicioSesion = true;
		}
		else {
			System.out.println("Invalid Option");
		}
    }
    //crear un nuevo planeta
    private void newPlanet() {
    	int id = 1;
    	boolean selid = false;
    	System.out.println("Planet Name:");
    	while (!opc.hasNext()) {
			System.out.println("Invalid Option");
			opc.nextLine();
			
		}
		planetName = opc.next();
		
		miPlaneta = new planeta();
		String update = "INSERT INTO Planet_stats(planet_id, planet_name, resource_metal_amount, resource_deuterion_amount,"
	            + "technology_defense_level, technology_attack_level, battle_counter, "
	            + "missile_launcher_remaining, ion_cannon_remaining, plasma_canon_remaining, "
	            + "light_hunter_remaining, heavy_hunter_remaining, battleship_remaining, armored_ship_remaining,user_id)"
	            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			rs = stmnt.executeQuery("SELECT * FROM Planet_stats");
			while(rs.next()) {
				id = rs.getInt(1)+1;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			//System.out.println("peta en el id");
			
		}
		System.out.println(id);
		planetId = id;
		try {
			System.out.println("a");
			PreparedStatement ps = conexion.prepareStatement(update,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id );
			ps.setString(2, planetName);
			ps.setInt(3, miPlaneta.getMetal());
			ps.setInt(4, miPlaneta.getDeuterium());
			ps.setInt(5, miPlaneta.getTechnologyDefense());
			ps.setInt(6, miPlaneta.getTechnologyAtack());
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.setInt(10, 0);
			ps.setInt(11, 0);
			ps.setInt(12, 0);
			ps.setInt(13, 0);
			ps.setInt(14, 0);
			ps.setInt(15, userId);
			ps.executeUpdate();
			
			System.out.println("insertado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //SELECCIONAR PLANETA EXISTENTE
    private void selectExistPlanets() {
    	String query = "SELECT * FROM Planet_stats where User_id = "+"\'"+userId+"\'";
    	String mens = "";
    	int num = 0;
    	boolean exist = false;
    	boolean comp = true;
    	ArrayList opciones = new ArrayList();
    	try {
			rs = stmnt.executeQuery(query);
			while(rs.next()) {
				mens += rs.getInt(1)+") "+rs.getString(2)+"\n";
				opciones.add(rs.getInt(1));
			}
			mens += "\nOption:";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while (comp) {
	    	System.out.println(mens);
	    	while (!opc.hasNextInt()) {
				System.out.println("Invalid Option");
				opc.nextLine();
			}
	    	num = opc.nextInt();
	    	for (Object object : opciones) {
				if(num == (int)object) {
					exist = true;
					comp = false;
				}
			}
	    	if(!exist) {
	    		System.out.println("Invalid option");
	    	}
    	}
    	planetId = num;
    	String[] recNaves = {"LIGHT_HUNTERS","HEAVY_HUNTERS","BATTLE_SHIPS","ARMORED_SHIPS","MISSILE_LAUNCHER","ION_CANNON","PLASMA_CANNON"};
    	query = "SELECT * FROM Planet_stats where Planet_id = "+"\'"+planetId+"\'";
    	try {
			rs = stmnt.executeQuery(query);
			while(rs.next()) {
				planetName = rs.getString(2);
				miPlaneta = new planeta(rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
				enemyLevel = rs.getInt(7);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	miPlaneta.setArray();
    	ArrayList<MilitaryUnit>[] army = new ArrayList[7];
    	for (int i = 0;i<army.length;i++) {
    		army[i] = new ArrayList<MilitaryUnit>();
    	}
    	for (int i = 0; i< recNaves.length;i++) {
    		query = "SELECT * FROM " + recNaves[i] + " WHERE planet_id = " + planetId;
    		try {
				rs = stmnt.executeQuery(query);
				while(rs.next()) {
					switch (i) {
						case 0: 
							LigthHunter light = new LigthHunter(rs.getInt(3),rs.getInt(4));
							army[i].add(light);
							break;
						case 1:
							HeavyHunter heavy = new HeavyHunter(rs.getInt(3),rs.getInt(4));
							army[i].add(heavy);
							break;
						case 2:
							BattleShip bat = new BattleShip(rs.getInt(3),rs.getInt(4));
							army[i].add(bat);
							break;
						case 3:
							ArmoredShip arm = new ArmoredShip(rs.getInt(3),rs.getInt(4));
							army[i].add(arm);
							break;
						case 4:
							MissileLauncher mis = new MissileLauncher(rs.getInt(3),rs.getInt(4));
							army[i].add(mis);
							break;
						case 5:
							IonCannon ion = new IonCannon(rs.getInt(3),rs.getInt(4));
							army[i].add(ion);
							break;
						case 6:
							PlasmaCannon plasm = new PlasmaCannon(rs.getInt(3),rs.getInt(4));
							army[i].add(plasm);
							break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
//    	for(int i = 0;i< army.length;i++) {
//    		System.out.println(army[i]);
//    	}
    	miPlaneta.setArmy(army);
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
				menu = false;
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
    		opc.next();
    		flag_04 = false;
    		flag_00 = true;
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
    	System.out.println("Empieza pelea");
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
    	//System.out.println("el while");
    	while(!salir) {
    		mensaje += "\n"+"*".repeat(20)+"CHANGE ATTACKER"+"*".repeat(20)+"\n";
    		pelear = true;
    		eliminado = false;
    		//System.out.println("Primer if");
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
    		//System.out.println("Grupos");
    		gruDefensor = pelea.getGroupDefender(pelea.getArmies()[empieza%2]);
    		//System.out.println("No es el defensor");
    		atacante = (int) (Math.random()*pelea.getArmies()[contEmpieza][gruAtacante].size());
    		//System.out.println("No es el atacate");
    		//para ver al atacante
    		mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" attacks ";
    		defensor = (int) (Math.random()*pelea.getArmies()[empieza%2][gruDefensor].size());
    		//System.out.println("No es la unidad defensora");
    		//mas el defensor
    		mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+"\n";
    		
			((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).tekeDamage(((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack());
    		//System.out.println("golpes");
			while (pelear) {
    			mensaje += pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" generates the damage = "+
    					((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack()+"\n"+
    					pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+" stays with armor = "+
    					((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).getActualArmor()+"\n";
    			atacar = (int) (Math.random()*100);
    			if (((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).getActualArmor() <= 0) {
    				mensaje += "We eliminate "+pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().substring(pelea.getArmies()[contEmpieza][gruAtacante].get(atacante).getClass().getName().lastIndexOf(".")+1)+"\n";
    				pelea.getArmies()[empieza%2][gruDefensor].remove(defensor);
    				
    				if (pelea.getArmies()[empieza%2][gruDefensor].isEmpty()) {
    					if (pelea.remainderPercentageFleet(pelea.getArmies()[1])<= 20 | pelea.remainderPercentageFleet(pelea.getArmies()[0])<= 20) {
    		    			salir = true;
    		    			pelear = false;
    		    			break;
    		    		}
    					gruDefensor = pelea.getGroupDefender(pelea.getArmies()[empieza%2]);
    				}
					defensor = (int) (Math.random()*pelea.getArmies()[empieza%2][gruDefensor].size());				
					eliminado = true;
				}
    			if (empieza%2 == 0) {
    				if (CHANCE_ATTACK_ENEMY_UNITS[gruAtacante]<=atacar) {
    					((MilitaryUnit) pelea.getArmies()[empieza%2][gruDefensor].get(defensor)).tekeDamage(((MilitaryUnit) pelea.getArmies()[contEmpieza][gruAtacante].get(atacante)).attack());
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
			//System.out.println("comprueba eliminado");
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
    //System.out.println("ns");
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
    //reseteamos armadura
    pelea.resetArmyArmor();
    miPlaneta.setArmy(pelea.getPlanetArmy());
    //System.out.println("todo normal");
    int idBatle = 1;
    try {
		rs = stmnt.executeQuery("SELECT * FROM battle");
		while(rs.next()) {
			idBatle = rs.getInt(1)+1;
		}
	} catch (SQLException e) {
		e.printStackTrace();		
	}
    try {
    	String update = "INSERT INTO battle(planet_id,num_battles,battle_stats,battle_log) VALUES (?,?,?,?)";
    	pst = conexion.prepareStatement(update,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	pst.setInt(1, planetId);
    	pst.setInt(2, idBatle);
    	pst.setString(3, pelea.getBattleDevelopment());
    	pst.setString(4, pelea.getBattleDevelopment());
    	pst.executeUpdate();
    	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
