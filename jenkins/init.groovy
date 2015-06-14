/**
 * Created by carlosfrias on 6/13/15.
 */

sonar = new File("${System.env.JENKINS_HOME}/sonar.groovy")
if (sonar.exists()) {
    println "INIT: Loading Sonar Configuration..."
    evaluate(sonar)
    println "INIT: Sonar Configuration Loaded."
}

gitScm = new File("${System.env.JENKINS_HOME}/gitScm.groovy")
if(gitScm.exists()) {
    println "INIT: Loading GitSCM Configuration..."
    evaluate(gitScm)
    prinlnt "INIT: GitSCM Configuration Loaded."
}


