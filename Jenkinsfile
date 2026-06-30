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

stage('Build with Maven Container') {
steps {
echo 'Building application inside a temporary Maven container...'

sh '''
docker run --rm \
-v "$WORKSPACE":/usr/src/app \
-w /usr/src/app \
maven:3.8.5-openjdk-17 \
mvn clean package
'''
}
}

stage('Build Docker Image') {
steps {
echo 'Building Docker image...'

sh """
docker build \
-t ${IMAGE_NAME}:${TAG} \
.
"""
}
}

stage('Verify Docker Image') {
steps {
echo 'Verifying Docker image...'

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

