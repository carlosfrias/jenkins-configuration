/**
 * Created by carlosfrias on 6/14/15.
 */
import jenkins.*
import jenkins.model.*
import hudson.*
import husdon.model.*
import hudson.tasks.*
import hudson.tasks.Maven.*

configPrefixMessage = "CUSTOM CONFIGURATION"
    println "$configPrefixMessage: Configuring Maven 3..."
m2Home = System.env.'M2_HOME'
assert m2Home, "Please set environment variable M2_HOME"
descriptor = Jenkins.instance.getDescriptorByType(Maven.DescriptorImpl.class)
if(!descriptor.installations.size()) {
println "$configPrefixMessage: Creating a Maven Configuration..."
    installation = new Maven.MavenInstallation("Maven 3.x", m2Home, null)
    descriptor.installations = [ installation ]
    descriptor.save()
    println "$configPrefixMessage: Saved Maven configuration."
}

