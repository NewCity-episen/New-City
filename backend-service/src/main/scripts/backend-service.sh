classpat="${M2_REPO}/edu/episen/si/ing1/pds/backend-service/0.0.1-SNAPSHOT"
jarfile="${classpat}/backend-service-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
exec java -cp ${jarfile} edu.episen.si.ing1.pds.server.BackendService ${*}
