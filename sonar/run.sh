#!/bin/bash
set -e

if [ "${1:0:1}" != '-' ]; then
	exec "$@"
fi

 exec java -cp lib/jdbc/postgresql/postgresql-9.3-1102-jdbc41.jar -jar lib/sonar-application-$SONAR_VERSION.jar \
   -Dsonar.log.console=true \
   -Dsonar.jdbc.username="$SONARQUBE_JDBC_USERNAME" \
   -Dsonar.jdbc.password="$SONARQUBE_JDBC_PASSWORD" \
   -Dsonar.jdbc.url="jdbc:postgresql://$DB_PORT_5432_TCP_ADDR:$DB_PORT_5432_TCP_PORT/sonar" \
   -Dsonar.web.javaAdditionalOpts="-Djava.security.egd=file:/dev/./urandom" \
 	"$@"