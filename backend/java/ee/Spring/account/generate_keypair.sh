if [ -z "$1" ]; then 
  KEY_STORE_PATH=src/main/resources/httpscert.p12
else  
  KEY_STORE_PATH=${1} 
fi
if [ -f ${KEY_STORE_PATH} ]; then rm ${KEY_STORE_PATH}; fi && keytool -genkeypair -alias httpscert -keyalg RSA -keysize 2048 -validity 1 -keystore ${KEY_STORE_PATH} -storetype PKCS12 -storepass testme -keypass testme -dname "CN=Xue Rui, OU=RXUnit, O=RXOrg, L=Espoo, S=Uusimaa, C=FI" -noprompt
