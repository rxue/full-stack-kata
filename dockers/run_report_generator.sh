mvn -f ../backend/java/ee/7/ejb3.2/report-generator/pom.xml clean package
docker-compose up -d --build report-generator
docker-compose logs -f report-generator
