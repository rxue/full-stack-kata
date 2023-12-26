FROM jboss/wildfly
COPY target/report-generator-1.0-SNAPSHOT.jar /opt/jboss/wildfly/standalone/deployments/
COPY docker-config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/
RUN /opt/jboss/wildfly/bin/add-user.sh -a -u test -p test --group guest--silent
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-Djboss.http.port=8081", "-b", "172.20.0.2", "-bmanagement", "0.0.0.0"]
