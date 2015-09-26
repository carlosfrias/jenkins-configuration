/**
 * Created by carlosfrias on 6/13/15.
 */

def load = { script ->
    scriptFile = new File("${System.env.JENKINS_HOME}/initialization/${script}.groovy")
    if (scriptFile.exists()) {
        println "INIT: Loading ${script} Configuration..."
        evaluate(scriptFile)
        println "INIT: ${script} Configuration Loaded."
    } else {
        println "INIT: Unable to find ${script} to load."
    }
}
scripts = [
        'configureGitScm' //,
        //'configureBasicSshUserPrivateKeyCredentials',
        //'configureJava',
        //'configureMaven',
//        'sonar'
]
scripts.each { load(it) }
