/**
 * Created by carlosfrias on 6/14/15.
 */

import jenkins.model.*
import husdon.model.Cause.*

def configPrefixMessage = "CUSTOM CONFIGURATION"
def projectName = 'JobSeederProject'
println "$configPrefixMessage: Configuring the $projectName"

def jenkinsHome = System.env.'JENKINS_HOME'
def initFolder = "$jenkinsHome/initialization/jobseed"
def workspaceJobFolder = "$jenkinsHome/workspace/$projectName"
def ant = new AntBuilder()

def configXml = new File("$initFolder/JobSeeder-config.xml")
if (configXml.exists()) {
    println "$configPrefixMessage: $projectName found"
    def job = null
    println "$configPrefixMessage: Loading $projectName..."
    configXml.withInputStream {
        job = Jenkins.instance.createProjectFromXML(projectName, it)
    }
    println "$configPrefixMessage: Saving $projectName"
    job.save()

    job.scheduleBuild(new Cause.UserIdCause())

    println "$configPrefixMessage: Creating $projectName workspace folder"
    ant.with {
        mkdir dir: new File(workspaceJobFolder)
        copy(todir: new File(workspaceJobFolder)) {
            fileset dir: new File(initFolder), includes: "**/*Job.groovy"
        }
        println "$configPrefixMessage: $projectName Job Folder Configuration complete."
    }

    println "$configPrefixMessage: $projectName configuration complete."
} else {
    println "$configPrefixMessage: No $projectName found."
}

