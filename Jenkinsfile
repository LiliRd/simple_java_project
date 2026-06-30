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
                sh """

                    CONTAINER_ID=\$(docker create maven:3.8.5-openjdk-17)

                    docker cp . \$CONTAINER_ID:/usr/src/app

                    docker exec -it \$CONTAINER_ID mvn clean package -DskipTests

                    docker cp \$CONTAINER_ID:/usr/src/app/target/. .

                    docker rm -f \$CONTAINER_ID
                """
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
