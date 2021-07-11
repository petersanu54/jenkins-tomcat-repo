job('sun-Jenkins-tomcat-package-job-createdby-DSL'){
    description('This job will generate a war file.')
    logRotator(5,5)
    wrappers {
        timestamps()
    }
    scm{
        git('https://github.com/petersanu54/jenkins-tomcat-repo.git','main')
    }
    triggers{
        scm('* * * * *')
    }
    steps{
        maven('clean package', 'java-tomcat-sample/pom.xml')
    }
    publishers{
        archiveArtifacts('**/*.war')
        downstream('sun-Jenkins-tomcat-deploy-to-stage')
    }
}

job('sun-Jenkins-tomcat-deploy-to-stage') {
    description('This job will copy the artificats from upstream and deploy in staging environment.')
    logRotator(5,5)
    wrappers {
        timestamps()
    }
    steps{
        copyArtifcats('upstream'){
            includePattern('**/*.war')
            buildSelector{
                latestSuccessful(true)
            }
        }
    }
}