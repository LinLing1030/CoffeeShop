pipeline {
    agent any  

    environment {
        MAVEN_HOME = "E:/apache-maven-3.9.9"  // Maven 
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
                sh 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Static Code Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL'
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
                // 假设是简单的输出，你可以修改为真实部署
            }
        }
    }
}
