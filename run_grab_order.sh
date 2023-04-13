mvn -f ../backend/java/ee/7/grab-order/pom.xml clean package
docker-compose up -d --build grab-order
docker-compose logs -f grab-order
