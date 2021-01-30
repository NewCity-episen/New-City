
set CLASSPATH=C:\Users\user16\new-city\backend-service\target\classes;C:\Users\user16\.m2\repository\ch\qos\logback\logback-classic\1.1.7\logback-classic-1.1.7.jar;C:\Users\user16\.m2\repository\ch\qos\logback\logback-core\1.1.7\logback-core-1.1.7.jar;C:\Users\user16\.m2\repository\org\slf4j\slf4j-api\1.7.20\slf4j-api-1.7.20.jar;C:\Users\user16\.m2\repository\commons-cli\commons-cli\1.4\commons-cli-1.4.jar;
set arg1=%1
set arg2=%2
set arg3=%3
set arg4=%4
java edu.episen.si.ing1.pds.server.BackendService %arg1% %arg2% %arg3% %arg4%