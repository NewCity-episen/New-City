m2=${M2_REPO}
clsspth=${m2}/edu/episen/si/ing1/pds/client/0.0.1-SNAPSHOT/client-0.0.1-SNAPSHOT-jar-with-dependencies.jar
exec java -cp ${clsspth} edu.episen.si.ing1.pds.client.Client ${*}
