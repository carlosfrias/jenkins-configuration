FROM jenkinsconfiguration_jenkinscore

# Change back to the jenkins user
USER jenkins
COPY ssh/ /usr/share/jenkins/ref/.ssh

# Add initializtion configuration
COPY initialization/ /usr/share/jenkins/ref/initialization
COPY initialization/init.groovy /usr/share/jenkins/ref/init.groovy

# Add plugin installer as jenkins user
COPY initialization/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

CMD ["/usr/local/bin/jenkins.sh"]
WORKDIR $JENKINS_HOME
