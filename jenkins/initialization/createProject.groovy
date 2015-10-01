/**
 * Created by carlosfrias on 6/21/15.
 */

import javaposse.jobdsl.plugin.*

scriptText = '''job('DSL-Tutorial-1-Script-Console-Test') {
    scm {
        git('git://github.com/jgritman/aws-sdk-test.git')
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        configureMaven('-e clean test')
    }
}'''

jenkins = Jenkins.instance
executeDsl = new ExecuteDslScripts(scriptText)
project = new FreeStyleProject(jenkins, "JobDslProjectFromScriptConsole")
project.buildersList.add executeDsl
project.save()
jenkins.doReload()
