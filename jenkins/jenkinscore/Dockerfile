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

CMD ["/usr/local/bin/jenkins.sh"]