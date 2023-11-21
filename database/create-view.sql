USE talesof;

CREATE OR REPLACE VIEW saves_list AS 
SELECT p.id AS player_id, s.id AS save_id, s.slot AS slot, s.character_class AS class, s.character_name AS character_name, cs.play_time AS play_time, MAX(st.date) AS last_saved 
FROM Player p 
INNER JOIN Save s ON p.id = s.player_id 
INNER JOIN Save_state st ON s.id = st.save_id 
INNER JOIN Character_state cs ON st.character_state_id = cs.id 
GROUP BY p.id, s.id, class, character_name, play_time;

CREATE OR REPLACE VIEW load_player_position AS 
SELECT p.id AS player_id, s.id AS save_id, sp.map AS map, sp.world_x AS world_x, sp.world_y AS world_y, MAX(st.date) AS recent_save_state 
FROM Player p 
INNER JOIN Save s ON p.id = s.player_id 
INNER JOIN Save_state st ON s.id = st.save_id 
INNER JOIN Save_point sp ON st.save_point_id = sp.id 
GROUP BY p.id, s.id, sp.map, sp.world_x, sp.world_y;  

-- SELECT * FROM load_player_position
-- WHERE player_id = 1 AND save_id = 1
-- ORDER BY recent_save_state DESC
-- LIMIT 1;

CREATE OR REPLACE VIEW load_config AS 
SELECT p.id AS player_id, stt.volume_effects AS volume_effects, stt.volume_music AS volume_music, stt.volume_geral AS volume_geral, 
stt.full_screen AS full_screen, stt.resolution AS resolution, MAX(stt.save_date) AS most_recent_save_date 
FROM Player p 
LEFT JOIN Settings stt ON p.id = stt.player_id 
GROUP BY player_id, volume_effects, volume_music, volume_geral, full_screen, resolution;

-- SELECT * FROM load_config 
-- WHERE player_id = 1;

CREATE OR REPLACE VIEW players_with_most_experience AS 
SELECT p.id AS player_id, p.username AS player_name, s.character_name, cs.experience 
FROM Player p 
INNER JOIN Save s ON p.id = s.player_id 
INNER JOIN Save_state st ON s.id = st.save_id 
INNER JOIN Character_state cs ON cs.id = st.character_state_id 
GROUP BY player_id, player_name, s.character_name, cs.experience 
HAVING cs.experience > 50 
ORDER BY cs.experience DESC;

-- SELECT * FROM players_with_most_experience;

CREATE OR REPLACE VIEW most_recent_inventory_state AS 
SELECT i.name AS item_name, ii.amount, ii.current_equipped, s.player_id 
FROM Item_in_inventory ii 
JOIN Character_state cs ON ii.character_state_id = cs.id 
JOIN Save_state ss ON cs.id = ss.character_state_id 
JOIN Save s ON ss.save_id = s.id 
JOIN Item i ON ii.item_id = i.id 
AND s.id = ( 
    SELECT MAX(save.id) 
    FROM Save save 
    WHERE save.player_id = s.player_id 
);

-- SELECT * FROM most_recent_inventory_state
-- WHERE player_id = 1;