package jobseed

mavenJob("TestSupportServiceSampleCodeMetrics") {
    scm {
        git('git@gitub.com:carlosfrias/jenkins-configuration.git', '*/development') {
            remote {
                credentials('LocalUser')
            }
            clean()
        }
    }
    rootPOM("test-support/pom.xml")
    goals("clean verify cobertura:cobertura pmd:pmd findbugs:findbugs checkstyle:checkstyle -N -e")
    triggers {
        scm('H/5 * * * *')
    }
    publishers {
        cobertura('**/target/cobertura/coverage.xml') {
            failNoReports(false)
            sourceEncoding('ASCII')
        }
        pmd("**/*.pmd")
        findbugs("**/findbugsXml.xml", false)
        checkstyle('**/checkstyle-result.xml')
        sonar {
            branch('development')
        }
    }
}