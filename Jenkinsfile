pipeline {
    agent any
    stages {
        stage('Checkout') { steps { checkout scm } }
        stage('Install Dependencies') {
            steps {
                bat 'python -m pip install --upgrade pip'
                bat 'python -m pip install -r requirements.txt'
            }
        }
        stage('Build / Verify') {
            steps { bat 'python -m py_compile app.py' }
        }
        stage('Deploy') {
            steps {
                bat 'if not exist deploy mkdir deploy'
                bat 'xcopy /E /I /Y templates deploy\\templates'
                bat 'xcopy /E /I /Y static deploy\\static'
                bat 'copy /Y app.py deploy\\app.py'
                bat 'copy /Y requirements.txt deploy\\requirements.txt'
            }
        }
    }
    post {
        success { echo 'Build and deployment completed successfully.' }
        failure { echo 'Build or deployment failed.' }
    }
}
