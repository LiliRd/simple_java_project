pipeline {
    agent any

    environment {
        IMAGE_NAME = "my-java-app"
        TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test (Maven)') {
            steps {
                echo 'Building the application with Maven...'
                // تغییر اصلی اینجا انجام شد: استفاده از 'mvn' به جای './mvnw'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker Image...'
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Verify Image') {
            steps {
                echo 'Verifying built image...'
                sh "docker images | grep ${IMAGE_NAME}"
            }
        }
    }

    post {
        success {
            echo "🎉 YES! Build Successful, Lili! Your Docker Image is ready!"
        }
        failure {
            echo "❌ Oh no! Something went wrong. Check the logs, Lili!"
        }
    }
}
