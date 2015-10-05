import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*

configPrefixMessage = "CUSTOM CONFIGURATION"
println "$configPrefixMessage: Configuring BasicSshPrivateKeyCredentials..."

println "$configPrefixMessage: Creating BasicSshPrivateKeyCredentials..."

sshPem = "${System.env.'JENKINS_HOME'}/.ssh/id_rsa"
if (new File(sshPem).exists()) {
    new AntBuilder().chmod(perm: 400, file: sshPem)
    privateKeySource = new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource(sshPem);

    systemCredProvider = SystemCredentialsProvider.instance
    sshCredentials = new BasicSSHUserPrivateKey(
            CredentialsScope.GLOBAL,
            "LocalUser",
            'git',
            privateKeySource,
            null,
            "SSH Keys Configured with Git"
    )
    assert sshCredentials, "SSH Credentials not available"

    domain = Domain.global()

    println "$configPrefixMessage: Searching for existing usernamePasswordCredentials..."
    existingCredentials = systemCredProvider.credentials

    println "$configPrefixMessage: DEBUGGING: ${existingCredentials.dump()}"
    credential = existingCredentials.find { it.username == 'git' }
    if (credential) {
        println "$configPrefixMessage: Updating SSH private key credentials"
        systemCredProvider.updateCredentials domain, credential, sshCredentials
    } else {
        println "$configPrefixMessage: Adding SSH private key credentials"
        systemCredProvider.addCredentials domain, sshCredentials
    }
    println "$configPrefixMessage: BasicSshPrivateKeyCredentials created."
} else {
    println "$configPrefixMessage: SSH private key file not available"
}

