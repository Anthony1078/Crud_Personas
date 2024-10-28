pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven'
        MAVEN_OPTS = '-Dmaven.multiModuleProjectDirectory=$WORKSPACE'
    }

    tools {
        maven 'Maven'
        jdk 'Java-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Testing') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('', 'DOCKERHUB_CREDENTIALS') {
                        def app = docker.build("wguizado/crud-personas:${env.BUILD_NUMBER}")
                        app.push()
                        app.push('latest')
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        success {
            echo 'Build was successful.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
