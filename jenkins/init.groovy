/**
 * Created by carlosfrias on 6/13/15.
 */

sonar = new File("${System.env.JENKINS_HOME}/plugin_config/sonar.groovy")
if (sonar.exists()) {
    println "INIT: Loading Sonar Configuration..."
    evaluate(sonar)
    println "INIT: Sonar Configuration Loaded."
}

gitScm = new File("${System.env.JENKINS_HOME}/plugin_config/gitScm.groovy")
if(gitScm.exists()) {
    println "INIT: Loading GitSCM Configuration..."
    evaluate(gitScm)
    println "INIT: GitSCM Configuration Loaded."
}

credentials = new File("${System.env.JENKINS_HOME}/plugin_config/credentials.groovy")
if(credentials.exists()) {
    println "INIT: Loading Credentials Configuration..."
    evaluate(credentials)
    println "INIT: Credentials Configuration Loaded."
}

maven = new File("${System.env.JENKINS_HOME}/plugin_config/maven.groovy")
if(maven.exists()) {
    println "INIT: Loading Maven Configuration..."
    evaluate(maven)
    println "INIT: Maven Configuration Loaded."
}