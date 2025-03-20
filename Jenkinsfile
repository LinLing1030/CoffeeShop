pipeline {
    agent any  

    environment {
        MAVEN_HOME = "E:/apache-maven-3.9.9"  
        SONAR_HOST_URL = "http://localhost:9000"  
        SONAR_TOKEN = credentials('sonar_token') 
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs() 
            }
        }

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

        stage('Run Unit Tests & Coverage') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" verify'
            }
        }

        stage('Static Code Analysis') {
            steps {
                bat '"%MAVEN_HOME%/bin/mvn" sonar:sonar -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.login=%SONAR_TOKEN% -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
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
            }
        }
    }
}
