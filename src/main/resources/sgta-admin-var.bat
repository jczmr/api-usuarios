@echo off
REM para desarrollo

echo creación de variables proyecto API Usuarios
REM para database
setx JDBC_DATABASE_URL jdbc:postgresql://172.29.117.210:5432/postgres
setx JDBC_DATABASE_USERNAME postgres
setx JDBC_DATABASE_PASSWORD c33766232
pause