FROM jboss/wildfly
COPY target/workmanagement-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-Djboss.http.port=8080", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
