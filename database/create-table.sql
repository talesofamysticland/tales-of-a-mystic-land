CREATE DATABASE IF NOT EXISTS talesof;

USE talesof;

CREATE TABLE IF NOT EXISTS Player(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password CHAR(60) NOT NULL,
    verified BOOLEAN NOT NULL,
    verification_token CHAR(36) NOT NULL,
    register_date DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS Settings(
	id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
	volume_effects NUMERIC(3,1) NOT NULL,
    volume_music NUMERIC(3,1) NOT NULL,
    volume_geral NUMERIC(3,1) NOT NULL,
    full_screen BOOLEAN NOT NULL,
    resolution ENUM('4:3', '16:9', '21:9') NOT NULL,
    save_date DATETIME NOT NULL,
    
    FOREIGN KEY(player_id) REFERENCES Player(id)
);

CREATE TABLE IF NOT EXISTS Change_password(
	id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    validated BOOLEAN NOT NULL,
    verification_date DATETIME,
    verification_token CHAR(36) NOT NULL,
    
    FOREIGN KEY(player_id) REFERENCES Player(id)
);

CREATE TABLE IF NOT EXISTS Save(
	id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    character_name VARCHAR(20) NOT NULL,
    character_class ENUM('Warrior', 'Wizard', 'Archer') NOT NULL,

    FOREIGN KEY(player_id) REFERENCES Player(id)
);

CREATE TABLE IF NOT EXISTS Save_point(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    map INT NOT NULL,
    world_x INT NOT NULL,
    world_y INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Character_state(
	id INT AUTO_INCREMENT PRIMARY KEY,
    play_time DECIMAL(15) NOT NULL,
    experience INT NOT NULL,
    coins INT NOT NULL,
    strenght INT NOT NULL,
    resistance INT NOT NULL,
    constitution INT NOT NULL,
    dexterity INT NOT NULL,
    wisdom INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Save_state(
	id INT AUTO_INCREMENT PRIMARY KEY,
    save_id INT NOT NULL,
    character_state_id INT NOT NULL,
    save_point_id INT NOT NULL,
	date DATETIME NOT NULL,
    
    FOREIGN KEY(save_id) REFERENCES Save(id),
    FOREIGN KEY(character_state_id) REFERENCES Character_state(id),
    FOREIGN KEY(save_point_id) REFERENCES Save_point(id)
);
    
CREATE TABLE IF NOT EXISTS Item(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Item_in_inventory(
	id INT AUTO_INCREMENT PRIMARY KEY,
	character_state_id INT NOT NULL,
    item_id INT NOT NULL,
    amount INT NOT NULL,
    current_equipped BOOLEAN NOT NULL,
    
    FOREIGN KEY(character_state_id) REFERENCES Character_state(id),
    FOREIGN KEY(item_id) REFERENCES Item(id)
);
