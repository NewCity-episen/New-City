
set m2=%M2_REPO%
set clsspth=%m2%/edu/episen/si/ing1/pds/backend-service/0.0.1-SNAPSHOT/backend-service-0.0.1-SNAPSHOT-jar-with-dependencies.jar
call java -cp %clsspth% edu.episen.si.ing1.pds.server.BackendService %*
