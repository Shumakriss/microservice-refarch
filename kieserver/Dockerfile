FROM jboss/kie-server-showcase:6.5.0.Final

COPY standalone-full-kie-server.xml /opt/jboss/wildfly/standalone/configuration/standalone-full-kie-server.xml

COPY mysql-connector-java-5.1.43-bin.jar /opt/jboss/wildfly/modules/com/mysql/main/
COPY module.xml /opt/jboss/wildfly/modules/com/mysql/main/