pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
			sh '(cd ./SMB/; mvn clean package)'
            }
        }
    }
}