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
                echo 'Building the application using a Maven Docker Container...'
                // تغییر اصلی: استفاده از ${WORKSPACE} به جای $(pwd)
                // همچنین از یک روش مستقیم‌تر برای آدرس‌دهی استفاده می‌کنیم
                sh "docker run --rm -v ${WORKSPACE}:/usr/src/app -w /usr/src/app maven:3.8.5-openjdk-17 mvn clean package -DskipTests"
            }
        }


        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker Image...'
                // حالا که فایل JAR در پوشه target ساخته شده، ایمیج اصلی را می‌سازیم
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
            echo "🎉 BOOM! You did it, Lili! The Containerized Build worked!"
        }
        failure {
            echo "❌ Still stuck? Don't worry, we'll debug this together!"
        }
    }
}
