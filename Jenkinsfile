pipeline {
    agent any

    stages {

        stage('Check Java') {
            steps {
                sh 'java -version'
                sh 'javac -version'
            }
        }

        stage('Compile') {
            steps {
                sh 'javac Hello.java'
            }
        }

        stage('Run') {
            steps {
                sh 'java Hello'
            }
        }
    }
}
