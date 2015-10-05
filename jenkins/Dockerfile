# Instructions: https://hub.docker.com/_/jenkins/
FROM jenkins:1.609.3

# Install additional tools
USER root
RUN apt-get update
RUN apt-get install maven curl apt-utils -y
#RUN curl -sSL https://get.docker.com/gpg | apt-key add -
#RUN curl -sSL https://get.docker.com | sh
#RUN usermod -aG docker jenkins
#RUN systemctl enable docker
#RUN service docker start
#RUN curl -L https://github.com/docker/machine/releases/download/v0.4.0/docker-machine_linux-amd64 > /usr/local/bin/docker-machine
#RUN chmod +x /usr/local/bin/docker-machine

ENV M2_HOME /usr/share/maven
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Change back to the jenkins user
USER jenkins
COPY ssh/ /usr/share/jenkins/ref/.ssh

# Add initialization configuration
COPY initialization/ /usr/share/jenkins/ref/initialization
COPY initialization/init.groovy /usr/share/jenkins/ref/init.groovy

# Add private libraries
COPY m2/ /usr/share/jenkins/ref/.m2

# Add plugin installer as jenkins user
COPY initialization/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

CMD ["/usr/local/bin/jenkins.sh"]

WORKDIR $JENKINS_HOME
