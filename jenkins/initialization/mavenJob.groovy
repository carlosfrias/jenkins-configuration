/**
 * Created by carlosfrias on 6/14/15.
 */

import hudson.tasks.*
import hudson.maven.*

jenkins = Jenkins.instance

println "Job Names Listing"
jenkins.jobNames.each {
    println "\tJob Name: \n\t\t$it"
}

/*
println "All Jobs Listing"
jenkins.allJobs.each { 
  println "Job: $it"
}
*/

println "Jenkins Maven Modules"
jenkins.getItems(hudson.maven.MavenModuleSet).each {
    println "\tMaven Module: $it.name"
}

return ""