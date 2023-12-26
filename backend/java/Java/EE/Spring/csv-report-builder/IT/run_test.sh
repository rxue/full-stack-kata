# PKCS12 is an industry standard format
KEY_STORE_PATH=../src/main/resources/httpscert.p12
if [ -f ${KEY_STORE_PATH} ]; then rm ${KEY_STORE_PATH}; fi && keytool -genkeypair -alias httpscert -keyalg RSA -keysize 2048 -validity 1 -keystore ${KEY_STORE_PATH} -storetype PKCS12 -storepass testme -keypass testme -dname "CN=Xue Rui, OU=RXUnit, O=RXOrg, L=Espoo, S=Uusimaa, C=FI" -noprompt
USERNAME=${1}
PASSWORD=${2}
echo "username is "${USERNAME}
mvn --file ../pom.xml clean verify -Dusername=${USERNAME} -Dpassword=${PASSWORD}
ps aux |grep spring-boot |awk '{print $2}' |xargs kill
mvn --file ../pom.xml spring-boot:run &
sleep 8
npm test -- --username=${USERNAME} --password=${PASSWORD}
ps aux |grep spring-boot |awk '{print $2}' |xargs kill
