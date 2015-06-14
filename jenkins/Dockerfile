FROM jenkinsconfiguration_jenkinscore

#################################################
# Moved into jenkinscore to speed up builds
# Install additional tools
#USER root
#RUN apt-get update && apt-get install -y maven
# Moved into jenkinscore to speed up builds
#################################################

# Change back to the jenkins user
USER jenkins

# Add plugin installer as jenkins user
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Add sonar configuration
COPY init.groovy /usr/share/jenkins/ref/init.groovy
COPY sonar.groovy /usr/share/jenkins/ref/sonar.groovy

