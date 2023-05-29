TRUNCATE TABLE ROLES CASCADE;
TRUNCATE TABLE USERS CASCADE;


INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'ADMIN', 'ACTIVE');
INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'MANAGER', 'ACTIVE');
INSERT INTO ROLES (created_at, role_name, status) VALUES (NOW(), 'ANALYST', 'ACTIVE');


INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (30, currval('roles_id_seq'), NOW(), 'CASAS', 'PABLO', 'ACTIVE', 'pablo123', 'casas@mail.com');

INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (23, currval('roles_id_seq'), NOW(), 'MARINEZ', 'CARLA', 'ACTIVE', 'carla123', 'martinez@mail.com');

INSERT INTO USUARIOS (age, role_id, created_at, last_name, name, status, username, email)
VALUES (46, currval('roles_id_seq'), NOW(), 'JOTA', 'FFF', 'ACTIVE', 'carlasdfa123', 'asdf@mail.com');