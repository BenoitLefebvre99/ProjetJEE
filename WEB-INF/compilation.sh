#!/bin/bash

cd ../../../bin
./shutdown.sh
cd ../webapps/projetprogweb/WEB-INF/classes
rm *.class
cd ../lib
javac -cp ".:lib/commons-io-2.6.jar:lib/postgresql-jdbc.jar:lib/servlet-api.jar:lib/unbescape-1.1.6.RELEASE.jar" *.java
mv *.class ../classes
cd ../../../../bin
./startup.sh