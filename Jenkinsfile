pipeline{
    agent any
    stages{
        stage('Build Application'){
            steps{
                bat 'mvn -f java-tomcat-sample/pom.xml clean package'
            }
            post{
                success{
                    echo "Now archiving the artifacts..."
                    archiveArtifacts artifacts: '**/*.war'
                }
            }
        }
        stage('Deploy to Staging env'){
            steps{
                build job: 'mon-Jenkins-tomcat-deploy-to-stage'
            }
        }
    }
}