pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                bat 'if not exist deploy mkdir deploy'
                bat 'copy /Y target\\*.jar deploy\\currency-converter.jar'
                bat 'echo Deployment package created in deploy folder'
            }
        }
    }

    post {
        success {
            echo 'Java currency converter build and deployment package created successfully.'
        }
        failure {
            echo 'Build failed. Please check the Jenkins console output.'
        }
    }
}
