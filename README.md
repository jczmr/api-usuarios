# Demo api rest en Spring Boot 


#### Para arrancar el proyecto:
* se puede utilizar en Docker, el siguiente comando para levantar una instancia de PostgreSql.
><sup>docker run --name postgresDev01 -dp 5432:5432 -e POSTGRES_PASSWORD=c33766232 postgres:alpine3.18</sup>
* Luego el proyecto usa Gradle como getor de dependencias, se puede arrancar desde la linea de ejecutando el comando:
* * ./gradlew bootRun


* se incluye en la carpeta ***src/main/resources*** del proyecto los siguientes archivos:
> REST-demo-falabella-soapui-project.xml Para realizar consultas a la API.

> data.sql Para realizar una carga de datos en la base de datos.

> sgta-admin-var.bat Para crear en el SO Windows las variables de entorno, 
> declaradas en el archivo: ***src/main/resources/application.properties***

#### Se encontraron las siguientes consideraciones.
* En la capa modelo la ***Entidad User***, continene un atributo ***Age*** de tipo numérico, para almacenar la edad. 
 Al ser un atributo derivado se agregó el atributo ***dateOfBirth*** de tipo ***LocalDateTime*** para luego poder calcualr la edad.
* Se podría modificar a ***Many-to-Many***  la relación ***Many-to-One*** entre ***Users*** y ***Roles***, 
 para que la ***Enridad User*** soporte más de un ***Rol***.
*  La petición Get retorna sólo registros con estado ACTIVO, se podría incluir la opcion de mostrar todos los registros.



### Capturas de pantalla de la API
<p>Roles</p>
<img src="src/main/resources/static/Captura de pantalla roles.png" width="369" height="491" />
<p>Users</p>
<img src="src/main/resources/static/Captura de pantalla users.png" width="371" height="516" />
<p>Crear Rol</p>
<img src="src/main/resources/static/Captura de pantalla crear rol.png" width="729" height="483" />