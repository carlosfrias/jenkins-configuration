FROM java:openjdk-8u45-jdk

MAINTAINER David Gageot <david.gageot@sonarsource.com>

ENV SONARQUBE_HOME /opt/sonarqube

# Http port
EXPOSE 9000

# H2 Database port
EXPOSE 9092

# Database configuration
# Defaults to using H2
ENV SONARQUBE_JDBC_USERNAME sonar
ENV SONARQUBE_JDBC_PASSWORD sonar
ENV SONARQUBE_JDBC_URL jdbc:h2:tcp://localhost:9092/sonar

ENV SONAR_VERSION 5.1.2

# pub   2048R/D26468DE 2015-05-25
#      Key fingerprint = F118 2E81 C792 9289 21DB  CAB4 CFCA 4A29 D264 68DE
# uid       [ unknown] sonarsource_deployer (Sonarsource Deployer) <infra@sonarsource.com>
# sub   2048R/06855C1D 2015-05-25
RUN gpg --keyserver ha.pool.sks-keyservers.net --recv-keys F1182E81C792928921DBCAB4CFCA4A29D26468DE

RUN set -x \
	&& cd /opt \
	&& curl -o sonarqube.zip -fSL http://downloads.sonarsource.com/sonarqube/sonarqube-$SONAR_VERSION.zip \
	&& curl -o sonarqube.zip.asc -fSL http://downloads.sonarsource.com/sonarqube/sonarqube-$SONAR_VERSION.zip.asc \
	&& gpg --verify sonarqube.zip.asc \
	&& unzip sonarqube.zip \
	&& mv sonarqube-$SONAR_VERSION sonarqube \
	&& rm sonarqube.zip* \
	&& rm -rf $SONARQUBE_HOME/bin/*

VOLUME ["$SONARQUBE_HOME/data", "$SONARQUBE_HOME/extensions"]

WORKDIR $SONARQUBE_HOME

ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/sonar-cobertura-plugin/1.6.2/sonar-cobertura-plugin-1.6.2.jar /opt/sonarqube/extensions/plugins/
ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/sonar-groovy-plugin/1.1/sonar-groovy-plugin-1.1.jar /opt/sonarqube/extensions/plugins/
ADD https://github.com/racodond/sonar-jproperties-plugin/releases/download/1.3/sonar-jproperties-plugin-1.3.jar /opt/sonarqube/extensions/plugins/
ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/java/sonar-checkstyle-plugin/2.1.1/sonar-checkstyle-plugin-2.1.1.jar /opt/sonarqube/extensions/plugins/
ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/java/sonar-findbugs-plugin/2.3/sonar-findbugs-plugin-2.3.jar /opt/sonarqube/extensions/plugins/
ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/java/sonar-pmd-plugin/2.3/sonar-pmd-plugin-2.3.jar /opt/sonarqube/extensions/plugins/
ADD http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/sonar-scm-stats-plugin/0.3.1/sonar-scm-stats-plugin-0.3.1.jar /opt/sonarqube/extensions/plugins/

COPY run.sh $SONARQUBE_HOME/bin/
ENTRYPOINT ["./bin/run.sh"]