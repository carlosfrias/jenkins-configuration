/**
 * Created by carlosfrias on 6/13/15.
 */
sonar = new File("${System.env.JENKINS_HOME}/sonar.groovy")
if (sonar.exists())
    evaluate(sonar)

