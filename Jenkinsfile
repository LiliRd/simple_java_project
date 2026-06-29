pipeline {
    agent any

    environment {
        // تعریف نام ایمیج برای اینکه در مراحل بعدی راحت استفاده کنیم
        IMAGE_NAME = "my-java-app"
        TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                // دریافت آخرین کدها از گیت‌هاب
                checkout scm
            }
        }

        stage('Build & Test (Maven)') {
            steps {
                echo 'Building the application with Maven...'
                // اجرای دستور Maven برای ساخت فایل JAR
                // ما از sh استفاده می‌کنیم چون در محیط لینوکسی/داکر هستیم
                sh './mvnw clean package -DskipTests'
                // نکته: اگر فایل mvnw نداری، از 'mvn clean package -DskipTests' استفاده کن
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker Image...'
                // این همان جادویی است که با Dockerfile کار می‌کند!
                // . یعنی Dockerfile در همین پوشه است
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Verify Image') {
            steps {
                echo 'Verifying built image...'
                // چک می‌کنیم که آیا ایمیج واقعاً ساخته شده یا نه
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
