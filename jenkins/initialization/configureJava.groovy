import hudson.model.*
import jenkins.model.*

configPrefixMessage = "CUSTOM CONFIGURATION"
jenkins = Jenkins.instance
jdks = jenkins.getDescriptorByType(hudson.model.JDK.DescriptorImpl.class)
println "$configPrefixMessage: Configuring Java (${System.env.JAVA_HOME})"
jdks.setInstallations new JDK('initialJava', System.env.'JAVA_HOME')
println "$configPrefixMessage: Configured Java (${System.env.JAVA_HOME})"
jenkins.save()
println "$configPrefixMessage: Saved Java Configurartion"
