@echo off

set "JDBC_DATABASE_URL=jdbc:postgresql://172.29.117.210:5432/postgres"
set "JDBC_DATABASE_USERNAME=postgres"
set "JDBC_DATABASE_PASSWORD=c33766232"

call gradlew.bat bootRun