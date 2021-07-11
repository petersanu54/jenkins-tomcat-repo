job('sun-Jenkins-tomcat-package-job-createdby-DSL'){
    logRotator(5,5)
    scm{
        git('https://github.com/petersanu54/jenkins-tomcat-repo.git','master')
    }
    triggers{
        scm('* * * * *')
    }
    steps{
        mvn('clean package', 'java-tomcat-sample/pom.xml')
    }
    publishers{
        archiveArtifacts('**/*.war')
    }
}