# Demo api rest en Spring Boot

## Pre requisitos
- docker
- java 17 o posterior
- PostgreSQL

## Para arrancar el proyecto
Se puede utilizar en Docker, el siguiente comando para levantar una instancia de PostgreSql.  
En unix:
```shell
docker run --name postgresDev01 -dp 5432:5432 -e POSTGRES_PASSWORD=c33766232 postgres:alpine3.18<
```
En windows hacer uso de Docker Desktop.

La aplicación está usando un archivo llamado `data.sql` para precargar información el cual usa spring.

Para iniciar el proyecto con el servidor web:
```shell
./scripts/start-unix.sh 
```
o windows

```shell
scripts\start-windows.bat
```

## Correr pruebas
```shell
./gradlew test
```
o
```shell
.\gradlew.bat test
```


Para probar la API se puede hacer uso de soap ui usando el siguiente archivo en la carpeta docs:
```text
REST-demo-falabella-soapui-project.xml
```

## Se encontraron las siguientes consideraciones.
* En la capa modelo la ***Entidad User***, continene un atributo ***Age*** de tipo numérico, para almacenar la edad.
  Al ser un atributo derivado se agregó el atributo ***dateOfBirth*** de tipo ***LocalDateTime*** para luego poder calcualr la edad.
* Se podría modificar a ***Many-to-Many***  la relación ***Many-to-One*** entre ***Users*** y ***Roles***,
  para que la ***Enridad User*** soporte más de un ***Rol***.
*  La petición Get retorna sólo registros con estado ACTIVO, se podría incluir la opcion de mostrar todos los registros.


### Capturas de pantalla de la API
<p>Roles</p>
<img src="docs/Captura de pantalla roles.png" width="369" height="491" />
<p>Users</p>
<img src="docs/Captura de pantalla users.png" width="371" height="516" />
<p>Crear Rol</p>
<img src="docs/Captura de pantalla crear rol.png" width="729" height="483" />