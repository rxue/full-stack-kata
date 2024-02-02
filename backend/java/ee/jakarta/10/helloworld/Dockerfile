FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17
COPY target/helloworld.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8080", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]