ALTER TABLE IES.t_alumno CHANGE referencia nie varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL;
ALTER TABLE ies.t_alumno CHANGE id_alumno nia int(11) auto_increment NOT NULL;
ALTER TABLE IES.t_alumno CHANGE referencia nie varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL;
ALTER TABLE ies.t_alumno MODIFY COLUMN email varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE ies.t_alumno MODIFY COLUMN telefono int(11) NULL;