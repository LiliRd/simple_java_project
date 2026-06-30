pipeline {
agent any


environment {
IMAGE_NAME = "lilird/my-java-app"
TAG = "${BUILD_NUMBER}"
}

stages {

stage('Checkout') {
steps {
checkout scm
}
}



stage('Build & Test') {
agent {
docker {
image 'maven:3.8.5-openjdk-17'
reuseNode true
}
}

steps {
sh 'mvn clean verify'
}
}

stage('Build Docker Image') {
steps {
sh """
docker build \
-t ${IMAGE_NAME}:${TAG} .
"""
}
}

stage('Verify Image') {
steps {
sh """
docker image inspect ${IMAGE_NAME}:${TAG}
"""
}
}
}

post {
success {
echo '🎉 Pipeline completed successfully!'

archiveArtifacts artifacts: 'my-app/target/*.jar'
}

failure {
echo '❌ Pipeline failed.'
}

always {
echo '🧹 Pipeline finished.'
}
}
}