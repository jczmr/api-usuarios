/**
* Carga de datos para la aplicación durante desarrollo y pruebas.
* 
* Para el funcionamiento de la aplicación se requiere que al menos un rol 
* esté registrado en la tabla ROLES.
*
*
*
*/

TRUNCATE TABLE ROLES CASCADE;
TRUNCATE TABLE USERS CASCADE;

INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'ADMIN', 'ACTIVE');
INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'MANAGER', 'ACTIVE');
INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'ANALYST', 'ACTIVE');

INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (30, currval('roles_id_seq'), NOW(), 'MARTINEZ', 'PABLO', 'ACTIVE', 'pablo4587', 'martinez@mail.com');

INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (23, currval('roles_id_seq'), NOW(), 'ARAYA', 'CARLA', 'ACTIVE', 'carla123', 'araya@mail.com');

INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (46, currval('roles_id_seq'), NOW(), 'CABRERA', 'NICOLE', 'ACTIVE', 'nicole795', 'cabrera@mail.com');