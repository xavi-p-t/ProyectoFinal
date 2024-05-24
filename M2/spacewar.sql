-- Crear la tabla de usuario
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

-- Crear la tabla Battle_stats
CREATE TABLE Battle_stats (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    PRIMARY KEY (planet_id, num_battle), 
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    resource_metal_acquired NUMBER,
    resource_deuterior_acquired NUMBER 
);

-- Crear la tabla Battle_Log
CREATE TABLE Battle_log (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    PRIMARY KEY (planet_id, num_battle), 
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    num_line PRIMARY KEY NOT NULL,
    log_entry VARCHAR2(100)
);

-- Crear la tabla Planet_battle_defenses
CREATE TABLE Planet_battle_defenses (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    PRIMARY KEY (planet_id, num_battle), 
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    missile_launcher_built NUMBER,
    missile_launcher_destroyed NUMBER,
    ion_cannon_built NUMBER,
    ion_cannon_destroyed NUMBER,
    plasma_cannon_built NUMBER,
    plasma_cannon_destroyed NUMBER
);

-- Crear Planet_battle_army
CREATE TABLE Planet_battle_army (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    PRIMARY KEY (planet_id, num_battle), 
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    light_hunter_built NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_built NUMBER,
    heavy_hunter_destroyed NUMBER,
    battleship_built NUMBER,
    battleship_destroyed NUMBER,
    armored_ship_built NUMBER,
    armored_ship_destroyed NUMBER
);

-- Crear tabla Enemy_army
CREATE TABLE Enemy_army (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    PRIMARY KEY (planet_id, num_battle), 
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    light_hunter_threat NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_threat NUMBER,
    heavy_hunter_destroyed NUMBER,
    battleship_threat NUMBER,
    battleship_destroyed NUMBER,
    armored_ship_threat NUMBER,
    armored_ship_destroyed NUMBER
);

