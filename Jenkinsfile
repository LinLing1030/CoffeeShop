pipeline {
    agent any  

    environment {
        MAVEN_HOME = "E:/apache-maven-3.9.9"  // Windows 需要用 / 或者 \\
        SONAR_HOST_URL = "http://localhost:9000"  
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'maven', url: 'https://github.com/LinLing1030/CoffeeShop.git'
            }
        }

        stage('Build with Maven') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" test'
            }
        }

        stage('Static Code Analysis') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" sonar:sonar -Dsonar.host.url=%SONAR_HOST_URL%'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy Application') {
            steps {
                echo 'Deploying application...'
                // 
            }
        }
    }
}
