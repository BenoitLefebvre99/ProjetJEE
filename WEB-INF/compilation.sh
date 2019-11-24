#!/bin/bash

cd ../../../bin
./shutdown.sh
cd ../webapps/projetprogweb/WEB-INF/classes
rm *.class
cd ../lib
javac *.java
mv *.class ../classes
cd ../../../../bin
./startup.sh