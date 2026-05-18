pipeline {
    agent any

    tools {
        jdk 'JDK21'
    }

    stages {

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
