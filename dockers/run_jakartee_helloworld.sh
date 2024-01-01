mvn -f ../backend/java/ee/Jakarta/10/helloworld/pom.xml clean package
docker-compose up -d --build jakartaee-helloworld
docker-compose logs -f jakartaee-helloworld
