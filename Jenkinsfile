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

stage('Build') {
agent {
docker {
image 'maven:3.8.5-openjdk-17'
reuseNode true
}
}

steps {
sh '''
mvn clean package
'''
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
}

failure {
echo '❌ Pipeline failed.'
}

always {
echo '🧹 Pipeline finished.'
}
}
}