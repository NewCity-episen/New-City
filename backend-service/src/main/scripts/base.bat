set m2=%M2_REPO%

set clsspth=%m2%/edu/episen/si/ing1/pds/client/0.0.1-SNAPSHOT/client-0.0.1-SNAPSHOT-jar-with-dependencies.jar

scp %clsspth% newcity@172.31.249.91:/home/newcity/

ssh newcity@172.31.249.91