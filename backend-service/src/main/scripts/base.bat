
set clsspth=C:\Users\user16\new-city\backend-service\target\backend-service-0.0.1-SNAPSHOT-jar-with-dependencies.jar

scp %clsspth% newcity@172.31.254.95:/home/newcity/

ssh newcity@172.31.254.95