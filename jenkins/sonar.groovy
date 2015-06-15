/**
 * Created by carlosfrias on 6/10/15.
 */

configPrefixMessage = "CUSTOM CONFIGURATION"

println "$configPrefixMessage: Configuring Sonar"
import jenkins.model.Jenkins
import hudson.plugins.sonar.*
import hudson.plugins.sonar.model.TriggersConfig;
import hudson.tools.*

println "$configPrefixMessage: Loading Sonar properties..."
prop = [:]
prop.name = System.env.SONARQUBE_NAME
prop.disabled = false
prop.serverUrl = "http://${System.env.SONARQUBE_PORT_9000_TCP_ADDR}:${System.env.SONARQUBE_PORT_9000_TCP_PORT}"
prop.databaseUrl = System.env.SONARQUBE_ENV_JDBC_URL
prop.databaseLogin = System.env.SONARQUBE_ENV_JDBC_USERNAME
prop.databasePassword = System.env.SONARQUBE_ENV_JDBC_PASSWORD
prop.sonarRunnerVersion = System.env.SONAR_RUNNER_VERSION
//prop.mojoVersion = null
//prop.additionalProperties = null
//prop.triggers = null
//prop.sonarLogin = null
//prop.sonarPassword = null

println "$configPrefixMessage: Creating SonarInstallation instance..."
updatedSonarInstall = new SonarInstallation(prop?.name,
        prop?.disabled,
        prop?.serverUrl,
        prop?.databaseUrl,
        prop?.databaseLogin,
        prop?.databasePassword,
        prop?.mojoVersion,
        prop?.additionalProperties,
        prop?.triggers as TriggersConfig,
        prop?.sonarLogin,
        prop?.sonarPassword)

println "$configPrefixMessage: Replacing SonarInstallation with the updated instance..."
descriptor = Jenkins.instance.getDescriptorByType(SonarPublisher.DescriptorImpl.class)
descriptor.installations = updatedSonarInstall

println "$configPrefixMessage: Saving updated SonarInstallation instance... "
descriptor.save()

println "$configPrefixMessage: Creating SonarRunerInstaller instance..."
installer = new SonarRunnerInstaller(prop?.sonarRunnerVersion)

println "$configPrefixMessage: Creating InstallSourceProperty instance..."
property = new InstallSourceProperty([installer])

println "$configPrefixMessage: Creating SonarRunnerInstallation instance..."
runner = new SonarRunnerInstallation(prop?.name, null, [property])

println "$configPrefixMessage: Obtaining the SonarRunnerInstallation Descriptor instance..."
descriptor = Jenkins.instance.getDescriptorByType(SonarRunnerInstallation.DescriptorImpl.class)

println "$configPrefixMessage: Saving SonarRnnerInstallation..."
descriptor.installations = runner
descriptor.save()

println "$configPrefixMessage: Sonar configuration complete."
