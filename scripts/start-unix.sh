#!/usr/bin/env sh

export JDBC_DATABASE_URL=jdbc:postgresql://172.29.117.210:5432/postgres
export JDBC_DATABASE_USERNAME=postgres
export JDBC_DATABASE_PASSWORD=c33766232

./gradlew bootRun