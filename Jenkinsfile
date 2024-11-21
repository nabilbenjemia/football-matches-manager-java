pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Pull the code from GitHub
                git 'https://github.com/nabilbenjemia/task-tracker-java.git'
            }
        }
        stage('Build') {
            steps {
                // Run Gradle build
                echo 'Running Gradle build...'
                sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                // Run Gradle tests
                echo 'Running Gradle tests...'
                sh './gradlew test'
            }
        }
    }
    post {
        always {
            // Archive test results
            echo 'Archiving test results...'
            junit '**/build/test-results/test/*.xml' // Adjust this path if necessary
        }
        success {
            echo 'Build and tests passed!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
