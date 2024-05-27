--USERS
CREATE TABLE Users (
    user_id NUMBER PRIMARY KEY NOT NULL,
    username VARCHAR2(30) NOT NULL,
    user_password VARCHAR2(200)
);

-- Crear la tabla Planet_stats
CREATE TABLE Planet_stats (
    planet_id NUMBER PRIMARY KEY NOT NULL,
    planet_name VARCHAR2(30),
    resource_metal_amount NUMBER,
    resource_deuterion_amount NUMBER, 
    technology_defense_level NUMBER, 
    technology_attack_level NUMBER, 
    battle_counter NUMBER, 
    missile_launcher_remaining NUMBER, 
    ion_cannon_remaining NUMBER, 
    plasma_canon_remaining NUMBER, 
    light_hunter_remaining NUMBER, 
    heavy_hunter_remaining NUMBER, 
    battleship_remaining NUMBER, 
    armored_ship_remaining NUMBER,
    user_id NUMBER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE battle(
    planet_id NUMBER,
    num_battles NUMBER PRIMARY KEY NOT NULL,
    battle_stats CLOB,
    battle_log CLOB,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
--tablas para las naves
CREATE TABLE LIGHT_HUNTERS (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE HEAVY_HUNTERS (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE BATTLE_SHIPS (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE ARMORED_SHIPS (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE MISSILE_LAUNCHER (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE ION_CANNON(
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
CREATE TABLE PLASMA_CANNON (
    id NUMBER  NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);
