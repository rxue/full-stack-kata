source ../generate_keypair.sh ../src/main/resources/httpscert.p12
echo "execute unit tests and integration tests"
mvn --file ../pom.xml clean verify
mvn --file ../pom.xml spring-boot:run &
sleep 8
npm install
echo "execute end-to-end tests on the API"
npm test
ps aux |grep spring-boot |awk '{print $2}' |xargs kill
