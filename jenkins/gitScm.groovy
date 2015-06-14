/**
 * Created by carlosfrias on 6/10/15.
 */
configPrefixMessage = "CUSTOM CONFIGURATION"

println "$configPrefixMessage: Configuring Git"
import hudson.plugins.git.*

descriptor = Jenkins.instance.getDescriptorByType(hudson.plugins.git.GitSCM.DescriptorImpl.class)
println "$configPrefixMessage: Configuring GitSCM..."

println "$configPrefixMessage: Setting global git properties..."
descriptor.globalConfigName = "Carlos Frias"
descriptor.globalConfigEmail = "carlos.frias.01@gmail.com"
descriptor.createAccountBasedOnEmail = false


println "$configPrefixMessage: Saving Git Configuration."
descriptor.save()

println "$configPrefixMessage: Git Configuration Complete."