pipeline {
	agent any

	options {
        timestamps()
        // ansiColor('xterm')
    }

	environment {
		IMAGE_NAME = "lirahmani/my-portfolio"
		TAG = "${BUILD_NUMBER}"
	}

	stages {
			stage('Checkout') {
			steps {
			checkout scm
			}
		}

		
		stage('Build') {
			steps {
			sh 'mvn clean compile'
			}
		}

		stage('Run Unit Tests') {
			steps {
			sh 'mvn test'
			}
		}

		stage('Package') {
			steps {
			sh 'mvn package -DskipTests'
			}
		}


		stage('Build Docker Image') {
			steps {
			echo '🐳 Building Docker image...'

			sh """
			docker build \
			-t ${IMAGE_NAME}:${TAG} \
			-t ${IMAGE_NAME}:latest \
			.
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
			

		stage('Push Docker Image') {
			steps {
			echo '📤 Pushing Docker image to Docker Hub...'

			withCredentials([
			usernamePassword(
			credentialsId: 'dockerhub',
			usernameVariable: 'DOCKER_USER',
			passwordVariable: 'DOCKER_PASS'
			)
			]) {
			sh """
			echo "\$DOCKER_PASS" | docker login \
			-u "\$DOCKER_USER" \
			--password-stdin

			docker push ${IMAGE_NAME}:${TAG}

			docker push ${IMAGE_NAME}:latest

			docker logout
			"""
			}
			}
		}			
	}

	post {
		success {
		echo '🎉 Pipeline completed successfully!'

		archiveArtifacts artifacts: 'target/*.jar', fingerprint:true
		}

		failure {
		echo '❌ Pipeline failed.'
		}

		always {
		sh """
		docker image rm ${IMAGE_NAME}:${TAG} || true
		docker image rm ${IMAGE_NAME}:latest || true
		"""
		echo '🧹 Pipeline finished.'
		}
	}
}