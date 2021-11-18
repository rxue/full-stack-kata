mvn -f ../stock-portfolio/web/pom.xml clean package
KEYSTORE_FILE="../stock-portfolio/web/docker-config/application.keystore"
if [ ! -f ${KEYSTORE_FILE} ]; then
  echo "Going to generate key store"
  keytool -genkey -alias server -keyalg RSA -keystore ${KEYSTORE_FILE} -storepass password -validity 365
fi
docker-compose up -d --build stock-portfolio
docker-compose logs -f stock-portfolio
