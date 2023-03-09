pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    docker.image('maven:3.9-amazoncorretto-19').inside {
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    docker.image('maven:3.9-amazoncorretto-19').inside {
                        sh 'mvn test'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}