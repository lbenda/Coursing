# mvn -f ../server/pom.xml clean install && mvn -f ../report/pom.xml clean install && mvn clean install && mvn -f ../application/pom.xml nbm:cluster-app nbm:run-platform

mvn clean install && mvn -f ../application/pom.xml nbm:cluster-app nbm:run-platform
