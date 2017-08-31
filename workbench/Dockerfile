FROM jboss/jbpm-workbench-showcase:6.5.0.Final


COPY standalone-full-jbpm.xml /opt/jboss/wildfly/standalone/configuration/standalone-full-jbpm.xml
COPY persistence.xml /opt/jboss/wildfly/standalone/deployments/jbpm-console.war/WEB-INF/classes/META-INF/persistence.xml

COPY mysql-connector-java-5.1.43-bin.jar /opt/jboss/wildfly/modules/com/mysql/main/
COPY module.xml /opt/jboss/wildfly/modules/com/mysql/main/

COPY jbpm-users.properties /opt/jboss/wildfly/standalone/configuration/jbpm-users.properties
COPY jbpm-roles.properties /opt/jboss/wildfly/standalone/configuration/jbpm-roles.properties