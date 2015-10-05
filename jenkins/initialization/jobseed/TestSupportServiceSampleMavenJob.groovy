package jobseed

mavenJob("TestSupportServiceSample") {
    scm {
        git('git@github.com:carlosfrias/jenkins-configuration.git', '*/development') {
            remote {
                credentials('LocalUser')
            }
            clean()
        }
    }
    rootPOM("test-support/pom.xml")
    goals("clean install -N -e")
    triggers {
        scm('H/5 * * * *')
    }
    publishers {
        sonar {
            branch('development')
        }
    }
}