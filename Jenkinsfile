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
                sh './mvnw clean package -DskipTests'

            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker Image...'
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Verify
