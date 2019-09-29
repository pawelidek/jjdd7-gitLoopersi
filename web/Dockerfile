FROM jboss/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#79426 --silent

ADD target/vacation-calendar-engine.war /opt/jboss/wildfly/standalone/deployments/

ENV APP_DOMAIN="http://localhost:8080ocke/"

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]