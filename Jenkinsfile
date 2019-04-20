pipeline {
    agent any
    stages {
        stage('Build') {
             agent {
              docker {
               image 'maven:3-alpine'
              }
            }
            steps {
			    sh '(mvn clean package)'
			    sh '(mvn sonar:sonar -Dsonar.projectKey=qdl-smb_SMB -Dsonar.organization=qdl -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=04f5e2082e159d655d42843f6d903606d34e37a3)'
                stash name: "app", includes: "**"
            }
        }
		stage('Katalon') {
		     agent {
              docker {
               image 'maven:3-alpine'
              }
            }
            steps {
			    sh '(java -jar target/SMF-0.0.1-SNAPSHOT.jar &)'
			    sh '(katalon -noSplash  -runMode=console -projectPath="SMBKatalon/SMBKatalon.prj" -retry=0 -testSuitePath="Test Suites/full_test" -executionProfile="default" -browserType="Web Service")'
            }
        }
    }
}